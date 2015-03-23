package ie.cork.mycit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionClass {

    ArrayList<IDNameLink> academicInfoArray = new ArrayList<IDNameLink>();
    ArrayList<DeptIDClass> classesArray = new ArrayList<DeptIDClass>();
    ArrayList<DeptIDNameArrayList> departmentArray = new ArrayList<DeptIDNameArrayList>();
    ArrayList<IDNameLink> itServicesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> mapsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> newStudentsArray = new ArrayList<IDNameLink>();
    ArrayList<IDItem> sideMenuArray = new ArrayList<IDItem>();
    ArrayList<IDNameLink> studentAppsArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> supportServicesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> usefulResourcesArray = new ArrayList<IDNameLink>();
    ArrayList<IDNameLink> videosArray = new ArrayList<IDNameLink>();

    String username = "9bd38f_citdata";
    String password = "citadmin";
    String connectionString = "Driver={MySQL ODBC 5.1 Driver};Server=MYSQL5006.Smarterasp.net;Database=db_9bd38f_citdata;Uid=" + username + ";Password=" + password + ";";
    //"Driver={MySQL ODBC 5.1 Driver};Server=MYSQL5006.Smarterasp.net;Database=db_9bd38f_citdata;Uid=9bd38f_citdata;Password=YOUR_DB_PASSWORD;"

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
    }

    private static Connection getConnection() throws Exception {
        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        String url = "jdbc:odbc:northwind";
        String username = "";
        String password = "";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}
