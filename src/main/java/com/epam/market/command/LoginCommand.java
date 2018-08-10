package com.epam.market.command;

import com.epam.market.daoimplementation.CustomerDAOImpl;
import com.epam.market.hash.PasswordHash;
import com.epam.market.logic.LoginLogic;
import com.epam.market.propertymenager.ConfigurationManager;
import com.epam.market.propertymenager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PROPERTY_PATH_LOGIN = "path.page.login";
    private static final String ATTRIBUTE_NAME_ACCESS_ERROR = "accessError";
    private static final String ATTRIBUTE_NAME_LOGIN_ERROR = "loginError";
    private static final String ATTRIBUTE_NAME_CURRENT_PAGE = "currentPage";

    @Override
    public String execute(HttpServletRequest request) {
        String path = getPathByLogin(request);
        return path;
    }

    private String getPathByLogin(HttpServletRequest request) {
        String path = null;
        LoginLogic loginLogic = new LoginLogic(new CustomerDAOImpl());
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        int hashPassword = PasswordHash.hashPassword(password);
        if (loginLogic.isLoginValid(login, hashPassword)) {
            if (!loginLogic.isAccessValid(login)) {
                request.setAttribute(ATTRIBUTE_NAME_ACCESS_ERROR, MessageManager.getProperty(ATTRIBUTE_NAME_ACCESS_ERROR));
                path = ConfigurationManager.getProperty(PROPERTY_PATH_LOGIN);
            } else {
                String pathByRole = loginLogic.isRoleValid(login);
                final HttpSession session = request.getSession();
                session.setAttribute(PARAM_NAME_LOGIN, login);
                session.setAttribute(PARAM_NAME_PASSWORD, hashPassword);
                path = ConfigurationManager.getProperty(pathByRole);
            }
        } else {
            request.setAttribute(ATTRIBUTE_NAME_LOGIN_ERROR, MessageManager.getProperty(ATTRIBUTE_NAME_LOGIN_ERROR));
            path = ConfigurationManager.getProperty(PROPERTY_PATH_LOGIN);
        }
        final HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_CURRENT_PAGE, PROPERTY_PATH_LOGIN);
        return path;
    }
}