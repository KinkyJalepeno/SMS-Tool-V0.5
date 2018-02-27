package sender;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.sql.*;

public class SiteList {

    private TextArea console;
    private ChoiceBox<String> cBox;
    private String url;

    ObservableList list = FXCollections.observableArrayList();


    public SiteList(TextArea console, ChoiceBox<String> cBox, String url) {

        this.console = console;
        this.cBox = cBox;
        this.url = url;

    }


    public void readList() {
        list.removeAll(list);

        try {
        Connection conn = DriverManager.getConnection(url);

            console.appendText("SQLite DB is connected\n" );
            String sql = "SELECT * FROM gateways";//construct db query

            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {

                String site = rs.getString("site_name");
                list.addAll(site);

            }
            cBox.getItems().addAll(list);//add site names to list
            console.appendText("Site data pre-loaded\n");

            conn.close();

        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }


    }//end read list
}//end class
