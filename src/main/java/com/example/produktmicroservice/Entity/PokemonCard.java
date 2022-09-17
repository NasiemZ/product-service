package com.example.produktmicroservice.Entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "pokemon")
@JsonPropertyOrder({"id", "name", "description", "hp", "type", "stage", "expansion", "rarity", "number", "illustrator", "price"})
//ID,Name,Description,HP,Type,Stage,Expansion,Rarity,Number,Illustrator,Price
public class PokemonCard {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private String hp;

    @Column
    private String type;

    @Column
    private String stage;

    @Column
    private String expansion;

    @Column
    private String rarity;

    @Column
    private String number;

    @Column
    private String illustrator;

    @Column
    private String price;
}
