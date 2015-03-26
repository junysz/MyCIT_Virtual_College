package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import ie.cork.mycit.database.IDItem;
import ie.cork.mycit.database.IDNameLink;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;

public class SplashActivity extends Activity {
    private TableData data = new TableData();
    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 3;    // Sleep for some time

    private FileOutputStream fos;
    private static String FILENAME = "InteralString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            new RetrieveData().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        setContentView(R.layout.activity_splash);

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(SplashActivity.this, HomePage.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }

    private class RetrieveData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... uri) {

            Log.i("result","Starting method doInBackground()");
            String url = "jdbc:mysql://mycitvappapp.cksebamxxpti.eu-west-1.rds.amazonaws.com:3306/My_CIT_App";
            String username = "andApp";
            String password = "a";
            Connection con;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Log.i("result", "got the driver");
            } catch (ClassNotFoundException e) {
                Log.i("result","failed to get driver");

            }

            try {
                con = DriverManager.getConnection(url, username,password);
            } catch (SQLException e) {
                e.printStackTrace();
                Log.i("result","Went into catch SQLException con = null");
                Log.i("result",e.getMessage());
                con = null;
            }

            try {
                con = DriverManager.getConnection(url, username, password);
                Statement st = con.createStatement();

                //Log.i("result", (rs.getInt(1) + " : " + rsmd.getColumnName(1)));
                //Log.i("result", (rs.getString(2)+" : "+ rsmd.getColumnName(2)));
                //Log.i("result", (rs.getString(3)+" : "+ rsmd.getColumnName(3)));

                ResultSet rs = st.executeQuery("select * from sidemenu");
                while (rs.next()) {
                    data.getSideMenuArray().add(new IDItem(rs.getInt(1), rs.getString(2)));
                }

                rs = st.executeQuery("select * from academicinformation");
                while (rs.next()) {
                    data.getAcademicInfoArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from classes");
                while (rs.next()) {
                    data.getClassesArray().add(new IDItem(rs.getInt(1), rs.getString(2)));
                }

                rs = st.executeQuery("select * from department");
                while (rs.next()) {
                    data.getDepartmentArray().add(new IDItem(rs.getInt(1), rs.getString(2)));
                }

                rs = st.executeQuery("select * from importantdocuments");
                while (rs.next()) {
                    data.getImportantDocsArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from itservices");
                while (rs.next()) {
                    data.getItServicesArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from maps");
                while (rs.next()) {
                    data.getMapsArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from newstudents");
                while (rs.next()) {
                    data.getNewStudentsArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from othercolleges");
                while (rs.next()) {
                    data.getOtherCollegesArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from studentapplications");
                while (rs.next()) {
                    data.getStudentAppsArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from studenthandbooks");
                while (rs.next()) {
                    data.getStudentHandbooksArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from studentnews");
                while (rs.next()) {
                    data.getStudentNewsArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from supportservices");
                while (rs.next()) {
                    data.getSupportServicesArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from usefulresources");
                while (rs.next()) {
                    data.getUsefulResourcesArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }

                rs = st.executeQuery("select * from videos");
                while (rs.next()) {
                    data.getVideosArray().add(new IDNameLink(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                Log.i("result", "Got all DB info");

                LocalPersistence.writeObjectToFile(SplashActivity.this, data);

            }catch (java.sql.SQLException sqlE){
                Log.i("result","Went into catch SQLException sqlE");
                Log.i("result",sqlE.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Do anything with response..
        }

    }
}