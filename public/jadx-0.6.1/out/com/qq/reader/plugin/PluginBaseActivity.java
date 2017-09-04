package com.qq.reader.plugin;

import android.widget.ImageView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public abstract class PluginBaseActivity extends ReaderBaseActivity {
    protected ImageView q;
    protected final int r = ErrorCode.INFO_CAN_NOT_LOAD_TBS;

    public abstract void a();

    public void h() {
        this.q = (ImageView) findViewById(R.id.profile_header_left_back);
        this.q.setOnClickListener(new 1(this));
    }
}
