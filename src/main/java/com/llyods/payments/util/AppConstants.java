package com.llyods.payments.util;

import org.json.simple.JSONObject;

public final class AppConstants {

  public static final String STATUS = "status";

  public static final String SUCCESS_STR = "Successfully saved the event";
  public static final String RECORDS_NOT_FOUND = "Unable to retrieve records";

  private AppConstants() {
    super();
  }


  /**
   * Builds a success response JSON string.
   *
   * @return The success response JSON string.
   */
  public static String buildSuccessResponse() {

    JSONObject responseObj = new JSONObject();

    responseObj.put(STATUS, SUCCESS_STR);

    return responseObj.toString();
  }

}
