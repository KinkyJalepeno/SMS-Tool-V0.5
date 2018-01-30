package sender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;

import static sender.Controller.*;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Dave's SMS Sender");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void connect()throws IOException {


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

    public static void checkStatus(Socket s)throws IOException{

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
    }



}//end class

