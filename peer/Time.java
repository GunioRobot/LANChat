package peer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
//OVERVIEW: Function to get formatted local time
	public static final String DATE_FORMAT_NOW = "HH:mm:ss";
	
	//Constructor
	public Time(){
	//Create empty time object;
		super();
	}
	
	public static String time() {
	//EFFECTS: returns the current time in HH:mm:ss
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
