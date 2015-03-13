package ie.cork.mycit.group1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import ie.cork.mycit.settings.HomePageSettings;

public class HomePage extends ActionBarActivity implements
	NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	//Used to store the last screen title. For use in{@link #restoreActionBar()}.
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

        loadTime();
        saveTime();

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));		
	}

	public void email(View v) {
        viewWeb("Student Email", "http://mail.mycit.ie/");
	}
	
	public void timetables(View v) {
		Intent timetables = new Intent(HomePage.this, Timetables.class);
		startActivity(timetables);
	}
	
	public void card(View v) {
        viewWeb("Student Card Top-Up", "https://citcard.cit.ie/icmserver/#student_number");
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
			mTitle = "MyCit Home Page";
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
			/*
			 * File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ filename);
			Intent target = new Intent(Intent.ACTION_VIEW);
			target.setDataAndType(Uri.fromFile(file),"application/pdf");
			target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			
			Intent intent = Intent.createChooser(target, "Open File");
			try {
			    startActivity(intent);
			} catch (ActivityNotFoundException e) {
			    // Instruct the user to install a PDF reader here, or something
			}
			*/ 
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

    public void viewWeb(String title, String url) {
        Intent web = new Intent(HomePage.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

    @SuppressLint("SimpleDateFormat")
    public void saveTime(){
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String formattedDate = new SimpleDateFormat("MMM d y h:ma").format(date);
        String message = "You last started MyCIT's Android app on\n\t" + formattedDate;

        SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Time", message);
        editor.commit();
    }

    public void loadTime(){
        SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String defValue = "This is the first time you have opened MyCIT's android app";
        String message = sharedpreferences.getString("Time", defValue);
        if(message.equalsIgnoreCase(defValue)){
            Toast.makeText(HomePage.this, defValue, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(HomePage.this, message, Toast.LENGTH_LONG).show();
        }
    }

}
