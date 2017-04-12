package daos;

import models.Apartment;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface ApartmentDAO {
    List<Apartment> findAll();

    List<Apartment> findById(int id);

    List<Apartment> findByRenterEmail(String email);

    List<Apartment> findByRenterId(int id);

    List<Apartment> findAvailableByRenterId(int id);

    boolean insertApartment(Apartment apartment);

    boolean updateApartment(Apartment apartment);

    boolean deleteAparment(Apartment apartment);
}
