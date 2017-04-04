package server;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQueryAppartments implements Runnable {
    private String criteria;
    private int value;
    public static final String ALL = "ALL";
    public static final String RENT = "ALL";

    public SlaveQueryAppartments(String criteria, int value) {
        this.criteria = criteria;
        this.value = value;
    }

    public SlaveQueryAppartments(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public void run() {

    }
}
