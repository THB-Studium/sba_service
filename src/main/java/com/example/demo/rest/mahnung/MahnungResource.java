package com.example.demo.rest.mahnung;

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

import com.example.demo.model.Mahnung;
import com.example.demo.rest.ApiConstants;
import com.example.demo.service.MahnungService;

@RestController
@RequestMapping(ApiConstants.MAHNUNG_ITEM)
public class MahnungResource {
    private static final Logger log = LoggerFactory.getLogger(MahnungRootResource.class);

    @Autowired
    private MahnungService mahnungService;
    
    
    
    /**
     * TO GET ONE MAHNUNGEN BY ID: 
     * path: "/api/mahnungen/{mahnungId}"
     * 
     * @param mahnungId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Mahnung getOneById(@PathParam("mahnungId") UUID mahnungId) {
        Mahnung ausleiheFound = mahnungService.getOne(mahnungId);
        log.info(
                String.format("The Mahnung with the id=%s has been found!"), mahnungId.toString());
        return ausleiheFound;
    }
    

//    /**
//     * TO DELETE A MAHNUNGEN
//     * path: "/api/mahnungen/{mahnungId}"
//     * 
//     * @param mahnungId
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathParam("mahnungId") UUID mahnungId) {
//        mahnungService.delete(mahnungId);
//            log.info(String.format("The Mahnung with the id=%s has been successfully deleted!"), mahnungId.toString());
//    }
    
    

}
