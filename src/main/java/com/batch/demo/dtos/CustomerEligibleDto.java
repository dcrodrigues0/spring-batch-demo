package com.batch.demo.dtos;

public class CustomerEligibleDto {

    private String lastName;
    private String firstName;
    private String city;

    public CustomerEligibleDto() {

    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCity() {
        return city;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
