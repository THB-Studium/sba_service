package com.example.demo.rest.buch;

import java.util.UUID;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Buch;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.BuchService;

@RestController
@RequestMapping(ApiConstants.BUECHER_ITEM)
public class BuchResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);

    @Autowired
    private BuchService buchService;
    
    

    /**
     * TO GET ONE BUCH BY ID: 
     * path: "/api/.../{buchId}"
     * 
     * @param buchId
     * @return
     */
    public Buch getOneById(@PathParam("buchId") UUID buchId) {
        Buch buchFound = buchService.getOne(buchId);
        log.info(
                String.format("The Buch with the id=%s has been found!"), buchId.toString());
        return buchFound;
    }

//    /**
//     * TO DELETE A BUCH 
//     * path: "/api/.../{buchId}"
//     * 
//     * @param buchId
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@PathParam("buchId") UUID buchId) {
//         buchService.delete(buchId);
//         log.info(String.format("The Book with the id=%s has been successfully updated!"), buchId.toString());
//    }

}
