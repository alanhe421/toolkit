package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.qurl.c;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class PageAdvertismentView extends TextView implements OnClickListener {
    private Activity a;
    private a b;
    private Context c;

    public PageAdvertismentView(Context context) {
        super(context);
        this.c = context;
        setOnClickListener(this);
    }

    public PageAdvertismentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        setOnClickListener(this);
    }

    public void a(Activity activity) {
        this.a = activity;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.b = aVar;
            setText(this.b.e());
        }
    }

    public void onClick(View view) {
        if (this.b != null) {
            Intent intent = new Intent();
            this.b.f();
            String h = this.b.h();
            String str = "";
            if (h == null) {
                h = str;
            }
            if (h.indexOf("=") != -1) {
                h = h + "&" + e.b(this.c);
            } else {
                if (!h.endsWith("?")) {
                    h = h + "?";
                }
                h = h + e.b(this.c);
            }
            if (c.a(h)) {
                try {
                    c.a(this.a, h);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            intent.setClass(this.c, WebBrowserForContents.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            intent.putExtra("com.qq.reader.WebContent", h);
            this.c.startActivity(intent);
        }
    }
}
