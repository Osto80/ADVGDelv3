package com.example.advgdelv3.service;

import com.example.advgdelv3.entities.Game;
import com.example.advgdelv3.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public List<Game> findAllGames(){
        return gameRepository.findAll();
    }

    public Game findGameById(int id){
        return gameRepository.findById(id).orElseThrow();
    }

    public void deleteGameById(int id){
        gameRepository.deleteById(id);
    }

    public Game save(Game game){
        return gameRepository.save(game);
    }

    public Game updateById(int id, Game changedGame){
        Game originalGame = gameRepository.findById(id).orElseThrow();

        if(changedGame.getGameTitle() != null)
            originalGame.setGameTitle(changedGame.getGameTitle());
        if(changedGame.getGameDeveloper() != null)
            originalGame.setGameDeveloper(changedGame.getGameDeveloper());

        if(changedGame.getGameReleaseYear() < 1975)
            originalGame.setGameReleaseYear(1975);

        gameRepository.save(originalGame);

        return originalGame;

    }

}
