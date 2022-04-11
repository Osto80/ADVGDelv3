package com.example.advgdelv3.components;

import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.service.ReviewService;
import com.example.advgdelv3.views.ManageReviewsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationErrorHandler;

public class ReviewForm extends FormLayout {

    //TODO Detta är troligtvis ett fel, som måste åtgärdas! Lägg till rätt fält!
    // revGame, revTitle, RevText, RevPlus, RevMinus, RevScore

    //TODO 2 Bind if used other name for Textfield, TextAre etc than stated previously.
    // binder.bind(title, "MyNameInTheJavaObject");

    // Har troligtvis med Game klassen att göra. Hur vi konstruerar en Review. Kolla upp detta.

    TextField revTitle = new TextField("revTitle");
    TextArea revText = new TextArea("revText");
    TextField revPlus = new TextField("revPlus");
    TextField revMinus = new TextField("revMinus");
    TextField revScore = new TextField("revScore");
    TextField revGame = new TextField("revGame");
    Button saveButton = new Button("Save");

    Binder<Review> binder = new BeanValidationBinder<>(Review.class);
    ReviewService reviewService;
    ManageReviewsView manageReviewsView;

    public ReviewForm(ReviewService reviewService, ManageReviewsView manageReviewsView){
        this.reviewService = reviewService;
        this.manageReviewsView = manageReviewsView;
        binder.bindInstanceFields(this);
        setVisible(false);

        saveButton.addClickListener(evt -> handleSave());

        //Bind if used other name for Textfield, TextAre etc than stated previously.
        //binder.bind(title, "MyNameInTheJavaObject");

        add(revGame, revTitle, revText, revPlus, revMinus, revScore, saveButton);

    }

    private void handleSave(){
        Review review = binder.validate().getBinder().getBean();
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
