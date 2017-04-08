package daofactory;

import daos.*;
import database.SQLiteJDBCDriverConnection;

import java.sql.Connection;

/**
 * Created by caoquan on 4/8/17.
 */
public class DefaultDAOFactory implements DAOFactory {

    private static DAOFactory daoFactory;
    private static PersonDAO personDAO;
    private static ApartmentDAO apartmentDAO;
    private static AddressDAO addressDAO;
    private static RentalDAO rentalDAO;

    private Connection connection;

    private DefaultDAOFactory() {
        this.connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
    }

    public static DAOFactory getInstance() {
        if (DefaultDAOFactory.daoFactory == null) {
            DefaultDAOFactory.daoFactory = new DefaultDAOFactory();
        }
        return DefaultDAOFactory.daoFactory;
    }

    @Override
    public AddressDAO getAddressDAO() {
        if (DefaultDAOFactory.addressDAO == null) {
            DefaultDAOFactory.addressDAO = new AddressDAOImpl(this.connection);
        }
        return DefaultDAOFactory.addressDAO;
    }

    @Override
    public RentalDAO getRentalDAO() {
        if (DefaultDAOFactory.rentalDAO == null) {
            DefaultDAOFactory.rentalDAO = new RentalDAOImpl(this.connection, getPersonDAO(), getApartmentDAO());
        }
        return DefaultDAOFactory.rentalDAO;
    }

    @Override
    public ApartmentDAO getApartmentDAO() {
        if (DefaultDAOFactory.apartmentDAO == null) {
            DefaultDAOFactory.apartmentDAO = new ApartmentDAOImpl(this.connection, getAddressDAO(), getPersonDAO());
        }
        return DefaultDAOFactory.apartmentDAO;
    }

    @Override
    public PersonDAO getPersonDAO() {
        if (DefaultDAOFactory.personDAO == null) {
            DefaultDAOFactory.personDAO = new PersonDAOImpl(this.connection);
        }
        return DefaultDAOFactory.personDAO;
    }
}
