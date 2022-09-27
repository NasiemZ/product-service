package com.example.produktmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PokemonCardResponse implements Serializable {
    private long id;
    private String name;
    private String description;
    private String hp;
    private String type;
    private String stage;
    private String expansion;
    private String rarity;
    private String number;
    private String illustrator;
    private BigDecimal price;

}
