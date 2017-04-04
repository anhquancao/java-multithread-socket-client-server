package server;

import java.io.OutputStream;

/**
 * Created by caoquan on 4/4/17.
 */
public class SlaveQuery extends Thread {
    private OutputStream outputStream;

    public SlaveQuery(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
