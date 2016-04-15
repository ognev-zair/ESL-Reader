package com.ognev.eslreader.translator;

import android.os.AsyncTask;
import com.ognev.eslreader.Lang;
import com.ognev.eslreader.callback.Callback;
import com.ognev.eslreader.prefs.ESLPrefs;
import com.ognev.eslreader.translator.translate.Translate;

public class Translator {

  public static void translate(final String text, final Callback callback) {

    new AsyncTask<Void, String, String>() {
      @Override
      protected String doInBackground(Void... params) {
        String translatedText = null;
        try {
          String[] langs = ESLPrefs.getLang().split("-");
          translatedText = Translate.execute(text, langs[0], langs[1]);
        } catch (Exception e) {
          e.printStackTrace();
        }

        return translatedText;
      }

      @Override
      protected void onPostExecute(String translatedText) {
        super.onPostExecute(translatedText);
        callback.onTranslated(translatedText);
      }
    }.execute();

  }

  public static void getLangs(final Callback callback) {
    new AsyncTask<Void, Void, Void>() {
      Lang lang = null;
      @Override
      protected Void doInBackground(Void... params) {
        try {
          lang = Translate.getLangs();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.onLang(lang);
      }
    }.execute();
  }

  public static void cancelTranslating() {
    Translate.cancel();
  }
}
