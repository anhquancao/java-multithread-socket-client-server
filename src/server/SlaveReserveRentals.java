package server;

import controllers.RentalController;
import exceptions.RentalReservedException;
import utils.Constant;

import java.io.*;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveReserveRentals extends SlaveQuery {
    private int rentalId;
    private int personId;
    private BufferedWriter writer;
    private RentalController rentalController;

    public SlaveReserveRentals(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.rentalId = Integer.parseInt(splittedParams[0]);
        this.personId = Integer.parseInt(splittedParams[1]);

        this.rentalController = new RentalController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String results = null;
        try {
            results = this.rentalController.requestReserve(this.rentalId, this.personId);
        } catch (RentalReservedException e) {
            results = e.getMessage();
        }

        try {
            writer.write(results);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
