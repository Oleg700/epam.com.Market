package com.epam.market.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapOfCommand {
    private static  Map<String, ActionCommand> MapPath = createMap();
    private static Map<String, ActionCommand> createMap()
    {
        Map<String,ActionCommand> MapPath= new ConcurrentHashMap<>();
        MapPath.put("ADDPRODUCT", new AdditionProductCommand());
        MapPath.put("BLOCKUSER", new BlockCommand());
        MapPath.put("FINDPAGE", new PageCommand());
        MapPath.put("LOGIN", new LoginCommand());
        MapPath.put("LOGOUT", new LogoutCommand());
        MapPath.put("MAKEORDER", new RegistrationOrderCommand());
        MapPath.put("REGISTER", new RegistrationCommand());
        MapPath.put("SELECTCUSTOMERS", new CustomerCommand());
        MapPath.put("SELECTPRODUCTS", new ProductCommand());
        MapPath.put("SETLANGUAGE", new LanguageCommand());
        MapPath.put("SELECTORDERS",new OrderCommand());
        return MapPath;
    }
    public static Map<String,ActionCommand> getMapPath() {
        return MapPath;
    }
}
