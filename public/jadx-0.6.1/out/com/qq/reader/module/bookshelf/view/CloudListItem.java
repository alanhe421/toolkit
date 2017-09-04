package com.qq.reader.module.bookshelf.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.view.QRImageView;
import com.tencent.feedback.proguard.R;

public class CloudListItem extends RelativeLayout {
    int a;
    int b;
    private QRImageView c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;

    public CloudListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CloudListItem(Context context) {
        super(context);
    }

    public void a() {
        this.c = (QRImageView) findViewById(R.id.book_icon);
        this.d = (ImageView) findViewById(R.id.book_res_type);
        this.e = (TextView) findViewById(R.id.bookname);
        this.f = (TextView) findViewById(R.id.last_chapter);
        this.g = (TextView) findViewById(R.id.add_layout);
        this.a = getResources().getColor(R.color.textcolor_gray);
        this.b = getResources().getColor(R.color.textcolor_white);
    }

    public ImageView getIconImageView() {
        return this.c;
    }

    public void setBookResType(int i) {
        if (i == 3) {
            this.d.setImageResource(R.drawable.comic_book_icon);
            this.d.setVisibility(0);
            return;
        }
        this.d.setVisibility(8);
    }

    public void setFileItemInfo(g gVar, boolean z) {
        if (gVar != null) {
            this.e.setText(gVar.v());
            if (gVar.w() == 3) {
                int parseInt;
                Object t = gVar.t();
                if (!TextUtils.isEmpty(t)) {
                    String[] a = e.a(t);
                    if (a != null) {
                        parseInt = Integer.parseInt(a[2]) + 1;
                        this.f.setText(getContext().getResources().getString(R.string.comic_read_progress, new Object[]{Integer.valueOf(parseInt)}));
                    }
                }
                parseInt = 1;
                this.f.setText(getContext().getResources().getString(R.string.comic_read_progress, new Object[]{Integer.valueOf(parseInt)}));
            } else {
                this.f.setText("读至:" + gVar.q());
            }
            if (z) {
                this.g.setBackgroundDrawable(null);
                this.g.setText("已添加");
                this.g.setTextSize(1, 12.0f);
                this.g.setTextColor(this.a);
                this.g.setGravity(21);
            } else if (gVar.a().a()) {
                this.g.setBackgroundDrawable(null);
                this.g.setText("");
            } else {
                this.g.setBackgroundResource(R.drawable.button_bg);
                this.g.setText("添加");
                this.g.setTextSize(1, 12.0f);
                this.g.setTextColor(this.b);
                this.g.setGravity(17);
            }
        }
    }
}
