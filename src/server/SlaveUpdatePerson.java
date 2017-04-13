package server;

import actions.UpdatePersonAction;
import controllers.PersonController;
import models.Person;
import utils.Constant;
import utils.PersonType;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveUpdatePerson extends SlaveQuery {
    private String queryType;
    private String email;
    private PersonType type;
    private String password;
    private PersonController personController;
    private BufferedWriter writer;

    public SlaveUpdatePerson(OutputStream outputStream, String params) {
        super(outputStream);

        String[] splittedParams = params.split(" ");
        this.queryType = splittedParams[0];
        this.email = splittedParams[1];
        this.type = PersonType.valueOf(splittedParams[2]);
        this.password = splittedParams[3];

        this.personController = new PersonController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.queryType) {
            case UpdatePersonAction.CREATE:
                try {
                    Person person = new Person(this.email, this.type);
                    person.setPasswordHash(this.password);
                    String results = this.personController.createPerson(person);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
