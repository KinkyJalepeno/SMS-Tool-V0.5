package sender;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

public class SelectSite {

    private ChoiceBox<String> cBox;
    private TextField ipAddress;
    private TextField passWord;
    private TextField siteName;
    private TextArea console;
    private String url;


    public SelectSite(ChoiceBox<String> cBox, TextField ipAddress, TextField passWord, TextArea console, String url, TextField siteName) {

        this.cBox= cBox;
        this.ipAddress = ipAddress;
        this.passWord = passWord;
        this.siteName = siteName;
        this.console = console;
        this.url = url;
    }


    public void selectSite() {

        String site = cBox.getValue();
        if(site == null){
            console.appendText("Please select a gateway from  the drop-down\n");
        }else {
            String sql = ("SELECT * FROM gateways WHERE site_name = " +"\"" + site + "\" ");

            try (
                    Connection conn = DriverManager.getConnection(url);
                    Statement stmt  = conn.createStatement();
                    ResultSet rs    = stmt.executeQuery(sql)) {

                site = rs.getString("site_name");
                String address = rs.getString("ip_address");
                String password = rs.getString("password");

                ipAddress.setText(address);
                passWord.setText(password);
                siteName.setText(site);

                conn.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }//end else block
    }//end select site
}//end class
