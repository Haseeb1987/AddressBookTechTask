package com.addressbook.model;

import java.util.Objects;

public class Contact {
	private Long id;
	private String name;
	private String phoneNumber;
	
	public Contact() {}
	
	public Contact(long id,String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
    public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null || getClass() != o.getClass()) 
			return false;
		Contact contact = (Contact) o;
		return id == contact.id;
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(name, phoneNumber);
	}
	
	@Override
	public String toString() {
		return "AddressBook {" + "id = " + id + ", name = " + name + ", phone number = " + phoneNumber + '}';
	}
	
}
