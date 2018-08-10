package com.epam.market.daoimplementation;

import com.epam.market.dao.ProductDAO;
import com.epam.market.model.Product;
import com.epam.market.database.ConnectionPool;
import com.epam.market.model.ProductTranslation;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    private static final String QUERY_ADD_PRODUCT = "INSERT INTO `product`(`price`,`category_id`, producer_id) VALUES( ?,?,?)";
    private static final String QUERY_GET_lAST_PRODUCT = "SELECT product_id FROM product ORDER BY product_id DESC LIMIT 1;";
    private static final Logger LOGGER = Logger.getLogger(ProductDAOImpl.class);
    @Override
    public void addProductTranslate(Product product, ProductTranslation productsTranslationEn, ProductTranslation productsTranslationRu) {
        ProductTranslationDAOImpl productTranslationDAO = new ProductTranslationDAOImpl();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool()) {
            connection.setAutoCommit(false);
            add(product);
            productTranslationDAO.add(productsTranslationEn);
            productTranslationDAO.add(productsTranslationRu);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error adding a product to database",e);
        }
    }

    @Override
    public int getLastProductId() {
        int lastId = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_GET_lAST_PRODUCT)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lastId = resultSet.getInt("product_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error getting a a last porduct id from database",e);
        }
        return lastId;
    }

    @Override
    public void add(Product product) {
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
            PreparedStatement statement = connection.prepareStatement(QUERY_ADD_PRODUCT)) {
            statement.setDouble(1, product.getPrice());
            statement.setInt(2, product.getCategoryId());
            statement.setInt(3, product.getProducerId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Error adding a product to database",e);
        }
    }
}
