package server;

import utils.Constant;

import java.io.*;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQuery extends Thread {
    protected BufferedWriter writer;

    public SlaveQuery(OutputStream outputStream) {
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(outputStream, Constant.CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    protected void writeData(String results) {
        try {
            writer.write(results);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
