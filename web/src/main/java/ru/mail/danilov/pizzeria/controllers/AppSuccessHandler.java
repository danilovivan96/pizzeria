package ru.mail.danilov.pizzeria.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component("appSuccessHandler")
public class AppSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final Logger logger = LogManager.getLogger(AppSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) throws IOException {
        String targetUrl = determinateTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to {}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determinateTargetUrl(Authentication authentication) {
        boolean isCustomer = false;
        boolean isSecurityUser = false;
        boolean isSaleUser = false;
        boolean isAPIUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        label:
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "SECURITY_USER_PERMISSION":
                    isSecurityUser = true;
                    break label;
                case "SALE_USER_PERMISSION":
                    isSaleUser = true;
                    break label;
                case "CUSTOMER_PERMISSION":
                    isCustomer = true;
                    break label;
                case "API_USER_PERMISSION":
                    isAPIUser = true;
                    break label;
            }
        }
        if (isSecurityUser) {
            return "/users";
        } else if (isCustomer) {
            return "/items";
        } else if (isSaleUser) {
            return "/orders";
        } else if (isAPIUser) {
            return "/api/items";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
