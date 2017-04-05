package actions;

/**
 * Created by caoquan on 4/5/17.
 */
public abstract class Action {
    private String type;

    public Action(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String command();
}
