package com.example.advgdelv3.service;

import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public List<Review> findAllByOrderByIdDesc(){
        return reviewRepository.findAllByOrderByIdDesc();
    }

    public Review findReviewById(int id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        reviewRepository.deleteById(id);
    }

    public Review save(Review review){
        return reviewRepository.save(review);
    }

    public Review updateById(int id, Review changedReview){
        Review originalReview = reviewRepository.findById(id).orElseThrow();

        if(changedReview.getRevTitle() != null)
            originalReview.setRevTitle(changedReview.getRevTitle());
        if(changedReview.getRevText() != null)
            originalReview.setRevText(changedReview.getRevText());
        if(changedReview.getRevPlus() != null)
            originalReview.setRevPlus(changedReview.getRevPlus());
        if(changedReview.getRevMinus() != null)
            originalReview.setRevMinus(changedReview.getRevMinus());
        if(changedReview.getRevGame() != null)
            originalReview.setRevGame(changedReview.getRevGame());

        // Checks if user score is above or under min-max allowed, and resets to allowed values.
        if(changedReview.getRevScore() < 1) changedReview.setRevScore(1);
        if(changedReview.getRevScore() > 5) changedReview.setRevScore(5);
        originalReview.setRevScore(changedReview.getRevScore());

        reviewRepository.save(originalReview);

        return originalReview;
    }

    public List<Review> findPostByAuthorUserName(String principalName) {
        return reviewRepository.findByAppUser_Username(principalName);
    }
}
