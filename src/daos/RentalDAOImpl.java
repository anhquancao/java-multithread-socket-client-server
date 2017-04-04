package daos;

import database.SQLiteJDBCDriverConnection;
import models.Rental;

import java.sql.Connection;
import java.util.List;

/**
 * Created by caoquan on 4/4/17.
 */
public class RentalDAOImpl implements RentalDAO {

    private Connection connection;

    public RentalDAOImpl(Connection connection) {
        this.connection = SQLiteJDBCDriverConnection.getInstance().getConnection();
    }

    @Override
    public List<Rental> findAll() {
        return null;
    }

    @Override
    public List<Rental> findById() {

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

    }

}
