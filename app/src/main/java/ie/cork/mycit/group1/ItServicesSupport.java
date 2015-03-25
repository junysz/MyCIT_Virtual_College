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

public class ItServicesSupport extends ActionBarActivity {

    List<String> itServicesSupportNames = new ArrayList<String>();
    List<String> itServicesSupportLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_services_support);

        TableData readData = LocalPersistence.readObjectFromFile(ItServicesSupport.this);
        ArrayList<IDNameLink> data = readData.getItServicesArray();
        itServicesSupportNames = LocalPersistence.readIDNameLink(2, data);
        itServicesSupportLinks = LocalPersistence.readIDNameLink(3, data);

        ListView listnames = (ListView) findViewById(R.id.listViewITServices);

        CustomViewAdapter adapter = new CustomViewAdapter(ItServicesSupport.this, itServicesSupportNames);
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
        ListView list = (ListView) findViewById(R.id.listViewITServices);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = itServicesSupportNames.get(position);
                int x = position + 1;
                String message = tempName + " opening";
                Toast.makeText(ItServicesSupport.this, message, Toast.LENGTH_LONG).show();
                viewWeb(itServicesSupportNames.get(position).toString(), itServicesSupportLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(ItServicesSupport.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
