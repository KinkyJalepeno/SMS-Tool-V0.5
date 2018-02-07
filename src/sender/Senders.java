package sender;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Senders {

    private static Socket s;
    private static String response;
    private static PrintWriter p;
    private static BufferedReader bufRd;
    private static Object obj;
    private static JSONObject jsonObject;

    public static boolean authenticate(String passWord, String ipAddress) throws IOException{

        s = new Socket(ipAddress, 63333);

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"authentication\",\"server_password\":\"" + passWord + "\"}");
        response = bufRd.readLine();
        p.println("");
        response = bufRd.readLine();

        if(response.equals("SMS server connected")){
            return true;
        }
        return false;
    }

    public static boolean serverStatus() throws IOException{

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"get_server_status\"}");
        response = bufRd.readLine();

        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String status = (String) jsonObject.get("server_currently_status");
        if(status.equals("Running")){
            return true;
        }
        return false;
    }

    public static String sendRand(String mobNumber) throws IOException{

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"number\":\"" + mobNumber + "\", \"msg\":\"Random port test\", \"unicode\":\"5\"}");

        response = bufRd.readLine();
        System.out.println(response);
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

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
    }

    public static String cardPort(String mobNumber, String card, String port) throws IOException{

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card +"#" + port +
                "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + port + "\"}");

        response = bufRd.readLine();
        //System.out.println(response);
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String part1 = (String) jsonObject.get("send_to_sim");
        String part2 = (String) jsonObject.get("reply");

        String collection = ("Send to sim: " + part1 + " \nStatus: " + part2 + "\n");

        return collection;
    }//end card/port part 1

    public static String cardPort2()throws IOException {

        //p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card +"#" + port +
        //       "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + port + "\"}");

        response = bufRd.readLine();
        //System.out.println(response);
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String part1 = (String) jsonObject.get("number");
        String part2 = (String) jsonObject.get("reply");
        if(part2.equals("proceeding")){
            String collection = ("Please check sims/port - SMS queued");
            return collection;
        }

        String collection = ("Number: " + part1 + " \nStatus: " + part2 + "\n");
        return collection;
    }

    public static String allCard(String mobNumber, String card, int i)throws IOException {

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card + " # " + i +
                "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + i + "\"}");

        response = bufRd.readLine();
        //System.out.println(response);
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String part1 = (String) jsonObject.get("send_to_sim");
        String part2 = (String) jsonObject.get("reply");

        String collection = ("Send to sim: " + part1 + " Status: " + part2 + "\n");

        return collection;
    }

    public static String allCard2()throws IOException {

        p = new PrintWriter(s.getOutputStream(), true);
        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        response = bufRd.readLine();
        //System.out.println(response);
        obj = JSONValue.parse(response);
        jsonObject = (JSONObject) obj;

        String part1 = (String) jsonObject.get("send_to_sim");
        String part2 = (String) jsonObject.get("reply");

        String collection = ("Send to sim: " + part1 + " Status: " + part2 + "\n");

        return collection;

    }


}
