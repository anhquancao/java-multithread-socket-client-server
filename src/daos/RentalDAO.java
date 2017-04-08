package daos;

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

    boolean insertRental(Rental rental);

    boolean updateRental(Rental rental);

    boolean deleteRental(Rental rental);
}
