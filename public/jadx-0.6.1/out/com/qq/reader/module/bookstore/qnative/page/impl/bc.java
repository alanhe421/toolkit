package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleCommentCard;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleReplyCard;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.d.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeUserCenterMoreCommentPage */
public class bc extends af {
    private static d a;

    public bc(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("");
        String string = this.f.getString("KEY_ACTIONTAG");
        String string2 = this.f.getString("userId");
        stringBuffer.append("nativepage/user/comment?platform=android");
        if ("1".equals(string)) {
            stringBuffer.append("&userId=" + string2 + "&showType=" + 1);
        } else if ("0".equals(string)) {
            stringBuffer.append("&userId=" + string2 + "&showType=" + 0);
        }
        return cVar.a(e.a, stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        try {
            this.p = jSONObject.toString();
            this.o = jSONObject.optLong("pagestamp");
            JSONArray optJSONArray = jSONObject.optJSONArray("replyList");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("commentList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    a singleReplyCard = new SingleReplyCard(this, "replyCard");
                    singleReplyCard.fillData(optJSONObject);
                    singleReplyCard.setEventListener(l());
                    this.l.put("i", singleReplyCard);
                    this.k.add(singleReplyCard);
                }
            }
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                while (i < optJSONArray2.length()) {
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                    a singleCommentCard = new SingleCommentCard(this, "commentCard");
                    singleCommentCard.fillData(optJSONObject2);
                    singleCommentCard.setEventListener(l());
                    this.l.put(singleCommentCard.getCardId(), singleCommentCard);
                    this.k.add(singleCommentCard);
                    i++;
                }
            }
            x();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    private void x() throws JSONException {
        if (a == null) {
            JSONObject jSONObject = new JSONObject(y());
            a = new d();
            a.a(jSONObject);
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = a.g();
            for (int i = 0; i < g.size(); i++) {
                b bVar = (b) g.get(i);
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
            }
        }
        this.n = a;
    }

    private String y() {
        InputStream open;
        Exception e;
        Throwable th;
        try {
            open = ReaderApplication.getApplicationImp().getResources().getAssets().open("all_user_center_comment_action_tags.txt");
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                String str = new String(bArr, "UTF-8");
                if (open == null) {
                    return str;
                }
                try {
                    open.close();
                    return str;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return str;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            open = null;
            e.printStackTrace();
            if (open != null) {
                open.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
        if (i2 == -1) {
            Bundle extras = intent.getExtras();
            String string = extras.getString("operation_comment_action");
            CharSequence string2 = extras.getString("operation_comment_id");
            if (!TextUtils.isEmpty(string2)) {
                a aVar = (a) this.l.get(string2);
                if (aVar != null && (aVar instanceof SingleCommentCard)) {
                    if (string.equalsIgnoreCase("operation_comment_action_del")) {
                        this.k.remove(aVar);
                        this.l.remove(aVar);
                        handler.sendMessage(handler.obtainMessage(7000002));
                    } else if (string.equalsIgnoreCase("operation_comment_action_edit")) {
                        SingleCommentCard singleCommentCard = (SingleCommentCard) aVar;
                        int i3 = extras.getInt("operation_comment_action_edit_agree");
                        if (i3 > -1) {
                            singleCommentCard.getItem().m = i3;
                        }
                        i3 = extras.getInt("operation_comment_action_edit_agreestatus");
                        if (i3 > -10) {
                            singleCommentCard.getItem().n = i3;
                        }
                        int i4 = extras.getInt("operation_comment_action_edit_reply");
                        if (i4 > -1) {
                            singleCommentCard.getItem().l = i4;
                        }
                        handler.sendMessage(handler.obtainMessage(7000002));
                    }
                }
            }
        }
    }
}
