package controllers;

import daos.ApartmentDAO;
import models.Apartment;

import java.util.List;

/**
 * Created by dosontung on 4/9/17.
 */
public class ApartmentController extends Controller {
    private ApartmentDAO apartmentDAO;

    public ApartmentController() {
        super();
        this.apartmentDAO = this.daoFactory.getApartmentDAO();
    }

    public String requestAllApartmentOfRenter(String email) {
        List<Apartment> apartments = this.apartmentDAO.findByRenterEmail(email);
        return renderResult(apartments);
    }

    public String requestAllAvailableApartmentOfRenter(int renterId){
        List<Apartment> apartments = this.apartmentDAO.findAvailableByRenterId(renterId);
        return renderResult(apartments);
    }
}
