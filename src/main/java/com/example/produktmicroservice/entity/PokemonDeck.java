package com.example.produktmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "pokemondeck")
public class PokemonDeck implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    @ManyToMany
    @JoinTable
    private List<PokemonCard> pokemonCardList;
}
