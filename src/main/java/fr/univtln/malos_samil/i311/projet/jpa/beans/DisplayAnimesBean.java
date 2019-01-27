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
    private int nbItems = 2;
    //private int page = 0;
    private boolean right, left = true;
    //private List<Anime> animeList;
    private int nbPage = 1;
    private int nbPageMax;
    private String text = "";
    private List<Anime> animes;

    @Inject
    AnimeCrud animeCrud;

    public void initVar(){
        double tmp = text.length()>0 ? (double)animeCrud.countAllLike(text) : (double)animeCrud.countAll();

        if(tmp == 0){
            nbPageMax = 1;
        }

        if(tmp > 0){
            nbPageMax = (int) Math.ceil(tmp/(double)nbItems);
        }

        if(nbPage>nbPageMax) nbPage = nbPageMax;

        right = nbPage >= nbPageMax;

        left = nbPage <= 1;

        animes =  findAll();
    }

    public List<Anime> findAll() {
        return animeCrud.getAll(nbPage, nbItems, text);
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
        if(nbPage >= nbPageMax)
            this.nbPage = nbPageMax;
        else if(nbPage<=1)
            this.nbPage = 1;
        initVar();
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
        this.text = text.trim();
        initVar();
    }

    public String getHeader(){
        initVar();
        return "Mes animes";
    }

    public List<Anime> getAnimes(){
        return animes;
    }
}
