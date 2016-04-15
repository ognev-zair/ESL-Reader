package com.ognev.eslreader.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class ESLPrefs {

  private static final String LANG = "LANG";
  static SharedPreferences sharedPreferences;
  public static void init(Context context) {
    sharedPreferences = context.getSharedPreferences("esl", Context.MODE_PRIVATE);
  }

  public static void saveLang(String lang) {
    sharedPreferences.edit().putString(LANG, lang).commit();
  }

  public static String getLang() {
    return sharedPreferences.getString(LANG, "en-ru");
  }
}
