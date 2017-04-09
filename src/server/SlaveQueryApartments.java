package server;

import actions.RequestApartmentAction;
import controllers.ApartmentController;
import models.Apartment;
import utils.Constant;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveQueryApartments extends SlaveQuery {
    private String param1;
    private int param2;
    private BufferedWriter writer;
    private ApartmentController apartmentController;


    public SlaveQueryApartments(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.param1 = splittedParams[0];
        this.param2 = Integer.parseInt(splittedParams[1]);

        this.apartmentController = new ApartmentController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.param1) {
            case RequestApartmentAction.ALL:
                break;
            case RequestApartmentAction.RENTER:
                try {
                    String results = this.apartmentController.requestAllApartmentOfRenter(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestApartmentAction.ID:
                break;
        }
    }
}
