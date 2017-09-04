package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.model.im.message.a.b;
import com.qq.reader.liveshow.utils.q;

public class SuperVipEnterView extends LinearLayout {
    private TextView a;
    private TextView b;
    private Context c;
    private b d;

    public SuperVipEnterView(Context context) {
        super(context);
        a(context);
    }

    public SuperVipEnterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        LayoutInflater.from(context).inflate(g.super_enter_layout, this);
        this.a = (TextView) findViewById(e.vip_level);
        this.b = (TextView) findViewById(e.content_text);
    }

    public void setData(final b bVar) {
        this.d = bVar;
        this.a.setText(this.c.getResources().getString(h.vip_text, new Object[]{Integer.valueOf(bVar.b().getVipLevel())}));
        String nickName = bVar.b().getNickName();
        CharSequence spannableString = new SpannableString(this.c.getResources().getString(h.welcome_vip) + " " + nickName + " " + this.c.getResources().getString(h.enter_room));
        spannableString.setSpan(new ForegroundColorSpan(this.c.getResources().getColor(a.b.text_color_c401)), 3, nickName.length() + 3, 34);
        this.b.setText(spannableString);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SuperVipEnterView b;

            public void onClick(View view) {
                q.a((Activity) view.getContext(), bVar.b().getAuthorId(), bVar.b().getId(), bVar.b().getNickName(), bVar.b().getAvatar(), null, false);
            }
        });
    }

    public b getData() {
        return this.d;
    }
}
