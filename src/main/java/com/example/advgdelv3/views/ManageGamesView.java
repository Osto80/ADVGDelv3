package com.example.advgdelv3.views;

import com.example.advgdelv3.components.GameForm;
import com.example.advgdelv3.components.ReviewForm;
import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.entities.Game;
import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.AppUserRepository;
import com.example.advgdelv3.security.PrincipalUtil;
import com.example.advgdelv3.service.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@Route(value = "/managegames", layout = AppView.class)
@PermitAll
public class ManageGamesView extends VerticalLayout {

    Grid<Game> grid = new Grid<>(Game.class, false);
    GameService gameService;
    GameForm gameForm;
    AppUserRepository appUserRepository;

    public ManageGamesView(GameService gameService, AppUserRepository appUserRepository){
        this.gameService = gameService;
        this.appUserRepository = appUserRepository;
        this.gameForm = new GameForm(gameService, this);
        setAlignItems(Alignment.CENTER);
        add(new H2("Hantera spel"));

        grid.setItems(gameService.findAllGames());
        grid.setWidthFull();

        grid.addComponentColumn(game -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE_SMALL), evt -> {
                Notification.show("Post \"" +game.getGameTitle() + "\" was deleted successfully.");
                gameService.deleteGameById(game.getId());
                updateItems();
            });

            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });

        grid.addColumn(Game::getId).setHeader("Id").setSortable(true).setResizable(true);
        grid.addColumn(Game::getGameTitle).setHeader("Game title");
        grid.addColumn(Game::getGameDeveloper).setHeader("Developer");
        grid.addColumn(Game::getGameReleaseYear).setHeader("Release year");
        grid.asSingleSelect().addValueChangeListener(evt -> {
            gameForm.setGame(evt.getValue());
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid, gameForm);
        mainContent.setSizeFull();

        Button button = new Button("Add new game", evt -> {
            Dialog dialog = new Dialog();
            GameForm dialogForm = new GameForm(gameService, this);

            Game game = new Game();

            dialogForm.setGame(game);

            dialog.add(dialogForm);
            dialog.open();
        });

        add(mainContent, button);


    }
    public void updateItems(){
        grid.setItems(
                gameService
                        .findAllGames());
    }

}
