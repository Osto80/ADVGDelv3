package com.example.advgdelv3.repositories;

import com.example.advgdelv3.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByAppUser_Username(String username);

}
