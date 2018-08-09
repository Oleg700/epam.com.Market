package com.epam.market.command;

import com.epam.market.daoimplementation.CustomerDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomerCommand implements  ActionCommand {
    private static final String ATTRIBUTE_NAME_LIST = "listOfCustomers";
    private static final String PROPERTY_NAME_PATH = "path.page.customer";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    @Override
    public String execute(HttpServletRequest request) {
        CustomerDAOImpl customersDAO  = new CustomerDAOImpl();
        List listOfCustomers = customersDAO.getAllCustomers();
        request.setAttribute(ATTRIBUTE_NAME_LIST,listOfCustomers);
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }
}
