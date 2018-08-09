package com.epam.market.daoimplementation;

import com.epam.market.dao.ProductTranslationDAO;
import com.epam.market.database.ConnectionPool;
import com.epam.market.model.Producer;
import com.epam.market.model.Product;
import com.epam.market.model.ProductTranslation;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTranslationDAOImpl implements ProductTranslationDAO {

    private static final String QUERY_ADD_PRODUCT_TRANSLATION = "INSERT INTO `product_translation`(`product_title`,`description`, `language_id`,`product_id` ) VALUES( ?,?,?,?)";
    String QUERY_SELECT_PRODUCT_BY_LANGUAGE_CATEGORY = "SELECT  pt.product_title, pt.description,  price, producer_title, pt.product_id " +
            "from  product_translation pt " +
            "inner join language " +
            "on language.language_id = pt.language_id " +
            "inner join product " +
            "on product.product_id = pt.product_id " +
            "inner join producer " +
            "on producer.producer_id = product.producer_id " +
            "inner join category " +
            "on category.category_id = product.category_id " +
            "where category.category_id=? and pt.language_id=?";
    private static final Logger LOGGER = Logger.getLogger(ProductTranslationDAOImpl.class);

    @Override
    public void add(ProductTranslation productTranslation) {
        int productId  = new ProductDAOImpl().getLastProductId();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD_PRODUCT_TRANSLATION)) {
            statement.setString(1, productTranslation.getProductTitle());
            statement.setString(2, productTranslation.getDescription());
            statement.setInt(3,productTranslation.getLanguageId());
            statement.setInt(4,productId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class ProductTranslationDAOImpl method add(ProductTranslation productTranslation)");
        }
    }

    @Override
    public List<ProductTranslation> selectProductsByLangCateg(int category_id, int language_id){
        List<ProductTranslation> listOfTranslateProducts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_PRODUCT_BY_LANGUAGE_CATEGORY)) {
            statement.setInt(1,category_id);
            statement.setInt(2,language_id);
            ResultSet  resultSet = statement.executeQuery();
         listOfTranslateProducts =  selectProductsByLangCategResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("SQL exception in class ProductTranslationDAOImpl method selectProductsByLangCateg(int category_id, int language_id)");
        }
        return listOfTranslateProducts;
    }

    public List<ProductTranslation> selectProductsByLangCategResultSet(ResultSet resultSet) throws SQLException {
        List<ProductTranslation> listOfTranslateProducts = new ArrayList<>();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String productTitle = resultSet.getString("product_title");
            String description = resultSet.getString("description");
            Double price = resultSet.getDouble("price");
            String producerTitle = resultSet.getString("producer_title");
            Product product = new Product().setPrice(price);
            Producer producer = new Producer().setTitle(producerTitle);
            product.setProducer(producer);
            listOfTranslateProducts.add(new ProductTranslation()
                    .setProductId(productId)
                    .setProductTitle(productTitle)
                    .setDescription(description)
                    .setProduct(product));
        }
        return listOfTranslateProducts;
    }


}
