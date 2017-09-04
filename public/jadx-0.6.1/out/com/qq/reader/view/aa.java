package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: ReaderAlertDailog */
public class aa extends BaseDialog implements DialogInterface {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private FrameLayout e;
    private View i;

    public aa(Activity activity) {
        if (this.f == null) {
            a(activity, null, R.layout.readeralertdialog, 1, true);
        }
        this.a = (TextView) a(R.id.readeralert_possitive);
        this.b = (TextView) a(R.id.readeralert_negative);
        this.c = (TextView) a(R.id.readeralert_message);
        this.d = (TextView) a(R.id.readeralert_title);
        this.e = (FrameLayout) a(R.id.readeralert_content);
        this.i = a(R.id.readeralert_title_divider);
    }

    public void a(int i, final OnClickListener onClickListener) {
        this.a.setVisibility(0);
        this.a.setText(i);
        this.a.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ aa b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(this.b, -1);
                }
            }
        });
    }

    public void b(int i, final OnClickListener onClickListener) {
        this.b.setVisibility(0);
        this.b.setText(i);
        this.b.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ aa b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(this.b, -2);
                }
            }
        });
    }

    public Window g() {
        return this.f.getWindow();
    }

    public void a(boolean z) {
        this.i.setVisibility(z ? 0 : 8);
    }

    public void a(View view) {
        this.e.addView(view);
    }

    public void b(int i) {
        this.d.setText(i);
    }

    public FrameLayout h() {
        return this.e;
    }
}
