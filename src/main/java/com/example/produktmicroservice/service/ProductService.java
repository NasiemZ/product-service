package com.example.produktmicroservice.service;

import com.example.produktmicroservice.dto.*;
import com.example.produktmicroservice.entity.PokemonCard;
import com.example.produktmicroservice.entity.PokemonDeck;
import com.example.produktmicroservice.repository.PokemonDeckRepository;
import com.example.produktmicroservice.repository.PokemonCardRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private PokemonCardRepository cardRepository;
    @Autowired
    private PokemonDeckRepository cardDeckRepository;
    @Autowired
    private PriceService priceService;

    public List<PokemonCardResponse> getPokemonCardList() {
        List<PokemonCard> cards = cardRepository.findAll();
        return cards.stream().map(this::getCardResponse).collect(Collectors.toList());
    }

    public List<PokemonDeckResponse> getPokemonDeckList() {
        List<PokemonDeck> pokemonDecks = cardDeckRepository.findAll();
        List<PokemonDeckResponse> deckResponses = pokemonDecks.stream().map(this::getDeckResponse).collect(Collectors.toList());

        return deckResponses;
    }

    public PokemonDeckResponse createPokemonDeck(PokemonDeckRequest deckRequest) {
        List<PokemonCardRequest> cardRequests = deckRequest.getPokemonCardList();

        saveDeckToDatabase(deckRequest.getName(), cardRequests);

        List<PokemonCardResponse> cardResponses = getPokemonResponseList(cardRequests);
        List<BigDecimal> priceList = cardResponses.stream().map(PokemonCardResponse::getPrice).toList();

        PriceResponse priceResponse = priceService.getPrice(priceList);

        return new PokemonDeckResponse()
                .setName(deckRequest.getName())
                .setTotalPrice(priceResponse.getTotalPrice())
                .setPokemonCardList(cardResponses);
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

    private PokemonDeckResponse getDeckResponse(PokemonDeck pokemonDeck) {
        List<BigDecimal> cardPrices = pokemonDeck.getPokemonCardList().stream().map(PokemonCard::getPrice).toList();
        PriceResponse priceResponse = priceService.getPrice(cardPrices);

        List<PokemonCardResponse> cardsOfDeck = pokemonDeck.getPokemonCardList().stream().map(this::getCardResponse).toList();

        PokemonDeckResponse deckResponse = new PokemonDeckResponse()
                .setId(pokemonDeck.getId())
                .setName(pokemonDeck.getName())
                .setPokemonCardList(cardsOfDeck)
                .setTotalPrice(priceResponse.getTotalPrice());

        return deckResponse;
    }

    private void saveDeckToDatabase(String name, List<PokemonCardRequest> cardRequests) {
        List<Long> ids = cardRequests.stream().map(PokemonCardRequest::getId).toList();
        List<PokemonCard> cardList = cardRepository.findAllById(ids);

        long newId = UUID.randomUUID().getMostSignificantBits();

        PokemonDeck deckToSave = new PokemonDeck()
                .setId(newId)
                .setName(name)
                .setPokemonCardList(cardList);

        cardDeckRepository.save(deckToSave);
    }
}
