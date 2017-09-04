package com.qq.reader.liveshow.views.roomdialog;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.i;
import com.qq.reader.liveshow.views.customviews.BaseDialog;

public class LiveEnterRoomDialog extends BaseDialog {
    private ViewGroup a;
    private View b;
    private TextView c;
    private Activity d;
    private com.qq.reader.liveshow.views.roomdialog.a.a.a e;

    public interface a {
        void a(int i, Object obj);

        void a(String str);
    }

    public LiveEnterRoomDialog(Activity activity) {
        super(activity, i.enterDialog);
        this.d = activity;
        c();
    }

    private void c() {
        setContentView(g.live_room_dialog_content);
        this.a = (ViewGroup) findViewById(e.room_dialog_content);
        this.b = findViewById(e.login_loading_layout);
        this.c = (TextView) findViewById(e.login_loading_msg);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void a(String str) {
        if (str != null) {
            this.c.setText(str);
            if (this.b.getVisibility() != 0) {
                this.b.setVisibility(0);
            }
        } else if (this.b.getVisibility() != 8) {
            this.b.setVisibility(8);
        }
    }

    public ViewGroup a() {
        return this.a;
    }

    public void dismiss() {
        super.dismiss();
        if (this.e != null) {
            this.e.c();
        }
    }

    public void a(com.qq.reader.liveshow.views.roomdialog.a.a.a aVar) {
        if (aVar != null && aVar != this.e) {
            this.a.removeAllViews();
            if (this.e != null) {
                this.e.c();
            }
            this.e = aVar;
            this.e.b();
        }
    }

    public com.qq.reader.liveshow.views.roomdialog.a.a.a b() {
        return this.e;
    }
}
