package com.example.advgdelv3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String gameTitle;

    @Column
    @NotBlank
    private String gameDeveloper;

    @Column
    private int gameReleaseYear;

    @OneToMany(mappedBy = "revGame")
    @JsonIgnoreProperties("revGame")
    private Set<Review> gameReviewSet;



    public Game(String gameTitle, String gameDeveloper, int gameReleaseYear) {
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
        this.gameReleaseYear = gameReleaseYear;
    }

    public Game(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public int getGameReleaseYear() {
        return gameReleaseYear;
    }

    public void setGameReleaseYear(int gameReleaseYear) {
        this.gameReleaseYear = gameReleaseYear;
    }

    public Set<Review> getGameReviewSet() {
        return gameReviewSet;
    }

    public void setGameReviewSet(Set<Review> gameReviewSet) {
        this.gameReviewSet = gameReviewSet;
    }
}
