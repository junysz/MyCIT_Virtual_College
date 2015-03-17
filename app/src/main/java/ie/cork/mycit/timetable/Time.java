package ie.cork.mycit.timetable;
/**
 * 
 * @author Daniel Junyszek <daniel.junyszek@mycit.ie>
 * 
 * 
 * Software Development Group project Year 3   -   2015
 * 
 */
public class Time {
	private int hours,minutes;
	public Time(int hours , int minutes)
	{
		this.hours=hours;
		this.minutes=minutes;
	}
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public String getTimeFormatted()
	{
		
		return ""+hours+":"+minutes;
	}
}
