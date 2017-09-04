package com.qq.reader.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import com.tencent.feedback.proguard.R;

@Deprecated
/* compiled from: ReadModeDialog */
public class y extends BaseDialog {
    private Button a;
    private Button b;
    private ImageView c;
    private a d;

    /* compiled from: ReadModeDialog */
    public interface a {
        void L();

        void M();
    }

    public y(Activity activity) {
        if (this.f == null) {
            a(activity, null, R.layout.readmodedlg, true, false, true);
        }
        this.a = (Button) this.f.findViewById(R.id.mode_auto);
        this.a.setOnClickListener(new 1(this));
        this.b = (Button) this.f.findViewById(R.id.mode_tts);
        this.b.setOnClickListener(new 2(this));
        this.c = (ImageView) this.f.findViewById(R.id.mode_tts_new);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void f_() {
        super.f_();
    }
}
