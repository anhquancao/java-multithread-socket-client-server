package actions;

/**
 * Created by dosontung on 4/9/17.
 */
public class RequestPersonAction extends Action {
    private String param1;
    private String param2;

    //List of Action for Person Schema
    public static final String ALLTENANT = "ALLTENANT";

    public RequestPersonAction(String param1, String param2) {
        super(ActionTypes.REQUEST_PERSON);
        this.param1 = param1;
        this.param2 = param2;
    }

    public RequestPersonAction(String param1) {
        super(ActionTypes.REQUEST_PERSON);
        this.param1 = param1;
    }

    @Override
    public String command() {
        return getType() + " " + param1 + " " + param2;
    }
}
