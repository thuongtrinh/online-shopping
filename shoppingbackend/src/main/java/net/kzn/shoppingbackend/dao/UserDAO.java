package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {

	// add an user
	boolean addUser(User user);
	// add an address
	boolean addAddress(Address address);
	// get user by email
	User getByEmail(String email);
	// get billing address
	Address getBillingAddress(User user);
	// get list shipping adrress
	List<Address> listShippingAddresses(User user);

	// alternative
	// Address getBillingAddress(int userId);
	// List<Address> listShippingAddresses(int userId);
}
