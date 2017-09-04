package com.qq.reader.common.receiver;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.common.conn.socket.PushMessageReceiver;
import com.qq.reader.common.conn.socket.QRPushMessage;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.push.pushAction.k;
import com.qq.reader.common.push.pushAction.n;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class QRPushReceiver extends PushMessageReceiver {
    public void a(Context context, QRPushMessage qRPushMessage) {
        c.a("QRPush", "onMessageReceived is called. " + qRPushMessage.toString());
        if (qRPushMessage != null) {
            switch (qRPushMessage.getMsgType()) {
                case 1000:
                    new n(context).a(null);
                    return;
                case 1001:
                    String content = qRPushMessage.getContent();
                    if (!TextUtils.isEmpty(content)) {
                        try {
                            JSONObject jSONObject = new JSONObject(content.substring(content.indexOf("{")));
                            JSONArray optJSONArray = jSONObject.optJSONArray("MsgList");
                            if (optJSONArray != null && optJSONArray.length() > 0) {
                                List arrayList = new ArrayList();
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                    optJSONObject.optInt("FormatType");
                                    arrayList.add(optJSONObject);
                                }
                                new k(context, arrayList).a(jSONObject);
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
