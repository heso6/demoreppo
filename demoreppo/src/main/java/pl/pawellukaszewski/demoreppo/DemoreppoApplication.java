package pl.pawellukaszewski.demoreppo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
////		Dzieki Jrs310 JPA jest w stanie przekonwertowac java.sql.Date na nowoczesna klase LocalDate
////		hibernate bedzie w stanie  zamienic date przychodzaca z bazy danych na nowy sposob wyrazania daty LocalDate
// Lub wykorzystac sterowniki:
//        <dependency>
//            <groupId>org.hibernate</groupId>
//            <artifactId>hibernate-java8</artifactId>
//            <version>5.1.0.Final</version>
//        </dependency>
		basePackageClasses = {DemoreppoApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class DemoreppoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoreppoApplication.class, args);
    }
}
