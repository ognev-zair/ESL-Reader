package com.ognev.eslreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ognev.eslreader.Lang;
import com.ognev.eslreader.R;

public class LanguageListAdapter extends BaseAdapter {
  private Lang langs;
  private Context context;
  private LayoutInflater inflater;
  private int selectedPosition;

  public LanguageListAdapter(Context context, Lang langs) {
    this.langs = langs;
    this.context = context;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return langs.dirs.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    if(convertView == null) {
      holder = new ViewHolder();
      convertView = inflater.inflate(R.layout.lang_item, null);
      holder.lang = (TextView) convertView.findViewById(R.id.lang);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    if(position == selectedPosition) {
      convertView.setBackgroundColor(R.color.color_primary_dark);
    } else {
      convertView.setBackgroundColor(0);
    }

    holder.lang.setText(langs.dirs.get(position).toUpperCase());

    return convertView;
  }

  public void setSelected(int i) {
    selectedPosition = i;
    notifyDataSetChanged();
  }

  private class ViewHolder {
    public TextView lang;
  }
}
