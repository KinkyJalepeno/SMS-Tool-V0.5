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


    public static String serverStatus(Socket s) throws Exception{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{ \"method\":\"get_server_status\" }");
        String response = bufRd.readLine();

        Object obj = JSONValue.parse(response);
        JSONObject jsonObject = (JSONObject) obj;

        String reply = (String) jsonObject.get("server_currently_status");



        return reply;
    }

    public static void pauseServer(Socket s) throws Exception{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{ \"method\":\"pause_server\" }");
        String response = bufRd.readLine();

    }// end pauseServer

    public static void runServer(Socket s) throws Exception{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{ \"method\":\"run_server\" }");
        String response = bufRd.readLine();

    }//end runServer

    public static String sendRand(Socket s, String mobNum) throws Exception{

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"number\":\"" + mobNum + "\", \"msg\":\"Random port test\", \"unicode\":\"5\"}");

        String response = bufRd.readLine();
        System.out.println(response);
        Object obj = JSONValue.parse(response);
        JSONObject jsonObject = (JSONObject) obj;

        String getReply = (String) jsonObject.get("reply");

        response = bufRd.readLine();
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String number = (String) jsonObject.get("number");
        String reply2 = (String) jsonObject.get("reply");
        String cardAdd = (String) jsonObject.get("card_add");
        String portNum = (String) jsonObject.get("port_num");

        String collection = ("Status: " + getReply + "\nNumber: " + number + "\nCard: " + cardAdd + "\nPort: " +
            portNum + "\nResult: " + reply2);

        return collection;

    }//end setRand
}//end main class

