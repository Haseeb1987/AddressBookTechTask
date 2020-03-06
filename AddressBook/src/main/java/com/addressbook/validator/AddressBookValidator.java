package com.addressbook.validator;

import org.springframework.stereotype.Component;

import com.addressbook.model.AddressBook;
import com.addressbook.model.Contact;

@Component
public class AddressBookValidator {

	public static final String PHONE_NUMBER_REGEX = "^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$";
		
	public Boolean validatePhoneNumer(AddressBook addressBook) {
		Boolean isValid = Boolean.TRUE;
		for(Contact contact : addressBook.getContact()) {
			if(!contact.getPhoneNumber().matches(PHONE_NUMBER_REGEX)) {
				isValid = Boolean.FALSE;
				break;
			}
		}
		return isValid;
	}
}
