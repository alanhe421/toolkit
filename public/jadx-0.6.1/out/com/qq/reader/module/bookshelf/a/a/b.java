package com.qq.reader.module.bookshelf.a.a;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.view.QRImageView;
import com.tencent.feedback.proguard.R;

/* compiled from: CategoryDetailListItem */
public class b {
    public QRImageView a;
    public CheckBox b;
    public TextView c;
    public TextView d;
    public TextView e;
    private ImageView f;
    private ImageView g;
    private int h = -1;

    public b(View view) {
        this.a = (QRImageView) view.findViewById(R.id.categrory_detail_bookcover);
        this.c = (TextView) view.findViewById(R.id.categrory_detail_bookname);
        this.d = (TextView) view.findViewById(R.id.categrory_detail_chaptername);
        this.b = (CheckBox) view.findViewById(R.id.categrory_detail_checkbox);
        this.e = (TextView) view.findViewById(R.id.categrory_book_type);
        this.f = (ImageView) view.findViewById(R.id.categrory_booktype_tag);
        this.g = (ImageView) view.findViewById(R.id.categrory_private_tag);
    }

    public void a(String str) {
        this.h = str.hashCode();
    }

    public void b(String str) {
        this.c.setText(str);
    }

    public void c(String str) {
        this.d.setText(str);
    }

    public void a(boolean z) {
        this.b.setChecked(z);
    }

    public void b(boolean z) {
        this.b.setVisibility(z ? 0 : 4);
    }

    public void a(int i) {
        if (i == 0) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
    }

    public void d(String str) {
        String str2 = null;
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf != -1) {
                str2 = str.substring(lastIndexOf + 1, str.length());
            }
        }
        if (str2 == null || str2.length() <= 0) {
            this.e.setVisibility(0);
            this.a.setImageResource(R.drawable.book_default_cover);
            this.e.setText(ReaderApplication.getApplicationImp().getString(R.string.app_name));
            return;
        }
        this.e.setText(str2.toUpperCase());
        this.e.setVisibility(0);
        this.a.setImageResource(R.drawable.book_default_cover);
    }

    public void b(int i) {
        if (i == 9) {
            this.f.setImageResource(R.drawable.comic_book_icon);
            this.f.setVisibility(0);
        } else if (i == 8) {
            this.f.setImageResource(R.drawable.listen_book_icon);
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
    }

    public void a() {
        this.e.setVisibility(8);
    }
}
