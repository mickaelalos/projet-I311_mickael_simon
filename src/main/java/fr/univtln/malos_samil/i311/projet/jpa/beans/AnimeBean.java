package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Stateless
@Named
public class AnimeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Anime> animeList;

    @Inject
    AnimeCrud animeCrud;

    private String newTitle;
    private int newEpisode;

    public String addAnime(){
        Anime anime = new Anime(newTitle, newEpisode);
        animeCrud.addAnime(anime);
        return "displayAnimes";
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public int getNewEpisode() {
        return newEpisode;
    }

    public void setNewEpisode(int newEpisode) {
        this.newEpisode = newEpisode;
    }

    public List<Anime> getAnimeList(){
        animeList = animeCrud.getAll();
        return animeList;
    }
}

