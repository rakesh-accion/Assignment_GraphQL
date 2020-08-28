package com.assignment.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity()
@Table(name = "user", indexes = @Index(name="user_Index", columnList ="user_id,firstName,lastName"))
@javax.persistence.Cacheable
public class User {
	@Id
	@Column(name = "user_id")
	private int userId;
	private String firstName;
	private String lastName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<Address> address;
	
	public User() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ "]";
	}

}
