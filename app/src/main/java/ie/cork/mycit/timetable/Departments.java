package ie.cork.mycit.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ie.cork.mycit.database.IDItem;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;
import ie.cork.mycit.group1.R;

public class Departments extends Activity{

    List<String> deptIDs = new ArrayList<String>();
    List<String> deptNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        TableData readData = LocalPersistence.readObjectFromFile(Departments.this);
        ArrayList<IDItem> data = readData.getDepartmentArray();
        deptIDs = LocalPersistence.readIDItem(1, data);
        deptNames = LocalPersistence.readIDItem(2, data);

        Spinner spinDepts = (Spinner) findViewById(R.id.spinnerDepts);

        spinDepts.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deptNames));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void submitDept(View v){
        Spinner spinDepts = (Spinner)findViewById(R.id.spinnerDepts);
        String dept = spinDepts.getSelectedItem().toString();
        Log.i("result", dept + " selected");

        Intent departments = new Intent(Departments.this, Timetables.class);
        int pos = findPosition(dept);
        String message = dept + " selected";
        Toast.makeText(Departments.this, message, Toast.LENGTH_LONG).show();
        departments.putExtra("id", deptIDs.get(pos).toString());
        departments.putExtra("dept", deptNames.get(pos).toString());
        startActivity(departments);
    }

    public int findPosition(String selected) {
        Iterator<String> itr = deptNames.iterator();
        int x = 0;
        while (itr.hasNext()) {
            if(selected.equalsIgnoreCase(deptNames.get(x))){
                return x;
            }
            x++;
        }
        return 0;
    }
}
