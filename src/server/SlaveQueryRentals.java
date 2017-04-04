package server;

import java.io.OutputStream;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQueryRentals extends SlaveQuery {
    private String criteria;
    private int value;

    public static final String ALL = "ALL";
    public static final String RENT = "RENT";
    public static final String ROOM = "ROOM";


    public SlaveQueryRentals(OutputStream outputStream, String criteria, int value) {
        super(outputStream);
        this.criteria = criteria;
        this.value = value;
    }

    public SlaveQueryRentals(OutputStream outputStream, String criteria) {
        super(outputStream);
        this.criteria = criteria;
    }

    @Override
    public void run() {
        switch (this.criteria) {
            case ALL:
                break;
            case RENT:
                break;
            case ROOM:
                break;
        }
    }
}
