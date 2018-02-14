
package com.abd.abcrbts.abcrbts.Model;
import javax.persistence.*;

/**
 * @author Abdurahman
 * @version 1.0
 * @created 30-Jan-2018 10:43:27 AM
 */
@Entity@Table(name="route")
public class Route {

	@OneToOne
	private Bus bus;
	@Column(name="departure")
	private String departure;
	@Column(name="destination")
	private String destination;
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="price")
	private double price;
	@Column(name="time")
	private String time;

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}