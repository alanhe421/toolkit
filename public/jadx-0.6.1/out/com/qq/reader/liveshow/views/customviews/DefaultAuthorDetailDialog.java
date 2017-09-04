package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.utils.p;

public class DefaultAuthorDetailDialog extends BaseAuthorDetailDialog {
    private View b = findViewById(e.host_detail_dialog_report_tv);
    private View c = findViewById(e.host_detail_dialog_follow_tv);
    private TextView d = ((TextView) findViewById(e.host_detail_dialog_shut_up));
    private ImageView e = ((ImageView) findViewById(e.host_info_avatar));
    private TextView f = ((TextView) findViewById(e.host_info_name));
    private View g = findViewById(e.host_detail_dialog_kick_out);

    public DefaultAuthorDetailDialog(Activity activity, int i) {
        super(activity, i);
        setContentView(g.host_info_layout);
    }

    public void show() {
        this.f.setText(getName());
        p.a(getContext(), this.e, getAvatarUrl(), true);
        super.show();
    }
}
