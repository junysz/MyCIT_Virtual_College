package ie.cork.mycit.timetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Daniel Junyszek <daniel.junyszek@mycit.ie>
 * 
 * 
 * Software Development Group project Year 3   -   2015
 * 
 */
public class TimetableExtractor {
	private final LinkedList<Integer> isClassonDay = new LinkedList<Integer>();
	private final Pattern regExEmptyCell = Pattern.compile("<td  >&nbsp;</td>");
	private final Pattern regExClassLentgh1 = Pattern.compile("<td   colspan='(\\d)' rowspan='4' >");
	private final Pattern regExClassLentgh2 = Pattern.compile("<td   colspan='(\\d)' rowspan='8' >");
	private final Pattern regExClassLentgh3 = Pattern.compile("<td   colspan='(\\d)' rowspan='12' >");
	private final Pattern regExClassLentgh4 = Pattern.compile("<td   colspan='(\\d)' rowspan='16' >");
	private final Pattern regExClassLentgh5 = Pattern.compile("<td   colspan='(\\d)' rowspan='20' >");
	private final Pattern regExClassLentgh6 = Pattern.compile("<td   colspan='(\\d)' rowspan='24' >");
	private Pattern regExClassLocation = Pattern.compile("<td align='right'><font color='#008000'>([\\S\\|,]{1,13})</font></td>");
	private Pattern regExClassName = Pattern.compile("<td align='left'><font color='#000000'>(.*?)</font></td>");
	private Pattern regExFirstCellInRow = Pattern.compile("<td  rowspan='1' bgcolor='#C0C0C0'><font color='#000000'>(\\d+):(\\d+)</font></td>");
    private int dayOfTheWeek=1;
	
	
	private LinkedList<Lclass> myClasses = new LinkedList<Lclass>();
	private final Time[] timesIndex = {new Time(8, 0), new Time(8,15), new Time(8,30), new Time(8,45),
								new Time(9, 0), new Time(9,15), new Time(9,30), new Time(9,45),
								new Time(10, 0), new Time(10,15), new Time(10,30), new Time(10,45),
								new Time(11, 0), new Time(11,15), new Time(11,30), new Time(11,45),
								new Time(12, 0), new Time(12,15), new Time(12,30), new Time(12,45),
								new Time(13, 0), new Time(13,15), new Time(13,30), new Time(13,45),
								new Time(14, 0), new Time(14,15), new Time(14,30), new Time(14,45),
								new Time(15, 0), new Time(15,15), new Time(15,30), new Time(15,45),
								new Time(16, 0), new Time(16,15), new Time(16,30), new Time(16,45),
								new Time(17, 0), new Time(17,15), new Time(17,30), new Time(17,45), new Time(18, 0)};

