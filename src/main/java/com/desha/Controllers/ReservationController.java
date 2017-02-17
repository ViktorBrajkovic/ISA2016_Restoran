package com.desha.Controllers;

import com.desha.Beans.Guest;
import com.desha.Beans.Reservation;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private GuestRepository guestRepository;
    private ReservationRepository repository;

    @Autowired
    public ReservationController(GuestRepository guestRepository, ReservationRepository repository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    List<Reservation> getAll() {
        return repository.findAll();
    }


    @RequestMapping(value = "/allActive/{id}")
    List<Reservation> getAllActive(@PathVariable long id) {
        Guest host = guestRepository.findOne(id);
        return repository.findByHostAndDateTimeAfter(host, new Date());
    }

    @RequestMapping(value = "/allInactive/{id}")
    List<Reservation> getAllInactive(@PathVariable long id) {
        Guest host = guestRepository.findOne(id);
        return repository.findByHostAndDateTimeBefore(host, new Date());
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    Reservation addReservation(@RequestBody Reservation reservation) {
        repository.save(reservation);
        return reservation;
    }


}
