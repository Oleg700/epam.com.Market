package com.epam.market.command;
import com.epam.market.daoimplementation.ProductTranslationDAOImpl;
import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProductCommand implements ActionCommand {
    private static final String PARAM_NAME_CATEGORY = "category";
    private static final String PARAM_NAME_CATEGORY_ID = "categoryId";
    private static final String PROPERTY_NAME_PATH = "path.page.products";
    private static final String ATTRIBUTE_NAME_LIST_PRODUCTS = "listOfProducts";
    private static final String PARAM_NAME_LANGUAGE_ID = "languageId";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    private static final String ATTRIBUTE_NAME_SELECT_PRODUCTS= "selectProducts";
    private static final String PARAM_NAME_COMMAND = "command";

    @Override
    public String execute(HttpServletRequest request) {
        int categoryId = Integer.parseInt(request.getParameter(PARAM_NAME_CATEGORY));
        final HttpSession session = request.getSession();
        session.setAttribute(PARAM_NAME_CATEGORY_ID,categoryId );
        String path =  selectProducts(request, categoryId);
        return path;
    }

    public String selectProducts(HttpServletRequest request, int categoryId){
        final HttpSession session = request.getSession();
        String languageId = (String)session.getAttribute(PARAM_NAME_LANGUAGE_ID);
        int lang = Integer.parseInt(languageId);
        ProductTranslationDAOImpl productTranslationDAO = new ProductTranslationDAOImpl();
        List listOfProducts =  productTranslationDAO.selectProductsByLangCateg(categoryId,lang);
        request.setAttribute(ATTRIBUTE_NAME_LIST_PRODUCTS,listOfProducts);
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,PROPERTY_NAME_PATH);
        request.setAttribute(PARAM_NAME_COMMAND, ATTRIBUTE_NAME_SELECT_PRODUCTS);
        return ConfigurationManager.getProperty(PROPERTY_NAME_PATH);
    }

}
