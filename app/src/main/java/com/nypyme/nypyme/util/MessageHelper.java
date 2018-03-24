package com.nypyme.nypyme.util;

import android.content.Context;
import android.widget.Toast;

/*
 * Created by Acer on 24/03/2018.
 */

public class MessageHelper {
  public static void showToast(Context context, int message){
    Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    toast.show();
  }

  public static void showToast(Context context, String message){
    Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
    toast.show();
  }
}
