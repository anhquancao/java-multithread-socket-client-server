package client;

import actions.Action;
import utils.Constant;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.security.Security;
import java.util.Scanner;

/**
 * Created by caoquan on 4/11/17.
 */
abstract public class Client {
    private SSLSocket sslSocket;

    private BufferedWriter writer;
    private BufferedReader reader;

    protected int port;

    protected Scanner sc;

    public Client() {
        this.port = Constant.PORT;
        this.sc = new Scanner(System.in);
    }

    public String doAction(Action action) {
        try {
            Security.addProvider(
                    new com.sun.net.ssl.internal.ssl.Provider());
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", Constant.PASSWORD);

            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            System.out.println("Send request: " + action.command());
            
            this.sslSocket = (SSLSocket) factory.createSocket("localhost", this.port);

            InputStream inputStream = sslSocket.getInputStream();
            this.writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream(), Constant.CHARSET));
            this.reader = new BufferedReader(new InputStreamReader(inputStream, Constant.CHARSET));


            this.writer.write(action.command());
            this.writer.newLine();
            this.writer.flush();


//            int attempts = 0;
//            while (inputStream.available() == 0 && attempts < 1000) {
//                attempts++;
//                Thread.sleep(10);
//            }
//            if (attempts == 1000) {
//                System.out.println("Request Timeout");
//            }
//            String line = reader.readLine();

            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                result.append(line);
            }
//            StringBuilder result = new StringBuilder();
//            while (reader.ready()) {
//                String line = reader.readLine();
//                System.out.println(line);
//
//                result.append(line);
//            }
            return result.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
