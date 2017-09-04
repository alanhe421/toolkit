package com.qq.reader.view.web;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: QRPopupMenuAdapter */
public class o extends BaseAdapter {
    public ArrayList<a> a;
    protected Context b;
    public int c = 0;
    private int d = R.layout.webpage_popupmenu_item;

    /* compiled from: QRPopupMenuAdapter */
    static class a {
        int a;
        String b;
        int c;
        String d;
        Bundle e;

        a() {
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public o(Context context) {
        this.b = context;
        this.a = new ArrayList();
    }

    public boolean a(int i, String str, boolean z, Bundle bundle) {
        a aVar = new a();
        aVar.a = i;
        aVar.b = str;
        aVar.e = bundle;
        this.a.add(aVar);
        notifyDataSetChanged();
        return true;
    }

    public void a() {
        this.a.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a.size();
    }

    public a a(int i) {
        return (a) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) ((a) this.a.get(i)).a;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        int i3 = 8;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(this.d, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.webpage_popupmenu_item_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.webpage_popupmenu_item_selected);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.webpage_popupmenu_item_icon);
        TextView textView2 = (TextView) view.findViewById(R.id.webpage_popupmenu_item_info);
        View findViewById = view.findViewById(R.id.webpage_popupmenu_item_divider);
        a aVar = (a) this.a.get(i);
        if (imageView2 != null && aVar.a > 0) {
            imageView2.setImageResource(aVar.c);
        }
        if (!(textView2 == null || TextUtils.isEmpty(aVar.d))) {
            textView2.setText(aVar.d);
        }
        if (this.c == i) {
            textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.text_color_c301));
        } else {
            textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.text_color_c103));
        }
        textView.setText(aVar.b);
        if (this.c == i) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        if (i != getCount() - 1) {
            i3 = 0;
        }
        findViewById.setVisibility(i3);
        return view;
    }
}
