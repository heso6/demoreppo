package pl.pawellukaszewski.demoreppo.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pawellukaszewski.demoreppo.models.ReservationModel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Integer> {
    //

    //
//    ReservationModel findByNameAndLastname(String name, String lastname);
//
//    List<ReservationModel> findByLastnameContaining(String text);
//
//    //    List<ReservationModel> findByDateGreaterThan(int id);
//
//    List<ReservationModel> findByDate(LocalDate id);
    List<ReservationModel> findByLastnameIgnoreCase(String lastname);

    boolean existsByDateEquals(LocalDate date);

    List<ReservationModel> findByDateBetweenOrderByDateAsc(LocalDate date1, LocalDate date2);


}
