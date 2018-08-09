package com.epam.market.database;

import java.util.ResourceBundle;

public class DBManager {
    private final static ResourceBundle resourceBundle  = ResourceBundle.getBundle("dataBaseConfiguration");
    private DBManager(){}
    public static  String getProperty (String key) {
        return  resourceBundle.getString(key);
}
}
