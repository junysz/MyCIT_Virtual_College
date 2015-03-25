package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
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
import ie.cork.mycit.rss.GenNews;

public class StudentNews extends ActionBarActivity {

    List<String> studentNewsNames = new ArrayList<String>();
    List<String> studentNewsLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_news);

        TableData readData = LocalPersistence.readObjectFromFile(StudentNews.this);
        ArrayList<IDNameLink> data = readData.getStudentNewsArray();
        studentNewsNames = LocalPersistence.readIDNameLink(2, data);
        studentNewsLinks = LocalPersistence.readIDNameLink(3, data);

        ListView listnames = (ListView) findViewById(R.id.listViewStudentNews);

        CustomViewAdapter adapter = new CustomViewAdapter(StudentNews.this, studentNewsNames);
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
        ListView list = (ListView) findViewById(R.id.listViewStudentNews);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = studentNewsNames.get(position);
                int x = position + 1;
                String message = tempName + " opening";
                Toast.makeText(StudentNews.this, message, Toast.LENGTH_LONG).show();
                viewWeb(studentNewsNames.get(position).toString(), studentNewsLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(StudentNews.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}