package sender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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

    @FXML private TextArea console;

    @FXML private Label connStatus;
    @FXML private Label serverStatus;






    public void button(ActionEvent connect) throws IOException {

    authenticate();

    }// end button method

    public void authenticate() throws IOException {
        Socket s = new Socket(ipAddress.getText(), 63333);

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"authentication\",\"server_password\":\"" + passWord.getText() + "\"}");
        String response = bufRd.readLine();
        p.println("");
        response = bufRd.readLine();
        console.appendText(response + "\n");

        if (response.equals("SMS server connected")){
            connStatus.setText("Connected");
            connStatus.setTextFill(Color.GREEN);
        }
    checkStatus(s);
    }

    public void checkStatus(Socket s)throws IOException{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"get_server_status\"}");
        String response = bufRd.readLine();

        //System.out.println(response +"\n");

        Object obj = JSONValue.parse(response);
        JSONObject jsonObject = (JSONObject) obj;

        String status = (String) jsonObject.get("server_currently_status");

        serverStatus.setText(status);
        if (status.equals("Running")) {
            serverStatus.setTextFill(Color.GREEN);
        }
        else serverStatus.setTextFill(Color.RED);

    }//end checkStatus
}//end class
