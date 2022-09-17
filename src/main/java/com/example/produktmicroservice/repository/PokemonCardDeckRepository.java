package com.example.produktmicroservice.repository;

import com.example.produktmicroservice.Entity.PokemonCardDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonCardDeckRepository extends JpaRepository<PokemonCardDeck,Integer> {
}
