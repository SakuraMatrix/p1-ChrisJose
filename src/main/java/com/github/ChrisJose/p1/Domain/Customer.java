package com.github.ChrisJose.p1.Domain;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String primaryPhoneNo;
    private String email;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;


    public Customer(int customerId, String firstName, String lastName, String primaryPhoneNo, String email,
                    String address, String address2, String city, String state, String zip) {
        this.customerId=customerId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.primaryPhoneNo=primaryPhoneNo;
        this.email=email;
        this.address=address;
        this.address2=address2;
        this.city=city;
        this.state=state;
        this.zip = zip;
    }

    public Customer() {};

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getPrimaryPhoneNo() {
        return primaryPhoneNo;
    }

    public void setPrimaryPhoneNo(String primaryPhoneNo) {
        this.primaryPhoneNo = primaryPhoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
