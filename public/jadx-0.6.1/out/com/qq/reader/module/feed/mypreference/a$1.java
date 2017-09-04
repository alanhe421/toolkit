package com.qq.reader.module.feed.mypreference;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.feed.mypreference.a.a;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import org.json.JSONObject;

/* compiled from: GeneListHandler */
class a$1 implements c {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    a$1(a aVar, a aVar2, String str) {
        this.c = aVar;
        this.a = aVar2;
        this.b = str;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code", -1);
            String optString = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            if (optInt != 0) {
                if (this.a != null) {
                    this.a.a(false);
                }
            } else if (!this.b.equals(optString)) {
                a.a(this.c, str);
                b.a();
                if (this.a != null) {
                    this.a.a(true);
                }
            } else if (this.a != null) {
                this.a.a(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        exception.printStackTrace();
        if (this.a != null) {
            this.a.a(false);
        }
    }
}
