package com.example.demo.rest.mahnung;

import java.util.Set;

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
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.MahnungService;

@RestController
@RequestMapping(ApiConstants.MAHNUNG_COLLECTION)
public class MahnungRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private MahnungService mahnungService;
    
    /**
     * TO GET ALL MAHNUNGEN: 
     * path: "/api/ausleihe"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Mahnung> getAll() {
        Set<Mahnung> mahnungen = mahnungService.listAll();
        log.info("Mahnung have been successfuly listed!");
        return mahnungen; 
    }   

}
