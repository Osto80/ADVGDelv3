package com.example.advgdelv3.views;

import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class AppView extends AppLayout {

    public AppView(){
        HorizontalLayout navbarLayout = new HorizontalLayout();

        navbarLayout.add(new DrawerToggle(),new H1("AD Game Reviews "));


        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());

        if(PrincipalUtil.isLoggedIn()){
            navbarLayout.add(logoutButton);
        } else {
            navbarLayout.add(loginButton);
        }

        navbarLayout.setWidthFull();
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setMargin(true);

        addToNavbar(navbarLayout);

        RouterLink blogViewLink = new RouterLink("Läs Recensioner", ReviewView.class);
        VerticalLayout sideDrawerContent = new VerticalLayout(blogViewLink);

        RouterLink managePostLink = new RouterLink("Hantera Recensioner", ManageReviewsView.class);
        if (PrincipalUtil.isLoggedIn())sideDrawerContent.add(managePostLink);

        addToDrawer(sideDrawerContent);
    }

}
