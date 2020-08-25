package com.example.demo.rest.favory;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Favory;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.FavoryService;
import com.example.demo.transfert.FavoryTO;
import com.example.demo.transfert.IdTO;

@RestController
@RequestMapping(ApiConstants.FAVORIES_COLLECTION)
public class FavoryRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private FavoryService favoryService;
    
    
    
    /**
     * TO GET ALL FAVORIES: 
     * path: "/api/favories"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Favory> getAll() {
        Set<Favory> favories = favoryService.listAll();
        log.info("Favories have been successfuly listed!");
        return favories; 
    } 
    
    /**
     * TO ADD/CREATE A NEW FAVORY: 
     * path: "/api/favories"
     * 
     * @param uriInfo
     * @param favoryTO
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST)
    public Response addBoking(@Context UriInfo uriInfo, FavoryTO favoryTO) {
        Favory createdFavory = favoryService.create(favoryTO);

        // build of the response:
        URI uri = uriInfo.getRequestUriBuilder()
                .path(createdFavory.getId().toString()).build();

        log.info("Favory successfully created!");

        return Response.created(uri).entity(new IdTO(createdFavory.getId())).build();
    }

}
