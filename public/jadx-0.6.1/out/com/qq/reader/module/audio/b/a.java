package com.qq.reader.module.audio.b;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

/* compiled from: AudioBookItem */
public class a extends g {
    private long a;
    private int b;
    private String c;
    private String d;
    private int h = -1;

    public long a() {
        return this.a;
    }

    public String b() {
        return ReaderApplication.getApplicationContext().getString(R.string.audio_listened_time, new Object[]{k.f((long) (this.b * 1000))});
    }

    public String c() {
        return this.c;
    }

    public long d() {
        if (super.d() > 0) {
            return super.d();
        }
        return m();
    }

    public int e() {
        if (this.h > 0) {
            return this.h;
        }
        return super.e();
    }

    public String f() {
        return ao.a(d(), true, 180);
    }

    public String g() {
        return this.d;
    }

    public String h() {
        if (TextUtils.isEmpty(super.h())) {
            return q();
        }
        return super.h();
    }

    public void parseData(JSONObject jSONObject) {
        super.parseData(jSONObject);
        this.a = jSONObject.optLong("listenCount");
        this.b = jSONObject.optInt("listenTimeIndex");
        this.c = jSONObject.optString("qurl");
        this.d = jSONObject.optString("image");
        this.h = jSONObject.optInt("chapterCount");
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        o.b(aVar.getFromActivity(), String.valueOf(d()), this.mStatParamString, null, null);
    }
}
