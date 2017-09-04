package com.qq.reader.module.comic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.c.b;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.comic.entity.q;
import com.tencent.feedback.proguard.R;

public class ComicSingleBookView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private String d;
    private int e;
    private int f;
    private String g;
    private int h;
    private int i;
    private View j;

    public ComicSingleBookView(Context context) {
        this(context, null, 0);
    }

    public ComicSingleBookView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ComicSingleBookView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a(context, attributeSet, i);
    }

    private void a(Context context) {
        this.j = LayoutInflater.from(context).inflate(R.layout.comic_book_simple_view, this);
        this.a = (ImageView) this.j.findViewById(R.id.iv_comic_cover);
        this.b = (TextView) this.j.findViewById(R.id.tv_comic_title);
        this.c = (TextView) this.j.findViewById(R.id.iv_comic_desc);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        int i2 = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, b.ComicSingleBookView, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        while (i2 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.d = obtainStyledAttributes.getString(index);
                    break;
                case 1:
                    this.e = obtainStyledAttributes.getColor(index, getResources().getColor(R.color.text_color_c101));
                    break;
                case 2:
                    this.f = obtainStyledAttributes.getDimensionPixelSize(index, (int) TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics()));
                    break;
                case 3:
                    this.g = obtainStyledAttributes.getString(index);
                    break;
                case 4:
                    this.h = obtainStyledAttributes.getColor(index, getResources().getColor(R.color.text_color_c103));
                    break;
                case 5:
                    this.i = obtainStyledAttributes.getDimensionPixelSize(index, (int) TypedValue.applyDimension(1, 12.0f, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
            i2++;
        }
        obtainStyledAttributes.recycle();
        setTitleStr(this.d);
        setDesStr(this.g);
    }

    public void setAllData(q qVar) {
        setTitleStr(qVar.b());
        if (TextUtils.isEmpty(qVar.h())) {
            setDesStr(qVar.d());
        } else {
            setDesStr(qVar.h());
        }
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            str2 = str;
        }
        c.a(getContext()).a(str2, this.a, a.a().j());
    }

    public void setTitleStr(String str) {
        this.b.setText(str);
    }

    public void setTitleColor(int i) {
        this.b.setTextColor(i);
    }

    public void setTitleSize(int i) {
        this.b.setTextSize((float) i);
    }

    public void setDesStr(String str) {
        this.c.setText(str);
    }

    public void setDesColor(int i) {
        this.c.setTextColor(i);
    }

    public void setDesSize(int i) {
        this.c.setTextSize((float) i);
    }

    public void setIvComicCover(int i) {
        this.a.setImageResource(i);
    }

    public void setIvComicCover(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    public ImageView getIvComicCover() {
        return this.a;
    }

    public TextView getTvComicName() {
        return this.b;
    }

    public TextView getTvComicDes() {
        return this.c;
    }
}
