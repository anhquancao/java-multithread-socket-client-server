package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;
import models.Apartment;
import models.Person;
import utils.ApartmentType;
import utils.RentalStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dosontung on 4/5/17.
 */
public class ApartmentDAOImpl implements ApartmentDAO {
    private Connection connection;
    private AddressDAO addressDAO;
    private PersonDAO personDAO;

    public ApartmentDAOImpl(Connection connection, AddressDAO addressDAO, PersonDAO personDAO) {
        this.connection = connection;
        this.addressDAO = addressDAO;
        this.personDAO = personDAO;
    }

    @Override
    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartment";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            apartments = getApartmentsFromStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    @Override
    public List<Apartment> findById(int apartmentId) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartment WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentId);
            apartments = getApartmentsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    @Override
    public List<Apartment> findByRenterEmail(String email) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartment WHERE apartment.renter_id " +
                "in  (SELECT id FROM person WHERE email = ?) " +
                "and id in (select apartment_id from rental where status = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, RentalStatus.AVAILABLE.toString());

            apartments = getApartmentsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    @Override
    public List<Apartment> findByRenterId(int renterId) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartment WHERE renter_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, renterId);
            apartments = getApartmentsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    private List<Apartment> getApartmentsFromStatement(PreparedStatement statement) {
        List<Apartment> apartments = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                Address newAddress = addressDAO.findById(result.getInt("address_id")).get(0);
                Person newPerson = personDAO.findById(result.getInt("renter_id")).get(0);

                Apartment newApartment = new Apartment(result.getInt("id"),
                        newAddress, result.getInt("num_rooms"), result.getInt("monthly_rent"),
                        newPerson,
                        ApartmentType.valueOf(result.getString("type")));

                apartments.add(newApartment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    @Override
    public List<Apartment> findAvailableByRenterId(int renterId) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT apartment.id AS id, address_id, renter_id, num_rooms, monthly_rent, type FROM apartment JOIN rental ON apartment.id = rental.apartment_id WHERE renter_id = ? AND status = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, renterId);
            statement.setString(2, RentalStatus.AVAILABLE.toString());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Address newAddress = addressDAO.findById(result.getInt("address_id")).get(0);
                Person newPerson = personDAO.findById(result.getInt("renter_id")).get(0);
                Apartment newApartment = new Apartment(result.getInt("id"), newAddress, result.getInt("num_rooms"), result.getInt("monthly_rent"), newPerson, ApartmentType.valueOf(result.getString("type")));

                apartments.add(newApartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    @Override
    public List<Apartment> findApartmentsOfRenter(int renterId) {
        return null;
    }

    @Override
    public boolean insertApartment(Apartment apartment) {
        String sql = "INSERT INTO apartment (address_id, num_rooms, monthly_rent, renter_id, type) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, apartment.getAddress().getId());
            statement.setInt(2, apartment.getNumRooms());
            statement.setInt(3, apartment.getMonthlyRent());
            statement.setInt(4, apartment.getRenter().getId());
            statement.setString(5, apartment.getType().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateApartment(Apartment apartment) {
        String sql = "UPDATE apartment SET address_id = ?, num_rooms = ?, monthly_rent = ?, renter_id = ?, type = ?) WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, apartment.getAddress().getId());
            statement.setInt(2, apartment.getNumRooms());
            statement.setInt(3, apartment.getMonthlyRent());
            statement.setInt(4, apartment.getRenter().getId());
            statement.setString(5, apartment.getType().toString());
            statement.setInt(6, apartment.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteAparment(Apartment apartment) {
        String sql = "DELETE FROM address WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Apartment> findAvailableByRenterIdForPropose(int renterId) {
        List<Apartment> apartments = null;
        String sql = "SELECT * from apartment where renter_id=? AND " +
                "id not in (select apartment_id from rental where status = ? or status = ? )";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, renterId);
            statement.setString(2, RentalStatus.AVAILABLE.toString());
            statement.setString(3, RentalStatus.RENTING.toString());
            apartments = getApartmentsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    public static void main(String agrs[]) {
        Connection connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
        ApartmentDAOImpl test = new ApartmentDAOImpl(connection, new AddressDAOImpl(connection), new PersonDAOImpl(connection));
        List<Apartment> apartments = test.findById(2);
//        test.findAll();

        List<Apartment> apartments2 = test.findAvailableByRenterId(4);
        for (Iterator<Apartment> i = apartments2.iterator(); i.hasNext(); ) {
            Apartment item = i.next();
            System.out.println(item);
        }
    }
}
