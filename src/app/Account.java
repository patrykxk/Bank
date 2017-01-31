package app;

import java.io.*;

class Account implements Serializable {

	private static final long serialVersionUID = -6361206702210932470L;
	int clientID;
	String firstName;
	String surname;
	String pesel;
	long balance = 0;


	class Address implements Serializable{
		String street;
		String streetNo;
		String city;
		String postCode;

		Address(){}

		Address(String street, String streetNo, String city, String postCode){
			this.street = street;
			this.streetNo = streetNo;
			this.city = city;
			this.postCode = postCode;
		}

	}

	Address address = new Address();
	Account(int clientID, String firstName, String name, String pesel, Address address){
		this.clientID = clientID;
		this.firstName = firstName;
		this.surname = name;
		this.pesel = pesel;
		this.address = address;
	}
	Account(){}

	public int getID(){
		return this.clientID;
	}
	public String getName() {return this.surname;}
	public String getFirstName() {
		return this.firstName;
	}
	public String getPesel(){
		return this.pesel;
	}
	public String getStreet(){return this.address.street;}
	public String getCity(){
		return this.address.city;
	}
	public String getPostCode(){
		return this.address.postCode;
	}

}
