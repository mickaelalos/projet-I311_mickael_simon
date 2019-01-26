package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

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

    private Anime selectedAnime;
    private int nbItems = 2;
    private int startP = 0;
    private boolean right, left = true;
    //private List<Anime> animeList;


    @Inject
    AnimeCrud animeCrud;

    @PostConstruct
    public void init(){
        initVar();
    }

    public List<Anime> initVar(){
        List<Anime> animeList =  findAll();

        if(animeList.size() < nbItems){
            right = true;
        }

        if(startP > 0){
            left = false;
        }

        if(animeList.size() >= nbItems){
            right = false;
        }

        if(startP <= 0){
            left = true;
        }

        return animeList;

    }

    public List<Anime> findAll() {
        return animeCrud.getAll(startP,nbItems);
    }

    public Anime getSelectedAnime() {
        return selectedAnime;
    }

    public void setSelectedAnime(Anime selectedAnime) {
        this.selectedAnime = selectedAnime;
    }

    public int getNbItems() {
        return nbItems;
    }

    public void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    public void onClickRight(){
        startP = startP + 1;
        initVar();
    }

    public void onClickLeft(){
        startP = startP - 1;
        initVar();
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getStartP() {
        return startP;
    }

    public void setStartP(int startP) {
        this.startP = startP;
    }

}
