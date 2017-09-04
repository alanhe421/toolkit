package com.qq.reader.module.qmessage.data;

import com.qq.reader.module.qmessage.data.impl.MessageAudioInterActiveCard;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import com.qq.reader.module.qmessage.data.impl.MessageInterActiveCard;
import com.qq.reader.module.qmessage.data.impl.MessageNotifyCard;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MessageCardBuilder */
public class a {
    public static synchronized List<MessageBaseCard> a(JSONArray jSONArray, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        List<MessageBaseCard> arrayList;
        synchronized (a.class) {
            arrayList = new ArrayList(jSONArray.length());
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    MessageBaseCard a = a(jSONArray.getJSONObject(i), aVar);
                    if (a != null) {
                        arrayList.add(a);
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static synchronized MessageBaseCard a(JSONObject jSONObject, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        MessageBaseCard messageNotifyCard;
        synchronized (a.class) {
            int optInt = jSONObject.optInt("uitype");
            int optInt2 = jSONObject.optInt("type");
            int optInt3 = jSONObject.optInt("subtype");
            if (optInt == 0) {
                optInt = optInt2;
            }
            switch (optInt) {
                case 1:
                    messageNotifyCard = new MessageNotifyCard(null);
                    break;
                case 2:
                    messageNotifyCard = new MessageInterActiveCard(null);
                    break;
                case 3:
                    messageNotifyCard = new MessageAudioInterActiveCard(null);
                    break;
                default:
                    messageNotifyCard = null;
                    break;
            }
            if (messageNotifyCard != null) {
                messageNotifyCard.setEventListener(aVar);
                messageNotifyCard.setMessageType(optInt2);
                messageNotifyCard.setMessageSubType(optInt3);
                messageNotifyCard.fillData(jSONObject);
            } else {
                messageNotifyCard = null;
            }
        }
        return messageNotifyCard;
    }
}
