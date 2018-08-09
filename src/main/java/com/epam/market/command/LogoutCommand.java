package com.epam.market.command;

import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements  ActionCommand {
    private static final String PROPERTY_NAME_PATH = "path.page.menu";
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);

    }
}
