package com.example.advgdelv3.views;

import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@CssImport("./themes/ad-theme/styles.css")
@CssImport(value = "./themes/ad-theme/components/vaadin-navbar-layout.css", themeFor = "vaadin-app-layout")

public class AppView extends AppLayout {

    public AppView(){
        HorizontalLayout navbarLayout = new HorizontalLayout();

        Icon gamepadIconNavbar = new Icon(VaadinIcon.GAMEPAD);
        gamepadIconNavbar.setSize("50px");
        Icon gamepadIconNavbar2 = new Icon(VaadinIcon.GAMEPAD);
        gamepadIconNavbar2.setSize("50px");

        navbarLayout.add(new DrawerToggle(), gamepadIconNavbar, new H1("AD Game Reviews"), gamepadIconNavbar2);


        Button loginButton = new Button("Login", evt -> UI.getCurrent().navigate(LoginView.class));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

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

        RouterLink reviewViewLink = new RouterLink("L??s Recensioner", ReviewView.class);
        VerticalLayout sideDrawerContent = new VerticalLayout(reviewViewLink);

        RouterLink manageReviewLink = new RouterLink("Hantera Recensioner", ManageReviewsView.class);
        if (PrincipalUtil.isLoggedIn())sideDrawerContent.add(manageReviewLink);

        RouterLink manageGamesLink = new RouterLink("Hantera Spel", ManageGamesView.class);
        if (PrincipalUtil.isLoggedIn())sideDrawerContent.add(manageGamesLink);

        sideDrawerContent.setAlignItems(FlexComponent.Alignment.CENTER);

        addToDrawer(sideDrawerContent);
    }

}
