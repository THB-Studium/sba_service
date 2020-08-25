package com.example.demo.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Buch;
import com.example.demo.model.Reservierung;
import com.example.demo.model.User;
import com.example.demo.repository.ReservierungRepository;
import com.example.demo.transfert.ReservierungTO;

@Service
@Transactional(rollbackOn = Exception.class)
public class ReservierungService {

    @Autowired
    private ReservierungRepository reservierungRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BuchService buchService;

    
    
    
    /**
     * TO LIST ALL RESERVIERUNG
     * 
     * @return
     */
    public Set<Reservierung> listAll() {
        return reservierungRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO LIST ALL RESERVIERUNG BY USER ID
     * 
     * @param userId
     * @return
     */
    public Set<Reservierung> listAllByUserId(UUID userId) {
        return reservierungRepository.findAllByUserId(userId);
    }

    /**
     * TO GET ONE RESERVIERUNG BY ID
     * 
     * @param reservierungId
     * @return
     */
    public Reservierung getOneById(UUID reservierungId) {
        Optional<Reservierung> reservierungOp = reservierungRepository.findById(reservierungId);
        if (reservierungOp.isPresent()) {
            return reservierungOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Reservierung with the id %s does not exist", reservierungId.toString()));
        }
    }

    /**
     * TO GET ONE RESERVIERUNG BY SIGNATUR
     * 
     * @param reservierungId
     * @return
     */
    public Reservierung getOneByBuchSignatur(String signatur) {
        Reservierung reservierungFound = reservierungRepository.findOneByBuchSignatur(signatur);
        if (reservierungFound != null) {
            return reservierungFound;
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Reservierung with the signatur [ %s ] does not exist", signatur));
        }
    }

    /**
     * TO CREATE A NEW RESERVIERUNG
     * 
     * @param newReservierung
     * @return
     */
    public Reservierung create(ReservierungTO reservierungTO) {
        User user = userService.getOneById(reservierungTO.getUserId());
        Buch buch = buchService.getOne(reservierungTO.getBuchId());

        Reservierung newReservierung = new Reservierung();
        newReservierung.setId(null);
        newReservierung.setUser(user);
        newReservierung.setBuch(buch);
        newReservierung.setVon(new Date());
        newReservierung.setBis(new Date(newReservierung.getVon().getTime() + (3 * 3600000)));

        return reservierungRepository.save(newReservierung);
    }

    /**
     * TO DELETE AN AUSLEIHE
     * 
     * @param reservierungId
     */
    public void deleteReservierung(UUID reservierungId) {
        Reservierung reservierungFound = getOneById(reservierungId);
        reservierungRepository.delete(reservierungFound);
    }

}
