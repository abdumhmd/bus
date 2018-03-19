package com.abd.abcrbts.abcrbts.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Abdurahman
 * @version 1.0
 * @created 30-Jan-2018 10:43:27 AM
 */
@Entity@Table(name="bus")
public class Bus {

	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="plate")
	private String plate;
	@Column(name="seats")
	private int seats;
	@Column(name="assigned",columnDefinition = "BOOLEAN DEFAULT 0")
    private boolean assigned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}