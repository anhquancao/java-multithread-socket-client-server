package server;

import actions.ActionTypes;
import utils.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caoquan on 4/4/17.
 */
public class MasterOrdinary extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService service;
    private int port;

    public MasterOrdinary(int port) {
        try {
            serverSocket = new ServerSocket(port);
            this.port = port;
            service = Executors.newFixedThreadPool(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server Master Ordinary is listen at " + port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Accept");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Constant.CHARSET));
                // Read command
                String input = reader.readLine();

                // parse command
                String[] splittedStr = input.split(" ", 2);
                System.out.println(splittedStr[0] + " " +splittedStr[1]);
                String command = splittedStr[0];
                switch (command) {
                    case ActionTypes.UPDATE_RENTAL:
                        SlaveUpdateRentals slaveUpdateRentals =
                                new SlaveUpdateRentals(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveUpdateRentals);
                        break;
                    case ActionTypes.REQUEST_RENTAL:
                        SlaveQueryRentals slaveQueryRentals =
                                new SlaveQueryRentals(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveQueryRentals);
                        break;
                    case ActionTypes.REQUEST_APARTMENT:
                        // Renter request apartment
                        SlaveQueryApartments slaveQueryApartments = new SlaveQueryApartments(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveQueryApartments);
                        break;
                    case ActionTypes.UPDATE_APARTMENT:
                        // Renter request apartment
                        SlaveUpdateApartment slaveUpdateApartment = new SlaveUpdateApartment(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveUpdateApartment);
                        break;
                    case ActionTypes.REQUEST_PERSON:
                        SlaveQueryPersons slaveQueryPersons = new SlaveQueryPersons(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveQueryPersons);
                        break;
                    case ActionTypes.UPDATE_PERSON:
                        SlaveUpdatePerson slaveUpdatePerson = new SlaveUpdatePerson(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveUpdatePerson);
                        break;
                    case ActionTypes.LOGIN:
                        SlaveLogin slaveLogin = new SlaveLogin(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveLogin);
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
