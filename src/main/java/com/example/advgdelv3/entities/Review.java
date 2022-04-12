package com.example.advgdelv3.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String revTitle;

    @Column
    @NotBlank
    private String revText;

    @Column
    private String revPlus;

    @Column
    private String revMinus;

    @Column
    private int revScore;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game revGame;

    public AppUser getAppUser() {
        return appUser;
    }

    public Review(Game revGame, String revTitle, String revText, String revPlus, String revMinus, int revScore, AppUser appUser) {
        this.revGame = revGame;
        this.revTitle = revTitle;
        this.revText = revText;
        this.revPlus = revPlus;
        this.revMinus = revMinus;
        this.revScore = revScore;
        this.appUser = appUser;
    }

    public Review(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevTitle() {
        return revTitle;
    }

    public void setRevTitle(String revTitle) {
        this.revTitle = revTitle;
    }

    public String getRevText() {
        return revText;
    }

    public void setRevText(String revText) {
        this.revText = revText;
    }

    public String getRevPlus() {
        return revPlus;
    }

    public void setRevPlus(String revPlus) {
        this.revPlus = revPlus;
    }

    public String getRevMinus() {
        return revMinus;
    }

    public void setRevMinus(String revMinus) {
        this.revMinus = revMinus;
    }

    public int getRevScore() {
        return revScore;
    }

    public void setRevScore(int revScore) {
        this.revScore = revScore;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Game getRevGame() {
        return revGame;
    }

    public void setRevGame(Game revGame) {
        this.revGame = revGame;
    }
}
