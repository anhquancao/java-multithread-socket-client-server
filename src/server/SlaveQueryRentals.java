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
            case RequestRentalAction.ALL:
                System.out.println("all");
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
                String results = this.rentalController.requestRentalBelowRent(this.param2);
                try {
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.ROOM:
                break;
        }
    }
}
