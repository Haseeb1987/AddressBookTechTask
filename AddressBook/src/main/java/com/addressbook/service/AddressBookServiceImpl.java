package com.addressbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.enums.Action;
import com.addressbook.exception.PhoneNumerValidationException;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import com.addressbook.utility.AddressBookConstants;
import com.addressbook.validator.AddressBookValidator;

@Service
public class AddressBookServiceImpl implements IAddressBook{
	
	@Autowired
	private AddressBookValidator validator;
	
	public List<AddressBook> addressBooks =  new ArrayList<AddressBook>();
	
	@Override
	public List<AddressBook> findAll() {
		return addressBooks;
	}

	@Override
	public AddressBook saveAddressBook(AddressBook addressBook) {
		if(!validator.validatePhoneNumer(addressBook)) {
			throw new PhoneNumerValidationException(AddressBookConstants.PHONE_NUMBER_NOT_VALID);
		}
		addressBooks.add(addressBook);
		return addressBook;
	}

	@Override
	public Boolean deleteAddressBook(AddressBook addressBook) {
		return addressBooks.remove(addressBook);
	}
	
	@Override
	public AddressBook findAddressBookById(long id) {
		for(AddressBook book : addressBooks) {
			if(book.getId() == id){
				return book;
			}
		}
		return null;
	}
	
	@Override
	public boolean addNewContactsOrRemoveExistingContact(AddressBook addressBook, String action) {
		AddressBook book = findAddressBookById(addressBook.getId());
		if (book != null) {
			List<Contact> contact = addressBook.getContact();
			if(contact != null) {
				if(action.equals(Action.ADD.toString())) {
					book.addNewContact(contact.get(0));
				}
				else if (action.equals(Action.DELETE.toString())) {
					book.removeExistingContact(contact.get(0));
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void printContactsinAddressBookById(long id) {
		AddressBook book = findAddressBookById(id);
		book.printAllContacts();
	}
	
	@Override
	public List<Contact> findAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		for(AddressBook book: addressBooks) {
			contacts.addAll(book.getContact());
		}
		return contacts;
	}
	
	@Override
	public List<Contact> uniqueContactsInAddressBooks(){
		return findAllContacts().stream().distinct().collect(Collectors.toList());
	}
}
