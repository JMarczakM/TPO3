import mainServer.MainServer;
import helpers.NetAddress;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Ui {
    private MainServer mainServer;
    private String[] validLanguageCodes;


    public Ui(MainServer mainServer, String[] validLanguageCodes) {
        this.mainServer = mainServer;
        this.validLanguageCodes = validLanguageCodes;
    }

    public void start(){
        JFrame jFrame = new JFrame("App");
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4,2);
        panel.setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            try {
                try {
                    int port = Integer.parseInt(portField.getText());
                    if(!Arrays.stream(validLanguageCodes).anyMatch(languageCodeField.getText()::equals)){
                        responseLabel.setText("Invalid input");
                    }
                    else {
                        Client client = new Client(port);
                        String word = client.sendRequest(mainServer.getAddress(), mainServer.getPort() ,wordField.getText(), languageCodeField.getText().toLowerCase());
                        responseLabel.setText(word);
                    }
                } catch (NumberFormatException exception) {
                    responseLabel.setText("Invalid input");
                }
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
