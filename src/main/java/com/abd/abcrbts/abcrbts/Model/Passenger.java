
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
@Entity@Table(name="passenger")
public class Passenger {

	@Column(name="fullName")
	private String fullName;
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="phoneNumber")
	private String phoneNember;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNember() {
		return phoneNember;
	}

	public void setPhoneNember(String phoneNember) {
		this.phoneNember = phoneNember;
	}
}