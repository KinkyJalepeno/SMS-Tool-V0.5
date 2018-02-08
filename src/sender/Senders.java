package sender;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Senders {

    private static String response;
    private static PrintWriter p;
    private static BufferedReader bufRd;
    private static Object obj;
    private static JSONObject jsonObject;

 //    public static String allPorts(String mobNumber, int card, int port) throws IOException{
//
//        p.println("{\"number\": \"" + mobNumber + "\",\"msg\":\"" + card + " # " + port +
//                "\",\"unicode\":\"2\",\"send_to_sim\":\"" + card + "#" + port + "\"}");
//        String response = bufRd.readLine();
//        Object obj = JSONValue.parse(response);
//        JSONObject jsonObject = (JSONObject) obj;
//
//        String part1 = (String) jsonObject.get("send_to_sim");
//        String part2 = (String) jsonObject.get("reply");
//
//        String collection = ("Send to sim: " + part1 + " Status: " + part2);
//        return collection;
//    }//end allPorts method
//
//    public static String allPorts2()throws IOException {
//
//        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        String response = bufRd.readLine();
//
//        Object obj = JSONValue.parse(response);
//        JSONObject jsonObject = (JSONObject) obj;
//
//        String part1 = (String) jsonObject.get("send_to_sim");
//        String part2 = (String) jsonObject.get("reply");
//
//        String collection = ("Send to sim: " + part1 + " Status: " + part2);
//        return collection;
//    }//end allPorts2 method
//
//    public static String pauseServer()throws IOException {
//
//        p = new PrintWriter(s.getOutputStream(), true);
//        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{\"method\":\"pause_server\"}");
//        response = bufRd.readLine();
//
//        obj = JSONValue.parse(response);
//        jsonObject = (JSONObject) obj;
//
//        String status = (String) jsonObject.get("reply");
//        return status;
//
//    }//end pause server
//
//    public static String runServer()throws IOException {
//
//        p = new PrintWriter(s.getOutputStream(), true);
//        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{\"method\":\"run_server\"}");
//        response = bufRd.readLine();
//
//        obj = JSONValue.parse(response);
//        jsonObject = (JSONObject) obj;
//
//        String status = (String) jsonObject.get("reply");
//        return status;
//
//    }//end
//
//    public static String queryGenQue(Socket s)throws IOException {
//
//        p = new PrintWriter(s.getOutputStream(), true);
//        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{\"method\":\"get_q_size\"}");
//        String response = bufRd.readLine();
//        Object obj = JSONValue.parse(response);
//        JSONObject jsonObject = (JSONObject) obj;
//
//        long gen = (long) jsonObject.get("total_len");
//        String collection = ("Number of messages in general queue: " + gen);
//
//        return collection;
//    }//end general queue check
//
//    public static String queryMasQue()throws IOException {
//
//        p = new PrintWriter(s.getOutputStream(), true);
//        bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{\"method\":\"get_q_size\",\"queue_type\":\"master\"}");
//        String response = bufRd.readLine();
//        Object obj = JSONValue.parse(response);
//        JSONObject jsonObject = (JSONObject) obj;
//
//        long gen = (long) jsonObject.get("total_len");
//        String collection = ("Number of messages in master queue: " + gen);
//
//        return collection;
//    }
//
//    public static String flushGenQue(Socket s)throws IOException {
//
//        p.println("{\"method\":\"delete_queue\"}");
//        response = bufRd.readLine();
//        obj = JSONValue.parse(response);
//        jsonObject = (JSONObject) obj;
//
//        String reply = (String) jsonObject.get("reply");
//        String collection = ("Reply: " + reply + ", general queue flushed \n");
//        return collection;
//    }
//
//    public static String flushMasQue()throws IOException {
//
//        p.println("{ \"method\":\"delete_queue\",\"queue_type\":\"master\" }");
//        response = bufRd.readLine();
//        obj = JSONValue.parse(response);
//        jsonObject = (JSONObject) obj;
//
//        String reply = (String) jsonObject.get("reply");
//        String collection = ("Reply: " + reply + ", master queue flushed \n");
//        return collection;
//    }

}//end class
