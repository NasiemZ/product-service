package com.example.produktmicroservice.Service;

import com.example.produktmicroservice.Entity.PokemonCard;
import com.example.produktmicroservice.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonCardRepository cardRepository;

   public List<PokemonCard> getPokemonList() {
       return cardRepository.findAll();
   }
}
