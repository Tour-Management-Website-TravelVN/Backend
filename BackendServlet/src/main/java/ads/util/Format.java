package ads.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Format {
	
	/**
	 * Định dạng thời gian
	 * @param time Thời gian
	 * @return dd/MM/yyyy HH:mm:ss
	 */
	public static String formatTime(Instant time) {
		if(time == null)return "";
	
		DateTimeFormatter dfm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
				.withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
		return dfm.format(time);
	}
	
	/**
	 * Chuyển chuỗi thành thời gian
	 * @param timeStr Chuỗi thời gian
	 * @return Thời gian instant
	 */
	public static Instant parseToInstant(String timeStr) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime localDateTime = LocalDateTime.parse(timeStr, formatter);
	    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
	    return zonedDateTime.toInstant();
	}

	/**
	 * Chuyển dấu \n thành <br>
	 * @param s Chuỗi vào
	 * @return Chuỗi chứa thẻ br
	 */
	public static String replaceEnter(String s) {
		return s==null?"":s.replace("\n", "<br>");
	}
	
}
