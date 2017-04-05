package daos;

import models.Address;
import models.Rental;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface AddressDAO {
    List<Address> findAll();

    List<Address> findById(int id);

    boolean insertAddress(Address address);

    boolean updateRental(Address address);

    boolean deleteRental(Rental rental);
}
