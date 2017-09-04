package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.w;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: NativeBookStoreListAdapterKinds */
public class d extends BaseAdapter {
    Context a;
    List<s> b;
    private a c = null;

    public d(Context context, List<s> list) {
        this.a = context;
        this.b = list;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public Object getItem(int i) {
        if (this.b == null) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        w wVar = (w) getItem(i);
        if ("webpage".equals(wVar.i())) {
            return 0;
        }
        if ("detail".equals(wVar.i())) {
            return 1;
        }
        return 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final w wVar;
        TextView textView;
        ImageView imageView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        if (getItemViewType(i) == 0) {
            if (view == null) {
                view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.localstore_listcard_item_horizontal2, null);
            }
            wVar = (w) getItem(i);
            textView = (TextView) ap.a(view, R.id.book_name);
            imageView = (ImageView) ap.a(view, R.id.book_cover);
            textView2 = (TextView) ap.a(view, R.id.book_author);
            textView2 = (TextView) ap.a(view, R.id.book_info);
            textView3 = (TextView) ap.a(view, R.id.book_popularity);
            textView4 = (TextView) ap.a(view, R.id.book_lable);
            if (TextUtils.isEmpty(wVar.a()) || TextUtils.isEmpty(wVar.b())) {
                textView4.setVisibility(8);
            } else {
                textView4.setVisibility(0);
                textView4.setText(wVar.a());
                textView4.setBackgroundColor(Color.parseColor(wVar.b()));
            }
            c.a(this.a).a(wVar.d(), imageView, com.qq.reader.common.imageloader.a.a().j());
            textView.setText(wVar.g());
            if (wVar.c() > 0) {
                textView3.setText(s.countTransform((long) wVar.c()) + "读过");
            }
            textView2.setText(wVar.h());
            if (this.c != null) {
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ d b;

                    public void onClick(View view) {
                        wVar.a(this.b.c);
                    }
                });
            }
        } else {
            if (view == null) {
                view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.localstore_listcard_item_vertical2, null);
            }
            wVar = (w) getItem(i);
            textView = (TextView) ap.a(view, R.id.book_name);
            imageView = (ImageView) ap.a(view, R.id.book_cover);
            textView2 = (TextView) ap.a(view, R.id.book_author);
            textView3 = (TextView) ap.a(view, R.id.book_info);
            textView4 = (TextView) ap.a(view, R.id.book_popularity);
            TextView textView5 = (TextView) ap.a(view, R.id.book_lable);
            if (wVar.c() > 0) {
                textView4.setText(s.countTransform((long) wVar.c()) + "读过");
            }
            if (TextUtils.isEmpty(wVar.a()) || TextUtils.isEmpty(wVar.b())) {
                textView5.setVisibility(8);
            } else {
                textView5.setVisibility(0);
                textView5.setText(wVar.a());
                textView5.setBackgroundColor(Color.parseColor(wVar.b()));
            }
            c.a(this.a).a(wVar.e(), imageView, com.qq.reader.common.imageloader.a.a().j());
            if (!TextUtils.isEmpty(wVar.k()) && !TextUtils.isEmpty(wVar.j())) {
                textView2.setText(wVar.k() + " | " + wVar.j());
            } else if (!TextUtils.isEmpty(wVar.k())) {
                textView2.setText(wVar.k());
            } else if (!TextUtils.isEmpty(wVar.j())) {
                textView2.setText(wVar.j());
            }
            textView3.setText(wVar.l());
            textView.setText(wVar.g());
            if (this.c != null) {
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ d b;

                    public void onClick(View view) {
                        wVar.a(this.b.c);
                    }
                });
            }
        }
        return view;
    }
}
