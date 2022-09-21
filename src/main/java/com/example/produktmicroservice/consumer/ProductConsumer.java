package com.example.produktmicroservice.consumer;

import com.example.produktmicroservice.dto.PokemonDeckRequest;
import com.example.produktmicroservice.service.ProductService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ProductConsumer {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${queue.product}")
    public String handleRequest(Message message) {
        APICall requestType = APICall.valueOf(message.getMessageProperties().getType());

        APICall test = requestType;

        switch (requestType) {
            case GET_CARDS -> {
                return new Gson().toJson(productService.getPokemonCardList());
            }
            case GET_DECKS -> {
                return new Gson().toJson(productService.getPokemonDeckList());
            }
            case CREATE_DECK -> {
                var deckRequest = unpackRequest(message);
                return new Gson().toJson(productService.createPokemonDeck(deckRequest));
            }
            default -> {
                return "Error: Product Service Request Handler";
            }
        }
    }

    private PokemonDeckRequest unpackRequest(Message message) {
        PokemonDeckRequest request = new Gson().fromJson(new String(message.getBody(), StandardCharsets.UTF_8), PokemonDeckRequest.class);

        return request;
    }

    private String getBodyFrom(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

}
