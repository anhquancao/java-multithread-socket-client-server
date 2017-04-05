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
        return null;
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
                Apartment newApartment = new Apartment(newAddress, result.getInt("num_rooms"), result.getInt("monthly_rent"), newPerson, ApartmentType.valueOf(result.getString("type")));

                apartments.add(newApartment);
                System.out.println(newApartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    @Override
    public boolean insertApartment(Apartment apartment) {
        return false;
    }

    @Override
    public boolean updateApartment(Apartment apartment) {
        return false;
    }

    @Override
    public boolean deleteAparment(Apartment apartment) {
        return false;
    }

    public static void main(String agrs[]) {
        Connection connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
        ApartmentDAOImpl test = new ApartmentDAOImpl(connection, new AddressDAOImpl(connection), new PersonDAOImpl(connection));

        test.findById(2);
    }
}
