package daos;

import models.Address;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface AddressDAO {
    List<Address> findAll();

    List<Address> findById(int id);

    boolean insertAddress(Address address);

    boolean updateAddress(Address address);

    boolean deleteAddress(Address address);
}
