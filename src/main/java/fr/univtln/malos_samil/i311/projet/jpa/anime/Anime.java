package fr.univtln.malos_samil.i311.projet.jpa.anime;

import fr.univtln.malos_samil.i311.projet.jpa.utils.StringQueries;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = StringQueries.GET_ANIME_ALL,
                query = "SELECT anime FROM Anime anime"),
        @NamedQuery(name = StringQueries.GET_ANIME_CNT,
                query = "SELECT count(anime) FROM Anime anime WHERE anime.title = :Ptitle")
})
public class Anime {
    public enum Season {
        None("n/a"),
        Winter("Hiver"),
        Summer("Été"),
        Spring("Printemps"),
        Fall("Automne");

        private final String vf;

        Season(String vf){
            this.vf = vf;
        }

        public String toVf() {
            return vf;
        }
    }
    public enum Status {
        None("n/a"),
        Currently("En Cours"),
        Finished("Terminé");

        private final String vf;

        Status(String vf){
            this.vf = vf;
        }

        public String getVf() {
            return vf;
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ANIME_ID")
    private int id;
    @Id
    @XmlElement
    @Column(name="TITLE", length = 50)
    private String title;
    @XmlElement
    @Column(name="YEAR")
    private int year;
    @XmlElement
    @Lob
    @Column(name="SYNOPSIS", length = 2000)
    private String synopsis;
    @XmlElement
    @Column(name="ICON", length = 100)
    private String icon;
    @XmlElement
    @Column(name="STUDIO", length = 50)
    private String studio;
    @XmlElement
    @Column(name="EPISODE")
    private int episode;
    @XmlElement
    @Column(name="SEASON")
    @Enumerated(EnumType.ORDINAL)
    private Season season;
    @XmlElement
    @Column(name="STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Anime() {
    }

    public Anime(String title, int year, String synopsis, String icon, String studio, int episode, Season season, Status status) {
        this.id = 0;
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.icon = icon;
        this.studio = studio;
        this.episode = episode;
        this.season = season;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Anime setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Anime setYear(int year) {
        this.year = year;
        return this;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Anime setSynopsis(String synopsis) {
        this.synopsis = synopsis;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Anime setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getStudio() {
        return studio;
    }

    public Anime setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public int getEpisode() {
        return episode;
    }

    public Anime setEpisode(int episode) {
        this.episode = episode;
        return this;
    }

    public Season getSeason() {
        return season;
    }

    public Anime setSeason(Season season) {
        this.season = season;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Anime setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return getTitle().equals(anime.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return "Anime{" +"\n" +
                "id=" + id + "\n" +
                ", title='" + title + '\'' +"\n" +
                ", year=" + year +"\n" +
                ", synopsis='" + synopsis + '\'' +"\n" +
                ", icon='" + icon + '\'' +"\n" +
                ", studio='" + studio + '\'' +"\n" +
                ", episode=" + episode +"\n" +
                ", season=" + season +"\n" +
                ", status=" + status +"\n" +
                '}';
    }
}
