package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;


@Named
@Stateless
public class DisplayAnimesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Anime selectedAnime;
    private int nbItems = 3;
    //private int page = 0;
    private boolean right, left = true;
    //private List<Anime> animeList;
    @Min(value = 1,message = "Le nombre de pages doit être supérieur à 0")
    @Max(value = 9999,message = "Le nombre de pages doit être inferieur à 9999")
    private int nbPage;
    private int nbPageMax;
    private String text = "";

    @Inject
    AnimeCrud animeCrud;

    @PostConstruct
    public void init(){
        initVar();
    }

    public List<Anime> initVar(){
        List<Anime> animeList =  findAll();

        int tmp = (int)animeCrud.countAll();
        nbPageMax = tmp/nbItems;

        if(animeList.size() < nbItems){
            right = true;
        }

        if(nbPage > 1){
            left = false;
        }

        if(animeList.size() >= nbItems){
            right = false;
        }

        if(nbPage <= 1){
            left = true;
        }

        return animeList;

    }

    public List<Anime> findAll() {
        return animeCrud.getAll(nbPage,nbItems, text);
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
        nbPage = nbPage + 1;
        initVar();
    }

    public void onClickLeft(){
        nbPage = nbPage - 1;
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

    public int getNbPage(){
        return nbPage;
    }

    public void setNbPage(int nbPage){
        this.nbPage = nbPage;
    }

    public int getNbPageMax() {
        return nbPageMax;
    }

    public void setNbPageMax(int nbPageMax) {
        this.nbPageMax = nbPageMax;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void filterList(){
        text = text.trim();
    }

}
