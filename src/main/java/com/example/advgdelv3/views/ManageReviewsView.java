package com.example.advgdelv3.views;

import com.example.advgdelv3.components.ReviewForm;
import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.AppUserRepository;
import com.example.advgdelv3.security.PrincipalUtil;
import com.example.advgdelv3.service.GameService;
import com.example.advgdelv3.service.ReviewService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
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
    GameService gameService;

    public ManageReviewsView(ReviewService reviewService, AppUserRepository appUserRepository, GameService gameService) {
        this.reviewService = reviewService;
        this.appUserRepository = appUserRepository;
        this.reviewForm = new ReviewForm(reviewService, this, gameService);
        setAlignItems(Alignment.CENTER);
        add(new H2("Handle reviews by " + PrincipalUtil.getPrincipalName() + ": "));

        grid.setItems(reviewService.findPostByAuthorUserName(PrincipalUtil.getPrincipalName()));
        grid.setWidth(100f, Unit.PERCENTAGE);

        grid.addColumn(Review::getId).setHeader("Id").setSortable(true).setFlexGrow(0).setWidth("80px").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Review::getRevTitle).setHeader("Title").setSortable(true).setResizable(true);
        grid.addColumn(Review::getRevText).setHeader("Text");
        grid.addColumn(Review::getRevPlus).setHeader("Plus");
        grid.addColumn(Review::getRevMinus).setHeader("Minus");
        grid.addColumn(Review::getRevScore).setHeader("Betyg").setSortable(true).setFlexGrow(0).setWidth("100px").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(review -> review.getRevGame().getGameTitle()).setHeader("Spel").setSortable(true);
        grid.addComponentColumn(review -> {
            Button button = new Button(new Icon(VaadinIcon.TRASH), evt -> {
                reviewService.deleteById(review.getId());
                //Notification.show("Review \"" +review.getRevTitle() + "\" was deleted successfully.");
                updateItems();
            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        }).setHeader("Delete Review")
                .setFlexGrow(0)
                .setWidth("150px")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.asSingleSelect().addValueChangeListener(evt -> {
            reviewForm.setReview(evt.getValue());
        });



        HorizontalLayout mainReviewContent = new HorizontalLayout(grid, reviewForm);
        mainReviewContent.setSizeFull();
        mainReviewContent.setWidth(90f, Unit.PERCENTAGE);

        Button newReviewButton = new Button("Add new review", evt -> {
            Dialog dialog = new Dialog();
            ReviewForm dialogAddReviewForm = new ReviewForm(reviewService, this, gameService);

            Review newReview = new Review();
            AppUser currentUser = appUserRepository
                    .findByUsername(PrincipalUtil.getPrincipalName())
                    .orElseThrow();

            newReview.setAppUser(currentUser);
            dialogAddReviewForm.setReview(newReview);

            dialog.add(dialogAddReviewForm);
            dialog.open();
        });

        newReviewButton.setIcon(new Icon(VaadinIcon.DATABASE));
        newReviewButton.setIconAfterText(true);

        add(mainReviewContent, newReviewButton);

    }

    public void updateItems(){
        grid.setItems(
                reviewService
                        .findPostByAuthorUserName(PrincipalUtil.getPrincipalName()));
    }
}

