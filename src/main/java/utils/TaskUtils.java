package utils;

import java.sql.Date;
import java.time.LocalDate;

public class TaskUtils {

	public static boolean isValidDate(Date sqlDate) {
		if (sqlDate == null) {
			return false;
		}
		
		LocalDate currentDate = LocalDate.now();
		LocalDate inputDate = sqlDate.toLocalDate();
		
		if(inputDate.isBefore(currentDate)) {
			return false;
		}

		return true;
	}

}
