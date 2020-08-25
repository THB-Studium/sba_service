package com.example.demo.rest.buch;

import java.util.Set;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Buch;
import com.example.demo.rest.ApiConstants;
import com.example.demo.rest.ausleihe.AusleiheRootResource;
import com.example.demo.search.criteria.BuchCriteria;
import com.example.demo.service.BuchService;

@RestController
@RequestMapping(ApiConstants.BUECHER_COLLECTION)
public class BuchRootResource {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);
    
    @Autowired
    private BuchService buchService;
    
    
    /**
     * TO GET ALL BUECHER: 
     * path: "/api/buecher"
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(method = RequestMethod.GET)
    public Set<Buch> getAll() {
        Set<Buch> buecher = buchService.listAll();
        log.info("Buecher have been successfuly listed!");
        return buecher; 
    }
    
    
    /**
     * TO SEARCH A BOOK BY CRITERIA: 
     * path: "/api/buecher/search"
     * 
     * 
     * @param titel
     * @param autor
     * @param datum
     * @param isbn13
     * @param isbn10
     * @param medienart
     * @param verlag
     * @param erscheinungsjahr
     * @param ausgabe
     * @param uebersicht
     * @param kategorie
     * @param zustand
     * @param anmerkungen
     * @param signatur
     * @param sprache
     * 
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public Set<Buch> search(
            @PathParam("titel") String titel,
            @PathParam("autor") String autor,
            @PathParam("erscheinungsdatum") String erscheinungsdatum,
            @PathParam("isbn13") long isbn13,
            @PathParam("isbn10") long isbn10,
            @PathParam("medienart") String medienart,
            @PathParam("verlag") String verlag,
            @PathParam("erscheinungsjahr") long erscheinungsjahr,
            @PathParam("ausgabe") String ausgabe,
            @PathParam("uebersicht") String uebersicht,
            @PathParam("kategorie") String kategorie,
            @PathParam("zustand") String zustand,
            @PathParam("anmerkungen") String anmerkungen,
            @PathParam("signatur") String signatur,
            @PathParam("sprache") String sprache
            ) {
        
        BuchCriteria buchCriteria = new BuchCriteria();
        
        buchCriteria.setTitel(titel);
        buchCriteria.setAutor(autor);
        buchCriteria.setErscheinungsdatum(erscheinungsdatum);
        buchCriteria.setIsbn13(isbn13);
        buchCriteria.setIsbn10(isbn10);
        buchCriteria.setMedienart(medienart);
        buchCriteria.setVerlag(verlag);
        buchCriteria.setErscheinungsjahr(erscheinungsjahr);
        buchCriteria.setAusgabe(ausgabe);
        buchCriteria.setKategorie(kategorie);
        buchCriteria.setUebersicht(uebersicht);
        buchCriteria.setZustand(zustand);
        buchCriteria.setAnmerkungen(anmerkungen);
        buchCriteria.setSignatur(signatur);
        buchCriteria.setSprache(sprache);
        
        Set<Buch> buecher = buchService.search(buchCriteria);
        log.info("Search has been successfuly done!");
        return buecher; 
    }

}
