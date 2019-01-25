package fr.univtln.malos_samil.i311.projet.jpa.anime;

import fr.univtln.malos_samil.i311.projet.jpa.utils.StringQueries;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AnimeCrud {
    @PersistenceContext
    EntityManager em;

    private int startPosition = 0;
    private int pageSize = 2;

    private boolean done = false;
    private Query query  = em.createNamedQuery(StringQueries.GET_ANIME_ALL);


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

    public List<Anime> getAll() {
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<Anime> animeList = (List<Anime>) query.getResultList();
        while (!done) {
            if (animeList.size() < pageSize) {
                done = true;

            }
            startPosition = startPosition + pageSize;
            query.setFirstResult(startPosition);
            if (!done) {
                animeList = query.getResultList();
            }
        }
        return animeList;
    }

    public long countAnime(String title){
        return (Long) em.createNamedQuery(StringQueries.GET_ANIME_CNT).setParameter("Ptitle",title).getSingleResult();
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

}
