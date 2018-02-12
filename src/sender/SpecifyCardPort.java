package sender;

import javafx.scene.control.TextArea;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SpecifyCardPort implements Runnable{

    private String mobNumber;
    private Socket s;
    private TextArea console;
    private String port;
    private String card;


    public SpecifyCardPort(String mobNumber, Socket s, TextArea console, String card, String port) {

        this.mobNumber = mobNumber;
        this.s = s;
        this.console = console;
        this.card = card;
        this.port = port;

    }

    @Override
    public void run() {

        try {
            s.setSoTimeout(8000);
            PrintWriter p = new PrintWriter(s.getOutputStream(), true);
            BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

            p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card + " # " + port +
                        "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + port + "\"}");

            String response = bufRd.readLine();

            Object obj = JSONValue.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            String part1 = (String) jsonObject.get("number");
            String part2 = (String) jsonObject.get("reply");

            console.appendText("Number: " + part1 + " Status: " + part2 +"\n");
            } catch(IOException e){
            console.appendText(e.getMessage()+ "\nCheck gateway connection \n");
        }//end try - catch block
        getResponses();
    }//end run method

    public void getResponses(){

        try {
            s.setSoTimeout(5000);
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
            console.appendText(e.getMessage()+ ", Check sims\n");
        }//end try - catch

    }
}//end class
