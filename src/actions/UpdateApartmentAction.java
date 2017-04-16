package actions;

/**
 * Created by dosontung on 4/9/17.
 */
public class UpdateApartmentAction extends Action {
    private String param1;
    private String param2;

    public static final String NEW = "NEW";


    public UpdateApartmentAction(String param1, String param2) {
        super(ActionTypes.UPDATE_APARTMENT);
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public String command() {
        return getType() + " " + param1 + " " + param2;
    }
}
