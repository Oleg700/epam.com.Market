package com.epam.market.daoimplementation;

import com.epam.market.dao.CustomerDAO;
import com.epam.market.model.Customer;
import com.epam.market.database.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String QUERY_ADD_CUSTOMER = "INSERT INTO `customer`( customer_name, customer_surname, login, password, email, access, role) VALUES( ?,?,?,?,?,?,?)";
    private static final String QUERY_READ_ALL_CUSTOMERS = "SELECT customer_id, customer_name, customer_surname, login, email, access, role FROM customer";
    private static final String QUERY_SELECT_CUSTOMER_BY_LOGIN = "SELECT  customer_id FROM customer where login =?";
    private static final String QUERY_BLOCK_CUSTOMER = "UPDATE customer SET access='denied'  WHERE login=?";
    private static final String QUERY_CUSTOMER_ACCESS = "SELECT access FROM customer where login =?";
    private static final String QUERY_CUSTOMER_ROLE = "SELECT role FROM customer where login =?";
    private static final String QUERY_CUSTOMER_LOGIN_PASSWORD = "SELECT login, password FROM customer WHERE login =? and password = ?";
    private static final Logger LOGGER = Logger.getLogger(CustomerDAOImpl.class);

    @Override
    public void add(Customer customer) {
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
            PreparedStatement statement = connection.prepareStatement(QUERY_ADD_CUSTOMER)) {
            addCustomerStatement(statement, customer);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method  add(Customer customer)");
        }
    }

    private void addCustomerStatement(PreparedStatement statement,Customer customer) throws SQLException {
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getSurname());
        statement.setString(3, customer.getLogin());
        statement.setInt(4, customer.getPassword());
        statement.setString(5, customer.getEmail());
        statement.setString(6, customer.getAccess());
        statement.setString(7, customer.getRole());
        statement.execute();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_READ_ALL_CUSTOMERS)) {
            ResultSet resultSet = statement.executeQuery();
            listOfCustomers =  getAllCustomersResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method  getAllCustomers()");
        }
        return listOfCustomers;
    }

   private List<Customer> getAllCustomersResultSet(ResultSet resultSet) throws SQLException {
        List<Customer> listOfCustomers = new ArrayList<>();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            String name = resultSet.getString("customer_name");
            String surname = resultSet.getString("customer_surname");
            String login = resultSet.getString("login");
            String email = resultSet.getString("email");
            String access = resultSet.getString("access");
            String role = resultSet.getString("role");
            listOfCustomers.add(new Customer()
                    .setCustomerId(customerId)
                    .setName(name)
                    .setSurname(surname)
                    .setLogin(login)
                    .setEmail(email)
                    .setAccess(access)
                    .setRole(role));
        }
        return listOfCustomers;
    }

    @Override
    public int getCustomerIdByLogin(String login) {
        Customer customer = new Customer();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_CUSTOMER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet   resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id()");
                customer.setCustomerId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method getCustomerIDByLogin()");
        }
        return  customer.getCustomerId();
    }
    @Override
    public void blockCustomerByLogin(String login) {
        try (Connection connection =ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_BLOCK_CUSTOMER)) {
            statement.setString(1, login);
            statement.execute();
            } catch (SQLException e1) {
            e1.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method blockCustomerByLogin");
        }
    }
    @Override
    public  boolean isLoginValid(String login, int password) {
        try (Connection connection =  ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_CUSTOMER_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            statement.setInt(2, password);
            ResultSet  resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method isLoginValid(String login, int password)");
        }
        return true;
    }
@Override
    public String isRoleValid(String login) {
        String path=null;
        try (Connection connection =  ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_CUSTOMER_ROLE)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            path = getPathByRole(resultSet);
        }  catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method isRoleValid(String login)");
        }
        return path;
    }

    private String getPathByRole(ResultSet resultSet) throws SQLException {
        String path=null;
        String role=null;
        while (resultSet.next()) {
            role = resultSet.getString("role");
        }
        if(role != null && role.equals("admin")){
            path = "path.page.admin";
        }else
            path = "path.page.menu";
        return path;
    }

    @Override
    public  boolean isAccessValid(String login) {
        String access = null;
        try (Connection connection =  ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_CUSTOMER_ACCESS)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            access = resultSet.getString("access");
            }
            if(access !=null && access.equals("allowed")){
             return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class CustomerDAOIMpl method isAccessValid(String login)");
        }
        return false;
    }
        }
