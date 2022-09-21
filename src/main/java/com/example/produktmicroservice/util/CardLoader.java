package com.example.produktmicroservice.util;

import com.example.produktmicroservice.entity.PokemonCard;
import com.example.produktmicroservice.entity.PokemonDeck;
import com.example.produktmicroservice.repository.PokemonDeckRepository;
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

    private final PokemonDeckImporter pokemonDeckImporter = new PokemonDeckImporter();

    @Bean
    CommandLineRunner commandLineRunner (PokemonCardRepository cardRepository, PokemonDeckRepository cardDeckRepository ){
        return args -> {
            try{
                List<PokemonCard> pokemonCardList = pokemonCardImporter.GetListonAllPokemonCards(webClient);
                cardRepository.saveAll(pokemonCardList);

                List<PokemonDeck> pokemonDeckList = pokemonDeckImporter.GetListonAllPokemonDecks(webClient);
                cardDeckRepository.saveAll(pokemonDeckList);
            }catch (DataIntegrityViolationException e){
                log.error("Database already exists"+ e);
            }
        };
    }

}
