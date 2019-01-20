package fr.univtln.malos_samil.i311.projet;

import fr.univtln.malos_samil.i311.projet.jpa.dao.StringQueries;
import fr.univtln.malos_samil.i311.projet.jpa.dao.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.dao.anime.AnimeBuilder;
import fr.univtln.malos_samil.i311.projet.jpa.dao.anime.AnimeCrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.invoke.MethodHandles;

/**
 * Hello world!
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        logger.info("App started.");
        logger.debug("About to talk :");
        System.out.println("Hello world !");
        Anime anime = new AnimeBuilder().setId(0).setTitle("oui").setYear(2019).setSynopsis("non").setIcon("http://oui.fr").setStudio("ppp").setEpisode(12).setSeason(Anime.Season.Winter).setStatus(Anime.Status.Currently).createAnime();
        System.out.println(anime);
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("persistenceAnimeLocal");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(anime);
        transaction.commit();
        System.out.println(anime);
        System.out.println(em.createNamedQuery(StringQueries.GET_ANIME_BY_ID).setParameter("Pid", 1).getResultList());
    }
}
