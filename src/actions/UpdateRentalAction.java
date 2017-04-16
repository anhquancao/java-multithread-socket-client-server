package actions;

/**
 * Created by caoquan on 4/5/17.
 */
public class UpdateRentalAction extends Action {
    private String param1;

    private int param2;
    private int renterId;

    public static final String DELETE_RENTAL = "DELETE_RENTAL";
    public static final String NEW_RENTAL = "NEW_RENTAL";
    public static final String RESERVE_RENTAL = "RESERVE_RENTAL";

    public UpdateRentalAction(String param1, int param2, int renterId) {
        super(ActionTypes.UPDATE_RENTAL);
        this.param1 = param1;
        this.param2 = param2;
        this.renterId = renterId;
    }

    public UpdateRentalAction(String param1, int param2) {
        super(ActionTypes.UPDATE_RENTAL);
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public String command() {
        return getType() + " " + param1 + " " + param2 + " " + this.renterId;
    }
}
