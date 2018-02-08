package sender;


import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

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

    public GetStatus(Label serverStatus, TextArea console, String passWord, Socket s) {

        this.serverStatus = serverStatus;
        this.passWord = passWord;
        this.console = console;
        this.s = s;

    }


    @Override
    public void run() {

        try{
            p = new PrintWriter(s.getOutputStream(), true);
            bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            p.println("{\"method\":\"authentication\",\"server_password\":\"" + passWord + "\"}");
            String response = bufRd.readLine();
            p.println("");

            response = bufRd.readLine();
            console.appendText(response + "\n");

            Platform.runLater(() -> {
                serverStatus.setTextFill(Color.GREEN);
                serverStatus.setText("Connected");
                console.appendText("Server Running\n");
            });

        } catch (IOException e) {
            console.appendText("Connection failed\n");
        } //end try/catch

    }//end run
}//
