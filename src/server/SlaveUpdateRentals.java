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
import java.util.Properties;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveUpdateRentals extends SlaveQuery {
    private int param1;
    private int param2;
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
        switch (this.criteria) {
            case UpdateRentalAction.NEW_RENTAL:
                try {
                    String results = this.rentalController.requestNewRental(this.param1, this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case UpdateRentalAction.RESERVE_RENTAL:
                String results = null;
                try {
                    results = this.rentalController.requestReserve(this.param1, this.param2);
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


                try {
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case UpdateRentalAction.DELETE_RENTAL:
                String deleteResults = null;
                deleteResults = this.rentalController.requestDeleteRental(this.param1, this.param2);
                try {
                    writer.write(deleteResults);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }

    }
}
