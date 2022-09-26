package com.example.produktmicroservice.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String catURI = "https://catfact.ninja/fact";

    public String getPokemonFact() {
        String returnedObject = restTemplate.getForObject(catURI, String.class);
        JsonObject jsonObject = new Gson().fromJson(returnedObject, JsonObject.class);
        String fact = String.valueOf(jsonObject.get("fact")).replaceAll("^\"|\"$", "");

        String pokemonifiedFact = fact.replaceAll("(?i)Cat", "Pokemon");
        return pokemonifiedFact;
    }
}
