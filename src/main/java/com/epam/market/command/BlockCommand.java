package com.epam.market.command;

import com.epam.market.daoimplementation.CustomerDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BlockCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "loginCustomer";
    private static final String ATTRIBUTE_NAME_INFO = "InfoMessage";
    private static final String PROPERTY_NAME_PATH= "path.page.admin";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    @Override
    public String execute(HttpServletRequest request) {
      blockUser(request);
        return  ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }

    private void blockUser(HttpServletRequest request){
        String login = request.getParameter(PARAM_NAME_LOGIN);
        CustomerDAOImpl customersDAO = new CustomerDAOImpl();
        customersDAO.blockCustomerByLogin(login);
        String InfoMessage = "customer with login"+login+"is blocked";
        request.setAttribute(ATTRIBUTE_NAME_INFO, InfoMessage);
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
    }
}
