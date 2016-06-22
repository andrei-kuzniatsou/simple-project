package org.gradle;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

  /**
   * Create date
   * 
   * @param day day
   * @param month month
   * @param year year
   * @return new date
   */
  public static Date createDate(int day, int month, int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }
}
