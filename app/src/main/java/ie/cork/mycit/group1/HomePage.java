package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ie.cork.mycit.database.IDItem;
import ie.cork.mycit.database.IDNameLink;
import ie.cork.mycit.database.LocalPersistence;
import ie.cork.mycit.database.TableData;
import ie.cork.mycit.othercolleges.OtherCollege;
import ie.cork.mycit.settings.HomePageSettings;
import ie.cork.mycit.timetable.Departments;
import ie.cork.mycit.timetable.Timetables;

public class HomePage extends ActionBarActivity implements
	NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;

    List<String> chosenApps = new ArrayList<String>();
    List<String> listNames = new ArrayList<String>();
    List<String> listLinks = new ArrayList<String>();
    String tempApp = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

        setupTwitterButton();
        setupFacebookButton();
        setupYoutubeButton();
        setupGoogleButton();
        setupLinkedinButton();

        TableData readData = LocalPersistence.readObjectFromFile(HomePage.this);
        ArrayList<IDNameLink> data = readData.getStudentAppsArray();
        listNames = LocalPersistence.readIDNameLink(2, data);
        listLinks = LocalPersistence.readIDNameLink(3, data);

        startCustom();

        ListView listApps = (ListView) findViewById(R.id.listHomePage);

        if(chosenApps != null){
            CustomViewAdapter adapter = new CustomViewAdapter(HomePage.this, chosenApps);
            listApps.setAdapter(adapter);
        }
        else{
            CustomViewAdapter adapter = new CustomViewAdapter(HomePage.this, listNames);
            listApps.setAdapter(adapter);
        }

        registerClickCallback();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));


    }

    public void startCustom(){
        checkCustom(0);
        checkCustom(1);
        checkCustom(2);
        checkCustom(3);
        checkCustom(4);
        checkCustom(5);
        checkCustom(6);
        checkCustom(7);
        checkCustom(8);
        checkCustom(9);
        checkCustom(10);
    }

    public void checkCustom(int x){
        String a = "" + x;
        boolean check = getFromSP(a);
        if(check){
            chosenApps.add(listNames.get(x));
        }
    }

    private boolean getFromSP(String key){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listHomePage);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                tempApp = chosenApps.get(position);
                int x = position + 1;
                int pos = findPosition(tempApp);
                String message = tempApp + " opening";
                Toast.makeText(HomePage.this, message, Toast.LENGTH_LONG).show();
                menuSelect(listNames.get(pos).toString(), listLinks.get(pos).toString());
            }

        });
    }

    public int findPosition(String selected) {
        Iterator<String> itr = listNames.iterator();
        int x = 0;
        while (itr.hasNext()) {
            if(selected.equalsIgnoreCase(listNames.get(x))){
                return x;
            }
            x++;
        }
        return 0;
    }

    public void menuSelect(String title, String url) {
        String timetable = getResources().getString(R.string.title_activity_timetable_view);
        if(title.equalsIgnoreCase(timetable)){
            Intent timetables = new Intent(HomePage.this, Departments.class);
            startActivity(timetables);
        }
        else{
            viewWeb(title, url);
        }
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(HomePage.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

    @Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container,PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getResources().getString(R.string.mycit_homepage);
			break;
		case 2:
			Intent newStudents = new Intent(HomePage.this, NewStudents.class);
			startActivity(newStudents);
			break;
		case 3:
			Intent itServicesSupport = new Intent(HomePage.this, ItServicesSupport.class);
			startActivity(itServicesSupport);
			break;
		case 4:
			Intent supportServices = new Intent(HomePage.this, SupportServices.class);
			startActivity(supportServices);
			break;
		case 5:
			Intent academicInformation = new Intent(HomePage.this, AcademicInformation.class);
			startActivity(academicInformation);
			break;
		case 6:
			Intent usefulResources = new Intent(HomePage.this, UsefulResources.class);
			startActivity(usefulResources);
			break;			
		case 7:
            viewWeb("Student Calendar", "http://www.mycit.ie/events#eventMonthsId");
			break;
		case 8:
			Intent studentNews = new Intent(HomePage.this, StudentNews.class);
			startActivity(studentNews);
			break;
		case 9:
			Intent studentApplications = new Intent(HomePage.this, StudentApplications.class);
			startActivity(studentApplications);
			break;
		case 10:
            viewWeb("Pay Fees Online", "https://live.runmyprocess.com/pub/925358723/appli/60224");
			break;
		case 11:
			Intent studentHandbooks = new Intent(HomePage.this, StudentHandbooks.class);
			startActivity(studentHandbooks);
			break;
		case 12:
			Intent citVideos = new Intent(HomePage.this, CITVideos.class);
			startActivity(citVideos);
			break;
		case 13:
			Intent campusMap = new Intent(HomePage.this, CampusMap.class);
			startActivity(campusMap);
			break;
		case 14:
			Intent importantDocuments = new Intent(HomePage.this, ImportantDocuments.class);
			startActivity(importantDocuments);
			break;
        case 15:
            viewWeb("Public Folders", "https://webvpn.cit.ie/+CSCOE+/logon.html#username");
            break;
        case 16:
            Intent othercollege = new Intent(HomePage.this, OtherCollege.class);
            startActivity(othercollege);
            break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.home_page, menu);
            getMenuInflater().inflate(R.menu.menu_home_page_settings, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
        else if(id == R.id.menu_settings){
            String message = "You selected the " + item.getTitle() + " menu";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomePageSettings.class));
        }
		return super.onOptionsItemSelected(item);
	}

	//A placeholder fragment containing a simple view.
	public static class PlaceholderFragment extends Fragment {
		//The fragment argument representing the section number for this fragment.
		private static final String ARG_SECTION_NUMBER = "section_number";

		//Returns a new instance of this fragment for the given section number.
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View rootView = inflater.inflate(R.layout.fragment_home_page,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((HomePage) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

    public void setupTwitterButton(){
        Button twitterButton = (Button) findViewById(R.id.twitter_feed_button);

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWeb("CIT Twitter", "http://mycit.16mb.com/html/open_CIT_twitter.html");
            }
        });
    }

    public void setupFacebookButton(){
        Button facebookButton = (Button) findViewById(R.id.facebook_feed_button);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWeb("CIT Facebook", "http://mycit.16mb.com/html/open_CIT_fb.html");
            }
        });
    }

    public void setupYoutubeButton(){
        Button youtubeButton = (Button) findViewById(R.id.youtube_feed_button);

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWeb("CIT Youtube", "http://mycit.16mb.com/html/open_CIT_youtube.html");
            }
        });
    }

    public void setupGoogleButton(){
        Button googleButton = (Button) findViewById(R.id.google_feed_button);

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWeb("CIT Google+", "http://mycit.16mb.com/html/open_CIT_googlePlus.html");
            }
        });
    }

    public void setupLinkedinButton(){
        Button linkedinButton = (Button) findViewById(R.id.linkedin_feed_button);

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewWeb("CIT Linkedin", "http://mycit.16mb.com/html/open_CIT_linkedin.html");
            }
        });
    }

}
