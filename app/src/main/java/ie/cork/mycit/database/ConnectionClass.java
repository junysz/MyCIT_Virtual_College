package ie.cork.mycit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionClass {

    ArrayList<IDNameLink> academicInfoArray = new ArrayList<IDNameLink>();
    ArrayList<IDItem> classesArray = new ArrayList<IDItem>();
    ArrayList<IDItem> departmentArray = new ArrayList<IDItem>();
    ArrayList<IDNameLink> importantDocsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> itServicesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> mapsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> newStudentsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> otherCollegesArray = new ArrayList<IDNameLink>();
    ArrayList<IDItem> sideMenuArray = new ArrayList<IDItem>();
    ArrayList<IDNameLink> studentAppsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> studentHandbooksArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> supportServicesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> usefulResourcesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> videosArray = new ArrayList<IDNameLink>();

    String url = "jdbc:mysql://MYSQL5006.Smarterasp.net:3306";
    String username = "9bd38f_citdata";
    String password = "citadmin";

    Connection con;
    Thread thrd1;
    Thread thrd2;

    public void mysql() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        thrd1 = new Thread(new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {

                    }
                    if (con == null) {
                        try {
                            con = DriverManager.getConnection(url, username, password);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            con = null;
                        }

                        if ((thrd2 != null) && (!thrd2.isAlive())){
                            thrd2.start();
                        }
                    }
                }

            }
        });
        if ((thrd1 != null) && (!thrd1.isAlive())) thrd1.start();

        thrd2 = new Thread(new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {

                    if (con != null) {
                        try {
                            con = DriverManager.getConnection(url, username, password);
                            //   con = DriverManager.getConnection("jdbc:mysql://192.168.1.45:3306/deneme", "ali", "12345");
                            Statement st = con.createStatement();
                            //String ali = "'fff'";
                            //st.execute("INSERT INTO deneme (name) VALUES(" + ali + ")");
                            ResultSet rs = st.executeQuery("select * from sidemenu");
                            ResultSetMetaData rsmd = rs.getMetaData();
                            String result = new String();

                            while (rs.next()) {
                                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                            con = null;
                        }

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /*
    String username = "9bd38f_citdata";
    String password = "citadmin";
    String connectionString = "Driver={MySQL ODBC 5.1 Driver};Server=MYSQL5006.Smarterasp.net;Database=db_9bd38f_citdata;Uid=" + username + ";Password=" + password + ";";
    "Driver={MySQL ODBC 5.1 Driver};Server=MYSQL5006.Smarterasp.net;Database=db_9bd38f_citdata;Uid=9bd38f_citdata;Password=YOUR_DB_PASSWORD;"

    String url = "jdbc:mysql://MYSQL5006.Smarterasp.net:3306";
    String username = "9bd38f_citdata";
    String password = "citadmin";
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url,"9bd38f_citdata","citadmin");
    Log.i("DATABASE", "Database connection established");
    Statement stmt = conn.createStatement() ;
    String query = "select * from sidemenu.menu_id;";
    ResultSet rs = stmt.executeQuery(query);
    while(rs.next())
    {
        System.out.println(rs.getString(1));
        System.out.println("\n");
        System.out.println(rs.getString(2));
        System.out.println("\n");
        System.out.println(rs.getString(3));
        System.out.println("\n");
    }

    public void main() throws Exception{
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        // st.executeUpdate("drop table survey;");
        st.executeUpdate("create table survey (id int,name varchar(30));");
        st.executeUpdate("insert into survey (id,name ) values (1,'nameValue')");

        st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM survey");

        ResultSetMetaData rsMetaData = rs.getMetaData();

        int numberOfColumns = rsMetaData.getColumnCount();
        System.out.println("resultSet MetaData column Count=" + numberOfColumns);

        st.close();
        conn.close();
    }*/

}
