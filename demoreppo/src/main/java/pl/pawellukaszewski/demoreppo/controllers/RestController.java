package pl.pawellukaszewski.demoreppo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pawellukaszewski.demoreppo.models.ReservationModel;
import pl.pawellukaszewski.demoreppo.models.repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RestController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity reservationIndex(@RequestHeader("Password-App") String password) {

        if (!password.equalsIgnoreCase("akademia")) {
            return new ResponseEntity("Bad userid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(reservationRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation/{lastname}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity reservation(@PathVariable("lastname") String lastname) {

        return new ResponseEntity(reservationRepository.findByLastnameIgnoreCase(lastname), HttpStatus.OK);

    }

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity reservation(@RequestBody ReservationModel model) {
        reservationRepository.save(model);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/rest/reservation/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity reservation(@PathVariable("id") int id) {
        reservationRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/rest/reservation/{id}/{date}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity reservationDateChange(@PathVariable("id") int id,
                                                @PathVariable("date") String date) {
        ReservationModel model = reservationRepository.findOne(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.from(formatter.parse(date));

        if (reservationRepository.existsByDateEquals(newDate)) {
            return new ResponseEntity("This date is busy", HttpStatus.CONFLICT);
        }
        model.setDate(newDate);

        reservationRepository.save(model);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity responseAct(@RequestBody ReservationModel reservationModel) {
        reservationRepository.save(reservationModel);
        return new ResponseEntity(HttpStatus.OK);
    }

}


