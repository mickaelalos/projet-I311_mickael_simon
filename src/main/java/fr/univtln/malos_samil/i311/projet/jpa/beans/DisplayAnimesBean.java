package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@Stateless
public class DisplayAnimesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Anime selectedAnime;

    @Inject
    AnimeCrud animeCrud;

    public List<Anime> findAll() {
        return animeCrud.getAll();
    }

    public Anime getSelectedAnime() {
        return selectedAnime;
    }

    public void setSelectedAnime(Anime selectedAnime) {
        this.selectedAnime = selectedAnime;
    }

}
