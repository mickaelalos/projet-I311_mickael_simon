package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeBuilder;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;

@Stateless
@Named
public class AnimeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Anime> animeList;

    @Inject
    AnimeCrud animeCrud;

    private String newTitle;
    @Pattern(regexp = "^https://cdn\\.myanimelist\\.net/images/.*\\.jpg$", message = "URL non valide. Veuillez prendre un image sur myanimelist.net")
    private String newUrlIcon;
    @Min(value = 1,message = "Le nombre d'épisode doit être supérieur à 1")
    @Max(value = 9999,message = "Le nombre d'épisode doit être inferieur à 9999")
    private int newEpisode;
    private Anime.Season newSeason;
    private Map<String,Anime.Season> seasons;
    private Anime.Status newStatus;
    private Map<String,Anime.Status> statuss;
    @Min(value = 1970,message = "L'année doit être supérieur à 1970")
    @Max(value = 2030,message = "L'année doit être inferieur à 2030")
    private int newYear;
    private String newSynopsis;
    private String newStudio;

    @PostConstruct
    public void init() {
        newTitle = "";
        newEpisode = 0;
        newUrlIcon = "";
        newYear = Calendar.getInstance().get(Calendar.YEAR);
        seasons = new HashMap<>();
        Arrays.asList(Anime.Season.values())
                .forEach(s -> {
                    if (s!=Anime.Season.None)
                        seasons.put(s.toVf(),s);
                });
        newSeason = Anime.Season.None;
        statuss = new HashMap<>();
        Arrays.asList(Anime.Status.values())
                .forEach(s -> {
                    if (s!=Anime.Status.None)
                        statuss.put(s.getVf(),s);
                });
        newStatus = Anime.Status.None;
        newSynopsis = "";
        newStudio = "";
    }

    public Anime.Season getNewSeason() {
        return newSeason;
    }

    public void setNewSeason(Anime.Season newSeason) {
        this.newSeason = newSeason;
    }

    public Anime.Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Anime.Status newStatus) {
        this.newStatus = newStatus;
    }

    public Map<String, Anime.Season> getSeasons() {
        return seasons;
    }

    public Map<String, Anime.Status> getStatuss() {
        return statuss;
    }

    public List<Anime> findAll(){
        return animeCrud.getAll();
    }

    public String addAnime() {
        Anime anime = new AnimeBuilder().setTitle(newTitle).setEpisode(newEpisode).setSeason(newSeason).setYear(newYear).setIcon(newUrlIcon).setStatus(newStatus).setStudio(newStudio).setSynopsis(newSynopsis).createAnime();
        animeCrud.addAnime(anime);
        init();
        return "displayAnimes";
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        if(animeCrud.countAnime(newTitle)>0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Anime déjà existant");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else
            this.newTitle = newTitle;
    }

    public int getNewEpisode() {
        return newEpisode;
    }

    public void setNewEpisode(int newEpisode) {
        this.newEpisode = newEpisode;
    }


    public String getNewUrlIcon() {
        return newUrlIcon;
    }

    public void setNewUrlIcon(String newUrlIcon) {
        this.newUrlIcon = newUrlIcon;
    }

    public String getUrl() {
        if (newUrlIcon.equals("")) return "resources/images/na.jpg";
        return newUrlIcon;
    }

    public int getNewYear() {
        return newYear;
    }

    public void setNewYear(int newYear) {
        this.newYear = newYear;
    }

    public String getNewSynopsis() {
        return newSynopsis;
    }

    public void setNewSynopsis(String newSynopsis) {
        this.newSynopsis = newSynopsis.trim();
    }

    public String getNewStudio() {
        return newStudio;
    }

    public void setNewStudio(String newStudio) {
        this.newStudio = newStudio;
    }

    public int getPageSize(){
        return animeCrud.getPageSize();
    }
}

