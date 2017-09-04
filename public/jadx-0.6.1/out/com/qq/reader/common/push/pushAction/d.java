package com.qq.reader.common.push.pushAction;

import android.content.Context;
import android.content.Intent;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.cservice.bookfollow.b;
import com.qq.reader.cservice.bookfollow.b.a;
import com.qq.reader.cservice.onlineread.OnlineTag;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: ChapterUpdateAction */
public class d extends i implements a {
    public d(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (com.qq.reader.common.push.a.a.equals(this.b) || com.qq.reader.common.push.a.b.equals(this.b)) {
            com.qq.reader.appconfig.a.d.f(a(), System.currentTimeMillis());
            b bVar = new b(a());
            bVar.a((a) this);
            bVar.a(jSONObject, null);
        }
    }

    public void onQueryNewResult(int i, Object obj) {
        if (i == 8007) {
            OnlineTag[] onlineTagArr = (OnlineTag[]) obj;
            if (onlineTagArr != null && onlineTagArr.length != 0) {
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : onlineTagArr) {
                    if (obj2 != null) {
                        arrayList.add(obj2);
                    }
                }
                if (arrayList.size() <= 0) {
                    return;
                }
                Intent intent;
                if (BookShelfFragment.isInShelf) {
                    intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.cy);
                    a().sendBroadcast(intent);
                } else if (com.qq.reader.appconfig.a.d.aC(a())) {
                    intent = new Intent();
                    intent.setAction("com.qq.reader.notification");
                    intent.putParcelableArrayListExtra("onlinetag", arrayList);
                    a().sendBroadcast(intent);
                }
            }
        }
    }
}
