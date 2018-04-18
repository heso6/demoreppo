package pl.pawellukaszewski.demoreppo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.pawellukaszewski.demoreppo.models.ReservationModel;
import pl.pawellukaszewski.demoreppo.models.forms.ReservationForm;
import pl.pawellukaszewski.demoreppo.models.repositories.ReservationRepository;
import pl.pawellukaszewski.demoreppo.models.services.StringService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;


@Controller
public class MainController {
    @Autowired
    MessageSource messageSource;

    @Autowired
    StringService stringService;

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/")
    public String index(Model model) {
//        w tym miejscu bedziemy zapisywac recznie cos do tabeli (encji)
//        ReservationModel model = new ReservationModel();
////        model.setAdres("Warszawa");
////        model.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
////        model.setLastname("Lukaszewski");
////        model.setName("Roman");
////
////        reservationRepository.save(model);

        model.addAttribute("reservationForm", new ReservationForm());
        model.addAttribute("reservations", reservationRepository.findByDateBetweenOrderByDateAsc(LocalDate.now(),
                LocalDate.now().plusWeeks(1)));
        return "index";
    }

    @PostMapping("/")
    public String index(@ModelAttribute("reservationForm") @Valid ReservationForm form, BindingResult result, Model model, Locale locale) {

        model.addAttribute("reservations", reservationRepository.findByDateBetweenOrderByDateAsc(LocalDate.now(),
                LocalDate.now().plusWeeks(1)));


        if (result.hasErrors()) {
            model.addAttribute("errorInfo", "Błąd podczas rezerwacji");
            return "index";
        } else if (reservationRepository.existsByDateEquals(form.getFormatedDate())) {
            model.addAttribute("errorDate", "Ta data jest juz zajęta");
            return "index";
        } else {
            String s = messageSource.getMessage("hello.messagee", new Object[]{"zupcionek"}, locale);
            model.addAttribute("infoMessage", s);
            reservationRepository.save(new ReservationModel(form));
            return "redirect:/removeReservation";

        }


    }

//    @GetMapping("/test")
//    @ResponseBody
//    public String index() {
//// Dluzsza wersja
////        List<ReservationModel> reservationModel = reservationRepository.findByName("Oskar");
////return reservationModel.toString();
////        wyswietlanie listy osob za pomoca petli
////        StringBuilder builder = new StringBuilder();
////        for (ReservationModel model : reservationModel){
////            builder.append(model.toString());
////        }
////        i za pomoca stumieni
////        return reservationModel.stream().map(s -> s.toString()).collect(Collectors.joining(" , "));
//
////        return reservationRepository.findByNameAndLastname("Roman","Franek").getDate().toString();
//
//        return reservationRepository.findByDate(LocalDate.of(2018, 11, 26)).toString();
//    }
}
