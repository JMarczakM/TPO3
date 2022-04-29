import MainServer.MainServer;
import helpers.NetAddress;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Ui {
    private MainServer mainServer;

    public Ui(MainServer mainServer) {
        this.mainServer = mainServer;
    }

    public void start(){
        JFrame jFrame = new JFrame("App");
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4,2);
        panel.setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(200, 150);

        //labels
        JLabel portLabel = new JLabel("port");
        JLabel wordLabel = new JLabel("Word");
        JLabel languageCodeLabel = new JLabel("Language Code");
        JLabel responseLabel = new JLabel("Response");

        //textFields
        JTextField portField = new JTextField();
        JTextField wordField = new JTextField();
        JTextField languageCodeField = new JTextField();

        //button
        JButton jButton = new JButton("submit");
        jButton.addActionListener(e -> {
            System.out.println("Send request to main server through client");
            try {
                Client client = new Client(Integer.parseInt(portField.getText()));
                NetAddress languageServerNetAddress = client.sendRequest(mainServer.getAddress(), mainServer.getPort() ,wordField.getText(), languageCodeField.getText());
                responseLabel.setText(client.receiveResponse(languageServerNetAddress.getAddress(), languageServerNetAddress.getPort()));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(portLabel);
        panel.add(portField);
        panel.add(wordLabel);
        panel.add(wordField);
        panel.add(languageCodeLabel);
        panel.add(languageCodeField);
        panel.add(jButton);
        panel.add(responseLabel);

        jFrame.setContentPane(panel);
        jFrame.setVisible(true);
    }
}
