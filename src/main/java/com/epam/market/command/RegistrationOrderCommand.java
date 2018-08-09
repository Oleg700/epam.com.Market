package com.epam.market.command;
import com.epam.market.daoimplementation.CustomerDAOImpl;
import com.epam.market.daoimplementation.OrderDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import com.epam.market.propertymenager.MessageManager;
import com.epam.market.model.Order;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationOrderCommand implements  ActionCommand {
    private static final String PARAM_NAME_PRODUCT_ID= "productId";
    private static final String PARAM_NAME_LOGIN= "login";
    private static final String PARAM_NAME_PASSWORD= "password";
    private static final String ATTRIBUTE_NAME_ERROR_LOGIN= "errorLogin";
    private static final String PATH= "path.page.login";
    private static final String PROPERTY_NAME_PATH = "path.page.orderInfo";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
       String path = null;
        if (!checkLoginPassword(request)){
            request.setAttribute(ATTRIBUTE_NAME_ERROR_LOGIN, MessageManager.getProperty(ATTRIBUTE_NAME_ERROR_LOGIN));
            path = ConfigurationManager.getProperty(PATH);
        }
          else {
            registerOrder(request);
            path = ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
        }

            return path;
        }

    private Boolean checkLoginPassword(HttpServletRequest request){
    Boolean check = true;
        int password = 0;
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);
        if(session.getAttribute(PARAM_NAME_PASSWORD) != null){
            password = (int) session.getAttribute(PARAM_NAME_PASSWORD);
        }
        if (login == null && password ==0){
            check= false;
        }
        return check;
    }

    private void registerOrder(HttpServletRequest request){
        Order order = new Order();
        OrderDAOImpl ordersDAO = new OrderDAOImpl();
        HttpSession session = request.getSession(true);
        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);
        CustomerDAOImpl customersDAO = new CustomerDAOImpl();
        int  customerId = customersDAO.getCustomerIdByLogin(login);
        int productId= Integer.parseInt(request.getParameter(PARAM_NAME_PRODUCT_ID));
        order.setProductId(productId).setCustomerId(customerId);
        ordersDAO.add(order);
        LOGGER.info("new order is registered, " + " id of customer " + customerId + " login  " + login + " product_id " + productId);
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
    }
}
