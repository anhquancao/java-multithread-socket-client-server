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

    public SlaveQueryRentals(OutputStream outputStream, String param1, int param2) {
        super(outputStream);
        this.param1 = param1;
        this.param2 = param2;
        this.rentalController = new RentalController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public SlaveQueryRentals(OutputStream outputStream, String param1) {
        super(outputStream);
        this.param1 = param1;
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
