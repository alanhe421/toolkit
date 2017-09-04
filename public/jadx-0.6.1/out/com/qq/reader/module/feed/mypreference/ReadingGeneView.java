package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.feed.mypreference.b.a;
import com.tencent.feedback.proguard.R;

public class ReadingGeneView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private ImageView c;
    private ImageView d;

    public ReadingGeneView(Context context) {
        super(context);
        a(context);
    }

    public ReadingGeneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ReadingGeneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gene_icon_layout, this);
        this.a = (TextView) inflate.findViewById(R.id.tv_category);
        this.b = (TextView) inflate.findViewById(R.id.tv_percent);
        this.c = (ImageView) inflate.findViewById(R.id.img_category);
        this.d = (ImageView) inflate.findViewById(R.id.img_mask);
    }

    public void setCategory(String str) {
        this.a.setText(str);
    }

    public void setCategoryById(String str) {
        a a = b.a(str);
        if (a != null) {
            setCategory(a.a);
        } else {
            setCategory("其它");
        }
    }

    public void setPercent(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.setText("");
            return;
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt > 0) {
                this.b.setText(str + "%");
            } else {
                this.b.setText("");
            }
            if (parseInt > 34) {
                this.a.setTextSize(0, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.read_gene_category_large_size));
                this.b.setTextSize(0, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.read_gene_percent_large_size));
                return;
            }
            this.a.setTextSize(0, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.read_gene_category_small_size));
            this.b.setTextSize(0, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.read_gene_percent_small_size));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIcon(int i) {
        this.c.setBackgroundResource(i);
    }

    public void setIconOnClickListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setIconByCategoryId(String str, int i, int i2) {
        try {
            c.a(getContext()).a(b.b(str), this.c, com.qq.reader.common.imageloader.a.a().q());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        this.d.setVisibility(8);
    }
}
