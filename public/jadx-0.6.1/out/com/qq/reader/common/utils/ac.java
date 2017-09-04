package com.qq.reader.common.utils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.tencent.feedback.proguard.R;

/* compiled from: ReaderMenuExtContainerHandler */
public class ac {
    public static void a(final Activity activity, LinearLayout linearLayout, final String str, final String str2) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            linearLayout.setGravity(49);
            if (d.aW(activity)) {
                View findViewById = linearLayout.getRootView().findViewById(R.id.top_shadow);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
                findViewById = new ImageView(ReaderApplication.getApplicationImp());
                if (d.P(ReaderApplication.getApplicationImp())) {
                    findViewById.setImageResource(R.drawable.reader_menu_red_packet_bottom_night_mode);
                } else {
                    findViewById.setImageResource(R.drawable.reader_menu_red_packet_bottom);
                }
                findViewById.setLayoutParams(new LayoutParams(-2, -2));
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            o.a(activity, str2, Long.parseLong(str), -1, true, null);
                            i.a("event_D206", null, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            c.e("Error", e.getMessage());
                        }
                    }
                });
                i.a("event_D205", null, ReaderApplication.getApplicationImp());
                linearLayout.addView(findViewById);
            }
        }
    }
}
