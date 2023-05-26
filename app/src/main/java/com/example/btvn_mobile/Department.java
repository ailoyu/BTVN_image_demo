package com.example.btvn_mobile;

public class Department {

    private int id_department;
    private String departmentName;
    private String phoneNumber;

    public Department() {
    }

    public Department(int id_department, String departmentName, String phoneNumber) {
        this.id_department = id_department;
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
