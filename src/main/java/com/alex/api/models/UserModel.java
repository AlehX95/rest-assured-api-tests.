package com.alex.api.models;

import java.util.List;

public class UserModel {

    private String name;
    private String job;
    private Integer age;
    private List<String> roles;
    private AddressModel address;

    // Constructor principal
    public UserModel(String name, String job, Integer age, List<String> roles, AddressModel address) {
        this.name = name;
        this.job = job;
        this.age = age;
        this.roles = roles;
        this.address = address;
    }

    // Getters
    public String getName() { return name; }
    public String getJob() { return job; }
    public Integer getAge() { return age; }
    public List<String> getRoles() { return roles; }
    public AddressModel getAddress() { return address; }

    // Setters opcionales
    public void setName(String name) { this.name = name; }
    public void setJob(String job) { this.job = job; }
    public void setAge(Integer age) { this.age = age; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public void setAddress(AddressModel address) { this.address = address; }
}
