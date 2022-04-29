import helpers.NetAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    int clientPort;

    public Client(int clientPort) {
        this.clientPort = clientPort;
    }

    public NetAddress sendRequest(String mainServerAddress, int mainServerPort, String word, String languageCode) throws IOException {
        Socket mainServerSocket = new Socket(mainServerAddress, mainServerPort, InetAddress.getLocalHost(), clientPort);
        //send request
        mainServerSocket.close();
        return new NetAddress(0, ""); //todo
    }

    public String receiveResponse(String languageServerAddress, int languageServerPort) throws IOException {
        Socket languageServerSocket = new Socket(languageServerAddress, languageServerPort, InetAddress.getLocalHost(), clientPort);
        //wait for response
        languageServerSocket.close();
        return ""; //todo
    }


}
