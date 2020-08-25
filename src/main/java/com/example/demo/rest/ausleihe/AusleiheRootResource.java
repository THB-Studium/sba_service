package com.example.demo.rest.ausleihe;

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

import com.example.demo.model.Ausleihe;
import com.example.demo.rest.ApiConstants;
import com.example.demo.service.AusleiheService;
import com.example.demo.transfert.AusleiheSignaturTO;
import com.example.demo.transfert.AusleiheTO;
import com.example.demo.transfert.IdTO;

@RestController
@RequestMapping(ApiConstants.AUSLEIHE_COLLECTION)
public class AusleiheRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);

    @Autowired
    private AusleiheService ausleiheService;
    
    

    /**
     * TO GET ALL AUSLEIHE: 
     * path: "/api/ausleihe"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Ausleihe> getAll() {
        Set<Ausleihe> ausleihe = ausleiheService.listAll();
        log.info("Ausleihe have been successfuly listed!");
        return ausleihe;
    }

    /**
     * TO ADD/CREATE A NEW AUSLEIHE: 
     * path: "/api/ausleihe"
     * 
     * @param uriInfo
     * @param ausleiheTO
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST)
    public Response addAusleihe(@Context UriInfo uriInfo, AusleiheTO ausleiheTO) {
        Ausleihe createdAusleihe = ausleiheService.createAusleihe(ausleiheTO);

        // build of the response:
        URI uri = uriInfo.getRequestUriBuilder()
                .path(createdAusleihe.getId().toString()).build();

        log.info("Ausleihe successfully created!");

        return Response.created(uri).entity(new IdTO(createdAusleihe.getId())).build();
    }

    /**
     * TO GET ONE AUSLEIHE BY SIGNATUR: path: "/api/ausleihe/signatur"
     * 
     * @param signatur
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/signatur", method = RequestMethod.GET)
    public Ausleihe getOneBySignatur(AusleiheSignaturTO ausleiheSignaturTO) {
        Ausleihe ausleiheFound = ausleiheService.getOneBySignatur(ausleiheSignaturTO.getSignatur());
        log.info(
                String.format("The Ausleihe with the id=%s has been found!"), ausleiheSignaturTO.getSignatur());
        return ausleiheFound;
    }

}
