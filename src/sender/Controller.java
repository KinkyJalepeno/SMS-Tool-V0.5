package sender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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

        (new Thread(new GetStatus(serverStatus, console, passWord.getText(), s, connStatus))).start();
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
        int portCheck = Integer.parseInt(port.getText());
        if(portCheck < 1 || portCheck >4){
            console.appendText("You must enter a port number from 1 - 4 \n");
            return;
        }

        (new Thread(new SpecifyCardPort(mobNumber.getText(), s, console, card.getText(), port.getText()))).start();

        (new Thread(new CollectResponses(s, console))).start();

    }//end card/port method

    public void allCard(ActionEvent e)throws Exception{

        int cardCheck = Integer.parseInt(card2.getText());
        if(cardCheck < 21 || cardCheck >28){
            console.appendText("You must enter a card address from 21 - 28 \n");
            return;
        }//end cardCheck

        (new Thread(new AllCard(s, card2.getText(), console, mobNumber.getText()))).start();

    }//end allCard method

    public void allPorts(ActionEvent e)throws Exception {

        int cardCheck = Integer.parseInt(numCards.getText());
        if(cardCheck < 1 || cardCheck >8){
            console.appendText("You must enter a card number from 1 - 8 \n");
            return;
        }

        int totalCards = Integer.parseInt(numCards.getText());
        totalCards = totalCards + 20;

        (new Thread(new AllCardsPorts(console, s, mobNumber.getText(), totalCards))).start();


    }//end allPorts method

    public void clearConsole(ActionEvent e){

        console.clear();
    }

    public void pauseServer(ActionEvent e)throws Exception{

        String response = sender.Senders.pauseServer(s);
        if(response.equals("ok")) {
            serverStatus.setTextFill(Color.RED);
            serverStatus.setText("Paused");
        }

    }//end pause Server

    public void runServer(ActionEvent e)throws Exception{

        String response = sender.Senders.runServer(s);
        if(response.equals("ok")) {
            serverStatus.setTextFill(Color.GREEN);
            serverStatus.setText("Running");
        }

    }//end pause Server

    public void queryGeneral(ActionEvent e)throws Exception{

        String response = sender.Senders.queryGenQue(s);
        console.appendText(response + "\n");

    }//end query general queue

    public void queryMaster(ActionEvent e)throws Exception{


        String response = sender.Senders.queryMasQue(s);
        console.appendText(response + "\n");

    }//end query master queue

    public void flushGeneral(ActionEvent e)throws Exception{

        String response = sender.Senders.flushGenQue(s);
        console.appendText(response + "\n\n");

        response = sender.Senders.queryGenQue(s);
        console.appendText(response + "\n");

    }

    public void flushMaster(ActionEvent e)throws Exception{

        String response = sender.Senders.flushMasQue(s);
        console.appendText(response + "\n\n");

        response = sender.Senders.queryMasQue(s);
        console.appendText(response + "\n");

    }

}//end class
