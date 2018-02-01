package sender;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sun.net.www.http.KeepAliveStream;

import java.io.*;
import java.net.Socket;

public class Controller {


    @FXML private Button connect;
    @FXML private Button randPort;
    @FXML private Button cardPort;
    @FXML private Button allPorts;
    @FXML private Button allCards;
    @FXML private Button queryStand;
    @FXML private Button flushStand;
    @FXML private Button queryMast;
    @FXML private Button FlushMast;
    @FXML private Button clear;
    @FXML private Button runServer;
    @FXML private Button pauseServer;


    @FXML private TextField ipAddress;
    @FXML private TextField passWord;
    @FXML private TextField card;
    @FXML private TextField card2;
    @FXML private TextField port;
    @FXML private TextField mobNumber;

    @FXML private TextArea console;

    @FXML private Label connStatus;
    @FXML private Label serverStatus;

    public void authentication(ActionEvent e) throws Exception {

        Socket s = new Socket(ipAddress.getText(), 63333);
        String pass = passWord.getText();


        String response = sender.Main.authentication(s, pass);
        if (response.equals("SMS server connected")){
            connStatus.setTextFill(Color.GREEN);
            connStatus.setText("Connected");
        }else{
            connStatus.setText("Not Connected");
        }

        String status = sender.Main.serverStatus(s, pass);
            console.appendText(status + "\n");

        s.close();

    }



}//end class
