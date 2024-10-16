package utils;

import java.sql.Date;
import java.time.LocalDate;

public class TaskUtils {
	public final static int MAX_TASK_NAME_CHARACTERS = 50;
	public final static int MAX_MEMO_CHARACTERS = 100;

	public static String isValidTaskName(String taskName) {
		if (taskName == null || taskName.isBlank()) {
			return "タスク名は空欄に出来ません。";
		}
		if (taskName.length() > MAX_TASK_NAME_CHARACTERS) {
			return "タスク名は" + MAX_TASK_NAME_CHARACTERS + "文字以内で入力してください。";
		}
		return null;
	}

	public static String isValidMemo(String memo) {
		if (memo == null || memo.isBlank()) {
			return null;
		}
		if (memo.length() > MAX_MEMO_CHARACTERS) {
			return "メモは" + MAX_MEMO_CHARACTERS + "文字以内で入力してください。";
		}
		return null;
	}

	public static String isValidDate(String date) {
		if (date.isEmpty()) {
			return null;
		}

		LocalDate currentDate = LocalDate.now();
		LocalDate inputDate;
		try {
			inputDate = Date.valueOf(date).toLocalDate();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return "不正な入力値です";
		}

		if (inputDate.isBefore(currentDate)) {
			return "期限には今日より早い日付は登録できません。";
		}
		return null;
	}

}
