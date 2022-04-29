package MainServer;

import helpers.NetAddress;

import java.io.*;
import java.net.Socket;

public class MainServerHandler{
    public static NetAddress getLanguageServerNetAddress(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String request = in.readLine();
        System.out.println(request);


        return new NetAddress(0);
    }
}
