package com.qq.reader.liveshow.utils;

import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.im.a.a.a;
import com.qq.reader.liveshow.model.im.message.SenderProfile;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessagePriority;
import com.tencent.TIMTextElem;
import com.tencent.TIMValueCallBack;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class IMHelper {
    private static TIMConversation a;

    public static final class NotEnterIMException extends Exception {
        public NotEnterIMException(String str) {
            super(str);
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("IM roomId不能为空");
        }
        a = TIMManager.getInstance().getConversation(TIMConversationType.Group, str);
    }

    public static void a() {
        a = null;
    }

    public static boolean b() {
        return a != null;
    }

    public static a a(TIMElem tIMElem) throws UnsupportedEncodingException, JSONException {
        a aVar = new a();
        if (tIMElem != null) {
            String str = null;
            if (tIMElem.getType() == TIMElemType.Custom) {
                str = new String(((TIMCustomElem) tIMElem).getData(), "UTF-8");
            } else if (tIMElem.getType() == TIMElemType.Text) {
                str = ((TIMTextElem) tIMElem).getText();
            }
            if (!TextUtils.isEmpty(str)) {
                SxbLog.b("IMHelper", "cumstom msg  " + str);
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("userAction");
                String optString = jSONObject.optString("actionParam");
                SenderProfile b = b(jSONObject.optString("userProfile"));
                aVar.a(optInt);
                aVar.a(optString);
                aVar.a(b);
            }
        }
        return aVar;
    }

    private static SenderProfile b(String str) {
        SenderProfile senderProfile = new SenderProfile();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                senderProfile.setId(jSONObject.optString(SenderProfile.KEY_UID));
                senderProfile.setNickName(jSONObject.optString(SenderProfile.KEY_NICKNAME));
                senderProfile.setAvatar(jSONObject.optString(SenderProfile.KEY_HEADICION));
                senderProfile.setAuthorId(jSONObject.optLong(SenderProfile.KEY_AUTHORID));
                senderProfile.setPermissionsLevel(jSONObject.optInt(SenderProfile.KEY_USERLEVEL, 4));
                senderProfile.setVipLevel(jSONObject.optInt(SenderProfile.KEY_VIPLEVEL));
            }
        } catch (Exception e) {
            SxbLog.e("IMHelper", e.getMessage());
        }
        return senderProfile;
    }

    private static SenderProfile c() {
        SenderProfile senderProfile = new SenderProfile();
        senderProfile.setId(c.a().b());
        senderProfile.setNickName(c.a().d());
        senderProfile.setAvatar(c.a().e());
        senderProfile.setAuthorId(c.a().m());
        senderProfile.setPermissionsLevel(c.a().l());
        senderProfile.setVipLevel(c.a().n());
        return senderProfile;
    }

    public static a a(int i, String str) {
        a aVar = new a();
        aVar.a(i);
        aVar.a(str);
        aVar.a(c());
        return aVar;
    }

    public static a a(int i, String str, TIMMessagePriority tIMMessagePriority, TIMValueCallBack<TIMMessage> tIMValueCallBack) throws NotEnterIMException {
        return a(i, str, TIMElemType.Custom, tIMMessagePriority, tIMValueCallBack);
    }

    public static a a(int i, String str, TIMElemType tIMElemType, TIMMessagePriority tIMMessagePriority, TIMValueCallBack<TIMMessage> tIMValueCallBack) throws NotEnterIMException {
        a a = a(i, str);
        try {
            String d = a.d();
            SxbLog.b("IMHelper", "send cmd : " + i + DLConstants.DEPENDENCY_PACKAGE_DIV + d);
            TIMMessage tIMMessage = new TIMMessage();
            TIMElem tIMCustomElem;
            if (tIMElemType == TIMElemType.Custom) {
                tIMCustomElem = new TIMCustomElem();
                tIMCustomElem.setData(d.getBytes());
                tIMMessage.addElement(tIMCustomElem);
            } else if (tIMElemType == TIMElemType.Text) {
                tIMCustomElem = new TIMTextElem();
                tIMCustomElem.setText(d);
                tIMMessage.addElement(tIMCustomElem);
            } else {
                throw new RuntimeException("不支持发送该类型的IM消息");
            }
            tIMMessage.setPriority(tIMMessagePriority);
            if (tIMValueCallBack == null) {
                throw new RuntimeException("发送消息必须设置call back ，否则sdk不发送！！！！！！");
            } else if (a != null) {
                a.sendMessage(tIMMessage, tIMValueCallBack);
                return a;
            } else {
                throw new NotEnterIMException("进入IM房间以后才能发送消息");
            }
        } catch (JSONException e) {
            SxbLog.e("IMHelper", e.getMessage());
            return null;
        }
    }
}
