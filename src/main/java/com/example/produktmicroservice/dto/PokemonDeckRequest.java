package com.example.produktmicroservice.dto;

import com.example.produktmicroservice.entity.PokemonCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PokemonDeckRequest {
    private int id;
    private String name;
    private List<PokemonCardRequest> pokemonCardList;
}
