package com.addressbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.addressbook.exception.PhoneNumerValidationException;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookServiceImpl;
import com.addressbook.utility.AddressBookConstants;
import com.addressbook.validator.AddressBookValidator;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

	@Autowired
	private AddressBookServiceImpl addressBookServiceImpl;
	
	@GetMapping("/books")
	public List<AddressBook> getAllAddressBooks(){
		return addressBookServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAddressBooksById(@PathVariable String id){
		AddressBook addressBook = addressBookServiceImpl.findAddressBookById(Long.parseLong(id)); 
		Optional<AddressBook> addressBookOptional = Optional.ofNullable(addressBook);
		if(addressBookOptional.isPresent()) {
			return new ResponseEntity<>(addressBook, HttpStatus.OK);
		}
		return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_RECORD_NOT_EXITS, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/contacts")
	public ResponseEntity<Object> getAllContacts () {
		List<Contact> contacts = addressBookServiceImpl.findAllContacts();
		Optional<List<Contact>> contactsOptional = Optional.ofNullable(contacts);
		if(contactsOptional.isPresent()) {
			return new ResponseEntity<>(contactsOptional, HttpStatus.OK);
		}
		return new ResponseEntity<>(AddressBookConstants.CONTACT_RECORD_NOT_EXIST, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/books/{id}")
	public void printAddressBookContact(@PathVariable String id){
		addressBookServiceImpl.printContactsinAddressBookById(Long.parseLong(id));
	}
	
	@GetMapping("/contacts/unique")
	public List<Contact> printUniqueContacts(){
		return addressBookServiceImpl.uniqueContactsInAddressBooks();
	}
	
	@PostMapping("/book")
	public ResponseEntity<Object> saveAddressBook(@RequestBody AddressBook addressBook){
		addressBookServiceImpl.saveAddressBook(addressBook);
		return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_ADDED_SUCCESSFULLY, HttpStatus.OK);
	}

	@PutMapping("/book")
	public ResponseEntity<Object> addOrRemoveContact(@RequestBody AddressBook addressBook, @RequestParam(name = "action") String action) {
		Boolean result = addressBookServiceImpl.addNewContactsOrRemoveExistingContact(addressBook, action);
		if(result) {
			return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_UPDATED_SUCCESSFULLY, HttpStatus.OK);
		}
		return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_RECORD_NOT_EXITS, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/book")
	public ResponseEntity<Object> deleteAddressBook(@RequestBody AddressBook addressBook) {
		Boolean result = addressBookServiceImpl.deleteAddressBook(addressBook);
		if(result) {
			return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_RECORD_DELETED, HttpStatus.OK);
		}
		return new ResponseEntity<>(AddressBookConstants.ADDRESS_BOOK_RECORD_NOT_EXITS, HttpStatus.NOT_FOUND);
	}
}
