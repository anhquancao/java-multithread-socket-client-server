package models;

import utils.PersonType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caoquan on 4/4/17.
 */
public class Person {
    private int id;
    private String email;
    private PersonType personType;
    private String passwordHash;

    public Person(String email, PersonType personType) {
        this.email = email;
        this.personType = personType;
    }

    public Person(int id, String email, PersonType personType) {
        this.id = id;
        this.email = email;
        this.personType = personType;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = md5(password);
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

    public String md5(String input) {
        String md5 = null;
        if (null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
