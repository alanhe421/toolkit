package com.qq.reader.view;

import android.app.Activity;
import android.widget.TextView;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

/* compiled from: LuckyMoneyDialog */
public class o extends BaseDialog {
    String a;
    String b;
    String c;
    private String d;
    private String e;
    private String i;

    public o(Activity activity, JSONObject jSONObject) {
        this.d = jSONObject.optString("rid");
        this.e = jSONObject.optString("title");
        this.i = jSONObject.optString(MessageKey.MSG_CONTENT);
        this.a = jSONObject.optString("sharePic");
        this.b = jSONObject.optString("shareTitle");
        this.c = jSONObject.optString("shareDesc");
        a(activity, null, R.layout.luckymoneydialog, 0, false);
        TextView textView = (TextView) a((int) R.id.luckymoney_msg);
        ((TextView) a((int) R.id.luckymoney_title)).setText(this.e);
        textView.setText(this.i);
        a((int) R.id.luckymoney_share).setOnClickListener(new 1(this));
    }
}
