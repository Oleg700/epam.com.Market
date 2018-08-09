package com.epam.market.command;

import com.epam.market.daoimplementation.OrderDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderCommand implements  ActionCommand {
    private static final String ATTRIBUTE_NAME_LIST = "listOfOrders";
    private static final String PROPERTY_NAME_PATH = "path.page.order";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    @Override
    public String execute(HttpServletRequest request) {
        OrderDAOImpl orderDAO  = new OrderDAOImpl();
        List listOfOrders =  orderDAO.getAllOrders();
        request.setAttribute(ATTRIBUTE_NAME_LIST,listOfOrders);
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }

}
