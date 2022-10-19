package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "You must supply a String for esrbRating")
    private String esrbRating;

    @Column(unique=true)
    @NotNull(message = "You must supply a String for title")
    private String title;

    @NotNull(message = "You must supply a String for studio")
    private String studio;
    @NotNull(message = "You must supply a String for description")
    private String description;

    public Game() {
    }

    public Game(double price, Integer quantity, Integer gameId, String esrbRating, String title, String studio, String description) {
        super(price, quantity);
        this.gameId = gameId;
        this.esrbRating = esrbRating;
        this.title = title;
        this.studio = studio;
        this.description = description;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) && Objects.equals(esrbRating, game.esrbRating) && Objects.equals(title, game.title) && Objects.equals(studio, game.studio) && Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameId, esrbRating, title, studio, description);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", esrbRating='" + esrbRating + '\'' +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
