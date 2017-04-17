package server;

import actions.RequestApartmentAction;
import controllers.ApartmentController;
import utils.Constant;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveQueryApartments extends SlaveQuery {
    private String param1;
    private String param2;
    private BufferedWriter writer;
    private ApartmentController apartmentController;


    public SlaveQueryApartments(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.param1 = splittedParams[0];
        this.param2 = splittedParams[1];

        this.apartmentController = new ApartmentController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String results = "";
        switch (this.param1) {
            case RequestApartmentAction.FOR_PROPOSE:
                results = this.apartmentController.requestAllApartmentOfRenterForPropose(Integer.parseInt(this.param2));
                break;
            case RequestApartmentAction.RENTER:
                results = this.apartmentController.requestAllApartmentOfRenter(Integer.parseInt(this.param2));
                break;
            case RequestApartmentAction.ID:
                break;
        }
        writeData(results);
    }
}
