package ie.cork.mycit.group1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import ie.cork.mycit.rss.GenNews;

public class StudentNews extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_news);
	}

    public void general(View v) {
        Intent generalNews = new Intent(StudentNews.this, GenNews.class);
        startActivity(generalNews);
    }

    public void viewWeb(String title, String url) {
        Intent web = new Intent(StudentNews.this, Web.class);
        web.putExtra("title", title);
        web.putExtra("url", url);
        startActivity(web);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

}
