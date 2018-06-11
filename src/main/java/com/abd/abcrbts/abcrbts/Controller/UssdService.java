package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Agents;
import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.Model.Tickets;
import com.abd.abcrbts.abcrbts.MyOzSmsClient;
import com.abd.abcrbts.abcrbts.PropertyReader;
import com.abd.abcrbts.abcrbts.Service.AgentService;
import com.abd.abcrbts.abcrbts.Service.ReservationService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import com.abd.abcrbts.abcrbts.Service.TicketService;
import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.messages.MoSmsResp;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Abdurahman on 3/9/17.
 */
@Controller
public class UssdService {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AgentService agentService;

    // Service messages
    private static final String REQUEST_SEND_URL = "http://localhost:7000/ussd/send";
    private static final String OPERATION_MT_CONT = "mt-cont";
    private static final String OPERATION_MT_FIN = "mt-fin";


    Agents agents;
    java.sql.Date date1;



    // List to store the states of the menus
    private ArrayList<String> menuStates = new ArrayList<>();
    private ArrayList<String> responses=new ArrayList<>();
    private String sessions="";
    private List<Route> departure ;
    private List<Route> destination;
    @RequestMapping("/springussd")
    @ResponseBody
    public void onReceivedUssd(@RequestBody MoUssdReq moUssdReq) throws IOException {
        try {
            processRequest(moUssdReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Process all kinds of requests to customer
    private void processRequest(MoUssdReq moUssdReq) throws IOException, InterruptedException {

        MtUssdReq mtUssdReq;
        String destinationAddress = moUssdReq.getSourceAddress();
        if("mo-init".equals(moUssdReq.getUssdOperation()) && "*724#".equals(moUssdReq.getMessage()))
        {

            mtUssdReq = generateMTRequest("This is Habesha Bus Ticketing System\n1. Reserve a seat\n2. Confirm Reservations", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
            sessions="welcome";
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation())&& "1".equals(moUssdReq.getMessage())&& "welcome".equals(sessions)){
            departure=routeService.findDistinct();
            String msg="Select a Departure City\n";
            for(int i=0;i<departure.size();i++)
            {
                msg=msg+departure.get(i).getId()+":"+departure.get(i).getDeparture()+"\n";
            }
            mtUssdReq = generateMTRequest(msg, moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);

            sessions="departure";
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation()) && "departure".equals(sessions))
        {
            String msg="";
            destination = routeService.findByDeparture(routeService.findById(Integer.parseInt(moUssdReq.getMessage())).getDeparture());
            msg = "Select a Destination City\n";
            for (int i = 0; i < destination.size(); i++) {
                msg = msg + destination.get(i).getId() + ":" + destination.get(i).getDestination()+"\n";
            }
            mtUssdReq = generateMTRequest(msg, moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
            responses.add(moUssdReq.getMessage());
            sessions="destination";
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation()) && "destination".equals(sessions))
        {   responses.add(moUssdReq.getMessage());
            String msg="";
            DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal=Calendar.getInstance();
            cal.setTime(new Date());

            for(int i=1;i<=5;i++)
            {
                cal.add(Calendar.DATE,1);
                msg=msg+i+": "+dateFormat.format(cal.getTime())+"\n";


            }
            System.out.println(msg);

            mtUssdReq = generateMTRequest("Please choose a travel date\n"+msg, moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
            sessions="date";
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation()) && "date".equals(sessions) && Integer.parseInt(moUssdReq.getMessage())<=5)
        {


            DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal=Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE,Integer.parseInt(moUssdReq.getMessage()));
            responses.add(dateFormat.format(cal.getTime()));

            Route route1=routeService.findById(Integer.parseInt(responses.get(1)));


            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,00);
            cal.set(Calendar.SECOND,00);
            cal.set(Calendar.MILLISECOND,0);

            date1 = new java.sql.Date(cal.getTimeInMillis());
            System.out.println("Selected Time = [" + date1.toString() + "]");
            if(ticketService.countByRouteAndDate(route1,date1)<route1.getBus().getSeats()) {
                Reservation reservation=new Reservation();

                reservation.setRoute(route1);
                reservation.setRefNumber(UUID.randomUUID().toString().substring(0,6));
                reservation.setPassengerphone(destinationAddress.substring(4));
                reservation.setPaid(Boolean.FALSE);
                reservation.setDepartureDate(date1);

                reservationService.save(reservation);

                mtUssdReq = generateMTRequest("Reservation has been made.\nYour code is "+reservation.getRefNumber(), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
             MyOzSmsClient myOzSmsClient=new MyOzSmsClient("localhost",9500);
                myOzSmsClient.login("admin","abc123");
                if(myOzSmsClient.isLoggedIn())
                {
                    myOzSmsClient.sendMessage("+251"+reservation.getPassengerphone().substring(2),"Reference Number is "+reservation.getRefNumber());
                }
                sessions="date";
            }
            else {
                mtUssdReq = generateMTRequest("Ohh Sorry, There are no seats for selected date", moUssdReq.getSessionId(), OPERATION_MT_FIN, destinationAddress);
            }

        }

        else if("mo-cont".equals(moUssdReq.getUssdOperation())&& "2".equals(moUssdReq.getMessage())&& "welcome".equals(sessions)){
            mtUssdReq = generateMTRequest("Please Provide your PIN Number", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
            sessions="Confirm";
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation())&& "Confirm".equals(sessions)){

            System.out.println(destinationAddress.substring(4));
            agents = agentService.findByPhoneAndPin(destinationAddress.substring(4),moUssdReq.getMessage());
            if(agents==null)
            {
                mtUssdReq = generateMTRequest("YOU ARE NOT A REGISTERED AGENT", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                sessions="WrongPIN";
            }
            else
            {
                mtUssdReq = generateMTRequest("Enter Reference Number", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                sessions="CorrectPIN";
            }
        }
        else if("mo-cont".equals(moUssdReq.getUssdOperation())&& "CorrectPIN".equals(sessions)) {
            Reservation reservation=reservationService.findByRefNum(moUssdReq.getMessage());
             if(reservation!=null)
            {
                reservationService.updatePaid(reservation.getRefNumber());
                Tickets tickets=new Tickets();
                tickets.setAgents(agents);
                tickets.setPassengerPhone(reservation.getPassengerphone());
                tickets.setRoute(reservation.getRoute());
                tickets.setDepartureDate(reservation.getDepartureDate());
                ticketService.save(tickets);
                mtUssdReq = generateMTRequest("Reservation Has Been Confirmed", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                MyOzSmsClient myOzSmsClient=new MyOzSmsClient("localhost",9500);
                myOzSmsClient.login("admin","abc123");
                if(myOzSmsClient.isLoggedIn())
                {
                    System.out.println("+251"+reservation.getPassengerphone().substring(4));
                    myOzSmsClient.sendMessage("+251"+reservation.getPassengerphone().substring(2),reservation.getRoute().getDeparture()+" - "+reservation.getRoute().getDestination()+"\nTravel date:"+reservation.getDepartureDate()+"\nSeat Number: "+ticketService.countByRouteAndDate(reservation.getRoute(),reservation.getDepartureDate())+1);
                }
            }
            else
            {
                mtUssdReq = generateMTRequest("Oops, An incorrect Reservation Number", moUssdReq.getSessionId(), OPERATION_MT_FIN, destinationAddress);
            }
        }
        else
        {
            mtUssdReq = generateMTRequest("Sorry! Invalid Input", moUssdReq.getSessionId(), OPERATION_MT_FIN, destinationAddress);
        }
        UssdRequestSender ussdRequestSender = new UssdRequestSender(new URL(REQUEST_SEND_URL));
        try {
            ussdRequestSender.sendUssdRequest(mtUssdReq);
        } catch (SdpException e) {
            e.printStackTrace();
            mtUssdReq=generateMTRequest("Oops... You've done something wrong", moUssdReq.getSessionId(), OPERATION_MT_FIN, destinationAddress);
        }
        System.out.println(menuStates);
    }
    // Generate request to the customer
    private MtUssdReq generateMTRequest(String message, String sessionId, String operation, String destinationAddress) {
        MtUssdReq mtUssdReq = new MtUssdReq();
        mtUssdReq.setApplicationId("APP_000001");
        mtUssdReq.setPassword("dfc0333b82a8e01f500e7e37188f97eo");
        mtUssdReq.setMessage(message);
        mtUssdReq.setSessionId(sessionId);
        mtUssdReq.setUssdOperation(operation);
        mtUssdReq.setDestinationAddress(destinationAddress);
        return mtUssdReq;
    }
    // Functionality of the back command
}
