package models;

import utils.PersonType;

/**
 * Created by caoquan on 4/4/17.
 */
public class Person {
    private int id;
    private String email;
    private PersonType personType;

    public Person(String email, PersonType personType) {
        this.email = email;
        this.personType = personType;
    }

    public Person(int id, String email, PersonType personType) {
        this.id = id;
        this.email = email;
        this.personType = personType;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", personType=" + personType +
                '}';
    }
}
