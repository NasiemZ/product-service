package com.example.produktmicroservice.service;

import com.example.produktmicroservice.dto.PriceRequest;
import com.example.produktmicroservice.dto.PriceResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
public class PriceService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;
    @Value("${routing-key.price}")
    private String priceKey;

    protected PriceResponse getPrice(List<BigDecimal> priceList) {
        PriceRequest priceRequest = new PriceRequest().setPriceList(priceList);

        Message requestMessage = new Message((new Gson().toJson(priceRequest)).getBytes());
        Message returnMessage = rabbitTemplate.sendAndReceive(directExchange.getName(), priceKey, requestMessage);

        if (returnMessage == null) {
            log.info("No Price Response");
            return new PriceResponse().setPrice(BigDecimal.ZERO);
        }

        return new Gson().fromJson(new String(returnMessage.getBody(), StandardCharsets.UTF_8), PriceResponse.class);
    }
}
