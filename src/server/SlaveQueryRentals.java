package server;

import actions.RequestRentalAction;
import controllers.RentalController;
import utils.Constant;

import java.io.*;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQueryRentals extends SlaveQuery {
    private String param1;
    private int param2;
    private BufferedWriter writer;
    private RentalController rentalController;

    public SlaveQueryRentals(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.param1 = splittedParams[0];
        this.param2 = Integer.parseInt(splittedParams[1]);

        this.rentalController = new RentalController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.param1) {
            case RequestRentalAction.RENTER:
                try {
                    String results = this.rentalController.requestRentalsByRenter(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.ALL:
                try {
                    String results = this.rentalController.requestAllAvailableRentals();
                    writer.write(results);
                    writer.newLine();
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.RENT:
                try {
                    String results = this.rentalController.requestRentalBelowRent(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.ROOM:
                try {
                    String results = this.rentalController.requestRenterByNumberOfRooms(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case RequestRentalAction.TENANT:
                try {
                    String results = this.rentalController.requestTenantOfRental(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.NEWRENTAL:
                try {
                    String results = this.rentalController.requestNewRental(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.DELETERENTAL:
                try {
                    String results = this.rentalController.requestDeleteRental(this.param2);
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