    public LinkedList<Lclass> getTimetable(BufferedReader code) throws IOException
	{
		String currentLine="", currentClassName="", classLocation="";
		int timeArrayIndexNum = 0, classLength = 0 ;
		dayOfTheWeek=1;
		if(code!=null)
        {
            while((currentLine=code.readLine()) != null)
            {
                isClassonDay.add(0);
                isClassonDay.add(0);
                isClassonDay.add(0);
                isClassonDay.add(0);
                isClassonDay.add(0);
                Matcher firstCellInRowMatch = regExFirstCellInRow.matcher(currentLine);
                regExFirstCellInRow.matcher(currentLine);
                Matcher emptyCellMatch = regExEmptyCell.matcher(currentLine);
                Matcher classLenth1hMatch = regExClassLentgh1.matcher(currentLine);
                Matcher classLenth2hMatch = regExClassLentgh2.matcher(currentLine);
                Matcher classLenth3hMatch = regExClassLentgh3.matcher(currentLine);
                Matcher classLenth4hMatch = regExClassLentgh4.matcher(currentLine);
                Matcher classLenth5hMatch = regExClassLentgh5.matcher(currentLine);
                Matcher classLenth6hMatch = regExClassLentgh6.matcher(currentLine);
                Matcher locationMatch = regExClassLocation.matcher(currentLine);
                Matcher classNameMatch = regExClassName.matcher(currentLine);

                if(firstCellInRowMatch.find())
                {
                    timeArrayIndexNum++;
                    dayOfTheWeek=1;

                    if(isClassonDay.get(0)>0)
                        isClassonDay.set(0, isClassonDay.get(0)-1);
                    if(isClassonDay.get(1)>0)
                        isClassonDay.set(1, isClassonDay.get(1)-1);
                    if(isClassonDay.get(2)>0)
                        isClassonDay.set(2, isClassonDay.get(2)-1);
                    if(isClassonDay.get(3)>0)
                        isClassonDay.set(3, isClassonDay.get(3)-1);
                    if(isClassonDay.get(4)>0)
                        isClassonDay.set(4, isClassonDay.get(4)-1);



                    while(isClassonDay.get(dayOfTheWeek)>0)
                    {
                        dayOfTheWeek++;
                    }
                }
                else if(emptyCellMatch.find())
                {
                    dayOfTheWeek++;
                    while(isClassonDay.get(dayOfTheWeek-1)>0)
                    {
                        dayOfTheWeek++;
                    }
                }
                else if(classLenth1hMatch.find())
                {
                    classLength=1;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+4);
                    dayOfTheWeek++;
                }
                else if(classLenth2hMatch.find())
                {
                    classLength=2;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+8);
                    dayOfTheWeek++;
                }
                else if(classLenth3hMatch.find())
                {
                    classLength=3;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+12);
                    dayOfTheWeek++;
                }
                else if(classLenth4hMatch.find())
                {
                    classLength=4;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+16);
                    dayOfTheWeek++;
                }
                else if(classLenth5hMatch.find())
                {
                    classLength=5;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+20);
                    dayOfTheWeek++;
                }
                else if(classLenth6hMatch.find())
                {
                    classLength=6;
                    isClassonDay.set(dayOfTheWeek-1, isClassonDay.get(dayOfTheWeek-1)+24);
                    dayOfTheWeek++;
                }
                else if(classNameMatch.find())
                {
                    currentClassName = classNameMatch.group(1);
                }
                else if(locationMatch.find())
                {
                    classLocation= locationMatch.group(1);
                    myClasses.add(new Lclass(currentClassName,classLocation,timesIndex[timeArrayIndexNum-1],timesIndex[timeArrayIndexNum+(classLength*4)-1],dayOfTheWeek-1));
                }

                /**
                 * End of loop reading the buffer
                 */
            }
        }
		/**
		 * Return part of getTimetable()
		 * method
		 */
		Collections.sort(myClasses, new ClassComparator());
		if(myClasses.isEmpty())
			return null;
		else
		{
			return myClasses;
		}
	}

