package com.epam.market.propertymenager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String BUNDLE_NAME= "config";
    private static final  ResourceBundle resourceBundle  = ResourceBundle.getBundle(BUNDLE_NAME);
    private  ConfigurationManager(){}
    public static  String getProperty (String key) {
        return  resourceBundle.getString(key);
    }
}
