package com.addressbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbook.exception.PhoneNumerValidationException;
import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookServiceImpl;

@SpringBootTest
class AddressBookApplicationTests {
	
	@Autowired
	private AddressBookServiceImpl addressBookService;
	
	public Contact contact1;
	public Contact contact2;
	public Contact contact3;
	public Contact contact4;
	public Contact invalidContact;
	public ArrayList<Contact> contactList1;
	public ArrayList<Contact> contactList2;
	public AddressBook addressBook1;
	public AddressBook addressBook2;
	int beforeSize;
	int afterSize;
	public static final int result = 1;

	public AddressBookApplicationTests() {
		contact1 = new Contact(1, "Mike", "0439571054");
		contact2 = new Contact(2, "Allan", "+61452951751");
		contact3 = new Contact(1, "Shank", "0264871459");
		contact4 = new Contact(2, "Allan", "+61452951751");
		invalidContact = new Contact(2, "Invalid", "14529517");
		contactList1 = new ArrayList<Contact>();
		contactList1.add(contact1);
		contactList1.add(contact2);
		contactList2 = new ArrayList<Contact>();
		contactList2.add(contact3);
		contactList2.add(contact4);
		addressBook1 = new AddressBook(1, "Manager AddressBook", contactList1);
		addressBook2 = new AddressBook(1, "Employee AddressBook", contactList2);
	}
	
	@Test
	public void addNewContactTest() {
		System.out.println("********** Add New Contact Entry Test **********");
		beforeSize = addressBook1.getContact().size();
		System.out.println("Initial AddressBook Contacts Size : " + beforeSize);
		addressBook1.addNewContact(contact3);
		afterSize = addressBook1.getContact().size();
		System.out.println("Final AddressBook Contacts Size : " + afterSize);
		try {
			assertEquals(afterSize - beforeSize, result);
			System.out.println("********** Add New Contact Entry Test : PASSED **********");
		}catch(AssertionError e) {
			System.out.println("********** Add New Contact Entry Test : FAILED **********");
			throw e;
		}
	}
	
	@Test
	public void removeExistingContactTest() {
		System.out.println("********** Add Remove Existing Contact Test **********");
		beforeSize = addressBook1.getContact().size();
		System.out.println("Initial AddressBook Contacts Size : " + beforeSize);
		addressBook1.removeExistingContact(contact2);
		afterSize = addressBook1.getContact().size();
		System.out.println("Final AddressBook Contacts Size : " + afterSize);
		try {
			assertFalse(addressBook1.getContact().contains(contact2));
			System.out.println("********** Add Remove Existing Contact Test : PASSED **********");
		}catch(AssertionError e) {
			System.out.println("********** Add Remove Existing Contact Test : FAILED **********");
			throw e;
		}
	}
	
	@Test
	public void printAllContactsTest() {
		System.out.println("********** Print All Contacts Test Started **********");
		addressBook1.printAllContacts();
		System.out.println("********** Print All Contacts Test Finish **********");
	}
	
	@Test
	void multipleAddressBookTest() {
		System.out.println("********** Maintain Multiple AddressBook Test **********");
		beforeSize = addressBookService.addressBooks.size();
		System.out.println("AddressBook List initial size : " + beforeSize);
		addressBookService.saveAddressBook(addressBook1);
		addressBookService.saveAddressBook(addressBook2);
		afterSize = addressBookService.addressBooks.size();
		System.out.println("AddressBook List Final size : " + afterSize);
        try{
        	assertEquals(afterSize - beforeSize, result + 1);
        	System.out.println("********** Maintain Multiple AddressBook Test : PASSED **********");
        }catch(AssertionError e) {
        	System.out.println("********** Maintain Multiple AddressBook Test : FAILED **********");
        	throw e;
        }
	}
	
	@Test
	void printUniqueContactsTest() {
		System.out.println("********** Print Unique Contacts Test Started **********");
		addressBookService.saveAddressBook(addressBook1);
		addressBookService.saveAddressBook(addressBook2);
		List<Contact> contacts = addressBookService.findAllContacts();
		System.out.println("All Contacts in All AddressBooks : " + contacts);
		System.out.println("Only UNIQUE SET Contacts in All AddressBooks : " + addressBookService.uniqueContactsInAddressBooks());
		System.out.println("********** Print Unique Contacts Test Finish **********");
	}
	
	@Test
    public void testInvalidPhoneNumer() {
		System.out.println("********** In Valid Phone Test Started **********");
		// To Fail the Test Case Uncomment 
//		addressBook1.addNewContact(invalidContact);
		// To Pass the Test Case Uncomment
		addressBook1.addNewContact(contact1);
		try {
			addressBookService.saveAddressBook(addressBook1);
			System.out.println("Valid PhoneNumber Provided");
		}
		catch(PhoneNumerValidationException e) {
			System.out.println("In Valid PhoneNumber Provided");
			throw e;
		}
		System.out.println("********** In Valid Phone Test Finish **********");
    }

}
