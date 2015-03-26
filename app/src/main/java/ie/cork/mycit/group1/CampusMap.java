package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
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
import ie.cork.mycit.timetable.Timetables;

public class CampusMap extends Activity {

    List<String> allMapNames = new ArrayList<String>();
    List<String> allMapLinks = new ArrayList<String>();
    String tempMapName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campus_map);

        TableData readData = LocalPersistence.readObjectFromFile(CampusMap.this);
        ArrayList<IDNameLink> data = readData.getMapsArray();
        allMapNames = LocalPersistence.readIDNameLink(2, data);
        allMapLinks = LocalPersistence.readIDNameLink(3, data);

        ListView listnames = (ListView) findViewById(R.id.listViewMaps);

        CustomViewAdapter adapter = new CustomViewAdapter(CampusMap.this, allMapNames);
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
        ListView list = (ListView) findViewById(R.id.listViewMaps);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempMapName = allMapNames.get(position);
                int x = position + 1;
                String message = tempMapName + " opening";
                Toast.makeText(CampusMap.this, message, Toast.LENGTH_LONG).show();
                menuSelect(allMapNames.get(position).toString(), allMapLinks.get(position).toString());
            }

        });
    }

    public void menuSelect(String title, String url) {
        String interactivemap = getResources().getString(R.string.title_activity_google_map);
        if(title.equalsIgnoreCase(interactivemap)){
            Uri gmmIntentUri = Uri.parse(url);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        else{
            viewWeb(title, url);
        }
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(CampusMap.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
