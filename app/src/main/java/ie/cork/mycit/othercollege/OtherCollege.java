package ie.cork.mycit.othercollege;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.cork.mycit.group1.CustomViewAdapter;
import ie.cork.mycit.group1.R;
import ie.cork.mycit.group1.Web;

public class OtherCollege extends ActionBarActivity {

    List<String> otherCollegeNames = new ArrayList<String>();
    List<String> otherCollegeLinks = new ArrayList<String>();
    String tempName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_information);

        Resources res = this.getResources();
        String[] names = res.getStringArray(R.array.otherCollegeNames);
        String[] links = res.getStringArray(R.array.otherCollegeLinks);
        otherCollegeNames = Arrays.asList(names);
        otherCollegeLinks = Arrays.asList(links);

        ListView listnames = (ListView) findViewById(R.id.listViewOtherCollege);

        CustomViewAdapter adapter = new CustomViewAdapter(OtherCollege.this, Arrays.asList(names));
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
        ListView list = (ListView) findViewById(R.id.listViewOtherCollege);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempName = otherCollegeNames.get(position);
                int x = position + 1;
                String message = tempName + " opening";
                Toast.makeText(OtherCollege.this, message, Toast.LENGTH_LONG).show();
                viewWeb(otherCollegeNames.get(position).toString(), otherCollegeLinks.get(position).toString());
            }

        });
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(OtherCollege.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

}
