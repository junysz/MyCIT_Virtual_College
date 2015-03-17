package ie.cork.mycit.group1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import ie.cork.mycit.timetable.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ie.cork.mycit.timetable.*;


public class Timetable extends ActionBarActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new LinkedList<String>();
    LinkedList<Lclass> classes=new LinkedList<Lclass>();
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    List<String> monday = new ArrayList<String>();
    List<String> tuesday = new ArrayList<String>();
    List<String> wednesday = new ArrayList<String>();
    List<String> thursday = new ArrayList<String>();
    List<String> friday = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        classes=new LinkedList<Lclass>();
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        setTitle(intent.getExtras().getString("title"));
        String url = intent.getExtras().getString("url");

                try{
                    try {
                        try {
                            classes = new TimetableExtractor().getTimetable(new RetrieveHTMLcode().execute(url).get());
                        }
                        catch (ExecutionException t)
                        {

                        }
                    }catch (InterruptedException r)
                    {

                    }
                }
                catch(IOException e)
                {

                }

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.timetableView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timetable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void prepareListData() {
        listDataHeader.add(getResources().getString(R.string.monday));
        listDataHeader.add(getResources().getString(R.string.tuesday));
        listDataHeader.add(getResources().getString(R.string.wednesday));
        listDataHeader.add(getResources().getString(R.string.thursday));
        listDataHeader.add(getResources().getString(R.string.friday));

        int currentDay = 1;
        int i = 0;
        if(classes!=null)
        {
            for(i=0;i<classes.size();i++)
            {
                switch (classes.get(i).getDayOfTheWeek()){
                    case 1:for(i=0;i<classes.size();i++)
                    {
                        switch (classes.get(i).getDayOfTheWeek()){
                            case 1:monday.add(classes.get(i).getStartTime() + classes.get(i).getName() +"\n\t\t" + classes.get(i).getLocation());
                                break;
                            case 2:tuesday.add(classes.get(i).getStartTime() + classes.get(i).getName() +"\n\t\t" + classes.get(i).getLocation());
                                break;
                            case 3:wednesday.add(classes.get(i).getStartTime() + classes.get(i).getName() +"\n\t\t" + classes.get(i).getLocation());
                                break;
                            case 4:thursday.add(classes.get(i).getStartTime() + classes.get(i).getName() +"\n\t\t" + classes.get(i).getLocation());
                                break;
                            case 5:friday.add(classes.get(i).getStartTime() + classes.get(i).getName() +"\n\t\t" + classes.get(i).getLocation());
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
                    monday.add("no classes found");
                    tuesday.add("no classes found");
                    wednesday.add("no classes found");
                    thursday.add("no classes found");
                    friday.add("no classes found");
                }
        listDataChild.put(listDataHeader.get(0),monday);
        listDataChild.put(listDataHeader.get(1),tuesday);
        listDataChild.put(listDataHeader.get(2),wednesday);
        listDataChild.put(listDataHeader.get(3),thursday);
        listDataChild.put(listDataHeader.get(4),friday);
            }
        }
