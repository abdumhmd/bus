package com.abd.abcrbts.abcrbts.Model;
import javax.persistence.*;
import java.util.Set;

/**
 * @author Abdurahman
 * @version 1.0
 * @created 30-Jan-2018 10:43:27 AM
 */
@Entity
@Table(name="users")
public class Users {

	@Column(name="attempt")
	private int attempt;
	@Column(name="firstName")
	private String firstName;
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="active")
	private boolean isActive;
	@Column(name="expired")
	private boolean isExpired;
	@Column(name="locked")
	private boolean isLocked;
	@Column(name="lastName")
	private String lastName;
	@Column(name = "password")
	private String password;
	@Column(name="phone")
	private String phone;
	@ManyToMany(fetch = FetchType.EAGER)@JoinTable(name="user_role",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
	@Column(name="username")
	private String username;

	public Users(Users users) {


	this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.phone = users.getPhone();

        this.username = users.getUsername();
        this.password = users.getPassword();
        this.attempt = users.getAttempt();
        this.isActive = users.isActive();
        this.isLocked = users.isLocked();
        this.isExpired = users.isExpired();
        this.roles = users.getRoles();}

	public Users() {
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean expired) {
		isExpired = expired;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}