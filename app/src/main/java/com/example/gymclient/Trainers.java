package com.example.gymclient;

public class Trainers {
    public String name,contact,experience,email,speciality;

    public Trainers() {
    }


    public Trainers(String Name, String Contact, String Email, String Experience, String Speciality) {
        this.name = Name;
        this.contact = Contact;
        this.experience= Experience;
        this.email = Email;
        this.speciality = Speciality;

    }


    public String getNamee() {
        return name;
    }

    public void setNamee(String name) {
        this.name = name;
    }

    public String getContactt() {
        return contact;
    }

    public void setContactt(String contact) {
        this.contact = contact;
    }

    public String getExperiencee() {
        return experience;
    }

    public void setExperiencee(String experience) {
        this.experience = experience;
    }

    public String getEmaill() {
        return email;
    }

    public void setEmaill(String email) {
        this.email = email;
    }

    public String getSpecialityy() {
        return speciality;
    }

    public void setSpecialityy(String speciality) {
        this.speciality = speciality;
    }
}
