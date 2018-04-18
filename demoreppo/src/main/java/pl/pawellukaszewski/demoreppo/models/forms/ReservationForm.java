package pl.pawellukaszewski.demoreppo.models.forms;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ReservationForm {
    @Getter
    @Setter
    @NotBlank
    @Size(min = 3, max = 25)
    private String name;
    @Getter
    @Setter
    @NotBlank
    @Size(min = 3, max = 25)
    private String lastname;
    @Getter
    @Setter
    @NotBlank
    @Pattern(regexp = "2[0-9]{3}-[0-9][0-9]-[0-9][0-9]", message = "wypełnij pole poprawnie yyyy-mm-dd")
    private String date;
    @Getter
    @Setter
    @NotBlank
    @Size(min = 3, max = 80)
    private String adres;


    //        YYYY-MM-DD;
    //        stare podejscie do czasu w javie
    //        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //        nowe podedjscie o czasu
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate getFormatedDate() {
//        YYYY-MM-DD;

        return LocalDate.parse(date, format);

    }
}
