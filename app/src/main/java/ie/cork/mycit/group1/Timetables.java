package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Timetables extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetables);

        Resources res = this.getResources();
        String[] classGroups = res.getStringArray(R.array.classes);
        String[] arrayOfTimes = res.getStringArray(R.array.time);
        Spinner spinClassGroup = (Spinner) findViewById(R.id.spinnerClassGroups);

        spinClassGroup.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classGroups));
        //android:entries="@array/classes" can be used to populate the spinner too put in the xml


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		return true;
	}

	public void submit(View v){
		Spinner spinnerClass = (Spinner)findViewById(R.id.spinnerClassGroups);
		String classGroup = spinnerClass.getSelectedItem().toString();

		
		String url = "http://timetables.cit.ie:70/reporting/Individual;Student+Set;name;";
		url += classGroup;
		url += "&%0D%0A?weeks=&days=1-5&periods=1-40&height=100&width=100";

        String title = classGroup + " Timetable";

        Intent timetable = new Intent(Timetables.this, Timetable.class);
        timetable.putExtra("title", title);
        timetable.putExtra("url", url);
        Toast.makeText(this,url,Toast.LENGTH_LONG);
        startActivity(timetable);
	}
}
