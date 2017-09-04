package com.qq.reader.module.bookstore.qnative.page;

import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.storage.task.BaseNativeDataTask;
import com.qq.reader.module.bookstore.qnative.storage.task.LoadNativePageDataTask;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeBaseServerPage */
public abstract class c extends b {
    public void a(b bVar) {
        super.a(bVar);
        this.g = ((c) bVar).g;
    }

    public boolean b() {
        if (h() > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public void a(String str) {
    }

    public String a(List<a> list) {
        return "";
    }

    public BaseNativeDataTask f() {
        return new LoadNativePageDataTask(ReaderApplication.getApplicationImp().getApplicationContext(), this);
    }

    public void b(JSONObject jSONObject) {
        this.k.clear();
        this.l.clear();
        this.p = jSONObject.toString();
        this.q = jSONObject;
    }

    public void c(String str) {
        this.p = str;
    }

    public int e() {
        if (this.p == null || this.p.length() < 0) {
            return -1;
        }
        return this.p.hashCode();
    }

    public void serialize(OutputStream outputStream) {
        if (this.p != null) {
            try {
                outputStream.write(this.p.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean a() {
        return true;
    }
}
