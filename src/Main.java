import MainServer.MainServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainServer mainServer = new MainServer();
        mainServer.run();

        Ui ui = new Ui(mainServer);
        ui.start();
    }
}
