package daos;

import models.Person;
import models.Renter;
import models.Tenant;
import utils.PersonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dosontung on 4/5/17.
 */
public class PersonDAOImpl implements PersonDAO {
    private Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }


    private List<Person> getPersonsFromStatement(PreparedStatement statement) {
        List<Person> persons = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Person newPerson = null;
                if (result.getString("type").equalsIgnoreCase(PersonType.RENTER.toString())) {
                    newPerson = new Renter(result.getInt("id"), result.getString("email"));
                }
                if (result.getString("type").equalsIgnoreCase(PersonType.TENANT.toString())) {
                    newPerson = new Tenant(result.getInt("id"), result.getString("email"));
                }
                persons.add(newPerson);
                //                System.out.println(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = null;
        String sql = "SELECT * FROM person";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> findAllTenant() {
        List<Person> persons = null;
        String sql = "SELECT * FROM person WHERE type = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, PersonType.TENANT.toString());

            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> findAllTenantByRenterId(int renterId) {
        List<Person> persons = null;
        String sql = "SELECT * FROM person where id in " +
                "(select tenant_id from rental where apartment_id in " +
                "(select id from apartment where renter_id=?))";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, renterId);
            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> findById(int personId) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);
            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> findByEmail(String email) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public List<Person> login(String email, String passwordHash) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE email = ? and password = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, passwordHash);

            persons = getPersonsFromStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public boolean insertPerson(Person person) {
        String sql = "INSERT INTO person (email, type, password) VALUES (?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, person.getEmail());
            statement.setString(2, person.getPersonType().toString());
            statement.setString(3, person.getPasswordHash());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
