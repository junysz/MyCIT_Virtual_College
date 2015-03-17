package ie.cork.mycit.timetable;


import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RetrieveHTMLcode extends AsyncTask<String, BufferedReader, BufferedReader> {

    private URL url;
    private BufferedReader in;
    private StringBuffer code;
    private BufferedReader theCode;
    @Override
    protected BufferedReader doInBackground(String... uri) {
        try {
            return retrieveCodeFromURL(uri[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(BufferedReader result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
    public BufferedReader retrieveCodeFromURL(String url) throws IOException
    {

        this.url = new URL(url);
        in = new BufferedReader(
                new InputStreamReader(this.url.openStream()));
        return in;
    }
}