package com.example.advgdelv3.views;

import com.example.advgdelv3.components.ReviewForm;
import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.AppUserRepository;
import com.example.advgdelv3.security.PrincipalUtil;
import com.example.advgdelv3.service.ReviewService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "/managereviews", layout = AppView.class)
@PermitAll
public class ManageReviewsView extends VerticalLayout {

    Grid<Review> grid = new Grid<>(Review.class, false);
    ReviewService reviewService;
    ReviewForm reviewForm;
    AppUserRepository appUserRepository;

    public ManageReviewsView(ReviewService reviewService, AppUserRepository appUserRepository) {
        this.reviewService = reviewService;
        this.appUserRepository = appUserRepository;
        this.reviewForm = new ReviewForm(reviewService, this);
        setAlignItems(Alignment.CENTER);
        add(new H2("Hantera inlägg av " + PrincipalUtil.getPrincipalName() + ": "));

        grid.setItems(reviewService.findPostByAuthorUserName(PrincipalUtil.getPrincipalName()));
        grid.setWidthFull();

        grid.addComponentColumn(review -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE_SMALL), evt -> {
                Notification.show("Post \"" +review.getRevTitle() + "\" was deleted successfully.");
                reviewService.deleteById(review.getId());
                updateItems();
            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });

        grid.addColumn(Review::getId).setHeader("Id").setSortable(true).setResizable(true);
        grid.addColumn(Review::getRevTitle).setHeader("Title");
        grid.addColumn(Review::getRevText).setHeader("Text");
        grid.addColumn(Review::getRevPlus).setHeader("Plus");
        grid.addColumn(Review::getRevMinus).setHeader("Minus");
        grid.addColumn(Review::getRevScore).setHeader("Betyg");
        grid.addColumn(review -> review.getRevGame().getGameTitle()).setHeader("Spel");
        grid.asSingleSelect().addValueChangeListener(evt -> {
            reviewForm.setReview(evt.getValue());
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid, reviewForm);
        mainContent.setSizeFull();

        Button button = new Button("Add new post", evt -> {
            Dialog dialog = new Dialog();
            ReviewForm dialogForm = new ReviewForm(reviewService, this);

            Review review = new Review();
            AppUser currentUser = appUserRepository
                    .findByUsername(PrincipalUtil.getPrincipalName())
                    .orElseThrow();

            review.setAppUser(currentUser);

            dialogForm.setReview(review);

            dialog.add(dialogForm);
            dialog.open();
        });

        add(mainContent, button);

    }

    public void updateItems(){
        grid.setItems(
                reviewService
                        .findPostByAuthorUserName(PrincipalUtil.getPrincipalName()));
    }
}

