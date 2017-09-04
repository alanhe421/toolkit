package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.utils.p;

public class DefaultMemberInfoDialog extends BaseMemberInfoDialog {
    private View a = findViewById(e.member_info_report);
    private View b = findViewById(e.member_info_shut_up);
    private View c = findViewById(e.member_info_kick_out);
    private TextView d = ((TextView) findViewById(e.member_info_name));
    private ImageView e = ((ImageView) findViewById(e.member_info_avatar));

    public DefaultMemberInfoDialog(Activity activity, int i) {
        super(activity, i);
        setContentView(g.member_info_dialog);
    }

    public void show() {
        this.d.setText(getName());
        p.a(getContext(), this.e, getAvatarUrl(), false);
        super.show();
    }
}
