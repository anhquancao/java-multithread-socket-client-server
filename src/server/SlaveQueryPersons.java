package server;

import controllers.PersonController;
import utils.Constant;

import java.io.*;

/**
 * Created by dosontung on 4/9/17.
 */
public class SlaveQueryPersons extends SlaveQuery {
    private String param1;
    private String param2;
    private PersonController personController;
    private BufferedWriter writer;

    public SlaveQueryPersons(OutputStream outputStream, String params) {
        super(outputStream);
        
        String[] splittedParams = params.split(" ");
        this.param1 = splittedParams[0];
        this.param2 = splittedParams[1];

        this.personController = new PersonController();
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        switch (this.param1) {
            case PersonController.ALLTENANT:
                try {
                    String results = this.personController.requestListAllTenantByRenter(this.param2);
                    writer.write(results);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
