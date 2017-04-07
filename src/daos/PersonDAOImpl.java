package daos;

import database.SQLiteJDBCDriverConnection;
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


    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Person newPerson = null;
                if (result.getString("type").equalsIgnoreCase(PersonType.RENTER.toString())) {
                    newPerson = new Renter(result.getString("email"));
                }
                if (result.getString("type").equalsIgnoreCase(PersonType.TENANT.toString())) {
                    newPerson = new Tenant(result.getString("email"));
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
    public List<Person> findById(int personId) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Person newPerson = null;
                if (result.getString("type").equalsIgnoreCase(PersonType.RENTER.toString())) {
                    newPerson = new Renter(result.getString("email"));
                }
                if (result.getString("type").equalsIgnoreCase(PersonType.TENANT.toString())) {
                    newPerson = new Tenant(result.getString("email"));
                }
                persons.add(newPerson);
//                System.out.println(newPerson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public static void main(String args[]) {
        PersonDAOImpl test = new PersonDAOImpl(SQLiteJDBCDriverConnection.getInstance().getConnection());

        test.findById(1);
        test.findAll();
    }
}
