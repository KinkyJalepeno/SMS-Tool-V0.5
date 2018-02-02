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

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        String status = sender.Main.serverStatus(s);
        if (status.equals("Running")){
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }else{
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }//end if else block

        s.close();

    }

    public void PauseServer(ActionEvent e) throws Exception{

        Socket s = new Socket(ipAddress.getText(), 63333);
        String pass = passWord.getText();

        String response = sender.Main.authentication(s, pass);
        if (response.equals("SMS server connected")){
            connStatus.setTextFill(Color.GREEN);
            connStatus.setText("Connected");
        }else{
            connStatus.setText("Not Connected");
        }//end if else block

        sender.Main.pauseServer(s);

        String status = sender.Main.serverStatus(s);
        if (status.equals("Running")){
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }else{
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }//end if else block

        s.close();

    }// end pauseServer

    public void runServer(ActionEvent e)throws Exception{

        Socket s = new Socket(ipAddress.getText(), 63333);
        String pass = passWord.getText();

        String response = sender.Main.authentication(s, pass);
        if (response.equals("SMS server connected")){
            connStatus.setTextFill(Color.GREEN);
            connStatus.setText("Connected");
        }else{
            connStatus.setText("Not Connected");
        }//end if else block

        sender.Main.runServer(s);

        String status = sender.Main.serverStatus(s);
        if (status.equals("Running")){
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }else{
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }//end if else block

        s.close();


    }

    public void clearConsole(ActionEvent e){

        console.clear();

    }

    public void randSend(ActionEvent e)throws Exception{

        Socket s = new Socket(ipAddress.getText(), 63333);

        String pass = passWord.getText();
        String mobNum = mobNumber.getText();

        sender.Main.authentication(s, pass);//call authentication method

        String response = sender.Main.sendRand(s, mobNum);//call random port send method

        console.appendText(response);

        s.close();

    }//end randSend



}//end class
