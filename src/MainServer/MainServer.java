package MainServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer implements Runnable{
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private boolean isStopped = false;


    public MainServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
        this.threadPool = Executors.newFixedThreadPool(3);
    }

    public String getAddress(){
        return serverSocket.getInetAddress().getHostAddress();
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    public void stop(){
        this.isStopped = true;
    }

    @Override
    public void run() {
        while (!isStopped){
            threadPool.submit(() -> {
                try {
                    System.out.println(MainServerHandler.getLanguageServerNetAddress(serverSocket.accept()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
