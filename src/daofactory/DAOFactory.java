package daofactory;

import daos.AddressDAO;
import daos.ApartmentDAO;
import daos.PersonDAO;
import daos.RentalDAO;

/**
 * Created by caoquan on 4/8/17.
 */
public interface DAOFactory {
    public AddressDAO getAddressDAO();

    public RentalDAO getRentalDAO();

    public ApartmentDAO getApartmentDAO();

    public PersonDAO getPersonDAO();
}
