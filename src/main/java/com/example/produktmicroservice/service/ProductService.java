package com.example.produktmicroservice.service;

import com.example.produktmicroservice.dto.*;
import com.example.produktmicroservice.entity.PokemonCard;
import com.example.produktmicroservice.entity.PokemonDeck;
import com.example.produktmicroservice.repository.PokemonDeckRepository;
import com.example.produktmicroservice.repository.PokemonCardRepository;

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
import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private PokemonCardRepository cardRepository;
    @Autowired
    private PokemonDeckRepository cardDeckRepository;

    @Autowired
    private PriceService priceService;

    public List<PokemonCard> getPokemonCardList() {
        return cardRepository.findAll();
    }

    public List<PokemonDeck> getPokemonDeckList() {
        return cardDeckRepository.findAll();
    }

    public PokemonDeckResponse createPokemonDeck(PokemonDeckRequest deckRequest) {
        List<PokemonCardRequest> cardRequests = deckRequest.getPokemonCardList();

        saveDeckToDatabase(deckRequest.getName(), cardRequests);

        List<PokemonCardResponse> cardResponses = getPokemonResponseList(cardRequests);

        PriceResponse priceResponse = priceService.getPrice(cardResponses);

        return new PokemonDeckResponse()
                .setName(deckRequest.getName())
                .setTotalPrice(priceResponse.getTotalPrice())
                .setPokemonCards(cardResponses);
    }

    private List<PokemonCardResponse> getPokemonResponseList(List<PokemonCardRequest> pokemonCardRequests) {
        List<Long> ids = pokemonCardRequests.stream().map(PokemonCardRequest::getId).toList();

        var selectedCards = cardRepository.findAllById(ids);
        return selectedCards.stream().map(this::getCardResponse).toList();
    }

    private PokemonCardResponse getCardResponse(PokemonCard pokemonCard) {
        PokemonCardResponse response = new PokemonCardResponse()
                .setId(pokemonCard.getId())
                .setName(pokemonCard.getName())
                .setDescription(pokemonCard.getDescription())
                .setHp(pokemonCard.getHp())
                .setType(pokemonCard.getType())
                .setStage(pokemonCard.getStage())
                .setExpansion(pokemonCard.getExpansion())
                .setRarity(pokemonCard.getRarity())
                .setNumber(pokemonCard.getNumber())
                .setIllustrator(pokemonCard.getIllustrator())
                .setPrice(pokemonCard.getPrice());

        return response;
    }

    private void saveDeckToDatabase(String name, List<PokemonCardRequest> cardRequests) {
        List<Long> ids = cardRequests.stream().map(PokemonCardRequest::getId).toList();
        var cardList = cardRepository.findAllById(ids);

        long newId = UUID.randomUUID().getMostSignificantBits();

        PokemonDeck deckToSave = new PokemonDeck()
                .setId(newId)
                .setName(name)
                .setPokemonCardList(cardList);

        cardDeckRepository.save(deckToSave);
    }
}
