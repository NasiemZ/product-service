package com.example.produktmicroservice.repository.util;

import com.example.produktmicroservice.repository.jpa.PokemonCard;
import com.example.produktmicroservice.repository.jpa.PokemonCardDeck;
import com.example.produktmicroservice.repository.PokemonCardDeckRepository;
import com.example.produktmicroservice.repository.PokemonCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Configuration
public class CardLoader {


    private final WebClient webClient = WebClient.create("http://warehouse:8081/warehouse/");
    private final PokemonCardImporter pokemonCardImporter = new PokemonCardImporter();

    private final PokemonCardDeckImporter pokemonCardDeckImporter = new PokemonCardDeckImporter();

    @Bean
    CommandLineRunner commandLineRunner (PokemonCardRepository cardRepository, PokemonCardDeckRepository cardDeckRepository ){
        return args -> {
            try{
                List<PokemonCard> pokemonCardList = pokemonCardImporter.GetListonAllPokemonCards(webClient);
                cardRepository.saveAll(pokemonCardList);

                List<PokemonCardDeck> pokemonCardDeckList = pokemonCardDeckImporter.GetListonAllPokemonCardDecks(webClient);
                cardDeckRepository.saveAll(pokemonCardDeckList);
            }catch (DataIntegrityViolationException e){
                log.error("Database already exists"+ e);
            }
        };
    }

}
