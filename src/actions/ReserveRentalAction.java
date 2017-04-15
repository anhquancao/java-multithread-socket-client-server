package actions;

/**
 * Created by caoquan on 4/5/17.
 */
public class ReserveRentalAction extends Action {
    private int rentalId;
    private int personId;

    public ReserveRentalAction(int rentalId, int personId) {
        super(ActionTypes.RESERVE_RENTAL);
        this.rentalId = rentalId;
        this.personId = personId;
    }

    @Override
    public String command() {
        return getType() + " " + this.rentalId + " " + this.personId;
    }
}
