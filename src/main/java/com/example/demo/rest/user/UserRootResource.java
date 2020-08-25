package com.example.demo.rest.user;

import java.util.Set;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(ApiConstants.USERS_COLLECTION)
public class UserRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);

    @Autowired
    private UserService userService;
    
    
    /**
     * TO GET ALL USER: 
     * path: "/api/users"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<User> getAll() {
        Set<User> users = userService.listAll();
        log.info("Users have been successfuly listed!");
        return users; 
    }
    
//    /**
//     * TO ADD/CREATE A NEW USER: 
//     * path: "/api/users"
//     * 
//     * @param uriInfo
//     * @param newUser
//     * @return
//     */
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @RequestMapping(method = RequestMethod.POST)
//    public Response addUser(@Context UriInfo uriInfo, User newUser) {
//        User createdUser = userService.create(newUser);
//        
//     // build of the response:
//        URI uri = uriInfo.getRequestUriBuilder()
//                .path(createdUser.getId().toString()).build();
//
//        log.info("User successfully created!");
//
//        return Response.created(uri).entity(new IdTO(createdUser.getId())).build();
//    }
    
}
