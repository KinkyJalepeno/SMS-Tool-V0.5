package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AllCardsPorts implements Runnable {

    private TextArea console;
    private Socket s;
    private String mobNumber;
    private int totalCards;



    public AllCardsPorts(TextArea console, Socket s, String mobNumber, int totalCards) {

        this.console = console;
        this.s = s;
        this.mobNumber = mobNumber;
        this.totalCards = totalCards;



    }

    @Override
    public void run() {


        try {
            PrintWriter p = new PrintWriter(s.getOutputStream(), true);
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            for(int card = 21; card <= totalCards; card++){
                for(int port = 1; port < 5; port++){

                    //System.out.println("card: " + card + " port: " + port);
                    p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card + " # " + port +
                            "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + port + "\"}");

                    String response = bufRd.readLine();
                    Object obj = JSONValue.parse(response);
                    JSONObject jsonObject = (JSONObject) obj;

                    String part1 = (String) jsonObject.get("send_to_sim");
                    String part2 = (String) jsonObject.get("reply");

                    console.appendText("Send to sim: " + part1 + " Status: " + part2);


                }//end inner port for loop
            }//end outer card for loop
        }catch(IOException e){
            console.appendText(e.getMessage());
        }//end try - catch block
        getResponses();
    }//end run method

    private void getResponses() {

        try {
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            for(int card = 21; card <= totalCards; card++){
                for(int port = 1; port < 5; port++) {
                    //System.out.println("card: " + card + " port: " + port);
                    String response = bufRd.readLine();

                    Object obj = JSONValue.parse(response);
                    JSONObject jsonObject = (JSONObject) obj;

                    String part1 = (String) jsonObject.get("send_to_sim");
                    String part2 = (String) jsonObject.get("reply");

                    console.appendText("Send to sim: " + part1 + " Status: " + part2 + "\n");
                }//end inner port loop
            }//end outer card loop
        }catch (IOException e){
            console.appendText(e.getMessage());
        }//end try - catch

    }//end getResponse
}//end class
