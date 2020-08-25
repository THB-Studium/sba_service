package com.example.demo.rest.weiterleitung;

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

import com.example.demo.model.Weiterleitung;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.WeiterleitungService;
import com.example.demo.transfert.IdTO;
import com.example.demo.transfert.WeiterleitungTO;

@RestController
@RequestMapping(ApiConstants.WEITERLEITUNGEN_COLLECTION)
public class WeiterleitungRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private WeiterleitungService weiterleitungService;
    
    
    /**
     * TO GET ALL WEITERLEITUNGEN: 
     * path: "/api/weiterleitungen"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Weiterleitung> getAll() {
        Set<Weiterleitung> weiterleitungen = weiterleitungService.listAll();
        log.info("Weiterleitungen have been successfuly listed!");
        return weiterleitungen; 
    } 
    
    /**
     * TO ADD/CREATE A NEW FAVORY: 
     * path: "/api/weiterleitungen"
     * 
     * @param uriInfo
     * @param weiterleitungTO
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST)
    public Response addBoking(@Context UriInfo uriInfo, WeiterleitungTO weiterleitungTO) {
        Weiterleitung createdWeiterleitung = weiterleitungService.create(weiterleitungTO);

        // build of the response:
        URI uri = uriInfo.getRequestUriBuilder()
                .path(createdWeiterleitung.getId().toString()).build();

        log.info("Weiterleitung successfully created!");

        return Response.created(uri).entity(new IdTO(createdWeiterleitung.getId())).build();
    }

}
