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

public class Timetables extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetables);

        Resources res = this.getResources();
        String[] classGroups = res.getStringArray(R.array.classes);
        String[] arrayOfTimes = res.getStringArray(R.array.time);
        Spinner spinClassGroup = (Spinner) findViewById(R.id.spinnerClassGroups);
        Spinner spinTime = (Spinner) findViewById(R.id.spinnerTime);

        spinClassGroup.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classGroups));
        //android:entries="@array/classes" can be used to populate the spinner too put in the xml

        spinTime.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfTimes));
        //android:id="@+id/spinnerTime" can be used to populate the spinner too put in the xml

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
		
		Spinner spinnerTime = (Spinner)findViewById(R.id.spinnerTime);
		String time = spinnerTime.getSelectedItem().toString();
		
		String url = "http://timetables.cit.ie:70/reporting/Individual;Student+Set;name;";
		url += classGroup + "%0D%0A?weeks=";
		
		if(time.equalsIgnoreCase("Semester 1")){
			url += "4-16";
		}
		else if(time.equalsIgnoreCase("Semester 2")){
			url += "24-31;34-38";
		}
		url += "&days=1-5&periods=1-40&height=100&width=100";

        String title = classGroup + " Timetable";

        Intent web = new Intent(Timetables.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
	}

    /*
    public void departmentListener(){
        spinDepartments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                String stringDepartment = parentView.getItemAtPosition(position).toString();

                ArrayList<String> curClasses;

                if(stringDepartment.equalsIgnoreCase("All")){
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, classGroups));
                }
                else if(stringDepartment.equalsIgnoreCase("ACCOUNTING")){
                    curClasses = departmentCheck("AI");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ADMIN")){
                    curClasses = departmentCheck("AI");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ALC")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ARCHITECTURE")){
                    curClasses = departmentCheck("AR");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ARTSOFFICE")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("BIOLOGY")){
                    curClasses = departmentCheck("BI");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CCAD")){
                    curClasses = departmentCheck("CC");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CHEMISTRY")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CIVIL")){
                    curClasses = departmentCheck("CS");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("COMPUTING")){
                    curClasses = departmentCheck("CO");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CONSTRUCTION")){
                    curClasses = departmentCheck("CA");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CRAFTSTUDIES")){
                    curClasses = departmentCheck("CR");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CSM")){
                    curClasses = departmentCheck("UQ");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CSMACADEMIC")){
                    curClasses = departmentCheck("UQ");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CSMKEYBOARD")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CSMSTRINGS")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("CSMWIND")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("DEIS")){
                    curClasses = departmentCheck("DE");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ELECTRICAL")){
                    curClasses = departmentCheck("EE");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("MANAGEMENT")){
                    curClasses = departmentCheck("MG");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("MATHS")){
                    curClasses = departmentCheck("MA");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("MECHANICAL")){
                    curClasses = departmentCheck("MB");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("MEDIA")){
                    curClasses = departmentCheck("MC");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("NMCI")){
                    curClasses = departmentCheck("NS");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ONLINE")){
                    curClasses = departmentCheck("0000");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("ORGANISATION")){
                    curClasses = departmentCheck("OP");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("PHYSICAL SCIENCES")){
                    curClasses = departmentCheck("PS");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("PROCESS")){
                    curClasses = departmentCheck("PE");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("SOCIAL")){
                    curClasses = departmentCheck("AS");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("SPORT")){
                    curClasses = departmentCheck("SL");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }
                else if(stringDepartment.equalsIgnoreCase("TOURISM")){
                    curClasses = departmentCheck("TC");
                    spinClassGroup.setAdapter(new ArrayAdapter<String>(Timetables.this, android.R.layout.simple_spinner_item, curClasses));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public ArrayList<String> departmentCheck(String departmentCode){
        List<String> classes = Arrays.asList(classGroups);
        ArrayList<String> curClasses = new ArrayList<String>();
        String temp = "";
        int x = 0;
        while(classes.listIterator().hasNext()){
            temp = classes.get(x);
            if(temp.startsWith(departmentCode)){
                curClasses.add(temp);
            }
            x++;
        }
        return curClasses;
    }
    */
}
