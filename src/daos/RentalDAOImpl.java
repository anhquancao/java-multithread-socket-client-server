package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;
import models.Apartment;
import models.Person;
import models.Rental;
import utils.ApartmentType;
import utils.PersonType;
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
    public List<Rental> findAllAvailable() {
        String sql = "SELECT * FROM rental WHERE status = ?";
        List<Rental> rentals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, RentalStatus.AVAILABLE.toString());

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
    public List<Rental> findAllBelow(int amount) {
        String sql = "SELECT * FROM rental JOIN apartment ON rental.apartment_id = apartment.id WHERE  monthly_rent < ?";
        List<Rental> rentals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, amount);

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
    public List<Rental> findAllNumberOfRoom(int amount) {
        String sql = "SELECT apartment_id, status, tenant_id FROM rental JOIN apartment ON rental.apartment_id = apartment.id WHERE apartment.num_rooms = ? AND rental.status = ?";
        List<Rental> rentals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, amount);
            statement.setString(2, RentalStatus.AVAILABLE.toString());

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
    public List<Rental> findById(int id) {
        String sql = "SELECT * FROM rental WHERE  id = ?";
        List<Rental> rentals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Rental newRental = null;
                Apartment newApartment = apartmentDAO.findById(result.getInt("apartment_id")).get(0);
                if (result.getInt("tenant_id") == 0) {
                    newRental = new Rental(result.getInt("id"), RentalStatus.valueOf(result.getString("status")), newApartment);
                } else {
                    Person newPerson = personDAO.findById(result.getInt("tenant_id")).get(0);
                    newRental = new Rental(result.getInt("id"), RentalStatus.valueOf(result.getString("status")), newApartment, newPerson);
                }
//                System.out.println(newRental);
                rentals.add(newRental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    @Override
    public List<Person> findTenantOfRental(int rentalId) {
        String sql = "SELECT * FROM rental WHERE  id = ?";
        String sql2 = "SELECT * FROM person WHERE id = ?";
        List<Person> persons = new ArrayList<>();
        int tenantId = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, rentalId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                tenantId = result.getInt("tenant_id");
            }
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, tenantId);

            ResultSet result2 = statement2.executeQuery();

            while (result2.next()) {
                Person newPerson = new Person(result2.getInt("id"), result2.getString("email"), PersonType.valueOf(result2.getString("type")));
                persons.add(newPerson);
                System.out.println(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public boolean insertRental(Rental rental) {
        String sql = "INSERT INTO rental (apartment_id,tenant_id,status) VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, rental.getApartment().getId());
            statement.setInt(2, rental.getTenant().getId());
            statement.setString(3, rental.getStatus().toString());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean updateRental(Rental rental) {
        String sql = "UPDATE rental SET apartment_id = ?,tenant_id = ?,status = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, rental.getApartment().getId());
            statement.setInt(2, rental.getTenant().getId());
            statement.setString(3, rental.getStatus().toString());
            statement.setInt(4, rental.getId());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean deleteRental(Rental rental) {
        String sql = "DELETE FROM rental WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) {
        Connection connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
        RentalDAOImpl test = new RentalDAOImpl(connection,
                new PersonDAOImpl(connection), new ApartmentDAOImpl(connection, new AddressDAOImpl(connection), new PersonDAOImpl(connection)));
//        test.findAll();
//        test.findAllBelow(1600);
//        test.findAllNumberOfRoom(3);
//        System.out.println("\n");
        Rental rental = test.findById(3).get(0);
        test.findTenantOfRental(rental.getId());
//        test.findAllAvailable();

    }

}
