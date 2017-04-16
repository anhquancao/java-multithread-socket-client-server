package daos;

import models.Person;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface PersonDAO {
    List<Person> findAll();

    List<Person> findAllTenant();

    List<Person> findAllTenantByRenterId(int renterId);

    List<Person> findById(int personId);

    List<Person> findByEmail(String email);

    List<Person> login(String email, String passwordHash);

    boolean insertPerson(Person person);

}
