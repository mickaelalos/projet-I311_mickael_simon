package fr.univtln.malos_samil.i311.projet.jpa.beans;

import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeBuilder;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;
import fr.univtln.malos_samil.i311.projet.jpa.utils.URLValidator;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Stateless
@Named
public class AnimeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    AnimeCrud animeCrud;

    private String newTitle;
    @Inject
    private URLValidator urlValidator;
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
        seasons = new HashMap<>();
        Arrays.asList(Anime.Season.values())
                .forEach(s -> {
                    if (s!=Anime.Season.None)
                        seasons.put(s.toVf(),s);
                });
        statuss = new HashMap<>();
        Arrays.asList(Anime.Status.values())
                .forEach(s -> {
                    if (s!=Anime.Status.None)
                        statuss.put(s.toVf(),s);
                });
    }

    public void resetVar(){
        newTitle = "";
        newEpisode = 0;
        newUrlIcon = "";
        newYear = Calendar.getInstance().get(Calendar.YEAR);
        newSeason = Anime.Season.None;
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

    public String addAnime() {
        Anime anime = new AnimeBuilder().setTitle(newTitle).setEpisode(newEpisode).setSeason(newSeason).setYear(newYear).setIcon(newUrlIcon).setStatus(newStatus).setStudio(newStudio).setSynopsis(newSynopsis).createAnime();
        animeCrud.addAnime(anime);
        resetVar();
        return "displayAnimes";
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        if(animeCrud.countAnime(newTitle)>0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Titre incorrect", "Anime déjà existant");
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
        if (urlValidator.validateURL(newUrlIcon)){
            this.newUrlIcon = newUrlIcon;
        } else {
            this.newUrlIcon = "";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "URL non valide.", "Veuillez prendre un image sur myanimelist.net");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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

    public String getHeader() {
        resetVar();
        return "Création d'un Anime";
    }
}