package com.borjabolufer.contactsfragments.models;

public class Contact {
    private final String name;
    private String firstSurname;
    private String secondSurname;
    private String addres;
    private String birth;
    private String company;
    private String email;
    private final String phoneOne;
    private String phoneTwo;
    private String address;

    public Contact(String name, String firstSurname, String secondSurname, String address, String company, String birth,  String phoneOne, String phoneTwo, String email) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.address = address;
        this.birth = birth;
        this.company = company;
        this.email = email;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.address = address;
    }

    public Contact(String name, String phoneOne) {
        this.name = name + getFirstSurname() + getSecondSurname();
        this.phoneOne = phoneOne;
    }


    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getFullSurname(){
        return  firstSurname + " " + secondSurname;
    }

    public String getBirth() {
        return birth;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public String getAddress() {
        return address;
    }
}
