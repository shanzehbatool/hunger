package com.example.hunger;

public class UserHelperClass {

    String name, username, email, address, phoneNo, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String address, String phoneNo, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
