package com.example.advgdelv3;

import com.example.advgdelv3.entities.AppUser;
import com.example.advgdelv3.entities.Game;
import com.example.advgdelv3.entities.Review;
import com.example.advgdelv3.repositories.AppUserRepository;
import com.example.advgdelv3.repositories.GameRepository;
import com.example.advgdelv3.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public class AdvgDelv3Application {

    public static void main(String[] args) {SpringApplication.run(AdvgDelv3Application.class, args);}


    @Bean
    CommandLineRunner init(AppUserRepository appUserRepository, ReviewRepository reviewRepository, GameRepository gameRepository){
        return args -> {
            // ##########################
            // Run first time for content
            // Then comment out
            // ##########################
            /*AppUser appUser = new AppUser("Daniel", "pass1");
            AppUser appUser1 = new AppUser("Arvid", "pass2");
            appUserRepository.saveAll(List.of(appUser, appUser1));

            Game game1 = new Game("Elden Ring", "FROM Software", 2022);
            Game game2 = new Game("Super Mario 64", "Nintendo", 1996);
            Game game3 = new Game("Valheim", "Iron Gate AB", 2021);
            gameRepository.saveAll(List.of(game1, game2, game3));

            Review review1 = new Review(gameRepository.getById(1), "Elden Ring Recension", "Ett enormt äventyr som inte vågar slå en på fingrarna när man gapar efter för mycket", "Stor öppen värld att utforska", "Otydligt vid tillfällen", 5, appUser);
            Review review2 = new Review(gameRepository.getById(2), "Super Mario 64 Recension", "Marios första steg på Z axeln är fantastiskt kul", "Kul att springa omkring", "Kameran hänger inte alltid med", 5, appUser1);
            reviewRepository.saveAll(List.of(review1, review2));*/
        };

    }

}