/**
* Setters and Getters from here
*_______________________________________________________________
*/
	
	

	public void checkDays()
	{
		
		switch(dayOfTheWeek){
		case 1:
			if(isClassonDay.get(0)>0)
			{
				dayOfTheWeek+=2;
				isClassonDay.set(0, isClassonDay.get(0)-1);if(isClassonDay.get(1)>0)
				{
					dayOfTheWeek+=2;
					isClassonDay.set(1, isClassonDay.get(1)-1);
					if(isClassonDay.get(2)>0)
					{
						dayOfTheWeek+=2;
						isClassonDay.set(2, isClassonDay.get(2)-1);
						if(isClassonDay.get(3)>0)
						{
							dayOfTheWeek+=2;
							isClassonDay.set(3, isClassonDay.get(3)-1);
							if(isClassonDay.get(3)>0)
							{
								dayOfTheWeek+=2;
								isClassonDay.set(3, isClassonDay.get(3)-1);
                                if(isClassonDay.get(4)>0)
                                {
                                    dayOfTheWeek+=2;
                                    isClassonDay.set(4, isClassonDay.get(4)-1);
                                    break;
                                }
                                else
                                {
                                    dayOfTheWeek++;
                                    break;
                                }
							}
							else
							{
								dayOfTheWeek++;
								break;
							}
						}
						else
						{
							dayOfTheWeek++;
							break;
						}
					}
					else
					{
						dayOfTheWeek++;
						break;
					}
				}
				else
				{
					dayOfTheWeek++;
					break;
				}
			}
			else
			{
				dayOfTheWeek++;
			}
		case 2:
			if(isClassonDay.get(1)>0)
			{
				dayOfTheWeek+=2;
				isClassonDay.set(1, isClassonDay.get(1)-1);
				if(isClassonDay.get(2)>0)
				{
					dayOfTheWeek+=2;
					isClassonDay.set(2, isClassonDay.get(2)-1);
					if(isClassonDay.get(3)>0)
					{
						dayOfTheWeek+=2;
						isClassonDay.set(3, isClassonDay.get(3)-1);
						if(isClassonDay.get(3)>0)
						{
							dayOfTheWeek+=2;
							isClassonDay.set(3, isClassonDay.get(3)-1);
                            if(isClassonDay.get(4)>0)
                            {
                                dayOfTheWeek+=2;
                                isClassonDay.set(4, isClassonDay.get(4)-1);
                                break;
                            }
                            else
                            {
                                dayOfTheWeek++;
                                break;
                            }
						}
						else
						{
							dayOfTheWeek++;
							break;
						}
					}
					else
					{
						dayOfTheWeek++;
						break;
					}
				}
				else
				{
					dayOfTheWeek++;
					break;
				}
			}
			else
			{
				dayOfTheWeek++;
				break;
			}
		case 3:
			if(isClassonDay.get(2)>0)
			{
				dayOfTheWeek+=2;
				isClassonDay.set(2, isClassonDay.get(2)-1);
				if(isClassonDay.get(3)>0)
				{
					dayOfTheWeek+=2;
					isClassonDay.set(3, isClassonDay.get(3)-1);
					if(isClassonDay.get(3)>0)
					{
						dayOfTheWeek+=2;
						isClassonDay.set(3, isClassonDay.get(3)-1);
                        if(isClassonDay.get(4)>0)
                        {
                            dayOfTheWeek+=2;
                            isClassonDay.set(4, isClassonDay.get(4)-1);
                            break;
                        }
                        else
                        {
                            dayOfTheWeek++;
                            break;
                        }
					}
					else
					{
						dayOfTheWeek++;
						break;
					}
				}
				else
				{
					dayOfTheWeek++;
					break;
				}
			}
			else
			{
				dayOfTheWeek++;
				break;
			}
		case 4:
            if(isClassonDay.get(3)>0)
            {
                dayOfTheWeek+=2;
                isClassonDay.set(3, isClassonDay.get(3)-1);
                if(isClassonDay.get(3)>0)
                {
                    dayOfTheWeek+=2;
                    isClassonDay.set(3, isClassonDay.get(3)-1);
                    if(isClassonDay.get(4)>0)
                    {
                        dayOfTheWeek+=2;
                        isClassonDay.set(4, isClassonDay.get(4)-1);
                        break;
                    }
                    else
                    {
                        dayOfTheWeek++;
                        break;
                    }
                }
                else
                {
                    dayOfTheWeek++;
                    break;
                }
            }
            case 5:
                if(isClassonDay.get(4)>0)
                {
                    dayOfTheWeek+=2;
                    isClassonDay.set(4, isClassonDay.get(4)-1);
                    break;
                }
                else
                {
                    dayOfTheWeek++;
                    break;
                }

		default:dayOfTheWeek++;
		}
	}
	public LinkedList<Lclass> getMyClasses() {
		return myClasses;
	}
	public void setRegExClassLocation(Pattern regExClassLocation) {
		this.regExClassLocation = regExClassLocation;
	}
	public void setRegExFirstCellInRow(Pattern regExFirstCellInRow) {
		this.regExFirstCellInRow = regExFirstCellInRow;
	}

	public void setMyClasses(LinkedList<Lclass> myClasses) {
		this.myClasses = myClasses;
	}
	public void setRegExClassName(Pattern regExClassName) {
		this.regExClassName = regExClassName;
	}
}