package daos;

import models.Person;

import java.util.List;

/**
 * Created by caoquan on 4/5/17.
 */
public interface PersonDAO {
    List<Person> findAll();

    List<Person> findById(int personId);


}
