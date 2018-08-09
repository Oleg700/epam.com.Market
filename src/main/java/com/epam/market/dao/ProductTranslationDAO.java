package com.epam.market.dao;
import com.epam.market.model.ProductTranslation;
import java.util.List;

public interface ProductTranslationDAO extends ItemDAO<ProductTranslation> {
    List<ProductTranslation> selectProductsByLangCateg(int category_id, int language_id);

}
