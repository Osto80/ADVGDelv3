package com.example.advgdelv3.repositories;

import com.example.advgdelv3.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    Optional<Game> findByGameTitle(String gameTitle);
}
