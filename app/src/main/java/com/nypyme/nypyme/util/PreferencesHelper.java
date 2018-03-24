package com.nypyme.nypyme.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 * Created by Acer on 24/03/2018.
 */

public class PreferencesHelper {
  private static final String TOKEN = "token";

  private final SharedPreferences sharedPreferences;

  public PreferencesHelper(Context context) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public void saveToken(String token) {
    sharedPreferences.edit().putString(TOKEN, token).commit();
  }

  public String getToken() {
    return sharedPreferences.getString(TOKEN, "");
  }

  public void removeToken() {
    sharedPreferences.edit().remove(TOKEN).commit();
  }

}
