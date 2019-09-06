package com.Softpig.Models;

import java.util.Date;

public class Employee {

    private short idEmployee;
    private String role;
    private String status;
    private Date admissionDate;
    private String document;
    private String gender;
    private String firstName;
    private String secondName;
    private String lastName;
    private String motherLastName;
    private String email;
    private String telephone;
    private String mobile;

    public Employee() {
    }

    public Employee(short idEmployee, String role, String status, Date admissionDate, String document,
                    String gender, String firstName, String secondName, String lastName,
                    String motherLastName, String email, String telephone, String mobile) {
        this.idEmployee = idEmployee;
        this.role = role;
        this.status = status;
        this.admissionDate = admissionDate;
        this.document = document;
        this.gender = gender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.email = email;
        this.telephone = telephone;
        this.mobile = mobile;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public String getDocument() {
        return document;
    }

    public String getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }
}
