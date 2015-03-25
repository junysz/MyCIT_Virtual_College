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

import ie.cork.mycit.database.IDNameLink;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;

public class SupportServices extends ActionBarActivity {

    List<String> supportServicesNames = new ArrayList<String>();
    List<String> supportServicesLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_services);

        TableData readData = LocalPersistence.readObjectFromFile(SupportServices.this);
        ArrayList<IDNameLink> data = readData.getSupportServicesArray();
        supportServicesNames = LocalPersistence.readIDNameLink(2, data);
        supportServicesLinks = LocalPersistence.readIDNameLink(3, data);

        ListView listnames = (ListView) findViewById(R.id.listViewSupportServices);

        CustomViewAdapter adapter = new CustomViewAdapter(SupportServices.this, supportServicesNames);
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
        ListView list = (ListView) findViewById(R.id.listViewSupportServices);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = supportServicesNames.get(position);
                int x = position + 1;
                String message = tempName + " opening";
                Toast.makeText(SupportServices.this, message, Toast.LENGTH_LONG).show();
                viewWeb(supportServicesNames.get(position).toString(), supportServicesLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(SupportServices.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}