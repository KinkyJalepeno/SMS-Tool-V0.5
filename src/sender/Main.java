package sender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Dave's SMS Sender");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



//    public static void pauseServer(Socket s) throws Exception{
//
//        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
//        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{ \"method\":\"pause_server\" }");
//        String response = bufRd.readLine();
//
//    }// end pauseServer
//
//    public static void runServer(Socket s) throws Exception{
//
//        PrintWriter p = new PrintWriter(s.getOutputStream(), true);
//        BufferedReader bufRd = new BufferedReader(new InputStreamReader(s.getInputStream()));
//
//        p.println("{ \"method\":\"run_server\" }");
//        String response = bufRd.readLine();
//
//    }//end runServer
//
//
//    }//end setRand
}//end main class

