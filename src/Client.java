import helpers.NetAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    int clientPort;

    public Client(int clientPort) {
        this.clientPort = clientPort;
    }

    public String sendRequest(String mainServerAddress, int mainServerPort, String word, String languageCode) throws IOException {
        Socket mainServerSocket = new Socket(mainServerAddress, mainServerPort);
        PrintWriter out = new PrintWriter(mainServerSocket.getOutputStream(), true);
        out.println(languageCode+" "+word+" "+clientPort);
        mainServerSocket.close();
        ServerSocket clientServerSocket = new ServerSocket(clientPort);
        Socket socket = clientServerSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String translatedWord = inFromClient.readLine();
        clientServerSocket.close();
        return translatedWord;
    }
}
