package com.qq.reader.module.audio.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.e;
import com.qq.reader.common.utils.e.a;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.comic.activity.NativeComicStoreBaseActivity;
import com.qq.reader.view.AudioFloatingWindowView;
import com.tencent.feedback.proguard.R;

public class NativeAudioZoneActivity extends NativeComicStoreBaseActivity implements OnClickListener, a {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e.a().a(this);
        onAudioFloatingStateChange(2, e.a().c(), e.a().b(), e.a().d());
    }

    public void onDestroy() {
        super.onDestroy();
        e.a().b(this);
    }

    protected String f() {
        return getString(R.string.audio_zone);
    }

    protected String g() {
        return "103536";
    }

    public void onAudioFloatingStateChange(int i, long j, boolean z, String str) {
        AudioFloatingWindowView audioFloatingWindowView = (AudioFloatingWindowView) findViewById(R.id.img_audio_floating);
        if (audioFloatingWindowView != null) {
            ao.a(2, this, audioFloatingWindowView, j, z, str);
        }
    }

    protected void h() {
        super.h();
        ImageButton imageButton = (ImageButton) findViewById(R.id.profile_header_right_image);
        imageButton.setVisibility(0);
        imageButton.setImageResource(R.drawable.titlebar_icon_search_selector);
        imageButton.setBackgroundDrawable(null);
        imageButton.setOnClickListener(new c(this) {
            final /* synthetic */ NativeAudioZoneActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                o.a(this.a, "", "");
                i.a("event_B305", null, this.a.getContext());
            }
        });
        i.a("event_B301", null, getContext());
    }

    protected void onResume() {
        super.onResume();
        i.a("event_B299", null, getContext());
    }
}
