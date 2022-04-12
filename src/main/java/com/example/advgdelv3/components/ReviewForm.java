package com.example.advgdelv3.components;

import com.example.advgdelv3.entities.Game;
import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.GameRepository;
import com.example.advgdelv3.service.GameService;
import com.example.advgdelv3.service.ReviewService;
import com.example.advgdelv3.views.ManageReviewsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class ReviewForm extends FormLayout {

    TextField revTitle = new TextField("Review Title");
    TextArea revText = new TextArea("Review Text");
    TextField revPlus = new TextField("Plus");
    TextField revMinus = new TextField("Minus");
    IntegerField revScore = new IntegerField("Score (1-5)");
    ComboBox<Game> revGame = new ComboBox<>("Game");
    Button saveButton = new Button("Save");

    Binder<Review> binder = new BeanValidationBinder<>(Review.class);
    ReviewService reviewService;
    ManageReviewsView manageReviewsView;
    GameService gameService;

    public ReviewForm(ReviewService reviewService, ManageReviewsView manageReviewsView, GameService gameService){
        this.reviewService = reviewService;
        this.manageReviewsView = manageReviewsView;
        this.gameService = gameService;
        revGame.setItems(gameService.findAllGames());
        revGame.setItemLabelGenerator(Game::getGameTitle);
        binder.bindInstanceFields(this);
        setVisible(false);

        saveButton.addClickListener(evt -> handleSave());

        add(revGame, revTitle, revText, revPlus, revMinus, revScore, saveButton);
    }

    private void handleSave(){
        Review review = binder.validate().getBinder().getBean();
        if(review.getRevScore() < 1) {
            review.setRevScore(1);
        } else if (review.getRevScore() > 5) {
            review.setRevScore(5);
        }
        if(review.getId() == 0){
            reviewService.save(review);
        } else {
            reviewService.updateById(review.getId(), review);
        }
        setReview(null);
        manageReviewsView.updateItems();

        this.getParent().ifPresent(component -> {
            if(component instanceof Dialog){
                ((Dialog)component).close();
            }
        });
    }

    public void setReview(Review review){
        if(review != null){
            binder.setBean(review);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }


}
