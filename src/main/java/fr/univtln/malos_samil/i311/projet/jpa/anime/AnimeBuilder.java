package fr.univtln.malos_samil.i311.projet.jpa.anime;

public class AnimeBuilder {
    private String title;
    private int year;
    private String synopsis;
    private String icon;
    private String studio;
    private int episode;
    private Anime.Season season;
    private Anime.Status status;

    public AnimeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AnimeBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public AnimeBuilder setSynopsis(String synopsis) {
        this.synopsis = synopsis;
        return this;
    }

    public AnimeBuilder setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public AnimeBuilder setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public AnimeBuilder setEpisode(int episode) {
        this.episode = episode;
        return this;
    }

    public AnimeBuilder setSeason(Anime.Season season) {
        this.season = season;
        return this;
    }

    public AnimeBuilder setStatus(Anime.Status status) {
        this.status = status;
        return this;
    }

    public Anime createAnime() {
        return new Anime(title, year, synopsis, icon, studio, episode, season, status);
    }
}