package com.example.produktmicroservice.service;

import com.example.produktmicroservice.repository.jpa.PokemonCard;
import com.example.produktmicroservice.repository.jpa.PokemonCardDeck;
import com.example.produktmicroservice.repository.PokemonCardDeckRepository;
import com.example.produktmicroservice.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private PokemonCardRepository cardRepository;

    @Autowired
    private PokemonCardDeckRepository cardDeckRepository;

   public List<PokemonCard> getPokemonCardList() {
       return cardRepository.findAll();
   }

   public List<PokemonCardDeck> getPokemonCardDeckList() {
       return cardDeckRepository.findAll();
   }
}
