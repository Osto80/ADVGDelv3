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

public class ReviewForm extends FormLayout {

    TextField title = new TextField("Title");
    TextArea message = new TextArea("Message");
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

        add(title, message, saveButton);

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
