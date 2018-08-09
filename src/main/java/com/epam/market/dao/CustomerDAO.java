package com.epam.market.dao;
import com.epam.market.model.Customer;
import java.util.List;

public interface CustomerDAO extends ItemDAO<Customer>{
    List<Customer> getAllCustomers();
    void blockCustomerByLogin(String login);
    int getCustomerIdByLogin(String login);
    boolean isLoginValid(String login, int password) ;
    String isRoleValid(String login);
    boolean isAccessValid(String login) ;

}
