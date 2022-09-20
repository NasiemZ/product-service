package com.example.produktmicroservice.consumer;

import com.example.produktmicroservice.service.ProductService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ProductConsumer {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${queue.product}")
    public String handleRequest(Message message) {
        APICall requestType = APICall.valueOf(message.getMessageProperties().getType());

        switch (requestType) {
            case GET_CARDS -> {
                return new Gson().toJson(productService.getPokemonCardList());
            }
            case GET_DECKS -> {
                return new Gson().toJson(productService.getPokemonCardDeckList());
            }
            default -> {
                return "Error: Product Service Request Handler";
            }
        }

    }
}
