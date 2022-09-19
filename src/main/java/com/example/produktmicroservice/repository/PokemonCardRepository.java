package com.example.produktmicroservice.repository;

import com.example.produktmicroservice.repository.jpa.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonCardRepository extends JpaRepository<PokemonCard,Integer> {
}
