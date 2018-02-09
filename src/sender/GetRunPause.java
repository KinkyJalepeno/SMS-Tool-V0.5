package sender;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GetRunPause {


    public static String getRunPause(Socket s)throws IOException {

        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));

        p.println("{\"method\":\"get_server_status\"}");
        String response = bufRd.readLine();

        System.out.println(response);

        Object obj = JSONValue.parse(response);
        JSONObject jsonObject = (JSONObject) obj;

        String status = (String) jsonObject.get("server_currently_status");

        return status;
    }
}
