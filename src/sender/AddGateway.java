package sender;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddGateway {

    @FXML private TextField siteName;
    @FXML private TextField ipAddress;
    @FXML private TextField passWord;
    @FXML private Label addStatus;

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

    @FXML
    private void addDetails(){
        String url = "jdbc:sqlite:C://sqlite/sites.db";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }

        String sql = ("INSERT INTO gateways VALUES ('" +siteName.getText() + "'" + ",'" + ipAddress.getText() + "','" +
        passWord.getText() + "')");

        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);

            conn.close();
        }catch(SQLException s){
            System.err.println(s.getMessage());
        }
        addStatus.setTextFill(Color.GREEN);
        addStatus.setText("Gateway Added");
    }
}
