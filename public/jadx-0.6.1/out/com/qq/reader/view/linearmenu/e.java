package com.qq.reader.view.linearmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: LinearMenuOfOneCategory */
public class e extends a {
    TextView a;

    public e(Activity activity) {
        super(activity);
        View inflate = LayoutInflater.from(this.k).inflate(R.layout.category_linear_menu_header_view, null);
        this.i.addHeaderView(inflate);
        this.a = (TextView) inflate.findViewById(R.id.catrgory_name);
        h();
    }

    public void a(CharSequence charSequence) {
        this.a.setText(charSequence);
    }
}
