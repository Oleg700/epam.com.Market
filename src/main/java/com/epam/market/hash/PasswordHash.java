package com.epam.market.hash;

public class PasswordHash {
    private PasswordHash(){}
    public static int hashPassword(String password) {
      int passwordHash =password.hashCode();
        return passwordHash;
    }
}


