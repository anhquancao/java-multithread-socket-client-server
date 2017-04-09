package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;
import models.Apartment;
import models.Person;
import utils.ApartmentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Address newAddress = addressDAO.findById(result.getInt("address_id")).get(0);
                Person newPerson = personDAO.findById(result.getInt("renter_id")).get(0);
                Apartment newApartment = new Apartment(result.getInt("id"), newAddress, result.getInt("num_rooms"), result.getInt("monthly_rent"), newPerson, ApartmentType.valueOf(result.getString("type")));

                apartments.add(newApartment);
                System.out.println(newApartment);
            }
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

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Address newAddress = addressDAO.findById(result.getInt("address_id")).get(0);
                Person newPerson = personDAO.findById(result.getInt("renter_id")).get(0);
                Apartment newApartment = new Apartment(result.getInt("id"), newAddress, result.getInt("num_rooms"), result.getInt("monthly_rent"), newPerson, ApartmentType.valueOf(result.getString("type")));

                apartments.add(newApartment);
//                System.out.println(newApartment);
            }
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

    public static void main(String agrs[]) {
        Connection connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
        ApartmentDAOImpl test = new ApartmentDAOImpl(connection, new AddressDAOImpl(connection), new PersonDAOImpl(connection));
        test.findById(2);
        test.findAll();
    }
}