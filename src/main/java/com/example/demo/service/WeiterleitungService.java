package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Ausleihe;
import com.example.demo.model.Buch;
import com.example.demo.model.User;
import com.example.demo.model.Weiterleitung;
import com.example.demo.repository.AusleiheRepository;
import com.example.demo.repository.WeiterleitungRepository;
import com.example.demo.transfert.EmailTO;
import com.example.demo.transfert.WeiterleitungTO;
import com.geminiald.email.services.EmailSender;

@Service
@Transactional(rollbackOn = Exception.class)
public class WeiterleitungService {
    
    @Autowired
    private WeiterleitungRepository weiterleitungRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AusleiheRepository ausleiheRepository;
    @Autowired
    private AusleiheService ausleiheService;
    @Autowired
    private  EmailSender emailSender;
    
    
    
    /**
     * TO LIST ALL WEITERLEITUNG
     * 
     * @return
     */
    public Set<Weiterleitung> listAll() {
        return weiterleitungRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO GET ONE WEITERLEITUNG BY ID
     * 
     * @param weiterleitungId
     * @return
     */
    public Weiterleitung getOne(UUID weiterleitungId) {
        Optional<Weiterleitung> weiterleitungOp = weiterleitungRepository.findById(weiterleitungId);
        if (weiterleitungOp.isPresent()) {
            return weiterleitungOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Weiterleitung with the id %s does not exist", weiterleitungId.toString()));
        }
    }   
    
    
    /**
     * TO ADD A NEW WEITERLEITUNG
     * 
     * @param weiterleitungTO
     * @return
     */
    public Weiterleitung create(WeiterleitungTO weiterleitungTO) {
        Ausleihe altAusleihe = ausleiheService.getOneBySignatur(weiterleitungTO.getBuchSignatur());
        Buch buch = altAusleihe.getBuch();
        
        User altBesitzer = userService.getOneById(altAusleihe.getUser().getId());
        User neuBesitzer = userService.getOneById(weiterleitungTO.getCurrentUserId());
        

        Ausleihe newAusleihe = new Ausleihe(new Date());
        Date bis = new Date(newAusleihe.getVon().getTime() + (3 * 7 * 24 * 60 * 60 * 1000));
        newAusleihe.setId(null);
        newAusleihe.setUser(neuBesitzer);
        newAusleihe.setBuch(buch);
        newAusleihe.setBis(bis);        
        
        ausleiheService.deleteAusleihe(altAusleihe.getId());
        ausleiheRepository.save(newAusleihe);
        

        Weiterleitung weiterleitung = new Weiterleitung();
        weiterleitung.setId(null);
        weiterleitung.setAltBesitzer(altBesitzer);
        weiterleitung.setNeuBesitzer(neuBesitzer);
        weiterleitung.setBuch(buch);
        weiterleitung.setDatum(new Date());
        
        weiterleitung = weiterleitungRepository.save(weiterleitung);
        
        return weiterleitung;
    }
    
    public void startWeiterleitung(EmailTO emailTO) throws MessagingException, UnsupportedEncodingException {
        Set<Ausleihe> ausleihe = ausleiheRepository.findAllByBuchIsbn(emailTO.getIsbn());
        User currentUser = userService.getOneById(emailTO.getUserId());
        
        for (Ausleihe ausleihe2 : ausleihe) {
            User recipient = ausleihe2.getUser();
            String message = buildMessage(emailTO.getSubject(), currentUser, ausleihe2.getBuch());
            
            final Context context = new Context(new Locale("de"));
            context.setVariable("firstname", recipient.getVorname());
            context.setVariable("lastname", recipient.getNachname());
            context.setVariable("title", emailTO.getSubject()); // title of the message
            context.setVariable("message", message); // body of the message
            
            emailSender.send(
                    new InternetAddress("sba@myanga.org", "THB - Search Book App"),
                    new InternetAddress(recipient.getEmail(), recipient.getEmail()),
                    emailTO.getSubject(),
                    "",
                    "templates/info-std.html",
                    context);
            
//            emailSender.send(
//                    "sba@myanga.org", 
//                    recipientEmail, 
//                    emailTO.getSubject(),
//                    "", 
//                    "templates/info-std.html",
//                    context
//                    );
        }       
        
    }
    
    private String buildMessage(String subject, User sender, Buch copie) {
        String message = "";
        
        if (subject == "Bilder der Buchkopie") {
            return message = String.format(
                    "Der Benutzer %s %s möchte, dass Sie ihm die Möglichkeit geben, einige Fotos von dem Exemplar "
                    + "des Buches [%s] zu machen, das Sie in Ihrem Besitz haben. Wenn Sie mit seiner Bitte einverstanden "
                    + "sind, hinterlassen Sie ihm bitte eine E-Mail an folgende E-Mail Adresse %s", 
                    sender.getVorname(), sender.getNachname(), copie.getTitel(), sender.getEmail());
        }
        
        if (subject == "Weiterleitung gefordet") {
            return message = String.format(
                    "Benutzer  %s %s möchte, dass Sie ihm die Möglichkeit geben, in den Besitz des Exemplars des "
                    + "Buches [%s] zu gelangen, das Sie in Ihrem Besitz haben. Wenn Sie mit seiner Bitte für eine Weiterleitung"
                    + "dieses Exemplares einverstanden sind, hinterlassen Sie ihm bitte eine E-Mail an folgende E-Mail Adresse %s", 
                    sender.getVorname(), sender.getNachname(), copie.getTitel(), sender.getEmail());
        }
        
        return message;
    }

}
