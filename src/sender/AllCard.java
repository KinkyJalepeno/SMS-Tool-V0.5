package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AllCard implements Runnable{

    private Socket s;
    private String card;
    private TextArea console;
    private String mobNumber;


    public AllCard(Socket s, String card, TextArea console, String mobNumber) {

        this.s = s;
        this.card = card;
        this.console = console;
        this.mobNumber = mobNumber;

    }

    @Override
    public void run() {

        for(int i = 1; i < 5; i++) {
            try {
                s.setSoTimeout(8000);
                PrintWriter p = new PrintWriter(s.getOutputStream(), true);
                BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

                System.out.println(card + " " + i);

                p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card + " # " + i +
                        "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + i + "\"}");

                String response = bufRd.readLine();
                Object obj = JSONValue.parse(response);
                JSONObject jsonObject = (JSONObject) obj;

                String part1 = (String) jsonObject.get("send_to_sim");
                String part2 = (String) jsonObject.get("reply");

                console.appendText("Send to sim: " + part1 + " Status: " + part2 + "\n");
            } catch(IOException e){
                console.appendText(e.getMessage()+ "\nCheck gateway connection \n");
            }//end try - catch block
        }//end for loop

        getResponses();

    }//end run

    private void getResponses() {

        try {
            s.setSoTimeout(5000);
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            for(int i = 1; i < 5; i++){

                String response = bufRd.readLine();

                Object obj = JSONValue.parse(response);
                JSONObject jsonObject = (JSONObject) obj;

                String part1 = (String) jsonObject.get("send_to_sim");
                String part2 = (String) jsonObject.get("reply");

                console.appendText("Send to sim: " + part1 + " Status: " + part2 +"\n");
            }//end for loop
        }catch(IOException e){
            console.appendText(e.getMessage()+ ", Check sims\n");
        }//end try - catch

    }//end getResponse
}//end class
