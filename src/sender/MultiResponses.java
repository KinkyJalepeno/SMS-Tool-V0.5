package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MultiResponses implements Runnable {

    private Socket s;
    private TextArea console;
    private int count;


    public MultiResponses(Socket s, TextArea console) {

        this.console = console;
        this.s = s;
        this.count = count;
    }

    @Override
    public void run() {

        for(int i = 1; i < count; i++) {
            try {
                BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String response = bufRd.readLine();

                System.out.println(response);
                Object obj = JSONValue.parse(response);
                JSONObject jsonObject = (JSONObject) obj;

                String number = (String) jsonObject.get("number");
                String reply2 = (String) jsonObject.get("reply");

                console.appendText("Number: " + number + "Result: " + reply2 + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }//end try-catch block
        }//end for loop
    }//end run method
}//end class
