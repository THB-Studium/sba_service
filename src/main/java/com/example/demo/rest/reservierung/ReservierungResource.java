package com.example.demo.rest.reservierung;

import java.util.UUID;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

@RestController
@RequestMapping(ApiConstants.RESERVIRUNGEN_ITEM)
public class ReservierungResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private ReservierungService reservierungService;
    
    
    /**
     * TO GET ONE RESERVIRUNGEN BY ID: 
     * path: "/api/reservierungen/{reservierungId}"
     * 
     * @param reservierungId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Reservierung getOneById(@PathParam("reservierungId") UUID reservierungId) {
        Reservierung reservierungFound = reservierungService.getOneById(reservierungId);
        log.info(
                String.format("The Reservierung with the id=%s has been found!"), reservierungId.toString());
        return reservierungFound;
    }
    
//    /**
//     * TO DELETE A RESERVIRUNGEN
//     * path: "/api/reservierungen/{reservierungId}"
//     * 
//     * @param reservierungId
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathParam("reservierungId") UUID reservierungId) {
//        reservierungService.delete(reservierungId);
//            log.info(String.format("The Reservierung with the id=%s has been successfully deleted!"), reservierungId.toString());
//    }

}
