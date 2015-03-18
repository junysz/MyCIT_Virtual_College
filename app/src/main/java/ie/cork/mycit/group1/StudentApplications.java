package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.cork.mycit.timetable.Timetables;

public class StudentApplications extends Activity {

    List<String> appNames = new ArrayList<String>();
    List<String> appLinks = new ArrayList<String>();
    String tempApp = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_applications);

        Resources res = this.getResources();
        String[] studentappsnames = res.getStringArray(R.array.studentappsnames);
        String[] studentappslinks = res.getStringArray(R.array.studentappslinks);
        appNames = Arrays.asList(studentappsnames);
        appLinks = Arrays.asList(studentappslinks);

        ListView listnames = (ListView) findViewById(R.id.listViewNames);

        CustomViewAdapter adapter = new CustomViewAdapter(StudentApplications.this, Arrays.asList(studentappsnames));
        listnames.setAdapter(adapter);

        registerClickCallback();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listViewNames);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempApp = appNames.get(position);
                int x = position + 1;
                String message = "You clicked # " + x + " " + tempApp;
                Toast.makeText(StudentApplications.this, message, Toast.LENGTH_LONG).show();
                menuSelect(appNames.get(position).toString(), appLinks.get(position).toString());
            }

        });
    }

    public void menuSelect(String title, String url) {
        if(title.equalsIgnoreCase("Timetables")){
            Intent timetables = new Intent(StudentApplications.this, Timetables.class);
            startActivity(timetables);
        }
        else{
            viewWeb(title, url);
        }
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(StudentApplications.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
