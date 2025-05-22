package com.usercrud.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String address;

    public User() {}

    public User(int id, String name, String email, String mobile, String gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public User(String name, String email, String mobile, String gender, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
} 