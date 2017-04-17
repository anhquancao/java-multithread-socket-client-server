package server;

import actions.ActionTypes;
import utils.Constant;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caoquan on 4/4/17.
 */
public class MasterAdministration extends Thread {
    private ServerSocket sslServerSocket;
    private ExecutorService service;
    private int port;


    public MasterAdministration(int port) {
        try {
            KeyManagerFactory kmf = null;
            SSLContext context = null;
            try {
                context = SSLContext.getInstance("SSLv3");
                kmf = KeyManagerFactory.getInstance("SunX509");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            KeyStore ks = null;
            try {
                ks = KeyStore.getInstance("JKS");
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            char[] passPhrase = Constant.PASSWORD.toCharArray();
            try {
                ks.load(new FileInputStream("certi"), passPhrase);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }
            try {
                kmf.init(ks, passPhrase);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            }
            try {
                context.init(kmf.getKeyManagers(), null, null);
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            SSLServerSocketFactory factory =
                    context.getServerSocketFactory();
            sslServerSocket = (SSLServerSocket) factory.createServerSocket(port);
            this.port = port;
            service = Executors.newFixedThreadPool(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server Administration is listen at " + port);
        while (true) {
            try {
                Socket socket = sslServerSocket.accept();
                System.out.print("Accepted request: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Constant.CHARSET));
                String input = reader.readLine();
                String[] splittedStr = input.split(" ", 2);
                System.out.println(splittedStr[0] + " " + splittedStr[1]);
                String command = splittedStr[0];
                switch (command) {
                    case ActionTypes.REQUEST_RENTAL:
                        SlaveQueryRentals slaveQueryRentals = new SlaveQueryRentals(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveQueryRentals);
                        break;
                    case ActionTypes.UPDATE_RENTAL:
                        SlaveUpdateRentals slaveUpdateRentals = new SlaveUpdateRentals(socket.getOutputStream(), splittedStr[1]);
                        service.submit(slaveUpdateRentals);
                        break;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        MasterAdministration server = new MasterAdministration(Constant.PORT_ADMINISTRATION);
        server.start();
    }
}
