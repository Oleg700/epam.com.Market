package com.epam.market.dao;
import com.epam.market.model.Product;
import com.epam.market.model.ProductTranslation;

public interface ProductDAO extends ItemDAO<Product> {
    int getLastProductId();
    void addProductTranslate(Product product, ProductTranslation productsTranslationEn, ProductTranslation productsTranslationRu );
}
