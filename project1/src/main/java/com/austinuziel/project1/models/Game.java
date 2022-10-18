package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game extends SaleItem{
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(name = "esrb_rating")
    private String esrbRating;

    private String studio;
    private String title;

    public Game() {
    }

    public Game(double price, Integer quantity, String description, Integer gameId, String esrbRating, String studio, String title) {
        super(price, quantity, description);
        this.gameId = gameId;
        this.esrbRating = esrbRating;
        this.studio = studio;
        this.title = title;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }


    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) && Objects.equals(title, game.title) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(studio, game.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameId, title, esrbRating, studio);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", studio='" + studio + '\'' +
                '}';
    }
}
