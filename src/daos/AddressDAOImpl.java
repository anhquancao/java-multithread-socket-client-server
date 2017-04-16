package daos;

import database.SQLiteJDBCDriverConnection;
import models.Address;

import java.sql.*;
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
        String sql = "SELECT * FROM address";
        List<Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Address newAddr = new Address(result.getInt("id"), result.getString("street"), result.getInt("postal"));
//                System.out.println(newAddr);
                addresses.add(newAddr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }


    @Override
    public List<Address> findById(int id) {
        String sql = "SELECT id, street, postal FROM address WHERE id = ?";
        List<Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Address newAddr = new Address(result.getInt("id"), result.getString("street"), result.getInt("postal"));
//                System.out.println(newAddr);
                addresses.add(newAddr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public int insertAddress(Address address) {
        String sql = "INSERT INTO address (street, postal) VALUES (?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getPostalCode());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int id = 0;
        try {
            id = statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updateAddress(Address address) {
        String sql = "UPDATE address SET street = ? , postal = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getPostalCode());
            statement.setInt(3, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteAddress(Address address) {
        String sql = "DELETE FROM address WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String agrs[]) {
        AddressDAO test = new AddressDAOImpl(SQLiteJDBCDriverConnection.getInstance().getConnection());
        test.findAll();
        test.findById(2);

        Address newAddress = new Address(8, "Hanoi", 100000);
//        Address newAddress2 = new Address(7, "Sai Gon", 250000);
//        test.insertAddress(newAddress);
//        test.findAll();
//        test.updateAddress(newAddress2);
//        test.findAll();
        test.deleteAddress(newAddress);
        test.findAll();

    }
}
