package server;

import utils.Constant;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQuery extends Thread {
    private OutputStreamWriter writer;

    public SlaveQuery(OutputStream outputStream) {
        try {
            this.writer = new OutputStreamWriter(outputStream, Constant.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
