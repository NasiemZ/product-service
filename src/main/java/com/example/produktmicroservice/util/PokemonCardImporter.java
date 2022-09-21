package com.example.produktmicroservice.util;

import com.example.produktmicroservice.entity.PokemonCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
public class PokemonCardImporter {
    public List<PokemonCard> GetListonAllPokemonCards(WebClient client){
        Mono<List<PokemonCard>> response = client.get().uri("pokemon-cards")
                .accept(MediaType.ALL).retrieve().bodyToMono(new ParameterizedTypeReference<>() {});
        return response.block();
    }
}
