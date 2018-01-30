package sender;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;

public class Controller {


    @FXML public Button connect;
    @FXML public Button randPort;
    @FXML public Button cardPort;
    @FXML public Button allPorts;
    @FXML public Button allCards;
    @FXML public Button queryStand;
    @FXML public Button flushStand;
    @FXML public Button queryMast;
    @FXML public Button FlushMast;
    @FXML public Button clear;
    @FXML public Button runServer;
    @FXML public Button pauseServer;



    @FXML public static TextField ipAddress;
    @FXML public static TextField passWord;
    @FXML public TextField card;
    @FXML public TextField card2;
    @FXML public TextField port;
    @FXML public TextField mobNumber;

    @FXML public static TextArea console;

    @FXML public static Label connStatus;
    @FXML public static Label serverStatus;

    public void button(ActionEvent connect) throws IOException {

    sender.Main.connect();

    }// end button method


}//end class
