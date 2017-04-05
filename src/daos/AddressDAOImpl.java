package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dosontung on 4/5/17.
 */
public class AddressDAOImpl implements AddressDAO {
    private Connection connection;

    public AddressDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Address> findAll() {
        String sql = "SELECT id, street, postal FROM address";
        List<Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Address newAddr = new Address(result.getInt("id"), result.getString("street"), result.getInt("postal"));
                System.out.println(newAddr);
                addresses.add(newAddr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }


    @Override
    public List<Address> findById(int id) {
        return null;
    }

    @Override
    public boolean insertAddress(Address address) {
        return false;
    }

    @Override
    public boolean updateAddress(Address address) {
        return false;
    }

    @Override
    public boolean deleteAddress(Address address) {
        return false;
    }

    public static void main(String agrs[]) {
        AddressDAO test = new AddressDAOImpl(SQLiteJDBCDriverConnection.getInstance().getConnection());
        test.findAll();
    }
}
