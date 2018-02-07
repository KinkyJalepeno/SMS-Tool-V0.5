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
    @FXML private TextField numCards;

    @FXML private TextArea console;

    @FXML private Label connStatus;
    @FXML private Label serverStatus;

    public void authentication(ActionEvent e) throws Exception {

        boolean response = sender.Senders.authenticate(passWord.getText(), ipAddress.getText());

        if(response == true){
            connStatus.setTextFill(Color.GREEN);
            connStatus.setText("Connected");
        }//end if block

        boolean status = sender.Senders.serverStatus();
        if (status == true){
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }else{
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }//end if else block



    }

    public void randSend(ActionEvent e)throws Exception{

        String response = sender.Senders.sendRand(mobNumber.getText());
        console.appendText(response +"\n\n");

    }//end randSend

    public void cardPort(ActionEvent e)throws Exception{

        String response = sender.Senders.cardPort(mobNumber.getText(), card.getText(), port.getText());
        console.appendText(response + "\n");

        response = sender.Senders.cardPort2();
        console.appendText(response + "\n");

    }//end card/port method

    public void allCard(ActionEvent e)throws Exception{

        for(int i = 1; i < 5; i++) {
            String response = sender.Senders.allCard(mobNumber.getText(), card2.getText(), i);
            console.appendText(response + "\n");
        }

        for(int i = 1; i < 5; i++) {
            String response = sender.Senders.allCard2();
            console.appendText(response + "\n");
        }
    }//end allCard method

    public void allPorts(ActionEvent e)throws Exception {

        int totalCards = Integer.parseInt(numCards.getText());
        totalCards = totalCards + 20;
        //System.out.println(totalCards + "\n");
        for (int i = 21; i <= totalCards; i++) {
            for (int j = 1; j < 5; j++) {
                //System.out.println("card: " + i + " port: " + j + "\n");
                String response = sender.Senders.allPorts(mobNumber.getText(), i, j);
               console.appendText(response + "\n");
            }//end inner port loop
        }//end outer card loop
        console.appendText("\n");

        for (int i = 21; i <= totalCards; i++) {
            for (int j = 1; j < 5; j++) {
                String response = sender.Senders.allPorts2();
                console.appendText(response + "\n");
            }//end inner port loop
        }//end outer card loop
        console.appendText("\n");
    }//end allPorts method



//    public void PauseServer(ActionEvent e) throws Exception{
//
//        Socket s = new Socket(ipAddress.getText(), 63333);
//        String pass = passWord.getText();
//
//        String response = sender.Main.authentication(s, pass);
//        if (response.equals("SMS server connected")){
//            connStatus.setTextFill(Color.GREEN);
//            connStatus.setText("Connected");
//        }else{
//            connStatus.setText("Not Connected");
//        }//end if else block
//
//        sender.Main.pauseServer(s);
//
//        String status = sender.Main.serverStatus(s);
//        if (status.equals("Running")){
//            serverStatus.setTextFill(Color.GREEN);
//            serverStatus.setText("Running");
//        }else{
//            serverStatus.setTextFill(Color.RED);
//            serverStatus.setText("Paused");
//        }//end if else block
//
//        s.close();
//
//    }// end pauseServer
//
//    public void runServer(ActionEvent e)throws Exception{
//
//        Socket s = new Socket(ipAddress.getText(), 63333);
//        String pass = passWord.getText();
//
//        String response = sender.Main.authentication(s, pass);
//        if (response.equals("SMS server connected")){
//            connStatus.setTextFill(Color.GREEN);
//            connStatus.setText("Connected");
//        }else{
//            connStatus.setText("Not Connected");
//        }//end if else block
//
//        sender.Main.runServer(s);
//
//        String status = sender.Main.serverStatus(s);
//        if (status.equals("Running")){
//            serverStatus.setTextFill(Color.GREEN);
//            serverStatus.setText("Running");
//        }else{
//            serverStatus.setTextFill(Color.RED);
//            serverStatus.setText("Paused");
//        }//end if else block
//
//        s.close();
//
//
//    }
//
    public void clearConsole(ActionEvent e){

        console.clear();
    }





}//end class
