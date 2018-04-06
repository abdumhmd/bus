package com.abd.abcrbts.abcrbts.Model;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Abdurahman
 * @version 1.0
 * @created 30-Jan-2018 10:43:27 AM
 */
@Entity@Table(name="tickets")
public class Tickets {

	@ManyToOne
	private Agents agents;
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "passenger_phone")
	private String passengerPhone;
	@Column(name = "passenger_name")
	private String passengerName;
	@ManyToOne
	private Route route;
	@ManyToOne
	private Users soldBy;
	@Column(name="date" ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")@Temporal(TemporalType.DATE)
	private Date timeSold;
	@Column(name="departure_date")
	private Date departureDate;

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Agents getAgents() {
		return agents;
	}

	public void setAgents(Agents agents) {
		this.agents = agents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerPhone() {
		return passengerPhone;
	}

	public void setPassengerPhone(String passengerPhone) {
		this.passengerPhone = passengerPhone;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Users getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(Users soldBy) {
		this.soldBy = soldBy;
	}

	public Date getTimeSold() {
		return timeSold;
	}

	public void setTimeSold(Date timeSold) {
		this.timeSold = timeSold;
	}
}