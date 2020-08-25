package com.example.demo.rest.reservierung;

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

import com.example.demo.model.Reservierung;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.ReservierungService;
import com.example.demo.transfert.IdTO;
import com.example.demo.transfert.ReservierungTO;

@RestController
@RequestMapping(ApiConstants.RESERVIRUNGEN_COLLECTION)
public class ReservierungRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private ReservierungService reservierungService;
    
    
    /**
     * TO GET ALL RESERVIRUNGEN: 
     * path: "/api/reservierungen"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Reservierung> getAll() {
        Set<Reservierung> reservierungen = reservierungService.listAll();
        log.info("Reservierung have been successfuly listed!");
        return reservierungen; 
    } 
    
    /**
     * TO ADD/CREATE A NEW RESERVIERUNG: 
     * path: "/api/reservierungen"
     * 
     * @param uriInfo
     * @param newBooking
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.POST)
    public Response addBoking(@Context UriInfo uriInfo, ReservierungTO reservierungTO) {
        Reservierung createdReservierung = reservierungService.create(reservierungTO);

        // build of the response:
        URI uri = uriInfo.getRequestUriBuilder()
                .path(createdReservierung.getId().toString()).build();

        log.info("Reservierung successfully created!");

        return Response.created(uri).entity(new IdTO(createdReservierung.getId())).build();
    }

}
