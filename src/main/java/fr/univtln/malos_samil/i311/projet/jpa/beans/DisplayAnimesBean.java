package fr.univtln.malos_samil.i311.projet.jpa.beans;


import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@Stateless
public class DisplayAnimesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Anime> animeList;
    private Anime selectedAnime;

    @Inject
    private AnimeBean animeBean;

    @PostConstruct
    public void init() {
        animeList = animeBean.getAnimeList();
    }

    public List<Anime> getAnimeList(){
        return animeList;
    }

    public Anime getSelectedAnime() {
        return selectedAnime;
    }

    public void setSelectedAnime(Anime selectedAnime) {
        this.selectedAnime = selectedAnime;
    }

    public void setAnimeBean(AnimeBean animeBean) {
        this.animeBean = animeBean;
    }

}
