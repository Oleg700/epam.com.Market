package com.epam.market.command;

import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements ActionCommand  {
    private static final String PARAM_NAME_LANGUAGE = "language";
    private static final String PARAM_NAME_LOCALE = "languageLocale";
    private static final String PARAM_NAME_LANGUAGE_ID = "languageId";
    private static final String PATH_PRODUCT = "path.page.products";
    private static final String PATH_CUSTOMER =  "path.page.customer";
    private static final String PATH_ORDER = "path.page.order";
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        String setLanguage = request.getParameter(PARAM_NAME_LANGUAGE);
        String setLanguageId = request.getParameter(PARAM_NAME_LANGUAGE_ID);
        String setLocale = request.getParameter(PARAM_NAME_LOCALE);
        session.setAttribute(PARAM_NAME_LANGUAGE_ID,setLanguageId);
        session.setAttribute(PARAM_NAME_LANGUAGE,setLanguage);
        session.setAttribute(PARAM_NAME_LOCALE,setLocale);
        String currentPage = (String) session.getAttribute("currentPage");
        setCommandByPath(request, currentPage);
        return ConfigurationManager.getProperty(currentPage);
    }

    public  void setCommandByPath(HttpServletRequest request,String currentPage){
        final HttpSession session = request.getSession();
        switch (currentPage) {
            case PATH_PRODUCT:
                ProductCommand productCommand = new ProductCommand();
                int categoryId = (int) session.getAttribute("categoryId");
                productCommand.selectProducts(request, categoryId);
                break;
            case PATH_CUSTOMER:
                CustomerCommand customerCommand = new CustomerCommand();
                customerCommand.execute(request);
                break;
            case PATH_ORDER:
              OrderCommand orderCommand = new OrderCommand();
              orderCommand.execute(request);
        }
    }

    }



