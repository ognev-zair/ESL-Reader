package com.ognev.eslreader.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.ognev.eslreader.Lang;
import com.ognev.eslreader.R;
import com.ognev.eslreader.adapter.LanguageListAdapter;
import com.ognev.eslreader.callback.Callback;
import com.ognev.eslreader.prefs.ESLPrefs;
import com.ognev.eslreader.translator.Translator;

public class SettingsDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {

  private ListView listView;
  private LanguageListAdapter langAdapter;
  private ProgressBar progressBar;
  private Lang languages;
  private String currentLang;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    currentLang = ESLPrefs.getLang();
    setCancelable(true);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    return super.onCreateDialog(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    getDialog().setTitle(getString(R.string.choose_language));
    View view = inflater.inflate(R.layout.settings, null);
    listView = (ListView) view.findViewById(R.id.list);
    progressBar = (ProgressBar) view.findViewById(R.id.progress);
    listView.setOnItemClickListener(this);
    Translator.getLangs(new Callback() {
      @Override
      public void onTranslated(String text) {

      }

      @Override
      public void onLang(Lang lang) {
        if (getActivity() != null) {
          progressBar.setVisibility(View.GONE);
          if (lang == null) {
            Toast.makeText(getActivity(), R.string.smth_happend, Toast.LENGTH_SHORT).show();
          } else {
            languages = lang;
            langAdapter = new LanguageListAdapter(getActivity(), lang);
            listView.setAdapter(langAdapter);
            for (int i = 0; i < lang.dirs.size(); i++)
              if (currentLang.equals(lang.dirs.get(i))) {
                listView.setSelection(i);
                langAdapter.setSelected(i);
              }
            langAdapter.notifyDataSetChanged();
          }
        }
      }

    });
    return view;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(getActivity(), languages.dirs.get(position), Toast.LENGTH_SHORT).show();
    ESLPrefs.saveLang(languages.dirs.get(position));
    dismiss();
  }
}
