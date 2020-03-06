package com.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private Long id;
	private String name;
	private List<Contact> contact = new ArrayList<Contact>(); 
	
	public AddressBook () {}
	
	public AddressBook(long id,String name, ArrayList<Contact> contact) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
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

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
	public boolean addNewContact(Contact contact) {
		return this.contact.add(contact);
	}
	
	public boolean removeExistingContact(Contact contact) {
		return this.contact.remove(contact);
	}
	
	public void printAllContacts() {
		for(Contact contact : this.contact) {
			System.out.println("Contacts : " + contact);
		}
	}
	
	@Override
    public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (o == null || getClass() != o.getClass()) 
			return false;
		AddressBook addressBook = (AddressBook) o;
		return id == addressBook.id;
    }
}
