package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CollectResponses implements Runnable {

    private TextArea console;
    private Socket s;


    public CollectResponses(Socket s, TextArea console) {

        this.console = console;
        this.s = s;

    }

    @Override
    public void run() {

        try {
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String response = bufRd.readLine();

            System.out.println(response);
            Object obj = JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            String number = (String) jsonObject.get("number");
            String reply2 = (String) jsonObject.get("reply");
            String cardAdd = (String) jsonObject.get("card_add");
            String portNum = (String) jsonObject.get("port_num");

            console.appendText("Number: " + number + "\nCard: " + cardAdd + "\nPort: " +
                    portNum + "\nResult: " + reply2 + "\n");

        }catch(IOException e){
            e.printStackTrace();
        }


    }//end run method
}//end CollectResponses class
