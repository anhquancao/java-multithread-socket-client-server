package server;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQueryAppartments extends SlaveQuery {
    private String criteria;
    private int value;
    private OutputStreamWriter writer;

    public static final String ALL = "ALL";
    public static final String RENT = "RENT";
    public static final String ROOM = "ROOM";


    public SlaveQueryAppartments(OutputStream outputStream, String criteria, int value) {
        super(outputStream);
        this.criteria = criteria;
        this.value = value;
    }

    public SlaveQueryAppartments(OutputStream outputStream, String criteria) {
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
