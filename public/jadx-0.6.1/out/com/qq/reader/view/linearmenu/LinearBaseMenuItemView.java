package com.qq.reader.view.linearmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class LinearBaseMenuItemView extends RelativeLayout {
    private TextView a;
    private ImageView b;
    private LinearLayout c;
    private Drawable d;

    public LinearBaseMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.linear_menu_name);
        this.b = (ImageView) findViewById(R.id.linear_menu_new);
        this.c = (LinearLayout) findViewById(R.id.linear_menu_right);
    }

    public void setText(String str) {
        this.a.setText(str);
        if (str.contains(getResources().getString(R.string.text_menu_cloud))) {
            this.d = getResources().getDrawable(R.drawable.profile_cloudshelf_icon);
            this.a.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            return;
        }
        this.a.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public LinearLayout getRightLayout() {
        return this.c;
    }

    public void setNewShow(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }
}
