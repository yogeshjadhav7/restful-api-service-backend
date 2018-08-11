package utils.general;

import java.util.Calendar;
import java.util.TimeZone;

public class DateAndTimeUtils {
	
	public static long compareEpoch(long epochOne, long epochTwo) {
		if(epochOne < epochTwo)
			return epochOne - epochTwo;
		if(epochOne > epochTwo)
			return epochOne - epochTwo;
		
		return 0;
	}
	
	public static long compareEpochWithCurrentTime(long epoch) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return compareEpoch(epoch, calendar.getTimeInMillis());
	}

	public static long getCurrentTime() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return calendar.getTimeInMillis();
	}
}
