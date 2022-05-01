import languageServer.LanguageServer;
import mainServer.MainServer;
import helpers.NetAddress;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static HashMap<String, NetAddress> languageServerAddresses = new HashMap<>();
    public static ArrayList<Thread> languageServerThreads = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for (File file : Objects.requireNonNull(new File("dictionaries").listFiles())) {
            HashMap<String, String> dictionary = new HashMap<>();
            String languageCode = file.getName();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                dictionary.put(data[0], data[1]);
            }
            myReader.close();
            LanguageServer languageServer = new LanguageServer(languageCode, dictionary);
            Thread thread = new Thread(languageServer);
            languageServerThreads.add(thread);
            languageServerAddresses.put(languageCode, languageServer.getNetAddress());
        }
        for (Thread thread : languageServerThreads) {
            thread.start();
        }

        MainServer mainServer = new MainServer(languageServerAddresses);
        Thread mainServerThread = new Thread(mainServer);
        mainServerThread.start();

        Ui ui1 = new Ui(mainServer, languageServerAddresses.keySet().toArray(new String[0]));
        ui1.start();

//        Ui ui2 = new Ui(mainServer);
//        ui2.start();
//
//        Ui ui3 = new Ui(mainServer);
//        ui3.start();
    }

}
