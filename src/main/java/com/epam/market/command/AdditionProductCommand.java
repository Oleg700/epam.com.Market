package com.epam.market.command;

import com.epam.market.daoimplementation.ProductDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import com.epam.market.model.Product;
import com.epam.market.model.ProductTranslation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdditionProductCommand implements ActionCommand{
        private static final String PARAM_NAME_TITLE_EN = "titleEn";
        private static final String PARAM_NAME_TITLE_RU = "titleRu";
        private static final String PARAM_NAME_PRODUCT_TITLE = "productTitle";
        private static final String PARAM_NAME_DESCRIPTION_EN = "descriptionEn";
        private static final String PARAM_NAME_DESCRIPTION_RU = "descriptionRu";
        private static final String PARAM_NAME_PRICE= "price";
        private static final String PARAM_NAME_CATEGORY_ID= "categoryId";
        private static final String PARAM_NAME_PRODUCER_ID= "producerId";
        private static final int LANGUAGE_ID_EN= 1;
        private static final int LANGUAGE_ID_RU= 2;
        private static final String PARAM_NAME_PATH_JSP= "path.page.addProduct";
  private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
        @Override
        public String execute(HttpServletRequest request) {
            ProductDAOImpl daoProduct  = new ProductDAOImpl();
            Product product= new Product();
            ProductTranslation productsTranslationEn = new ProductTranslation();
            ProductTranslation productsTranslationRu = new ProductTranslation();
            double price = Double.parseDouble((request.getParameter(PARAM_NAME_PRICE)));
            int categoryId = Integer.parseInt(request.getParameter(PARAM_NAME_CATEGORY_ID));
            int producerId= Integer.parseInt(request.getParameter(PARAM_NAME_PRODUCER_ID));
            String productTitleEn = request.getParameter(PARAM_NAME_TITLE_EN);
            String descriptionEn = request.getParameter(PARAM_NAME_DESCRIPTION_EN);
            String productTitleRu = request.getParameter(PARAM_NAME_TITLE_RU);
            String descriptionRu = request.getParameter(PARAM_NAME_DESCRIPTION_RU);
            product
                    .setPrice(price)
                    .setCategoryId(categoryId)
                    .setProducerId(producerId);
            productsTranslationEn
                    .setProductTitle(productTitleEn)
                    .setDescription(descriptionEn)
                    .setLanguageId(LANGUAGE_ID_EN);
            productsTranslationRu
                    .setProductTitle(productTitleRu)
                    .setDescription(descriptionRu)
                    .setLanguageId(LANGUAGE_ID_RU);
            daoProduct.addProductTranslate(product,productsTranslationEn, productsTranslationRu);
            request.setAttribute(PARAM_NAME_PRODUCT_TITLE, productTitleEn);
            final HttpSession session = request.getSession();
            session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PARAM_NAME_PATH_JSP );
            return ConfigurationManager.getProperty(PARAM_NAME_PATH_JSP);
        }
    }

