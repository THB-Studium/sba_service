package com.example.demo.rest.favory;

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

import com.example.demo.model.Favory;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.FavoryService;
import com.example.demo.transfert.FavoryTO;

@RestController
@RequestMapping(ApiConstants.FAVORIES_ITEM)
public class FavoryResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private FavoryService favoryService;
    
    
    /**
     * TO GET ONE FAVORIES BY ID: 
     * path: "/api/favories/{favoryId}"
     * 
     * @param favoryId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Favory getOneById(@PathParam("favoryId") UUID favoryId) {
        Favory favoryFound = favoryService.getOne(favoryId);
        log.info(
                String.format("The Favory with the id=%s has been found!"), favoryId.toString());
        return favoryFound;
    }
    

    /**
     * TO DELETE A FAVORIES
     * path: "/api/favories/{favoryId}"
     * 
     * @param favoryId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(FavoryTO favoryTO) {
        favoryService.delete(favoryTO);
            log.info(String.format("The Favory with the id=%s has been successfully deleted!"), favoryTO.getBuchId().toString());
    }

}
