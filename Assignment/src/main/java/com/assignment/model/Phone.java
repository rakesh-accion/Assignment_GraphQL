package com.assignment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@javax.persistence.Cacheable
@Table(name = "phone", indexes = @Index(name="phone_Index", columnList ="id,phoneNumber"))
public class Phone {

	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String phoneNumber;

	@ManyToOne(optional = false)
	@JoinColumn(name = "street_no")
	@JsonBackReference
	private Address address;
	
	public Phone() {}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Phone [phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}

}
