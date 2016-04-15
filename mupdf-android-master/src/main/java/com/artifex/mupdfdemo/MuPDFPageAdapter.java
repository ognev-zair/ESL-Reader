package com.artifex.mupdfdemo;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artifex.utils.PdfBitmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MuPDFPageAdapter extends BaseAdapter {
	private final Context mContext;
	private final FilePicker.FilePickerSupport mFilePickerSupport;
	private final MuPDFCore mCore;
	private final SparseArray<PointF> mPageSizes = new SparseArray<PointF>();
    private SparseArray<MuPDFPageView> pages;
    private Set<PdfBitmap> pdfBitmapList; // Each signature for each page.
    private int numSignature;

	public MuPDFPageAdapter(Context c, FilePicker.FilePickerSupport filePickerSupport, MuPDFCore core) {
		mContext = c;
		mFilePickerSupport = filePickerSupport;
		mCore = core;
        pages = new SparseArray<MuPDFPageView>();
        pdfBitmapList = new HashSet<PdfBitmap>();
	}

	public int getCount() {
		return mCore.countPages();
	}

	public Object getItem(int position) {
        return pages.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

        final MuPDFPageView pageView;
        if (pages.get(position) == null) {
            pageView = new MuPDFPageView(mContext, mFilePickerSupport, mCore, new Point(parent.getWidth(), parent.getHeight()), this);
            pages.put(position, pageView);
        } else {
            pageView = pages.get(position);
        }

		PointF pageSize = mPageSizes.get(position);
		if (pageSize != null) {
			// We already know the page size. Set it up
			// immediately
			pageView.setPage(position, pageSize);
		} else {
			// Page size as yet unknown. Blank it for now, and
			// start a background task to find the size
			pageView.blank(position);
			AsyncTask<Void,Void,PointF> sizingTask = new AsyncTask<Void,Void,PointF>() {
				@Override
				protected PointF doInBackground(Void... arg0) {
					return mCore.getPageSize(position);
				}

				@Override
				protected void onPostExecute(PointF result) {
					super.onPostExecute(result);
					// We now know the page size
					mPageSizes.put(position, result);
					// Check that this view hasn't been reused for
					// another page since we started
					if (pageView.getPage() == position)
						pageView.setPage(position, result);
				}
			};

			sizingTask.execute((Void)null);
		}
		return pageView;
	}

    public Set<PdfBitmap> getPdfBitmapList() {
        return pdfBitmapList;
    }

    public void setPdfBitmapList(Set<PdfBitmap> pdfBitmapList) {
        this.pdfBitmapList = pdfBitmapList;
    }

    public int getNumSignature() {
        return numSignature;
    }

    public void setNumSignature(int numSignature) {
        this.numSignature = numSignature;
    }
}
