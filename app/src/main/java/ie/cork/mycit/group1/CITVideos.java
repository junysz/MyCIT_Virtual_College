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

public class CITVideos extends Activity {

    List<String> videoNames = new ArrayList<String>();
    List<String> videoLinks = new ArrayList<String>();
    String tempName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citvideos);

        Resources res = this.getResources();
        String[] citvidnames = res.getStringArray(R.array.videoNames);
        String[] citvidlinks = res.getStringArray(R.array.videoLinks);
        videoNames = Arrays.asList(citvidnames);
        videoLinks = Arrays.asList(citvidlinks);

        ListView listnames = (ListView) findViewById(R.id.listViewVideos);

        CustomViewAdapter adapter = new CustomViewAdapter(CITVideos.this, Arrays.asList(citvidnames));
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
        ListView list = (ListView) findViewById(R.id.listViewVideos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = videoNames.get(position);
                String message = tempName + " opening";
                Toast.makeText(CITVideos.this, message, Toast.LENGTH_LONG).show();
                viewVideo(videoLinks.get(position).toString());
            }

        });
    }

    public void viewVideo(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void viewChannel(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/cit/videos")));
    }

}
