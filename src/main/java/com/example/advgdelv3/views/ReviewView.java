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
            //reviewTitle.getStyle().set("color", "red");
            //reviewTitle.getStyle().set("font-weight", "Bold");
            Paragraph reviewText = new Paragraph(review.getRevText());
            H5 reviewPlus = new H5("Plus: " + review.getRevPlus());
            H5 reviewMinus = new H5("Minus: " + review.getRevMinus());
            H2 reviewScore = new H2("Betyg: " + review.getRevScore() + " / 5");


            Paragraph writtenBY = new Paragraph(review.getRevGame().getGameTitle() + " recenserades av: " );
            Span author = new Span(review.getAppUser().getUsername());
            author.getStyle().set("font-weight", "bold");
            writtenBY.add(author);

            add(reviewTitle, reviewText, reviewPlus, reviewMinus, reviewScore, writtenBY, new Hr());


        });
    }

}
