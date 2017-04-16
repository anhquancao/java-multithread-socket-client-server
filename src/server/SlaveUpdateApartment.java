package server;

import actions.UpdateApartmentAction;
import controllers.ApartmentController;
import models.Address;
import models.Apartment;
import models.Person;
import utils.ApartmentType;
import utils.Constant;
import utils.PersonType;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveUpdateApartment extends SlaveQuery {
    private String queryType;
    private PersonType type;
    private ApartmentController apartmentController;
    private BufferedWriter writer;
    private String body;

    public SlaveUpdateApartment(OutputStream outputStream, String params) {
        super(outputStream);

        String[] splittedParams = params.split(" ");
        this.queryType = splittedParams[0];
        this.body = splittedParams[1];

        this.apartmentController = new ApartmentController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.queryType) {
            case UpdateApartmentAction.NEW:
                try {
                    String[] splittedBody = this.body.split(",");
                    Address address = new Address(splittedBody[0], Integer.parseInt(splittedBody[1]));
                    int renterId = Integer.parseInt(splittedBody[4]);
                    Person person = new Person(renterId);
                    int numRooms = Integer.parseInt(splittedBody[2]);
                    int monthlyRent = Integer.parseInt(splittedBody[3]);
                    Apartment apartment = new Apartment(address, numRooms, monthlyRent, person, ApartmentType.valueOf(splittedBody[5]));
                    String result = this.apartmentController.addApartment(apartment);
                    writer.write(result);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
