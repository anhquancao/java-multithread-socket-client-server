package server;

import actions.UpdateRentalAction;
import controllers.PersonController;
import controllers.RentalController;
import exceptions.RentalReservedException;
import models.Person;
import utils.Constant;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveUpdateRentals extends SlaveQuery {
    private int param1;
    private int param2;
    private String param3;
    private String criteria;

    private BufferedWriter writer;
    private RentalController rentalController;
    private PersonController personController;

    public SlaveUpdateRentals(OutputStream outputStream, String params) {
        super(outputStream);
        String[] splittedParams = params.split(" ");
        this.criteria = splittedParams[0];
        this.param1 = Integer.parseInt(splittedParams[1]);
        this.param2 = Integer.parseInt(splittedParams[2]);
        if (splittedParams.length == 4) {
            this.param3 = splittedParams[3];
        }

        this.rentalController = new RentalController();
        this.personController = new PersonController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String results = "";
        switch (this.criteria) {
            case UpdateRentalAction.NEW_RENTAL:
                results = this.rentalController.requestNewRental(this.param1, this.param2);
                break;
            case UpdateRentalAction.RESERVE_RENTAL:
                String[] splittedStringDate = this.param3.split(",");
                String startDateString = splittedStringDate[0];
                String endDateString = splittedStringDate[1];
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = df.parse(startDateString);
                    endDate = df.parse(endDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    results = this.rentalController.requestReserve(this.param1, this.param2, startDate, endDate);
                } catch (RentalReservedException e) {
                    results = e.getMessage();
                }


                try {
                    // Send confirmation mail to tenant
                    Person renter = this.personController.getPersonById(this.param2);
                    Properties props = System.getProperties();

                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.host", "upn.univ-paris13.fr");

                    Session session = Session.getInstance(props);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("rentalsite@univ-paris13.fr"));
                    message.setRecipient(
                            Message.RecipientType.TO,
                            new InternetAddress(renter.getEmail()));
                    message.setSubject("Reserve Confirmation");
                    message.setText("You have successfully reserved a rental");
                    Transport.send(message);
                } catch (MessagingException e) {
                    System.out.println("Message cannot be sent due to lack of SMTP server");
                }

                break;

            case UpdateRentalAction.DELETE_RENTAL:
                results = this.rentalController.requestDeleteRental(this.param1, this.param2);
                break;

        }
        writeData(results);
    }
}
