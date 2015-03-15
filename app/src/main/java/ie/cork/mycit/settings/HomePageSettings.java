package ie.cork.mycit.settings;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.view.View;
import ie.cork.mycit.group1.R;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ie.cork.mycit.group1.HomePage;
import ie.cork.mycit.group1.R;


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
        cb.setText(text);
        cb.setChecked(getFromSP("x"));
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
        saveBoxes(0, findViewById(R.id.checkBox1));
        saveBoxes(1, findViewById(R.id.checkBox2));
        saveBoxes(2, findViewById(R.id.checkBox3));
        saveBoxes(3, findViewById(R.id.checkBox4));
        saveBoxes(4, findViewById(R.id.checkBox5));
        saveBoxes(5, findViewById(R.id.checkBox6));
        saveBoxes(6, findViewById(R.id.checkBox7));
        saveBoxes(7, findViewById(R.id.checkBox8));
        saveBoxes(8, findViewById(R.id.checkBox9));
        saveBoxes(9, findViewById(R.id.checkBox10));
        saveBoxes(10, findViewById(R.id.checkBox11));
        try{
           // saveFile();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Intent homepage = new Intent(HomePageSettings.this, HomePage.class);
        startActivity(homepage);
    }

    public void saveBoxes(int x, View v) {
        CheckBox cb = (CheckBox) v;
        if(cb.isChecked() == true){
           // checkList.add(x, "true");
        }
        else {
          //  checkList.add(x, "false");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,
                                 boolean isChecked) {
        // TODO Auto-generated method stub
        switch(buttonView.getId()){
            case R.id.checkBox1:
                saveInSp("cb1",isChecked);
                break;
            case R.id.checkBox2:
                saveInSp("cb2",isChecked);
                break;

            case R.id.checkBox3:
                saveInSp("cb3",isChecked);
                break;

            case R.id.checkBox4:
                saveInSp("cb4",isChecked);
                break;
        }

    }
}
/*
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ie.cork.mycit.group1.HomePage;
import ie.cork.mycit.group1.R;

public class HomePageSettings extends ActionBarActivity {
    List<String> checkList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_settings);
        setTitle("Settings");

        Resources res = this.getResources();
        String[] optionNames = res.getStringArray(R.array.studentappsnames);
        try{
            loadFile();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        start();
    }

    public void save(View v) {
        saveBoxes(0, findViewById(R.id.checkBox1));
        saveBoxes(1, findViewById(R.id.checkBox2));
        saveBoxes(2, findViewById(R.id.checkBox3));
        saveBoxes(3, findViewById(R.id.checkBox4));
        saveBoxes(4, findViewById(R.id.checkBox5));
        saveBoxes(5, findViewById(R.id.checkBox6));
        saveBoxes(6, findViewById(R.id.checkBox7));
        saveBoxes(7, findViewById(R.id.checkBox8));
        saveBoxes(8, findViewById(R.id.checkBox9));
        saveBoxes(9, findViewById(R.id.checkBox10));
        saveBoxes(10, findViewById(R.id.checkBox11));
        try{
            saveFile();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Intent homepage = new Intent(HomePageSettings.this, HomePage.class);
        startActivity(homepage);
    }

    public void saveBoxes(int x, View v) {
        CheckBox cb = (CheckBox) v;
        if(cb.isChecked() == true){
            checkList.add(x, "true");
        }
        else {
            checkList.add(x, "false");
        }
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
        cb.setText(text);
        if(checkList.get(x).equalsIgnoreCase("true")){
            cb.setChecked(true);
        }
    }

    private void loadFile() throws FileNotFoundException{

        InputStream inStream = null;

        try {
            Resources res = getResources();
            inStream = res.openRawResource(R.raw.listfile);

            Scanner scan = new Scanner(inStream);
            String line;
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                checkList.add(line);
            }
            scan.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

    private void saveFile() throws IOException{

    }

}*/