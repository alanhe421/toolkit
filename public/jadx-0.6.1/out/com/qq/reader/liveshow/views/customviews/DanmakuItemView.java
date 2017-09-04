package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.model.im.message.a.b;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.utils.q;
import com.tencent.av.sdk.AVError;

public class DanmakuItemView extends LinearLayout {
    public int a;
    public int b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private Context f;
    private int g = 12000;
    private int h = AVError.AV_ERR_IMSDK_TIMEOUT;
    private boolean i = true;
    private b j;

    public DanmakuItemView(Context context) {
        super(context);
        a(context);
    }

    public DanmakuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.f = context;
        LayoutInflater.from(context).inflate(g.danmaku_item_layout, this);
        this.c = (ImageView) findViewById(e.avatar_icon);
        this.d = (TextView) findViewById(e.user_name);
        this.e = (TextView) findViewById(e.content_text);
        this.a = (int) (((double) this.h) + (((double) (this.g - this.h)) * Math.random()));
    }

    public void setData(final b bVar) {
        if (bVar != null) {
            this.j = bVar;
            p.a(this.f, this.c, bVar.b().getAvatar(), bVar.b().getAuthorId() > 0);
            this.d.setText(bVar.b().getNickName() + ": ");
            this.e.setText(bVar.c());
            this.b = getMeasuredWidth();
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DanmakuItemView b;

                public void onClick(View view) {
                    q.a((Activity) view.getContext(), bVar.b().getAuthorId(), bVar.b().getId(), bVar.b().getNickName(), bVar.b().getAvatar(), null, false);
                }
            });
        }
    }

    public b getData() {
        return this.j;
    }

    public boolean a() {
        return this.i;
    }

    public void b() {
        if (getVisibility() != 0) {
            this.i = false;
            setVisibility(0);
        }
    }

    public void c() {
        if (getVisibility() == 0) {
            this.i = true;
            setVisibility(4);
        }
    }
}
