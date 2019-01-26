package fr.univtln.malos_samil.i311.projet.jpa.anime;

import fr.univtln.malos_samil.i311.projet.jpa.utils.StringQueries;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AnimeCrud {
    @PersistenceContext
    EntityManager em;

    public Anime getAnime(int id){
        Anime anime = em.find(Anime.class, id);
        return anime;
    }

    public void deleteAnime(int id){
        Anime anime = em.find(Anime.class, id);
        em.remove(anime);
    }

    public void addAnime(Anime anime){
        em.persist(anime);
    }

    public void updateAnime(Anime anime){
        em.merge(anime);
    }

    public List<Anime> getAll(){
        return (List<Anime>) em.createNamedQuery(StringQueries.GET_ANIME_ALL).getResultList();
    }

    public long countAnime(String title){
        return (Long) em.createNamedQuery(StringQueries.GET_ANIME_CNT).setParameter("Ptitle",title).getSingleResult();
    }
}