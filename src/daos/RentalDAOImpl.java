package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;
import models.Apartment;
import models.Person;
import models.Rental;
import utils.ApartmentType;
import utils.RentalStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoquan on 4/4/17.
 */
public class RentalDAOImpl implements RentalDAO {

    private Connection connection;
    private PersonDAO personDAO;
    private ApartmentDAO apartmentDAO;

    public RentalDAOImpl(Connection connection, PersonDAO personDAO, ApartmentDAO apartmentDAO) {
        this.connection = connection;
        this.personDAO = personDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public List<Rental> findAll() {
        String sql = "SELECT * FROM rental";
        List<Rental> rentals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Rental newRental = null;
                Apartment newApartment = apartmentDAO.findById(result.getInt("apartment_id")).get(0);
                if (result.getInt("tenant_id") == 0) {
                    newRental = new Rental(RentalStatus.valueOf(result.getString("status")), newApartment);
                } else {
                    Person newPerson = personDAO.findById(result.getInt("tenant_id")).get(0);
                    newRental = new Rental(RentalStatus.valueOf(result.getString("status")), newApartment, newPerson);
                }
                System.out.println(newRental);
                rentals.add(newRental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    @Override
    public List<Rental> findAllBelow() {
        return null;
    }

    @Override
    public List<Rental> findAllNumberOfRoom() {
        return null;
    }

    @Override
    public List<Rental> findById(int id) {

        return null;
    }

    @Override
    public boolean insertRental(Rental rental) {
        return false;
    }

    @Override
    public boolean updateRental(Rental rental) {
        return false;
    }

    @Override
    public boolean deleteRental(Rental rental) {
        return false;
    }

    public static void main(String[] args) {
        Connection connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
        RentalDAOImpl test = new RentalDAOImpl(connection,
                new PersonDAOImpl(connection), new ApartmentDAOImpl(connection, new AddressDAOImpl(connection), new PersonDAOImpl(connection)));
        test.findAll();

    }

}
