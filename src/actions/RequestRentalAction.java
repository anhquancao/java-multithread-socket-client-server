package actions;

/**
 * Created by caoquan on 4/5/17.
 */
public class RequestRentalAction extends Action {
    private String param1;

    private int param2;

    public static final String ALL = "ALL";
    public static final String RENT = "RENT";
    public static final String ROOM = "ROOM";

    public RequestRentalAction(String param1, int param2) {
        super(ActionTypes.REQUEST_RENTAL);
        this.param1 = param1;
        this.param2 = param2;
    }

    public RequestRentalAction(String param1) {
        super(ActionTypes.REQUEST_RENTAL);
        this.param1 = param1;
    }

    @Override
    public String command() {
        return getType() + " " + param1 + " " + param2;
    }
}
