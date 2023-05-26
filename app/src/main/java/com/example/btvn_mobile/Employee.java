package com.example.btvn_mobile;

public class Employee {

    private int id_employee;
    private String name;
    private String gender;
    private String address;
    private String phone;
    private byte[] image;
    private int id_department;

    public Employee() {
    }

    public Employee(int id_employee, String name, String gender, String address, String phone, byte[] image, int id_department) {
        this.id_employee = id_employee;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.image = image;
        this.id_department = id_department;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }



}
