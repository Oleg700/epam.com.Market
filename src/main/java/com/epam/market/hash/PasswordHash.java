package com.epam.market.hash;

public class PasswordHash {
    public PasswordHash(){}
    public  int hashPassword(String password) {
      int passwordHash =password.hashCode();
        return passwordHash;
    }
}


