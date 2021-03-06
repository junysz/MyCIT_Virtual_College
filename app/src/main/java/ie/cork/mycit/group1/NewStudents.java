package ie.cork.mycit.group1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewStudents extends ActionBarActivity {

    List<String> newStudentNames = new ArrayList<String>();
    List<String> newStudentLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_students);

        Resources res = this.getResources();
        String[] names = res.getStringArray(R.array.newStudentNames);
        String[] links = res.getStringArray(R.array.newStudentLinks);
        newStudentNames = Arrays.asList(names);
        newStudentLinks = Arrays.asList(links);

        ListView listnames = (ListView) findViewById(R.id.listViewNewStudents);

        CustomViewAdapter adapter = new CustomViewAdapter(NewStudents.this, Arrays.asList(names));
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
        ListView list = (ListView) findViewById(R.id.listViewNewStudents);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = newStudentNames.get(position);
                int x = position + 1;
                String message = "You clicked # " + x + " " + tempName;
                Toast.makeText(NewStudents.this, message, Toast.LENGTH_LONG).show();
                viewWeb(newStudentNames.get(position).toString(), newStudentLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(NewStudents.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
