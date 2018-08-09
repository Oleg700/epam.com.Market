package com.epam.market.model;

public class Customer {
    private int customerId;
    private String name;
    private String surname;
    private String login;
    private int password;
    private String email;
    private String access;
    private String role;

    public Customer() {
    }

    public String getAccess() {
        return access;
    }

    public Customer setAccess(String block) {
        this.access = block;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Customer setRole(String role) {
        this.role = role;
        return  this;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Customer setLogin(String login) {
        this.login = login;
        return this;
    }

    public int  getPassword() {
        return password;
    }

    public Customer setPassword(int password) {
        this.password = password;
        return  this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }
}
