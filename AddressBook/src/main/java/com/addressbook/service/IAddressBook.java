package com.addressbook.service;

import java.util.List;

import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;

public interface IAddressBook {
	public List<AddressBook> findAll();
	public List<Contact> findAllContacts();
	public AddressBook saveAddressBook(AddressBook addressBook);
	public Boolean deleteAddressBook(AddressBook addressBook);
	public void printContactsinAddressBookById(long id);
	public boolean addNewContactsOrRemoveExistingContact(AddressBook addressBook, String action);
	public AddressBook findAddressBookById(long id);
	public List<Contact> uniqueContactsInAddressBooks();
}
