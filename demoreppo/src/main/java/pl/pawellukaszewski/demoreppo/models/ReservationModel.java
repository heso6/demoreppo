package pl.pawellukaszewski.demoreppo.models;


import lombok.Data;

import lombok.NoArgsConstructor;

import pl.pawellukaszewski.demoreppo.models.forms.ReservationForm;


import javax.persistence.*;

import java.time.LocalDate;



@Data
@Entity
@NoArgsConstructor
@Table(name = "reservation")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //    @Column(name = "name1")
    //    jesli bysmy inna nazwe w sql a inna w javie, tak jak zrobilismy z nazwa klasy
    private String name;
    private String lastname;
    private LocalDate date;
    private String adres;




    public ReservationModel(ReservationForm form) {
        name = form.getName();
        lastname = form.getLastname();
        date = form.getFormatedDate();
        adres = form.getAdres();
    }
}
