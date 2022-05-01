package mainServer;

import helpers.NetAddress;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class MainServerHandler{
    public static void getRequestFromClient(Socket clientSocket, HashMap<String, NetAddress> languageServerAddresses) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String[] request = inFromClient.readLine().split(" ");

        Socket languageServerSocket = new Socket(languageServerAddresses.get(request[0]).getAddress(), languageServerAddresses.get(request[0]).getPort());

        PrintWriter outToLanguageServer = new PrintWriter(languageServerSocket.getOutputStream(), true);
        outToLanguageServer.println(request[1]+" "+request[2]+" "+clientSocket.getInetAddress().getHostAddress());
    }
}
