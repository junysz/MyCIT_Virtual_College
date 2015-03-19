package ie.cork.mycit.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.view.View;
import ie.cork.mycit.group1.R;
import android.content.Intent;
import ie.cork.mycit.group1.HomePage;

public class HomePageSettings extends Activity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_settings);
        setTitle("Settings");
        start();
    }

    public void start(){
        startBoxes("Student E-mail", 0, findViewById(R.id.checkBox1));
        startBoxes("Blackboard", 1, findViewById(R.id.checkBox2));
        startBoxes("Timetables", 2, findViewById(R.id.checkBox3));
        startBoxes("Exam Papers", 3, findViewById(R.id.checkBox4));
        startBoxes("Library Search", 4, findViewById(R.id.checkBox5));
        startBoxes("Student Card Top-Up", 5, findViewById(R.id.checkBox6));
        startBoxes("Web for Student", 6, findViewById(R.id.checkBox7));
        startBoxes("CIT Password Reset", 7, findViewById(R.id.checkBox8));
        startBoxes("Wifi Registration", 8, findViewById(R.id.checkBox9));
        startBoxes("Student Drive", 9, findViewById(R.id.checkBox10));
        startBoxes("Job Listings", 10, findViewById(R.id.checkBox11));
    }

    public void startBoxes(String text, int x, View v){
        CheckBox cb = (CheckBox) v;
        String a = "" + x;
        cb.setText(text);
        cb.setChecked(getFromSP(a));
        cb.setOnCheckedChangeListener(this);
    }

    private boolean getFromSP(String key){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private void saveInSp(String key,boolean value){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void save(View v) {
        Intent homepage = new Intent(HomePageSettings.this, HomePage.class);
        startActivity(homepage);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        switch(buttonView.getId()){
            case R.id.checkBox1:
                saveInSp("0",isChecked);
                break;
            case R.id.checkBox2:
                saveInSp("1",isChecked);
                break;
            case R.id.checkBox3:
                saveInSp("2",isChecked);
                break;
            case R.id.checkBox4:
                saveInSp("3",isChecked);
                break;
            case R.id.checkBox5:
                saveInSp("4",isChecked);
                break;
            case R.id.checkBox6:
                saveInSp("5",isChecked);
                break;
            case R.id.checkBox7:
                saveInSp("6",isChecked);
                break;
            case R.id.checkBox8:
                saveInSp("7",isChecked);
                break;
            case R.id.checkBox9:
                saveInSp("8",isChecked);
                break;
            case R.id.checkBox10:
                saveInSp("9",isChecked);
                break;
            case R.id.checkBox11:
                saveInSp("10",isChecked);
                break;
        }

    }
}