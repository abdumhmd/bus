package com.abd.abcrbts.abcrbts.Model;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Abdurahman
 * @version 1.0
 * @created 30-Jan-2018 10:43:27 AM
 */
@Entity@Table(name="reservation")
public class Reservation {

	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="paid")
	private Boolean paid;
	@Column(name="passengerphone")
	private String passengerphone;
	@Column(name="refNumber")
	private String refNumber;
	@ManyToOne
	private Route route;
	@Column(name="reservationtime" ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")@Temporal(TemporalType.DATE)
	private Date timeReservation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public String getPassengerphone() {
		return passengerphone;
	}

	public void setPassengerphone(String passengerphone) {
		this.passengerphone = passengerphone;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Date getTimeReservation() {
		return timeReservation;
	}

	public void setTimeReservation(Date timeReservation) {
		this.timeReservation = timeReservation;
	}
}