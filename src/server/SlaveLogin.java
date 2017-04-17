package server;

import controllers.PersonController;
import models.Person;
import utils.PersonType;

import java.io.OutputStream;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveLogin extends SlaveQuery {
    private String queryType;
    private String email;
    private PersonType type;
    private String password;
    private PersonController personController;


    public SlaveLogin(OutputStream outputStream, String params) {
        super(outputStream);

        String[] splittedParams = params.split(" ");
        this.email = splittedParams[0];
        this.password = splittedParams[1];

        this.personController = new PersonController();
    }

    @Override
    public void run() {
        Person person = new Person(this.email);
        person.setPasswordHash(this.password);
        String results = this.personController.login(person);
        writeData(results);

    }
}
