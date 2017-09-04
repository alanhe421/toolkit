package com.qq.reader.module.bookstore.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchHotWordsHandlerView extends FrameLayout {
    LayoutInflater a;
    ViewGroup b;
    List<TextView> c = new ArrayList();
    List<ViewGroup> d = new ArrayList();
    List<SearchHotWords> e = new ArrayList();
    List<ImageView> f = new ArrayList();
    private WeakReference<Activity> g;
    private int h = R.color.text_color_c401;
    private int i = R.color.text_color_c101;
    private ISearchParamCollection j = new CommonBookSearchParamCollection();

    public SearchHotWordsHandlerView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = LayoutInflater.from(context);
        this.b = (ViewGroup) this.a.inflate(R.layout.search_hot_keyword_container_layout, null);
        addView(this.b);
        int childCount = this.b.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup viewGroup = (ViewGroup) this.b.getChildAt(i);
            this.d.add(viewGroup);
            this.c.add((TextView) viewGroup.findViewById(R.id.tv_left));
            this.c.add((TextView) viewGroup.findViewById(R.id.tv_right));
            this.f.add((ImageView) viewGroup.findViewById(R.id.iv_hot_bg_left));
            this.f.add((ImageView) viewGroup.findViewById(R.id.iv_hot_bg_right));
        }
    }

    public SearchHotWordsHandlerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SearchHotWordsHandlerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setSearchHotWords(List<SearchHotWords> list, Activity activity) {
        this.g = new WeakReference(activity);
        this.e.clear();
        this.e.addAll(list);
        a();
    }

    private void a() {
        Collections.shuffle(this.e);
        Iterator it = this.e.iterator();
        int i = 0;
        while (it.hasNext() && i < this.c.size()) {
            AbsSearchWords absSearchWords = (AbsSearchWords) it.next();
            TextView textView = (TextView) this.c.get(i);
            ImageView imageView = (ImageView) this.f.get(i);
            textView.setText(absSearchWords.getKeyWord());
            Object obj = (System.currentTimeMillis() + ((long) i)) % 5 == 0 ? 1 : null;
            textView.setTextColor(obj != null ? getResources().getColor(this.h) : getResources().getColor(this.i));
            imageView.setVisibility(obj != null ? 0 : 4);
            int i2 = absSearchWords.getIconResIds()[0];
            Options options = new Options();
            options.inMutable = true;
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i2, options);
            if (obj != null) {
                decodeResource = a(decodeResource, getResources().getColor(R.color.text_color_c401));
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), decodeResource), null, null, null);
            textView.setTag(R.string.obj_tag, absSearchWords);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchHotWordsHandlerView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Object tag = view.getTag(R.string.obj_tag);
                    Activity activity = (Activity) this.a.g.get();
                    if ((tag instanceof AbsSearchWords) && activity != null) {
                        Object hashMap = new HashMap();
                        hashMap.put("key", ((AbsSearchWords) tag).getKeyWord());
                        hashMap.put(s.ORIGIN, "909");
                        try {
                            c.a(activity, ((AbsSearchWords) tag).getQurl(), new JumpActivityParameter().a(hashMap));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SearchHistory searchHistory = new SearchHistory(System.currentTimeMillis(), ((AbsSearchWords) tag).getKeyWord(), ((AbsSearchWords) tag).getType());
                        searchHistory.setQurl(((AbsSearchWords) tag).getQurl());
                        g.a(ReaderApplication.getApplicationImp()).a(searchHistory, this.a.j);
                        Map hashMap2 = new HashMap();
                        hashMap2.put(s.ORIGIN, d.aS(ReaderApplication.getApplicationImp()) + "");
                        if (this.a.j != null) {
                            this.a.j.submitStaticsParam(hashMap2);
                        }
                        i.a("event_C259", hashMap2, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_C259", hashMap2);
                    }
                }
            });
            i++;
        }
        if (this.e.size() < this.c.size()) {
            List<TextView> subList = this.c.subList(this.e.size(), this.c.size());
            int size = subList.size();
            for (TextView textView2 : subList) {
                textView2.setText("");
                textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                textView2.setVisibility(8);
                textView2.setOnClickListener(null);
            }
            for (ViewGroup visibility : this.d.subList(this.d.size() - (size / 2), this.d.size())) {
                visibility.setVisibility(8);
            }
            return;
        }
        for (TextView textView22 : this.c) {
            textView22.setVisibility(0);
        }
        for (ViewGroup visibility2 : this.d) {
            visibility2.setVisibility(0);
        }
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        float f = (float) (i & 255);
        float f2 = (float) ((i >> 8) & 255);
        float f3 = (float) ((i >> 16) & 255);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f3, 0.0f, 0.0f, 0.0f, 0.0f, f2, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmap;
    }

    public void setSearchMode(ISearchParamCollection iSearchParamCollection) {
        this.j = iSearchParamCollection;
    }
}
