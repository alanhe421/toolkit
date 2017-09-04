package com.qq.reader.module.findhome.c;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.module.findhome.base.b;
import com.tencent.feedback.proguard.R;

/* compiled from: FindHomeExpandAudioBookVH */
public class a extends b<com.qq.reader.module.findhome.a.a> {
    private TextView q;

    public a(View view) {
        super(view);
        this.q = (TextView) view.findViewById(R.id.findhome_playtimes);
    }

    public void a(com.qq.reader.module.findhome.a.a aVar) {
        super.a(aVar);
        if (TextUtils.isEmpty(aVar.i())) {
            this.q.setVisibility(8);
            return;
        }
        this.q.setVisibility(0);
        this.q.setText(aVar.i());
    }
}
