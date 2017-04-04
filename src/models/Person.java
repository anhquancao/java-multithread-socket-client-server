package models;

/**
 * Created by caoquan on 4/4/17.
 */
public class Person {
    private int id;
    private String email;

    public Person(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
