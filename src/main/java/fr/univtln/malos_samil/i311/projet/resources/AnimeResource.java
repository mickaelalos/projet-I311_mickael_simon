package fr.univtln.malos_samil.i311.projet.resources;


import fr.univtln.malos_samil.i311.projet.jpa.anime.Anime;
import fr.univtln.malos_samil.i311.projet.jpa.anime.AnimeCrud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/anime")
@Produces({"application/json","application/xml"})
@Consumes({"application/json","application/xml"})
public class AnimeResource {

    @Inject
    AnimeCrud animeCrud;

    @GET
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }

    /*@GET
    @Path("/animes/{id}")
    @Produces("application/json")
    public Anime getAnime(@PathParam("id") final int id){
        return animeCrud.getAnime(id);
    }*/

    @GET
    @Path("/animes/{page}")
    @Produces("application/json")
    public List<Anime> getAll(@PathParam("page") final int page){
        return animeCrud.getAll(page,3,"");
    }

    @POST
    @Path("/animes")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAnime(Anime anime){
        animeCrud.addAnime(anime);
    }

    @PUT
    @Path("/animes")
    public void updateAnime(Anime anime){
        animeCrud.updateAnime(anime);
    }

}
