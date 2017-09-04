package com.qq.reader.module.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.protocol.c;
import com.qq.reader.common.utils.ap;
import com.tencent.feedback.proguard.R;

/* compiled from: AboutAdapter */
public class a extends BaseAdapter {
    private Context a;
    private String[] b;
    private int c = 4;
    private OnTouchListener d = new OnTouchListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                ((ImageView) ap.a(view, R.id.about_list_item_icon_image)).setVisibility(4);
                d.a = false;
            }
            return false;
        }
    };

    public a(Context context) {
        this.a = context;
        this.b = new String[]{"软件不错，赞一个", "帮助中心", "我要反馈", "版权声明", "检测更新"};
    }

    public int getCount() {
        return this.b.length;
    }

    public Object getItem(int i) {
        return this.b[i];
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.about_list_item, null);
        }
        ((TextView) ap.a(view, R.id.about_list_item_text)).setText(this.b[i]);
        view.setOnTouchListener(null);
        ImageView imageView = (ImageView) ap.a(view, R.id.about_list_item_icon_image);
        if (i != this.c) {
            imageView.setVisibility(4);
        } else if (c.a(this.a) && d.a) {
            imageView.setVisibility(0);
            view.setOnTouchListener(this.d);
        }
        return view;
    }
}
