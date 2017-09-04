package com.tencent.android.tpush;

import android.content.Intent;
import com.tencent.android.tpush.common.MessageKey;
import java.io.Serializable;

/* compiled from: ProGuard */
public class XGPushTextMessage implements Serializable {
    private static final long serialVersionUID = -1854661081378847806L;
    String content = "";
    String customContent = "";
    private Intent simpleIntent = null;
    String title = "";

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getCustomContent() {
        return this.customContent;
    }

    void a(Intent intent) {
        this.simpleIntent = intent;
        if (intent != null) {
            this.simpleIntent.removeExtra(MessageKey.MSG_CONTENT);
        }
    }

    Intent a() {
        return this.simpleIntent;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("XGPushShowedResult [title=").append(this.title).append(", content=").append(this.content).append(", customContent=").append(this.customContent).append("]");
        return stringBuilder.toString();
    }
}
