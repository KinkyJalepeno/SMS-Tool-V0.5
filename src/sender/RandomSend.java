package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RandomSend implements Runnable{

    private TextArea console;
    private String mobNumber;
    private Socket s;



    public RandomSend(String mobNumber, Socket s, TextArea console) {

        this.console = console;
        this.s = s;
        this.mobNumber = mobNumber;

    }

    @Override
    public void run() {
        console.clear();
        console.appendText("Submitted.......................\n\n");

        try {
            PrintWriter p = new PrintWriter(s.getOutputStream(), true);
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            p.println("{\"number\":\"" + mobNumber + "\", \"msg\":\"Random port test\", \"unicode\":\"5\"}");

            String response = bufRd.readLine();
            System.out.println(response);

            Object obj = JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            String part1 = (String) jsonObject.get("number");
            String part2 = (String) jsonObject.get("reply");

            console.appendText("Number: " + part1 + " Status: " + part2 +"\n");
        }catch(IOException e){
            e.printStackTrace();
        }//end try-catch

        getResponses();

    }//end run method

    private void getResponses() {

        try {
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String response = bufRd.readLine();

            System.out.println(response);
            Object obj = JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            String ccid = (String) jsonObject.get("ccid");
            String reply2 = (String) jsonObject.get("reply");
            String cardAdd = (String) jsonObject.get("card_add");
            String portNum = (String) jsonObject.get("port_num");

            console.appendText("Sim ID: " + ccid + "\nCard: " + cardAdd + "\nPort: " +
                    portNum + "\nResult: " + reply2 + "\n");

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}//end class
