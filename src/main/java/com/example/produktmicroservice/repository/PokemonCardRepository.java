package com.example.produktmicroservice.repository;

import com.example.produktmicroservice.entity.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public interface PokemonCardRepository extends JpaRepository<PokemonCard,Long> {
}
