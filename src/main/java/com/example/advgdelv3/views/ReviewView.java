package com.example.advgdelv3.views;


import com.example.advgdelv3.service.ReviewService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import net.bytebuddy.TypeCache;

import java.util.Comparator;

@Route(value = "/", layout = AppView.class)
@AnonymousAllowed
public class ReviewView extends VerticalLayout {

    public ReviewView(ReviewService reviewService) {
        add (new H2("Recensioner: "));

        setAlignItems(Alignment.CENTER);

        reviewService
                .findAllByOrderByIdDesc()
                .forEach(review -> {

            H3 reviewTitle = new H3(review.getRevTitle());
            reviewTitle.getStyle().set("text-decoration", "underline");
            Paragraph reviewText = new Paragraph(review.getRevText());
            reviewText.getStyle().set("padding", "15px");
            reviewText.getStyle().set("background-color", "rgba(216, 171, 65, 0.2)");
            reviewText.getStyle().set("width", "350px");
            reviewText.getStyle().set("border-radius", "10px");
            reviewText.getStyle().set("min-height", "120px");
            reviewText.getStyle().set("text-align", "justify");

            H5 reviewPlus = new H5("Plus: " + review.getRevPlus());
            H5 reviewMinus = new H5("Minus: " + review.getRevMinus());
            H2 reviewScore = new H2("Betyg: " + review.getRevScore() + " / 5");


            Paragraph reviewBy = new Paragraph(review.getRevGame().getGameTitle() + " recenserades av: " );
            Span author = new Span(review.getAppUser().getUsername());
            author.getStyle().set("font-weight", "bold");
            reviewBy.add(author);

            Hr hrEndReview = new Hr();


            add(reviewTitle, reviewText, reviewPlus, reviewMinus, reviewScore, reviewBy, hrEndReview);


        });
    }

}
