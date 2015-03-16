package ie.cork.mycit.group1.timetable;

public class Lclass{
	private String name,location;
	private int dayOfTheWeek;
	private Time startTime, endTime;
	
	/**
	 * 
	 * @author Daniel Junyszek <daniel.junyszek@mycit.ie>
	 * 
	 * 
	 * Software Development Group project Year 3   -   2015
	 * 
	 */
	
	public Lclass(String name , String location , Time startTime , Time endTime , int dayOfTheWeek)
	{
		setDayOfTheWeek(dayOfTheWeek);
		setEndTime(endTime);
		setLocation(location);
		setName(name);
		setStartTime(startTime);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the dayOfTheWeek
	 */
	public int getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	public String getStringDayOfTheWeek() {
		switch(dayOfTheWeek){
		case 1: return "Monday";
		case 2: return "Tuesday";
		case 3: return "Wednesday";
		case 4: return "Thursday";
		case 5: return "Friday";
		case 6: return "Saturday";
		case 7: return "Sunday";
		default: return "";
		}
	}
	/**
	 * @param dayOfTheWeek the dayOfTheWeek to set
	 */
	public void setDayOfTheWeek(int dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
	public String toString()
	{
		return startTime.getTimeFormatted() +"-"+ endTime.getTimeFormatted() +":"+ name + " class is on " + getStringDayOfTheWeek() + " in "+ location;
	}

}
