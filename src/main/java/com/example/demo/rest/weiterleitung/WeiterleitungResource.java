package com.example.demo.rest.weiterleitung;

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

import com.example.demo.model.Weiterleitung;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.WeiterleitungService;

@RestController
@RequestMapping(ApiConstants.WEITERLEITUNGEN_ITEM)
public class WeiterleitungResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private WeiterleitungService weiterleitungService;
    
    
    /**
     * TO GET ONE WEITERLEITUNGEN BY ID: 
     * path: "/api/weiterleitungen/{weiterleitungId}"
     * 
     * @param weiterleitungId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Weiterleitung getOneById(@PathParam("weiterleitungId") UUID weiterleitungId) {
        Weiterleitung weiterleitungFound = weiterleitungService.getOne(weiterleitungId);
        log.info(
                String.format("The Weiterleitung with the id=%s has been found!"), weiterleitungId.toString());
        return weiterleitungFound;
    }
    

//    /**
//     * TO DELETE A WEITERLEITUNGEN
//     * path: "/api/weiterleitungen/{weiterleitungId}"
//     * 
//     * @param weiterleitungId
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathParam("weiterleitungId") UUID weiterleitungId) {
//        weiterleitungService.delete(weiterleitungId);
//            log.info(String.format("The Weiterleitung with the id=%s has been successfully deleted!"), weiterleitungId.toString());
//    }

}
