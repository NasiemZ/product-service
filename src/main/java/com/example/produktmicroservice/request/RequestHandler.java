package com.example.produktmicroservice.request;

import com.example.produktmicroservice.Service.PokemonService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RequestHandler {

    @Autowired
    private PokemonService pokemonService;

    @RabbitListener(queues = "${queue.product}")
    public String getCards() {
        return new Gson().toJson(pokemonService.getPokemonList());
    }
}
