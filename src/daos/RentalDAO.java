package daos;

import models.Person;
import models.Rental;

import java.util.List;

/**
 * Created by caoquan on 4/4/17.
 */
public interface RentalDAO {
    List<Rental> findAll();

    List<Rental> findAllAvailable();

    List<Rental> findAllBelow(int amount);

    List<Rental> findAllNumberOfRoom(int amount);

    List<Rental> findById(int id);

    List<Rental> findByRenterId(int renterId);

    List<Person> findTenantOfRental(int id);

    boolean reserveRental(Rental rental, int tenantId);

    boolean insertRental(Rental rental);

    boolean updateRental(Rental rental);

    boolean deleteRental(int rentalId);
}
