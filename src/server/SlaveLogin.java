package server;

import controllers.PersonController;
import models.Person;
import utils.Constant;
import utils.PersonType;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveLogin extends SlaveQuery {
    private String queryType;
    private String email;
    private PersonType type;
    private String password;
    private PersonController personController;
    private BufferedWriter writer;

    public SlaveLogin(OutputStream outputStream, String params) {
        super(outputStream);

        String[] splittedParams = params.split(" ");
        this.email = splittedParams[0];
        this.password = splittedParams[1];

        this.personController = new PersonController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Person person = new Person(this.email);
            person.setPasswordHash(this.password);
            String results = this.personController.login(person);
            writer.write(results);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
