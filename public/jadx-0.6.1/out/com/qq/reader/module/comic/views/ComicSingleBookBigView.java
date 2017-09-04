package com.qq.reader.module.comic.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.comic.entity.q;
import com.tencent.feedback.proguard.R;

public class ComicSingleBookBigView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private View d;

    public ComicSingleBookBigView(Context context) {
        this(context, null, 0);
    }

    public ComicSingleBookBigView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ComicSingleBookBigView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a(context, attributeSet, i);
    }

    private void a(Context context) {
        this.d = LayoutInflater.from(context).inflate(R.layout.comic_book_simple_2_view, this);
        this.a = (ImageView) this.d.findViewById(R.id.iv_comic_cover);
        this.b = (TextView) this.d.findViewById(R.id.tv_comic_title);
        this.c = (TextView) this.d.findViewById(R.id.iv_comic_desc);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
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
