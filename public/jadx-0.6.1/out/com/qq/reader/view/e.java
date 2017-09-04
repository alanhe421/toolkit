package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.readpage.ReaderPageSwither;
import com.qq.reader.view.ColorPickerView.c;
import com.tencent.feedback.proguard.R;

/* compiled from: ColorPickDialog */
public class e extends BaseDialog {
    Context a;
    ColorPickerView b;
    private View c;
    private View d;
    private TextView e;
    private TextView i;
    private TextView j;

    public e(Activity activity, ReaderPageSwither readerPageSwither) {
        this.a = activity;
        d(false);
        if (this.f == null) {
            a(activity, null, R.layout.colorpick, true, false, true);
            this.b = (ColorPickerView) this.f.findViewById(R.id.colorview);
            this.c = this.f.findViewById(R.id.content_iew);
            this.d = this.f.findViewById(R.id.top_shadow);
            this.e = (TextView) this.f.findViewById(R.id.text1);
            this.i = (TextView) this.f.findViewById(R.id.text2);
            this.j = (TextView) this.f.findViewById(R.id.text3);
            this.b.setOnColorChangeListener(readerPageSwither);
            this.b.a((c) readerPageSwither);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.b.b();
                    this.a.b.a();
                }
            });
        }
        a(d.n);
    }

    public void a(boolean z) {
        if (z) {
            this.f.findViewById(R.id.top_shadow).setVisibility(8);
            this.c.setBackgroundResource(R.color.commonsetting_bg_color_night);
            this.e.setTextColor(this.a.getResources().getColor(R.color.text_color_c304));
            this.i.setTextColor(this.a.getResources().getColor(R.color.text_color_c304));
            this.j.setTextColor(this.a.getResources().getColor(R.color.text_color_c304));
            return;
        }
        this.f.findViewById(R.id.top_shadow).setVisibility(0);
        this.c.setBackgroundResource(R.color.commonsetting_bg_color);
        this.e.setTextColor(this.a.getResources().getColor(R.color.text_color_c301));
        this.i.setTextColor(this.a.getResources().getColor(R.color.text_color_c301));
        this.j.setTextColor(this.a.getResources().getColor(R.color.text_color_c301));
    }
}
