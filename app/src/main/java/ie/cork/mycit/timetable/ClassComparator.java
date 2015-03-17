package ie.cork.mycit.timetable;

import java.util.Comparator;
/**
 * 
 * @author Daniel Junyszek <daniel.junyszek@mycit.ie>
 * 
 * 
 * Software Development Group project Year 3   -   2015
 * 
 */
public class ClassComparator implements Comparator<Lclass> {
	public int compare(Lclass a , Lclass b)
	{
		int result;
		boolean aDayIsBiggerThanB = a.getDayOfTheWeek()>b.getDayOfTheWeek();
		boolean aTimeIsBiggerThanB = a.getStartTime().getHours()>b.getStartTime().getHours();
		if(aDayIsBiggerThanB)
		{
			result =  1;
		}
		else if(!aDayIsBiggerThanB)
		{
			result = -1;
		}
		else
		{
			if(aTimeIsBiggerThanB)
			{
				result = 1;
			}
			else if(!aTimeIsBiggerThanB)
			{
				result = -1;
			}
			else
			{
				result = 0;
			}
		}
		//System.out.println("This times : " + a.getStartTime().getHours()+ " , " + b.getStartTime().getHours()+ " and these days : "+ a.getDayOfTheWeek() + " , " +b.getDayOfTheWeek() + " gave result" + result);
		return result;
	}
}
