package com.example.advgdelv3.security;

import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class PrincipalUtil {

    public static String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean isLoggedIn(){
        return !SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName().equalsIgnoreCase("anonymousUser");
    }

    public static void logout(){
        new SecurityContextLogoutHandler()
                .logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

}