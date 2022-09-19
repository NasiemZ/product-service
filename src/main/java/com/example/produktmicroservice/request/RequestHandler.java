package com.example.produktmicroservice.request;

import com.example.produktmicroservice.Service.ProductService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RequestHandler {

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
                String test = new Gson().toJson(productService.getPokemonCardDeckList());
                return test;
            }
            default -> {
                return "Error: Product Service Request Handler";
            }
        }


    }
}
