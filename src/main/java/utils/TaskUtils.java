package utils;

import java.sql.Date;
import java.time.LocalDate;

public class TaskUtils {
	final static int MAX_TASK_NAME_CHARACTERS = 50;
	final static int MAX_MEMO_CHARACTERS = 100;

	public static boolean isValidTaskName(String taskName) {
		if (taskName == null || taskName.isBlank()) {
			return false;
		}
		if (taskName.length() > MAX_TASK_NAME_CHARACTERS) {
			return false;
		}
		return true;
	}

	public static boolean isValidMemo(String memo) {
		if (memo == null || memo.isBlank()) {
			return false;
		}
		if (memo.length() > MAX_MEMO_CHARACTERS) {
			return false;
		}
		return true;
	}

	public static boolean isValidDate(String date) {
		if (date == null) {
			return false;
		}

		LocalDate currentDate = LocalDate.now();
		LocalDate inputDate;
		try {
			inputDate = Date.valueOf(date).toLocalDate();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}

		if (inputDate.isBefore(currentDate)) {
			return false;
		}
		return true;
	}

}
