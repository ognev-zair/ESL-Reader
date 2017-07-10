package com.ognev.eslreader.app;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.ognev.eslreader.prefs.ESLPrefs;
import com.ognev.eslreader.translator.ApiKeys;
import com.ognev.eslreader.translator.translate.Translate;
//import com.parse.Parse;
import io.fabric.sdk.android.Fabric;

public class ESLApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
    ESLPrefs.init(getApplicationContext());
    Translate.setKey(ApiKeys.YANDEX_API_KEY);
    // Enable Local Datastore.
//    Parse.enableLocalDatastore(this);

//    Parse.initialize(this, "emitT3Y40tD55skdjd5he4d7SHyA1SLrUcWTkbp1", "GrRm1zGLzJVVSMK3sqX8Zg7PcKl6TaolWA5oSx14");
//    ParseCrashReporting.enable(context);
  }
}
