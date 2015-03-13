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

public class AcademicInformation extends ActionBarActivity {

    List<String> academicInformationNames = new ArrayList<String>();
    List<String> academicInformationLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_information);

        Resources res = this.getResources();
        String[] names = res.getStringArray(R.array.academicInformationNames);
        String[] links = res.getStringArray(R.array.academicInformationLinks);
        academicInformationNames = Arrays.asList(names);
        academicInformationLinks = Arrays.asList(links);

        ListView listnames = (ListView) findViewById(R.id.listViewAcademicInformation);

        CustomViewAdapter adapter = new CustomViewAdapter(AcademicInformation.this, Arrays.asList(names));
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
        ListView list = (ListView) findViewById(R.id.listViewAcademicInformation);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = academicInformationNames.get(position);
                int x = position + 1;
                String message = "You clicked # " + x + " " + tempName;
                Toast.makeText(AcademicInformation.this, message, Toast.LENGTH_LONG).show();
                viewWeb(academicInformationNames.get(position).toString(), academicInformationLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(AcademicInformation.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
