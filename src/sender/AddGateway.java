package sender;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class AddGateway {

    @FXML private TextField siteName;
    @FXML private TextField ipAddress;
    @FXML private TextField passWord;

    private Connection conn;


    public AddGateway(){

        this.conn = conn;
    }

    public void openAddWindow() {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddGateway.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Add Gateway");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

//    @FXML
//    private void addDetails(){
//
//
//    }
}
