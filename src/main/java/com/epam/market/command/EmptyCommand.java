package com.epam.market.command;

import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmptyCommand implements  ActionCommand {
    private static final String PROPERTY_NAME_PATH = "path.page.menu";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    @Override
    public String execute(HttpServletRequest request){
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }
}
