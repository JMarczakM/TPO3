package mainServer;

import helpers.NetAddress;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer implements Runnable {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private boolean isStopped = false;
    private HashMap<String, NetAddress> languageServerAddresses;


    public MainServer(HashMap<String, NetAddress> languageServerAddresses) throws IOException {
        this.serverSocket = new ServerSocket(0);
        this.threadPool = Executors.newFixedThreadPool(3);
        this.languageServerAddresses = languageServerAddresses;
    }

    public String getAddress(){
        return serverSocket.getInetAddress().getHostAddress();
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }


    @Override
    public void run() {
        while (!isStopped){
            try {
                Socket socket = serverSocket.accept();
                MainServerHandler.getRequestFromClient(socket, languageServerAddresses);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
