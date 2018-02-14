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
@Entity@Table(name="role")
public class Role {

	@Column(name="role")
	private String role;
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
}