package ie.cork.mycit.timetable;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ie.cork.mycit.database.IDItem;
import ie.cork.mycit.database.IDNameLink;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;
import ie.cork.mycit.group1.R;

public class Timetables extends Activity{

    private List<String> deptIDs = new ArrayList<String>();
    private List<String> classNames = new ArrayList<String>();
    private String dept;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetables);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        dept = intent.getExtras().getString("dept");

        TextView deptTextView = (TextView)findViewById(R.id.textViewClassGroups);
        deptTextView.setText(dept);

        TableData readData = LocalPersistence.readObjectFromFile(Timetables.this);
        ArrayList<IDItem> data = readData.getClassesArray();
        deptIDs = LocalPersistence.readIDItem(1, data);
        classNames = LocalPersistence.readIDItem(2, data);

        List<String> deptClassNames = findClasses(id);

        Spinner spinClassGroup = (Spinner) findViewById(R.id.spinnerClassGroups);

        if(id.equalsIgnoreCase("1")){
            Log.i("result", "classNames used");
            spinClassGroup.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classNames));
        }else{
            int x = deptClassNames.size();
            if(x >= 1){
                Log.i("result", "deptClassNames not null " + x);
                spinClassGroup.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deptClassNames));
            }else{
                Log.i("result", "deptClassNames null " + x);
                Intent timetables = new Intent(Timetables.this, Departments.class);
                String message = dept + " has no courses sorry";
                Toast.makeText(Timetables.this, message, Toast.LENGTH_LONG).show();
                startActivity(timetables);
            }
        }
    }

    public List<String> findClasses(String selected) {
        //Iterator<String> itr = classNames.iterator();
        int listCount = classNames.size();
        List<String> deptCNs = new ArrayList<String>();
        int x = 1;
        while (x < listCount) {
            if(selected.equalsIgnoreCase(deptIDs.get(x))){
                deptCNs.add(classNames.get(x));
            }
            x++;
        }
        return deptCNs;
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
		url += "%0D%0A?weeks=&days=1-5&periods=1-40&height=100&width=100";

        String title = classGroup + " Timetable";

        Intent timetable = new Intent(Timetables.this, Timetable.class);
        timetable.putExtra("title", title);
        timetable.putExtra("url", url);
        Toast.makeText(this,url,Toast.LENGTH_LONG);
        startActivity(timetable);
	}
}
