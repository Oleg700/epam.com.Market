package com.epam.market.command;
import com.epam.market.propertymenager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PageCommand implements  ActionCommand{
    private static final String PARAM_NAME_PATH_JSP= "path";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";
    @Override
    public String execute(HttpServletRequest request){
        String path = request.getParameter(PARAM_NAME_PATH_JSP);
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE,path);
        return ConfigurationManager.getProperty(path);
    }
}
