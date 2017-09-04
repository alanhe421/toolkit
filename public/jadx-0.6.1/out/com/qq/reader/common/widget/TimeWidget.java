package com.qq.reader.common.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeWidget extends LinearLayout {
    private long a;
    private TextView[] b;
    private a[] c;
    private Handler d;
    private Handler e;

    private static class a {
        private TextView a;
        private long b;
        private long c = -1;

        public a(TextView textView) {
            this.a = textView;
        }

        public void a(long j) {
            this.b = j;
            if (this.c != this.b) {
                if (j < 10) {
                    this.a.setText("0" + j);
                } else {
                    this.a.setText("" + j);
                }
            }
            this.c = this.b;
        }
    }

    public TimeWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new TextView[3];
        this.c = new a[3];
        this.e = new Handler(this) {
            final /* synthetic */ TimeWidget a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.a();
            }
        };
        this.d = this.e;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.timewidget, null);
        addView(inflate);
        this.b[0] = (TextView) inflate.findViewById(R.id.timeWidget_Hour);
        this.b[1] = (TextView) inflate.findViewById(R.id.timeWidget_Min);
        this.b[2] = (TextView) inflate.findViewById(R.id.timeWidget_Sec);
        for (int i = 0; i < this.c.length; i++) {
            this.c[i] = new a(this.b[i]);
        }
    }

    public TimeWidget(Context context) {
        this(context, null);
    }

    private void a() {
        long currentTimeMillis = (this.a - System.currentTimeMillis()) / 1000;
        long j = currentTimeMillis / 3600;
        long j2 = (currentTimeMillis / 60) % 60;
        currentTimeMillis %= 60;
        this.c[0].a(j);
        this.c[1].a(j2);
        this.c[2].a(currentTimeMillis);
    }

    protected void onAttachedToWindow() {
        this.d = this.e;
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        this.d = null;
        super.onDetachedFromWindow();
    }

    private long a(String str) {
        long j = 0;
        try {
            j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return j;
    }

    public void setEndTime(String str) {
        if (this.d != null) {
            this.d.removeMessages(0);
        }
        this.a = a(str);
        a();
    }
}
