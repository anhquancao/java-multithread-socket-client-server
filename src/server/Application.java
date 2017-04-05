package server;

import utils.Constant;

/**
 * Created by caoquan on 4/5/17.
 */
public class Application {
    public static void main(String args[]) {
        MasterOrdinary server = new MasterOrdinary(Constant.PORT);
        server.start();
    }
}
