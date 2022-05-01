package languageServer;

import helpers.NetAddress;
import mainServer.MainServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanguageServer implements Runnable{
    private String languageCode;
    private HashMap<String, String> dictionary;
    private boolean isStopped = false;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public LanguageServer(String languageCode, HashMap<String, String> dictionary) throws IOException {
        this.languageCode = languageCode;
        this.dictionary = dictionary;
        this.serverSocket = new ServerSocket(0);
        this.threadPool = Executors.newFixedThreadPool(3);
    }

    public void stop(){
        this.isStopped = true;
    }

    public String getAddress(){
        return serverSocket.getInetAddress().getHostAddress();
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    public NetAddress getNetAddress() {
        return new NetAddress(getPort(), getAddress());
    }

    @Override
    public void run() {
        while (!isStopped){
            try {
                Socket socket = serverSocket.accept();
                LanguageServerHandler.getRequestFromClient(socket, dictionary);
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
