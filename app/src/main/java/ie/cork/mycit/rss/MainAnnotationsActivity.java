package ie.cork.mycit.rss;
/*
import java.io.FileNotFoundException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_gen_news)
public class MainAnnotationsActivity extends Activity {

	private SiteAdapter mAdapter;

	@ViewById
	ListView sitesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@AfterViews
	public void refreshUI(){
		if(isNetworkAvailable())
			fetchFeed();
		else
			loadList();
	}

	@ItemClick
	protected void sitesListItemClicked(GeneralNewsRssItem selectedSite) {
		String url = selectedSite.getLink();
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	@Background
	protected void fetchFeed(){
		try {
			Downloader.DownloadFromUrl("https://dl.dropboxusercontent.com/u/21945941/MyCit%20News/RSSFile/generalNewsRssFeed.xml", openFileOutput("generalNewsRssFeed.xml", Context.MODE_PRIVATE));
			loadList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@UiThread
	protected void loadList(){
		mAdapter = new SiteAdapter(this, -1, SitesXmlPullParser.getStackSitesFromFile(this));
		sitesList.setAdapter(mAdapter);
	}

	//Helper method to determine if Internet connection is available.
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
		= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
*/