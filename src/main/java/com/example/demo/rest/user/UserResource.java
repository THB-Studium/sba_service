package com.example.demo.rest.user;

import java.util.Set;
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
import com.example.demo.model.Favory;
import com.example.demo.model.Mahnung;
import com.example.demo.model.Reservierung;
import com.example.demo.model.User;
import com.example.demo.rest.ApiConstants;
import com.example.demo.service.AusleiheService;
import com.example.demo.service.FavoryService;
import com.example.demo.service.MahnungService;
import com.example.demo.service.ReservierungService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/test" + ApiConstants.USERS_ITEM)
public class UserResource {
    private static final Logger log = LoggerFactory.getLogger(UserRootResource.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AusleiheService ausleiheService;
    @Autowired
    private MahnungService mahnungService;
    @Autowired
    private FavoryService favoryService;
    @Autowired
    private ReservierungService reservierungService;
    
    /**
     * TO GET ONE USER BY ID: 
     * path: "/api/users/{userId}"
     * 
     * @param userId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public User getOneById(@PathParam("userId") UUID userId) {
        log.info("------------ get o user with the id: " + userId.toString());
        UUID id = UUID.fromString(userId.toString());
        User ausleiheFound = userService.getOneById(id);
        log.info(
                String.format("The User with the id=%s has been found!"), userId.toString());
        return ausleiheFound;
    }
    
    /**
     * TO GET ALL AUSLEIHE BY USER ID: 
     * path: "/api/users/{userId}/ausleihe"
     * 
     * @param userId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/ausleihe", method = RequestMethod.GET)
    public Set<Ausleihe> getAllAusleiheByUserId(@PathParam("userId") UUID userId) {
        Set<Ausleihe> ausleihe = ausleiheService.listAllByUserId(userId);
        log.info("Ausleihe have been successfuly listed!");
        return ausleihe; 
    }
    
    /**
     * TO GET ALL MAHNUNGEN BY USER ID: 
     * path: "/api/users/{userId}/mahnungen"
     * 
     * @param userId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/mahnungen", method = RequestMethod.GET)
    public Set<Mahnung> getAllMahnungenByUserId(@PathParam("userId") UUID userId) {
        Set<Mahnung> mahnungen = mahnungService.listAllByUserId(userId);
        log.info("Mahnungen have been successfuly listed!");
        return mahnungen; 
    }
    
    /**
     * TO GET ALL FAVORIES BY USER ID: 
     * path: "/api/users/{userId}/favories"
     * 
     * @param userId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/favories", method = RequestMethod.GET)
    public Set<Favory> getAllFavoriesByUserId(@PathParam("userId") UUID userId) {
        Set<Favory> favories = favoryService.listAllByUserId(userId);
        log.info("Favories have been successfuly listed!");
        return favories; 
    }
    
    /**
     * TO GET ALL RESERVIERUNGEN BY USER ID: 
     * path: "/api/users/{userId}/reservierungen"
     * 
     * @param userId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/reservierungen", method = RequestMethod.GET)
    public Set<Reservierung> getAllReservierungenByUserId(@PathParam("userId") UUID userId) {
        Set<Reservierung> reservierungen = reservierungService.listAllByUserId(userId);
        log.info("Reservierungen have been successfuly listed!");
        return reservierungen; 
    }

}
