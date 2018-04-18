package pl.pawellukaszewski.demoreppo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pawellukaszewski.demoreppo.models.repositories.ReservationRepository;

import java.time.LocalDate;

@Controller
public class RemoveController {
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/removeReservation")
    public String remove(Model model) {
        model.addAttribute("errorInfo", "Zarezerwowano poprawnie");
        model.addAttribute("reservations", reservationRepository.findByDateBetweenOrderByDateAsc(LocalDate.now(),
                LocalDate.now().plusWeeks(1)));
        return "removeReservation";
    }
}
