package com.qq.reader.module.game.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.module.game.data.b;
import com.tencent.feedback.proguard.R;

public class GameCouponItemView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;

    public GameCouponItemView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public GameCouponItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public GameCouponItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(context).inflate(R.layout.game_coupon_item, this);
        this.f = findViewById(R.id.container);
        this.a = (TextView) findViewById(R.id.name);
        this.b = (TextView) findViewById(R.id.time);
        this.c = (TextView) findViewById(R.id.limit);
        this.d = (TextView) findViewById(R.id.count);
        this.e = (TextView) findViewById(R.id.unit);
    }

    public void a(b bVar) {
        switch (bVar.b()) {
            case 1:
                this.f.setBackgroundResource(R.drawable.game_coupon_type1);
                this.c.setTextColor(getResources().getColor(R.color.text_color_c301));
                this.d.setTextColor(getResources().getColor(R.color.text_color_c301));
                break;
            case 2:
                this.f.setBackgroundResource(R.drawable.game_coupon_type2);
                this.c.setTextColor(getResources().getColor(R.color.text_color_c701));
                this.d.setTextColor(getResources().getColor(R.color.text_color_c701));
                break;
        }
        this.a.setText(bVar.c());
        this.b.setText(bVar.e());
        Object d = bVar.d();
        if (TextUtils.isEmpty(d) || d.equals("0")) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.c.setText(bVar.d());
        }
        this.d.setText(bVar.f() + "");
        this.e.setText(bVar.g());
    }
}
