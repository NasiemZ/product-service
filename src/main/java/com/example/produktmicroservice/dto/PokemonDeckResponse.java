package com.example.produktmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PokemonDeckResponse {
    private long id;
    private String name;
    private List<PokemonCardResponse> pokemonCardList;
    private BigDecimal totalPrice;
}
