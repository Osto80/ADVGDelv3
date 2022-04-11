package com.example.advgdelv3.components;

import com.example.advgdelv3.entities.Game;
import com.example.advgdelv3.service.GameService;
import com.example.advgdelv3.views.ManageGamesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class GameForm extends FormLayout {

    TextField gameTitle = new TextField("gameTitle");
    TextField gameDeveloper = new TextField("gameDeveloper");
    IntegerField gameReleaseYear = new IntegerField("gameReleaseYear");
    Button saveButton = new Button("Add game");

    Binder<Game> binder = new BeanValidationBinder<>(Game.class);
    GameService gameService;
    ManageGamesView manageGamesView;

    public GameForm(GameService gameService, ManageGamesView manageGamesView){
        this.gameService = gameService;
        this.manageGamesView = manageGamesView;
        binder.bindInstanceFields(this);
        setVisible(false);

        saveButton.addClickListener(evt -> handleSave());

        add(gameTitle, gameDeveloper, gameReleaseYear, saveButton);
    }

    private void handleSave(){
        Game game = binder.validate().getBinder().getBean();
        if(game.getId() == 0) {
            gameService.save(game);
        }else {
            gameService.updateById(game.getId(), game);
        }
        // Glöm inte att skapa
        setGame(null);
        manageGamesView.updateItems();

        this.getParent().ifPresent(component -> {
            if(component instanceof Dialog){
                ((Dialog)component).close();
            }
        });
    }

    public void setGame(Game game){
        if(game != null){
            binder.setBean(game);
            setVisible(true);
        }else{
            setVisible(false);
        }
    }


}
