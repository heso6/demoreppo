package pl.pawellukaszewski.demoreppo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pawellukaszewski.demoreppo.models.ReservationModel;
import pl.pawellukaszewski.demoreppo.models.repositories.ReservationRepository;

@Controller
public class RestController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity reservation() {

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
}
