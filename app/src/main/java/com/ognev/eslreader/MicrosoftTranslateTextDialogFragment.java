package com.ognev.eslreader;


import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ognev on 20.02.15.
 */
public class MicrosoftTranslateTextDialogFragment extends DialogFragment {
    private Context context;
    private TextView translating;
    private TextView translatedText;
    private TextView sourceText;
    private String sourceT;
    private String translatedT;
    private LinearLayout translatedView;

  public MicrosoftTranslateTextDialogFragment() {
  }

  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public MicrosoftTranslateTextDialogFragment(Context context, String text) {
        this.sourceT = text;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(context, R.style.ReadMeDialog);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//       final View view = inflater.inflate(R.layout.translate_view, null);
//        translatedText = (TextView) view.findViewById(R.id.translatedtext);
//        sourceText = (TextView) view.findViewById(R.id.sourceText);
//        translating = (TextView) view.findViewById(R.id.translating);
//        translatedView = (LinearLayout) view.findViewById(R.id.translatedView);
//        translatedView.setVisibility(View.GONE);
//        translating.setVisibility(View.VISIBLE);
//        view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
////        translatedText.setText(text);
//        new MyAsyncTask() {
//            protected void onPostExecute(Boolean result) {
//                view.setBackgroundColor(getResources().getColor(R.color.text_normal));
//                translatedText.setText(translatedT);
//                sourceText.setText(sourceT);
//                translating.setVisibility(View.GONE);
//                translatedView.setVisibility(View.VISIBLE);
//            }
//        }.execute();

        return null;
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
//            Translate.setClientId("MicrosoftTranslatorJavaAPI");
//            Translate.setClientSecret("0VHbhXQnJrZ7OwVqcoX/PDZlyLJS9co3cVev1TPr8iM=");
//            try {
//                translatedT = Translate.execute(sourceT, Language.ENGLISH);
//            } catch(Exception e) {
//                translatedT = e.toString();
//            }
            return true;
        }
    }
}
