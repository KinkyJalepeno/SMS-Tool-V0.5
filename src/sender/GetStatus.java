package sender;


import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GetStatus implements Runnable {

    private Label serverStatus;
    private PrintWriter p;
    private BufferedReader bufRd;
    private String passWord;
    private Socket s;
    private TextArea console;
    private Label connStatus;

    public GetStatus(Label serverStatus, TextArea console, String passWord, Socket s, Label connStatus) {

        this.serverStatus = serverStatus;
        this.passWord = passWord;
        this.console = console;
        this.s = s;
        this.connStatus = connStatus;

    }


    @Override
    public void run() {
        console.clear();
        try{
            p = new PrintWriter(s.getOutputStream(), true);
            bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            p.println("{\"method\":\"authentication\",\"server_password\":\"" + passWord + "\"}");
            String response = bufRd.readLine();
            p.println("");

            response = bufRd.readLine();
            console.appendText(response + "\n");

            Platform.runLater(() -> {
                connStatus.setTextFill(Color.GREEN);
                connStatus.setText("Connected");
             });
            getRunPause();
        } catch (IOException e) {
            console.appendText("Connection failed\n");
        } //end try/catch

    }//end run

    private void getRunPause() {

        try{
            p = new PrintWriter(s.getOutputStream(), true);
            bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            p.println("{\"method\":\"get_server_status\"}");
            String response = bufRd.readLine();

            System.out.println(response);

            Object obj = JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            String status = (String) jsonObject.get("server_currently_status");
            String email = (String) jsonObject.get("email_server_status");
            String sql = (String) jsonObject.get("my_sql_status");
            String email2sms = (String) jsonObject.get("email2sms_status");

            console.appendText("Server Status : " + status +"\n" + "Email Server Status: " + email +"\n" +
                "MySQL Service Status: " + sql +"\n" + "Email to SMS Server Status: " + email2sms +"\n\n");

            if(status.equals("Running")){
                Platform.runLater(() -> {
                    serverStatus.setTextFill(Color.GREEN);
                    serverStatus.setText("Running");
           });
            }else {
                Platform.runLater(() -> {
                    serverStatus.setText("Paused");
                });
            }


        } catch (IOException e) {
            console.appendText("Connection failed\n");
        } //end try/catch

    }
}//end GetStatus class
