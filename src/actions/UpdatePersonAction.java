package actions;

import utils.PersonType;

/**
 * Created by dosontung on 4/9/17.
 */
public class UpdatePersonAction extends Action {
    private String queryType;
    private String email;
    private PersonType type;
    private String password;

    //List of Action for Person Schema
    public static final String CREATE = "CREATE";

    public UpdatePersonAction(String queryType, String email, PersonType type, String password) {
        super(ActionTypes.UPDATE_PERSON);
        this.queryType = queryType;
        this.email = email;
        this.type = type;
        this.password = password;
    }


    @Override
    public String command() {
        return getType() + " " + queryType + " " + email + " " + type + " " + password;
    }
}
