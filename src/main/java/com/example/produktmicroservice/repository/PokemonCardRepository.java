package com.example.produktmicroservice.repository;

import com.example.produktmicroservice.entity.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonCardRepository extends JpaRepository<PokemonCard,Integer> {
}
