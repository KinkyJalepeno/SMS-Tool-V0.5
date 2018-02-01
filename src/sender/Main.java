package sender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Dave's SMS Sender");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static String authentication(Socket s, String pass) throws Exception{

        PrintStream p = new PrintStream(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"authentication\",\"server_password\":\"" + pass + "\"}");

        String response = bufRd.readLine();
        //System.out.println(response);

        p.println("");
        response = bufRd.readLine();

        return response;
    }

    public static String serverStatus(Socket s, String pass) throws Exception{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{ \"method\":\"get_server_status\" }");
        String response = bufRd.readLine();

        Object obj = JSONValue.parse(response);
        JSONObject jsonObject = (JSONObject) obj;

        String reply = (String) jsonObject.get("server_currently_status");



        return reply;
    }
}//end main class

