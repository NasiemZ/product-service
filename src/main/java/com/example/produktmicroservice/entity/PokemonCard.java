package com.example.produktmicroservice.entity;


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
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "pokemon")
@JsonPropertyOrder({"id", "name", "description", "hp", "type", "stage", "expansion", "rarity", "number", "illustrator", "price"})
public class PokemonCard implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

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
    private BigDecimal price;
}
