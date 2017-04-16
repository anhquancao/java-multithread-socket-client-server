package server;

import actions.UpdateRentalAction;
import controllers.RentalController;
import exceptions.RentalReservedException;
import utils.Constant;

import java.io.*;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveUpdateRentals extends SlaveQuery {
    private int param1;
    private int renterId;
    private String criteria;

    private BufferedWriter writer;
    private RentalController rentalController;

    public SlaveUpdateRentals(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.criteria = splittedParams[0];
        this.param1 = Integer.parseInt(splittedParams[1]);
        this.renterId = Integer.parseInt(splittedParams[2]);

        this.rentalController = new RentalController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.criteria) {
            case UpdateRentalAction.NEW_RENTAL:
                try {
                    String results = this.rentalController.requestNewRental(this.param1, this.renterId);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case UpdateRentalAction.RESERVE_RENTAL:
                String results = null;
                try {
                    results = this.rentalController.requestReserve(this.param1, this.renterId);
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
                break;

            case UpdateRentalAction.DELETE_RENTAL:
                String deleteResults = null;
                deleteResults = this.rentalController.requestDeleteRental(this.param1, this.renterId);
                try {
                    writer.write(deleteResults);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

    }
}
