package com.epam.market.logic;
import com.epam.market.dao.CustomerDAO;

public class LoginLogic {
    private CustomerDAO customerDAO;

    public LoginLogic(CustomerDAO customerDAO){
     this.customerDAO = customerDAO;
 }
    public String isRoleValid(String login) {
     return customerDAO.isRoleValid(login);
    }
    public boolean isAccessValid(String login) {
     return customerDAO.isAccessValid(login);
    }
    public boolean isLoginValid(String login, int password) {
     return customerDAO.isLoginValid(login,password);
    }
    }





