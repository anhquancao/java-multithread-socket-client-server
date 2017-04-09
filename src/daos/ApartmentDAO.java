package daos;

import models.Address;
import models.Apartment;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface ApartmentDAO {
    List<Apartment> findAll();

    List<Apartment> findById(int id);
    
    List<Apartment> findByRenterId(int id);

    boolean insertApartment(Apartment apartment);

    boolean updateApartment(Apartment apartment);

    boolean deleteAparment(Apartment apartment);
}
