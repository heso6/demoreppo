package pl.pawellukaszewski.demoreppo.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import lombok.NoArgsConstructor;

import pl.pawellukaszewski.demoreppo.models.forms.ReservationForm;


import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@Table(name = "reservation")
@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.NONE)
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;
    //    @Column(name = "name1")
    //    jesli bysmy inna nazwe w sql a inna w javie, tak jak zrobilismy z nazwa klasy
    @XmlElement
    private String name;
    @XmlElement
    private String lastname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @XmlElement
    private LocalDate date;
    @XmlElement
    private String adres;


    public ReservationModel(ReservationForm form) {
        name = form.getName();
        lastname = form.getLastname();
        date = form.getFormatedDate();
        adres = form.getAdres();
    }
}
