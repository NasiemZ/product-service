package com.example.produktmicroservice.repository;

import com.example.produktmicroservice.entity.PokemonDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonDeckRepository extends JpaRepository<PokemonDeck,Long> {
}
