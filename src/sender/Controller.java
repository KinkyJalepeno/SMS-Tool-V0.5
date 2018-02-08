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

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
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

    private Socket s;

    public void openSocket(ActionEvent e) throws Exception {

        s = new Socket(ipAddress.getText(), 63333);

        (new Thread(new Connector(console, ipAddress.getText(), connStatus))).start();

        (new Thread(new GetStatus(serverStatus, console, passWord.getText(), s))).start();
        //s.close();
    }//end

    public void randSend(ActionEvent e)throws Exception{

        (new Thread(new RandomSend(mobNumber.getText(), s, console))).start();
        (new Thread(new CollectResponses(s, console))).start();
    //s.close();
    }//end randSend

    public void cardPort(ActionEvent e)throws Exception{

        int cardCheck = Integer.parseInt(card.getText());
            if(cardCheck < 21 || cardCheck >28){
                console.appendText("You must enter a card address from 21 - 28 \n");
                return;
        }
        (new Thread(new SpecifyCardPort(mobNumber.getText(), s, console, card.getText(), port.getText()))).start();

        (new Thread(new CollectResponses(s, console))).start();

//        String response = sender.Senders.cardPort(mobNumber.getText(), card.getText(), port.getText());
//        console.appendText(response + "\n");
//
//        response = sender.Senders.cardPort2();
//        console.appendText(response + "\n");

    }//end card/port method

    public void allCard(ActionEvent e)throws Exception{

        int cardCheck = Integer.parseInt(card2.getText());
        if(cardCheck < 21 || cardCheck >28){
            console.appendText("You must enter a card address from 21 - 28 \n");
            return;
        }

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

        int cardCheck = Integer.parseInt(numCards.getText());
        if(cardCheck < 1 || cardCheck >8){
            console.appendText("You must enter a card number from 1 - 8 \n");
            return;
        }

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

    public void clearConsole(ActionEvent e){

        console.clear();
    }

    public void pauseServer(ActionEvent e)throws Exception{

        String response = sender.Senders.pauseServer();
        if(response.equals("ok")) {
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }

    }//end pause Server

    public void runServer(ActionEvent e)throws Exception{

        String response = sender.Senders.runServer();
        if(response.equals("ok")) {
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }

    }//end pause Server

    public void queryGeneral(ActionEvent e)throws Exception{

        Socket s = new Socket(ipAddress.getText(), 63333);
        String response = sender.Senders.queryGenQue(s);
        console.appendText(response + "\n");

    }//end query general queue

    public void queryMaster(ActionEvent e)throws Exception{

        String response = sender.Senders.queryMasQue();
        console.appendText(response + "\n");

    }//end query master queue

    public void flushGeneral(ActionEvent e)throws Exception{

        Socket s = new Socket(ipAddress.getText(), 63333);

        String response = sender.Senders.flushGenQue(s);
        console.appendText(response + "\n");

        response = sender.Senders.queryGenQue(s);
        console.appendText(response + "\n");

    }

    public void flushMaster(ActionEvent e)throws Exception{

        String response = sender.Senders.flushMasQue();
        console.appendText(response + "\n");

        response = sender.Senders.queryMasQue();
        console.appendText(response + "\n");

    }

}//end class
