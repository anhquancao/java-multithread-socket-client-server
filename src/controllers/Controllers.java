package controllers;

import daofactory.DAOFactory;
import daofactory.DefaultDAOFactory;

import java.util.List;

/**
 * Created by dosontung on 4/8/17.
 */
public abstract class Controllers {
    protected DAOFactory daoFactory;

    public Controllers() {
        this.daoFactory = DefaultDAOFactory.getInstance();
    }

    protected <T> String renderResult(List<T> ts) {
        StringBuilder results = new StringBuilder();
        if (!ts.isEmpty()) {
            for (T t : ts) {
                results.append(t).append("\n");
            }
            return results.toString();
        } else {
            return "There is no result for your request";
        }
    }
}
