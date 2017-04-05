package server;

import actions.RequestRentalAction;
import utils.Constant;

import java.io.*;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQueryRentals extends SlaveQuery {
    private String criteria;
    private int value;
    private BufferedWriter writer;


    public SlaveQueryRentals(OutputStream outputStream, String criteria, int value) {
        super(outputStream);
        this.criteria = criteria;
        this.value = value;
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public SlaveQueryRentals(OutputStream outputStream, String criteria) {
        super(outputStream);
        this.criteria = criteria;
    }

    @Override
    public void run() {
        switch (this.criteria) {
            case RequestRentalAction.ALL:
                System.out.println("all");
                try {
                    writer.write("Answer from server");
                    writer.newLine();
                    writer.write("All");
                    writer.newLine();
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RequestRentalAction.RENT:
                break;
            case RequestRentalAction.ROOM:
                break;
        }
    }
}
