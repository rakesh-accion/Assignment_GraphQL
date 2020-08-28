package com.assignment.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "address", indexes = @Index(name="address_Index", columnList ="street_no,city,streetName"))
@javax.persistence.Cacheable
public class Address {

	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "street_no")
	private int streetNo;
	private String city;
	private String streetName;
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	 @JsonManagedReference
	private Set<Phone> phone;
	
	public Address() {}

	public int getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Phone> getPhone() {
		return phone;
	}

	public void setPhone(Set<Phone> phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Address [streetNo=" + streetNo + ", city=" + city + ", streetName=" + streetName + ", user=" + user
				+ ", phone=" + phone + "]";
	}
	

}
