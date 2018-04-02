package com.abd.abcrbts.abcrbts.Controller;

import com.abd.abcrbts.abcrbts.Model.Reservation;
import com.abd.abcrbts.abcrbts.Model.Route;
import com.abd.abcrbts.abcrbts.PropertyReader;
import com.abd.abcrbts.abcrbts.Service.ReservationService;
import com.abd.abcrbts.abcrbts.Service.RouteService;
import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 3/9/17.
 */
@Controller
public class UssdService {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RouteService routeService;
    // Service messages
    private static final String SERVICE_EXIT_CODE = "000";
    private static final String SERVICE_PREV_CODE = "999";
    private static final String SERVICE_INIT_CODE = "*724#";
    private static final String SERVICE_ELECTRONICS_CODE = "1";
    private static final String SERVICE_COSMETICS_CODE = "2";
    private static final String SERVICE_HOUSEHOLDS_CODE = "3";
    private static final String REQUEST_SEND_URL = "http://localhost:7000/ussd/send";
    private static final String OPERATION_MT_CONT = "mt-cont";
    private static final String OPERATION_MT_FIN = "mt-fin";

    PropertyReader propertyReader = new PropertyReader("messages.properties");

    // List to store the states of the menus
    private ArrayList<String> menuStates = new ArrayList<>();
    private List<Route> departure ;
    private List<Route> destination;
    @RequestMapping("/springussd")
    @ResponseBody
    public void onReceivedUssd(@RequestBody MoUssdReq moUssdReq) throws IOException {
        try {
            processRequest(moUssdReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SdpException e) {
            e.printStackTrace();
        }
    }

    // Process all kinds of requests to customer
    private void processRequest(MoUssdReq moUssdReq) throws IOException, SdpException {
        MtUssdReq mtUssdReq;
        String destinationAddress = moUssdReq.getSourceAddress();
        if (menuStates.size() > 0) {
            switch (moUssdReq.getMessage()) {
                case SERVICE_INIT_CODE:
                    mtUssdReq = generateMTRequest("This is Habesha Bus Ticketing System\n1. Reserve a seat\n2. Confirm Reservations", moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);

                    menuStates.add("welcome.page");

                    break;
                case SERVICE_EXIT_CODE:
                    mtUssdReq = generateMTRequest(propertyReader.readProperty("exit.page"), moUssdReq.getSessionId(), OPERATION_MT_FIN, destinationAddress);
                    menuStates.clear();
                    break;
                case SERVICE_ELECTRONICS_CODE:
                    departure=routeService.findAll();
                    String msg="Select a Departure City\n";
                    for(int i=0;i<departure.size();i++)
                    {
                        msg=msg+Math.addExact(i,1)+":"+departure.get(i).getDeparture();
                    }
                    mtUssdReq = generateMTRequest(msg, moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                    menuStates.add("electronics.page");
                    switch (moUssdReq.getMessage()) {
                        case "1":
                            destination=routeService.findAll();
                            msg="Select a Destination City\n";
                            for(int i=0;i<departure.size();i++)
                            {
                                msg=msg+Math.addExact(i,1)+":"+departure.get(i).getDestination();
                            }
                            mtUssdReq=generateMTRequest(msg,moUssdReq.getSessionId(),OPERATION_MT_CONT,destinationAddress);

                    }
                    break;
                case SERVICE_COSMETICS_CODE:
                    mtUssdReq = generateMTRequest(propertyReader.readProperty("cosmetics.page"), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                    menuStates.add("cosmetics.page");
                    break;
                case SERVICE_HOUSEHOLDS_CODE:
                    mtUssdReq = generateMTRequest(propertyReader.readProperty("households.page"), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                    menuStates.add("households.page");
                    break;
                case SERVICE_PREV_CODE:
                    mtUssdReq = generateMTRequest(backOperation(), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                    break;
                default:
                    mtUssdReq = generateMTRequest(propertyReader.readProperty("error.page"), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
                    menuStates.add("error.page");
            }
        } else {
            mtUssdReq = generateMTRequest(propertyReader.readProperty("error.page"), moUssdReq.getSessionId(), OPERATION_MT_CONT, destinationAddress);
            menuStates.add("error.page");
        }

        UssdRequestSender ussdRequestSender = new UssdRequestSender(new URL(REQUEST_SEND_URL));
        ussdRequestSender.sendUssdRequest(mtUssdReq);
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
    private String backOperation() {
        String prevState = propertyReader.readProperty("welcome.page");
        System.out.println(menuStates.size());
        if (menuStates.size() > 0 && (menuStates.size() - 1) != 0) {
            prevState = propertyReader.readProperty(menuStates.get(menuStates.size() - 2));
            menuStates.remove(menuStates.size() - 1);
        }
        return prevState;
    }
}
