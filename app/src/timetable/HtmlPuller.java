package ie.cork.mycit.group1.timetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * 
 * @author Daniel Junyszek <daniel.junyszek@mycit.ie>
 * 
 * 
 * Software Development Group project Year 3   -   2015
 * 
 */
public class HtmlPuller {
	
	private URL oracle;
	private BufferedReader in;
	private StringBuffer code;
	public HtmlPuller(String URL) throws IOException
	{
		getHTML(URL);
	}
	public HtmlPuller()
	{}
	//following method returns html code as a string
	public StringBuffer getHTML(String url)
	{
		if(url!=null)
			try {
				retrieveCodeFromURL(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		return code;
	}
	
	//this method places html code in the string buffer

	public BufferedReader retrieveCodeFromURL(String url) throws IOException 
	{

		oracle = new URL(url);
        in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        return in;
	}
	
	
	        
}
