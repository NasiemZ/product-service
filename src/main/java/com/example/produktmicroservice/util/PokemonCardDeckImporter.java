package com.example.produktmicroservice.util;


import com.example.produktmicroservice.entity.PokemonCardDeck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class PokemonCardDeckImporter {
    public List<PokemonCardDeck> GetListonAllPokemonCardDecks(WebClient client){
        log.info("yes");
        Mono<List<PokemonCardDeck>> response = client.get().uri("pokemon-deck")
                .accept(MediaType.ALL).retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
        log.info("yes2");
        return response.block();
    }
}
