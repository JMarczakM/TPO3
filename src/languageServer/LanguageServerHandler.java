package languageServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class LanguageServerHandler {
    public static void getRequestFromClient(Socket mainServerSocket, HashMap<String, String> dictionary) throws IOException {
        BufferedReader inFromMainServer = new BufferedReader(new InputStreamReader(mainServerSocket.getInputStream()));
        String[] request = inFromMainServer.readLine().split(" ");

        Socket clientClient = new Socket(request[2], Integer.parseInt(request[1]));
        PrintWriter outToClient = new PrintWriter(clientClient.getOutputStream(), true);
        if(dictionary.containsKey(request[0])){
            outToClient.println(dictionary.get(request[0]));
        } else {
            outToClient.println("No word was found in dictionary");
        }
    }
}
