package actions;

/**
 * Created by dosontung on 4/9/17.
 */
public class LoginAction extends Action {
    private String email;
    private String password;

    //List of Action for Person Schema
    public static final String CREATE = "CREATE";

    public LoginAction(String email, String password) {
        super(ActionTypes.LOGIN);
        this.email = email;
        this.password = password;
    }


    @Override
    public String command() {
        return getType() + " " + this.email + " " + this.password;
    }
}
