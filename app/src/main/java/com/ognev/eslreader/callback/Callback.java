package com.ognev.eslreader.callback;

import com.ognev.eslreader.Lang;

public interface Callback {

  void onTranslated(String text);
  void onLang(Lang lang);
}
