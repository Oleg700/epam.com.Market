package com.epam.market.command;
import com.epam.market.daoimplementation.CustomerDAOImpl;
import com.epam.market.hash.PasswordHash;
import com.epam.market.propertymenager.ConfigurationManager;
import com.epam.market.propertymenager.MessageManager;
import com.epam.market.model.Customer;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements  ActionCommand  {
    private static final String PARAM_NAME_NAME= "name";
    private static final String PARAM_NAME_SURNAME= "surname";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL= "email";
    private static final String PARAM_NAME_ACCESS= "access";
    private static final String PARAM_NAME_ROLE= "role";
    private static final String PARAM_NAME_CHECK_EMAIL= "checkEmail";
    private static final String PROPERTY_NAME_PATH = "path.page.menu";
    private static final String PATH_REGISTER= "path.page.register";
    private static final String ATTRIBUTE_NAME_LOGIN_ERROR= "errorEmail";
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        PasswordHash passwordHash = new PasswordHash();
        CustomerDAOImpl daoCustomer = new CustomerDAOImpl();
        Customer customer= new Customer();
        Boolean check = Boolean.valueOf(request.getParameter(PARAM_NAME_CHECK_EMAIL));
        if (check){
            request.setAttribute(ATTRIBUTE_NAME_LOGIN_ERROR, MessageManager.getProperty(ATTRIBUTE_NAME_LOGIN_ERROR));
            return ConfigurationManager.getProperty(PATH_REGISTER);
        }
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String access = request.getParameter(PARAM_NAME_ACCESS);
        String role = request.getParameter(PARAM_NAME_ROLE);
        int hashPassword = passwordHash.hashPassword(pass);
        customer.setName(name)
                .setSurname(surname)
                .setLogin(login)
              .setPassword(hashPassword)
                .setEmail(email)
                .setAccess(access)
                .setRole(role);
        daoCustomer.add(customer);
        LOGGER.info("new user is registered, with name "+name+"surname "+surname+"login "+login);
        request.setAttribute(PARAM_NAME_NAME, name);
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }
}
