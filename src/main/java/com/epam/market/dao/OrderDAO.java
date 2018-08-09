package com.epam.market.dao;
import com.epam.market.model.Order;
import java.util.List;

public interface OrderDAO extends ItemDAO<Order>{
    List getAllOrders();




}
