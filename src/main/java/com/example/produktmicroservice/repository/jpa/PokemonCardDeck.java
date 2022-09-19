package com.example.produktmicroservice.repository.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "pokemondeck")
public class PokemonCardDeck {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column
    @OneToMany
    @JoinTable
    private List<PokemonCard> pokemonCardList;
}
