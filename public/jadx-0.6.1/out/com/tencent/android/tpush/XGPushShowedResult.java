package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
public class XGPushShowedResult implements XGIResult {
    public static final int NOTIFICATION_ACTION_ACTIVITY = 1;
    public static final int NOTIFICATION_ACTION_INTENT = 3;
    public static final int NOTIFICATION_ACTION_PACKAGE = 4;
    public static final int NOTIFICATION_ACTION_URL = 2;
    long a = 0;
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    int f = 0;
    int g = 1;

    public int getNotifactionId() {
        return this.f;
    }

    public long getMsgId() {
        return this.a;
    }

    public String getTitle() {
        return this.b;
    }

    public String getContent() {
        return this.c;
    }

    public String getCustomContent() {
        return this.d;
    }

    public String getActivity() {
        return this.e;
    }

    public int getNotificationActionType() {
        return this.g;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("XGPushShowedResult [msgId=").append(this.a).append(", title=").append(this.b).append(", content=").append(this.c).append(", customContent=").append(this.d).append(", activity=").append(this.e).append(", notificationActionType").append(this.g).append("]");
        return stringBuilder.toString();
    }

    public void parseIntent(Intent intent) {
        this.a = intent.getLongExtra(MessageKey.MSG_ID, -1);
        this.e = intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME);
        this.b = Rijndael.decrypt(intent.getStringExtra("title"));
        this.c = Rijndael.decrypt(intent.getStringExtra(MessageKey.MSG_CONTENT));
        this.g = intent.getIntExtra(Constants.FLAG_NOTIFICATION_ACTION_TYPE, 1);
        this.d = Rijndael.decrypt(intent.getStringExtra("custom_content"));
        this.f = intent.getIntExtra(MessageKey.NOTIFACTION_ID, 0);
    }
}
