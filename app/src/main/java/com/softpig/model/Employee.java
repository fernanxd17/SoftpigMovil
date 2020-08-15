package com.softpig.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private short idEmployee;
    private String role;
    private String status;
    private String contract;
    private String hoursWorked;
    private String admissionDate;
    private String dateOff;
    private String document;
    private String gender;
    private String firstName;
    private String secondName;
    private String lastName;
    private String motherLastName;
    private String email;
    private String telephone;
    private String mobile;
    private String installation;
    private int salary;

    public Employee() {
    }

    public Employee(short idEmployee, String role, String contract, String hoursWorked, String status, String admissionDate, String dateOff ,String document,
                    String gender, String firstName, String secondName, String lastName,
                    String motherLastName, String email, String telephone, String mobile, String installation, int salary) {
        this.idEmployee = idEmployee;
        this.role = role;
        this.contract = contract;
        this.hoursWorked = hoursWorked;
        this.status = status;
        this.admissionDate = admissionDate;
        this.dateOff = dateOff;
        this.document = document;
        this.gender = gender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.email = email;
        this.telephone = telephone;
        this.mobile = mobile;
        this.installation = installation;
        this.salary = salary;
    }

    public short getIdEmployee() {
        return idEmployee;
    }

    public String getRole() {
        return role;
    }

    public String getContract() {
        return contract;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }

    public String getStatus() {
        return status;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDateOff() {
        return dateOff;
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

    public String getInstallation() {
        return installation;
    }

    public int getSalary() {
        return salary;
    }

    public void setIdEmployee(short idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDateOff(String dateOff) {
        this.dateOff = dateOff;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}