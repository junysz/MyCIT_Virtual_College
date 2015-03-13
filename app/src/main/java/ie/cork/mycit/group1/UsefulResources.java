package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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

public class UsefulResources extends Activity {

    List<String> usefulResourcesNames = new ArrayList<String>();
    List<String> usefulResourcesLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_resources);

        Resources res = this.getResources();
        String[] names = res.getStringArray(R.array.usefulResourcesNames);
        String[] links = res.getStringArray(R.array.usefulResourcesLinks);
        usefulResourcesNames = Arrays.asList(names);
        usefulResourcesLinks = Arrays.asList(links);

        ListView listnames = (ListView) findViewById(R.id.listViewUsefulResources);

        CustomViewAdapter adapter = new CustomViewAdapter(UsefulResources.this, Arrays.asList(names));
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
        ListView list = (ListView) findViewById(R.id.listViewUsefulResources);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = usefulResourcesNames.get(position);
                int x = position + 1;
                String message = "You clicked # " + x + " " + tempName;
                Toast.makeText(UsefulResources.this, message, Toast.LENGTH_LONG).show();
                viewWeb(usefulResourcesNames.get(position).toString(), usefulResourcesLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(UsefulResources.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
