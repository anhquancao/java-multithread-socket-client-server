package actions;

/**
 * Created by dosontung on 4/9/17.
 */
public class RequestApartmentAction extends Action {
    private String param1;
    private int param2;

    public static final String ALL = "ALL";
    public static final String ID = "ID";
    public static final String RENTER = "RENTER";
    public static final String FOR_PROPOSE = "FOR_PROPOSE";

    public RequestApartmentAction(String param1, int param2) {
        super(ActionTypes.REQUEST_APARTMENT);
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public String command() {
        return getType() + " " + param1 + " " + param2;
    }
}
