package com.epam.market.daoimplementation;

import com.epam.market.dao.OrderDAO;
import com.epam.market.model.Customer;
import com.epam.market.model.Order;
import com.epam.market.database.ConnectionPool;
import com.epam.market.model.Product;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String QUERY_ADD_ORDER = "INSERT INTO `orders`( `customer_id`,`product_id`,`order_date`,`order_time`) VALUES( ?,?,?,?)";
    private static final String QUERY_SELECT_ORDER = "select order_id, orders.customer_id ,login,  orders.product_id, price, order_date, order_time from orders " +
            "inner join customer on orders.customer_id = customer.customer_id " +
            "inner join product on orders.product_id = product.product_id";
    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);

    @Override
    public void add(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD_ORDER)) {
         addOrderStatement(statement, order);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error adding an order to database",e);
        }
    }

   private void addOrderStatement(PreparedStatement statement, Order order) throws SQLException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       String date = dateFormat.format(new Date());
       SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
       String time = currentTime.format(new Date());
       statement.setInt(1,order.getCustomerId());
       statement.setInt(2, order.getProductId());
       statement.setString(3, date);
       statement.setString(4,time);
       statement.execute();
   }

    @Override
    public List getAllOrders() {
        List<Order> listOfOrders = new ArrayList();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ORDER)) {
            ResultSet resultSet = statement.executeQuery();
            listOfOrders = getAllOrdersResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error getting all orders from database",e);
        }
        return listOfOrders;
    }

  private List getAllOrdersResultSet(ResultSet resultSet) throws SQLException {
      List<Order> listOfOrders = new ArrayList();
      while (resultSet.next()) {
          int orderId = resultSet.getInt("order_id");
          int customerId = resultSet.getInt("customer_id");
          String login = resultSet.getString("login");
          int productId = resultSet.getInt("product_id");
          double price = resultSet.getDouble("price");
          String orderDate = resultSet.getString("order_date");
          String orderTime = resultSet.getString("order_time");
          Customer customer  = new Customer().setCustomerId(customerId).setLogin(login);
          Product product = new Product().setProductId(productId).setPrice(price);
          listOfOrders.add(new Order()
                  .setOrderId(orderId)
                  .setCustomer(customer)
                  .setProduct(product)
                  .setOrderDate(orderDate)
                  .setOrderTime(orderTime));
      }
      return listOfOrders;
    }
}
