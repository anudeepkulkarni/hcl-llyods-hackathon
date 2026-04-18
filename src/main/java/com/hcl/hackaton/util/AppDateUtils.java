package com.hcl.hackaton.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppDateUtils {

  private AppDateUtils() {
    super();

  }

  /**
   * Converts a date string from the format 'yyyy-MM-dd HH:mm:ss.SSSSSS'
   * to the format 'dd MMM yyyy h a'.
   *
   * @param originalDate the original date string
   * @return the formatted date string
   */
  public static String convertDateFormat(String originalDate) {
    LocalDateTime dateTime = LocalDateTime.parse(originalDate.substring(0, 19)); // Trim to second precision
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    return dateTime.format(formatter);
  }

  /**
   * Converts a date string from of utc time zone to user time zone'
   *
   * @param utcTime the utc time date string
   * @return the user time zone date string
   */
  public static String convertUtcToUserTimeZone(String utcTime, String zone) {
    ZonedDateTime utcDateTime = ZonedDateTime.parse(utcTime);
    ZonedDateTime utcZonedDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
    ZonedDateTime userTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of(zone));
    return convertDateFormat(String.valueOf(userTime));
  }

}
