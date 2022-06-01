package com.blood.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotBlank(message="Name field is Required!!!")
	@Size(min=2,max=20,message="min is 2 and max is 20 characters are allowed!!!")
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	@Column(length=500)
	private String about;
	private String role;
	private String contact;
	private String city;
	private String state;
	private String designation;
	@JsonFormat(pattern="yyyy-MM-dd",shape=Shape.STRING)
	@Column(name="dob")
	private String dob;
	private String address;
	private boolean enabled;
	private String imageUrl;
	private String gender;
	@JsonFormat(pattern="yyyy-MM-dd",shape=Shape.STRING)
	@Column(name="requiredDate")
	private String requiredDate;
	private String bloodGroup;
	private String lastdateofDonation;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getLastdateofDonation() {
		return lastdateofDonation;
	}
	public void setLastdateofDonation(String lastdateofDonation) {
		this.lastdateofDonation = lastdateofDonation;
	}
	public User(int id,
			@NotBlank(message = "Name field is Required!!!") @Size(min = 2, max = 20, message = "min is 2 and max is 20 characters are allowed!!!") String name,
			String email, String password, String about, String role, String contact, String city, String state,
			String designation, String dob, String address, boolean enabled, String imageUrl, String gender,
			String requiredDate, String bloodGroup, String lastdateofDonation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.contact = contact;
		this.city = city;
		this.state = state;
		this.designation = designation;
		this.dob = dob;
		this.address = address;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.gender = gender;
		this.requiredDate = requiredDate;
		this.bloodGroup = bloodGroup;
		this.lastdateofDonation = lastdateofDonation;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", role=" + role + ", contact=" + contact + ", city=" + city + ", state=" + state + ", designation="
				+ designation + ", dob=" + dob + ", address=" + address + ", enabled=" + enabled + ", imageUrl="
				+ imageUrl + ", gender=" + gender + ", requiredDate=" + requiredDate + ", bloodGroup=" + bloodGroup
				+ ", lastdateofDonation=" + lastdateofDonation + "]";
	}
	
}
