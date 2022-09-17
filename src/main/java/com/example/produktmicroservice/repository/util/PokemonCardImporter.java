package com.example.produktmicroservice.repository.util;

import com.example.produktmicroservice.Entity.PokemonCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
public class PokemonCardImporter {
    public List<PokemonCard> GetListonAllPokemonCards(WebClient client){
        log.info("yes");
        Mono<List<PokemonCard>> response = client.get().uri("pokemon-card")
                .accept(MediaType.ALL).retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
        log.info("yes2");
        return response.block();
    }
}
