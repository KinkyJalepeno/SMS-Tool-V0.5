package sender;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector implements Runnable{


    private TextArea console;
    private String ipAddress;
    private String passWord;
    private Label connStatus;

    public Connector(TextArea console, String ipAddress, String passWord, Label connStatus) {

        this.console = console;
        this.ipAddress = ipAddress;
        this.passWord = passWord;
        this.connStatus = connStatus;
            }


    @Override
    public void run() {

        try{
            Socket s = new Socket(ipAddress, 63333);

            Platform.runLater(() -> {
                connStatus.setTextFill(Color.GREEN);
                connStatus.setText("Connected");
            });

        } catch (IOException e) {
            console.appendText("Connection failed - check IP address\n");
        } catch (NullPointerException e) {
            console.appendText("Connection failed - check password\n");
            console.appendText(e.getMessage());
        }


    }//end run

}//end class
