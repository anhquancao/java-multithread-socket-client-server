package controllers;

import daos.PersonDAO;
import models.Person;

import java.util.List;

/**
 * Created by dosontung on 4/9/17.
 */
public class PersonController extends Controller {
    private PersonDAO personDAO;

    public static final String ALLTENANT = "ALLTENANT";

    public PersonController() {
        super();
        this.personDAO = this.daoFactory.getPersonDAO();
    }

    public String requestListAllTenant() {
        List<Person> tenants = this.personDAO.findAllTenant();
        return renderResult(tenants);
    }

    public String requestListAllTenantByRenter(String email) {
        List<Person> tenants = this.personDAO.findAllTenantByRenter(email);
        return renderResult(tenants);
    }
}
