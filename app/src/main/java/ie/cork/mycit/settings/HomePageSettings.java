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
        startBoxes(0, findViewById(R.id.checkBox1));
        startBoxes(1, findViewById(R.id.checkBox2));
        startBoxes(2, findViewById(R.id.checkBox3));
        startBoxes(3, findViewById(R.id.checkBox4));
        startBoxes(4, findViewById(R.id.checkBox5));
        startBoxes(5, findViewById(R.id.checkBox6));
        startBoxes(6, findViewById(R.id.checkBox7));
        startBoxes(7, findViewById(R.id.checkBox8));
        startBoxes(8, findViewById(R.id.checkBox9));
        startBoxes(9, findViewById(R.id.checkBox10));
        startBoxes(10, findViewById(R.id.checkBox11));
    }

    public void startBoxes(int x, View v){
        CheckBox cb = (CheckBox) v;
        String a = "" + x;
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

/*
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="ie.cork.mycit.settings.HomePageSettings">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Customize Home Page "
        android:id="@+id/textView"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />

    <Button
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Save Changes"
        android:id="@+id/buttonSave"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:onClick="save" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox1"
        android:layout_below="@+id/textView"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox2"
        android:layout_below="@+id/checkBox1"
        android:layout_alignLeft="@+id/checkBox1"
        android:layout_alignStart="@+id/checkBox1" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox3"
        android:layout_below="@+id/checkBox2"
        android:layout_alignLeft="@+id/checkBox2"
        android:layout_alignStart="@+id/checkBox2" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox4"
        android:layout_below="@+id/checkBox3"
        android:layout_alignRight="@+id/checkBox3"
        android:layout_alignEnd="@+id/checkBox3" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox5"
        android:layout_below="@+id/checkBox4"
        android:layout_alignRight="@+id/checkBox4"
        android:layout_alignEnd="@+id/checkBox4" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox6"
        android:layout_below="@+id/checkBox5"
        android:layout_alignRight="@+id/checkBox5"
        android:layout_alignEnd="@+id/checkBox5" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox7"
        android:layout_below="@+id/checkBox6"
        android:layout_alignRight="@+id/checkBox6"
        android:layout_alignEnd="@+id/checkBox6" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox8"
        android:layout_below="@+id/checkBox7"
        android:layout_alignRight="@+id/checkBox7"
        android:layout_alignEnd="@+id/checkBox7" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox9"
        android:layout_below="@+id/checkBox8"
        android:layout_alignLeft="@+id/checkBox8"
        android:layout_alignStart="@+id/checkBox8" />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox10"
        android:layout_below="@+id/checkBox9"
        android:layout_alignLeft="@+id/checkBox9"
        android:layout_alignStart="@+id/checkBox9"  />

    <CheckBox
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New CheckBox"
        android:id="@+id/checkBox11"
        android:layout_below="@+id/checkBox10"
        android:layout_alignLeft="@+id/checkBox10"
        android:layout_alignStart="@+id/checkBox10"  />

</RelativeLayout>
 */