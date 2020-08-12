package com.rohit.project.myapplication;

import java.sql.Timestamp;

public class Residents {
    public String email;
    public String resident_name;
    public String house_number;
    public String phone;
    public int available;
    public Timestamp last_duty;

    public Residents() {
    }

    public Residents(String email, String resident_name, String house_number, String phone, int available, Timestamp last_duty) {
        this.email = email;
        this.resident_name = resident_name;
        this.house_number = house_number;
        this.phone = phone;
        this.available = available;
        this.last_duty = last_duty;
    }

    public Timestamp getLast_duty() {
        return last_duty;
    }

    public void setLast_duty(Timestamp last_duty) {
        this.last_duty = last_duty;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResident_name() {
        return resident_name;
    }

    public void setResident_name(String resident_name) {
        this.resident_name = resident_name;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
