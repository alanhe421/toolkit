package com.qq.reader.module.bookstore.qnative.page;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.card.b;
import com.qq.reader.module.bookstore.qnative.storage.task.BaseNativeDataTask;
import com.qq.reader.module.bookstore.qnative.storage.task.LoadNativeCardDataTask;
import java.io.OutputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeBaseLocalPage */
public abstract class a extends b {
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public int i_() {
        return this.i.hashCode();
    }

    public b e() {
        return this;
    }

    public boolean b() {
        List<com.qq.reader.module.bookstore.qnative.card.a> m = m();
        if (m == null) {
            return true;
        }
        for (com.qq.reader.module.bookstore.qnative.card.a isExpired : m) {
            if (isExpired.isExpired()) {
                return true;
            }
        }
        return false;
    }

    public void a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            Context applicationImp = ReaderApplication.getApplicationImp();
            String[] stringArray = applicationImp.getResources().getStringArray(applicationImp.getResources().getIdentifier(applicationImp.getPackageName() + ":array/" + this.i, null, null));
            if (jSONArray.length() != stringArray.length) {
                f.b("Native", "config  count not match");
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                com.qq.reader.module.bookstore.qnative.card.a a = b.a().a(this, n(), stringArray[i], jSONObject.optString("type"));
                a.build(jSONObject);
                this.k.add(a);
                this.l.put(a.getCardId(), a);
            }
        } catch (Exception e) {
            f.a("Native", "config  JSONException error!", e);
        } catch (Exception e2) {
            f.a("Native", "page build error!", e2);
        }
    }

    public BaseNativeDataTask f() {
        return new LoadNativeCardDataTask(ReaderApplication.getApplicationImp().getApplicationContext(), this);
    }

    public void serialize(OutputStream outputStream) {
    }

    public boolean a() {
        return true;
    }
}
