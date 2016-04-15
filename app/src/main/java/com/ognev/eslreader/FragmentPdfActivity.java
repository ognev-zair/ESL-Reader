package com.ognev.eslreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.artifex.mupdfdemo.MuPDFFragment;

public class FragmentPdfActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_container);
    MuPDFFragment muPDFFragment = new MuPDFFragment();
    Bundle bundle = new Bundle();
    bundle.putString(MuPDFFragment.PARAM_PATH_PDF, getIntent().getData().getPath());
    muPDFFragment.setArguments(bundle);
    getSupportFragmentManager().beginTransaction().replace(R.id.root, muPDFFragment).commit();
  }
}
