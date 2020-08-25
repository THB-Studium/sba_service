package com.example.demo.rest.ausleihe;

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

import com.example.demo.model.Ausleihe;
import com.example.demo.rest.ApiConstants;
import com.example.demo.service.AusleiheService;

@RestController
@RequestMapping(ApiConstants.AUSLEIHE_ITEM)
public class AusleiheResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);

    @Autowired
    private AusleiheService ausleiheService;

    
    
    /**
     * TO GET ONE AUSLEIHE BY ID: 
     * path: "/api/ausleihe/{ausleiheId}"
     * 
     * @param ausleiheId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Ausleihe getOneById(@PathParam("ausleiheId") UUID ausleiheId) {
        Ausleihe ausleiheFound = ausleiheService.getOneById(ausleiheId);
        log.info(
                String.format("The Ausleihe with the id=%s has been found!"), ausleiheId.toString());
        return ausleiheFound;
    }
    

//    /**
//     * TO DELETE A AUSLEIHE
//     * path: "/api/ausleihe/{ausleiheId}"
//     * 
//     * @param ausleiheId
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathParam("ausleiheId") UUID ausleiheId) {
//        ausleiheService.deleteAusleihe(ausleiheId);
//            log.info(String.format("The Ausleihe with the id=%s has been successfully updated!"), ausleiheId.toString());
//    }
}
