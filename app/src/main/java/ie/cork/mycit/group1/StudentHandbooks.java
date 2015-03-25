package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.cork.mycit.database.IDNameLink;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;

public class StudentHandbooks extends ActionBarActivity {

    List<String> studentHandbooksNames = new ArrayList<String>();
    List<String> studentHandbooksLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_handbooks);

        TableData readData = LocalPersistence.readObjectFromFile(StudentHandbooks.this);
        ArrayList<IDNameLink> data = readData.getStudentHandbooksArray();
        studentHandbooksNames = LocalPersistence.readIDNameLink(2, data);
        studentHandbooksLinks = LocalPersistence.readIDNameLink(3, data);

        ListView listnames = (ListView) findViewById(R.id.listViewStudentHandbooks);

        CustomViewAdapter adapter = new CustomViewAdapter(StudentHandbooks.this, studentHandbooksNames);
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
        ListView list = (ListView) findViewById(R.id.listViewStudentHandbooks);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = studentHandbooksNames.get(position);
                int x = position + 1;
                String message = tempName + " opening";
                Toast.makeText(StudentHandbooks.this, message, Toast.LENGTH_LONG).show();
                viewWeb(studentHandbooksNames.get(position).toString(), studentHandbooksLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(StudentHandbooks.this, Web.class);
        web.putExtra("title", title);
        String googleDocsLink = "https://docs.google.com/gview?url=";
        googleDocsLink += url;
        web.putExtra("url", googleDocsLink);
        startActivity(web);
    }

}