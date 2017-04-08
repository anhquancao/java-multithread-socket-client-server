package daofactory;

import daos.AddressDAO;
import daos.ApartmentDAO;
import daos.PersonDAO;
import daos.RentalDAO;

/**
 * Created by caoquan on 4/8/17.
 */
public interface DAOFactory {
    AddressDAO getAddressDAO();

    RentalDAO getRentalDAO();

    ApartmentDAO getApartmentDAO();

    PersonDAO getPersonDAO();
}
