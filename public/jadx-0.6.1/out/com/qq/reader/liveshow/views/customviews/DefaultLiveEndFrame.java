package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.utils.p;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a;

public class DefaultLiveEndFrame extends BaseLiveEndFrame {
    private TextView d = ((TextView) this.f.findViewById(e.live_end_page_host_name));
    private ImageView e = ((ImageView) this.f.findViewById(e.live_end_page_host_head_icon));
    private View f;

    public DefaultLiveEndFrame(final a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.f = LayoutInflater.from(activity).inflate(g.live_end_page_host_dialog, viewGroup, false);
        ((TextView) this.f.findViewById(e.btn_cancel)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DefaultLiveEndFrame b;

            public void onClick(View view) {
                aVar.a(7, null);
            }
        });
    }

    public void b() {
        super.b();
        this.c.addView(this.f);
        p.a(this.b, this.e, getAvatarUrl(), true);
        this.d.setText(getName());
    }
}
