package ie.cork.mycit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionClass {

    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connected..");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void gettingDataFromMySql() {
        connection();
        //mycitvappapp.cksebamxxpti.eu-west-1.rds.amazonaws.com:3306
        //My_CIT_App
        //UsernameName 	rooTmaster3
        //pass : 33080okvgnZxjNtvan
        String host = "jdbc:mysql://localhost/PROJECT";
        String username = "rooTmaster3";
        String password = "33080okvgnZxjNtvan";
        try {
            Connection connect = DriverManager.getConnection(host, username,
                    password);
            PreparedStatement statement = connect.prepareStatement(
                    "SELECT patient.name, history.histMedicine," +
                            " history.histProcedure, history.histDescription " +
                            "FROM patient, history WHERE patient.id = history.patientId GROUP BY patient.name");
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                System.out.println("Name: " + data.getString(1) + " - "
                        + "Medicine: " + data.getString(2) + "Procedure: "
                        + data.getString(3) + "Description: "
                        + data.getString(4));
                /*tList.append("Name: " + data.getString(1) + " Medicine: "
                        + data.getString(2) + " Procedure: "
                        + data.getString(3) + " Description: "
                        + data.getString(4)
                        + System.getProperty("line.separator"));
                */
            }
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
