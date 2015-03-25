package ie.cork.mycit.timetable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import ie.cork.mycit.group1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Timetable extends ActionBarActivity {
    private final List<String> listDataHeader = new LinkedList<String>();
    private LinkedList<Lclass> classes=new LinkedList<Lclass>();
    private final HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    private final List<String> monday = new ArrayList<String>();
    private final List<String> tuesday = new ArrayList<String>();
    private final List<String> wednesday = new ArrayList<String>();
    private final List<String> thursday = new ArrayList<String>();
    private final List<String> friday = new ArrayList<String>();
    boolean gotTimetable=false;
    private String url;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        classes=new LinkedList<Lclass>();
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        setTitle(intent.getExtras().getString("title"));
        url = intent.getExtras().getString("url");

        try {
            new RetrieveHTMLcode().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // get the listview
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.timetableView);

        // preparing list data
        if(!isNetworkConnected())
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Please make sure you have internet connection and try again.");
            dlgAlert.setTitle("No Internet Connection!");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent timetable = new Intent(Timetable.this, Timetables.class);
                            startActivity(timetable);
                        }
                    });
            dlgAlert.create().show();
        }
        else {
            prepareListData();
        }

        ClassComparator.ExpandableListAdapter listAdapter = new ClassComparator.ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    private void prepareListData() {
        listDataHeader.add(getResources().getString(R.string.monday));
        listDataHeader.add(getResources().getString(R.string.tuesday));
        listDataHeader.add(getResources().getString(R.string.wednesday));
        listDataHeader.add(getResources().getString(R.string.thursday));
        listDataHeader.add(getResources().getString(R.string.friday));

        int i = 0;
        while(gotTimetable==false&&i<50){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        if(classes!=null)
        {
            for(i=0;i<classes.size();i++)
            {
                switch (classes.get(i).getDayOfTheWeek()){
                    case 1:for(i=0;i<classes.size();i++)
                    {
                        switch (classes.get(i).getDayOfTheWeek()){
                            case 1:monday.add(0,classes.get(i).getStartTime().getTimeFormatted()+  " - " +classes.get(i).getEndTime().getTimeFormatted()+ "\n\t" + classes.get(i).getName() +"\n\t" + classes.get(i).getLocation());
                                break;
                            case 2:tuesday.add(0,classes.get(i).getStartTime().getTimeFormatted()+ " - " +classes.get(i).getEndTime().getTimeFormatted()+ "\n\t" + classes.get(i).getName() +"\n\t" + classes.get(i).getLocation());
                                break;
                            case 3:wednesday.add(0,classes.get(i).getStartTime().getTimeFormatted()+ " - " +classes.get(i).getEndTime().getTimeFormatted()+ "\n\t" + classes.get(i).getName() +"\n\t" + classes.get(i).getLocation());
                                break;
                            case 4:thursday.add(0,classes.get(i).getStartTime().getTimeFormatted()+ " - " +classes.get(i).getEndTime().getTimeFormatted()+ "\n\t" + classes.get(i).getName() +"\n\t" + classes.get(i).getLocation());
                                break;
                            case 5:friday.add(0,classes.get(i).getStartTime().getTimeFormatted()+ " - " +classes.get(i).getEndTime().getTimeFormatted()+ "\n" + classes.get(i).getName() +"\n\t" + classes.get(i).getLocation());
                                break;
                        }
                    }
                        if(monday.isEmpty())
                        {
                            monday.add("No classes on this day");
                        }
                        if(tuesday.isEmpty())
                        {
                            tuesday.add("No classes on this day");
                        }
                        if(wednesday.isEmpty())
                        {
                            wednesday.add("No classes on this day");
                        }
                        if(thursday.isEmpty())
                        {
                            thursday.add("No classes on this day");
                        }
                        if(friday.isEmpty())
                        {
                            friday.add("No classes on this day");
                        }
                }
            }
        }
        else{
                    monday.add("No classes found");
                    tuesday.add("No classes found");
                    wednesday.add("No classes found");
                    thursday.add("No classes found");
                    friday.add("No classes found");
                }
        listDataChild.put(listDataHeader.get(0),monday);
        listDataChild.put(listDataHeader.get(1),tuesday);
        listDataChild.put(listDataHeader.get(2),wednesday);
        listDataChild.put(listDataHeader.get(3),thursday);
        listDataChild.put(listDataHeader.get(4),friday);
            }
    private class RetrieveHTMLcode extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... uri) {
            try {
                classes = new TimetableExtractor().getTimetable(new BufferedReader(new InputStreamReader(new URL(url).openStream())));
                gotTimetable=true;
            } catch (IOException e) {
                e.printStackTrace();
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

