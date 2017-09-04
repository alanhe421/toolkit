package com.qq.reader.module.readpage;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.common.monitor.f;
import com.qq.reader.readengine.kernel.e;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderPageLayers */
public class u implements Callback {
    protected List<s> a = new ArrayList();
    private Handler b = new WeakReferenceHandler(this);

    public Handler a() {
        return this.b;
    }

    public void a(s sVar) {
        this.a.add(sVar);
    }

    public List<s> b() {
        return this.a;
    }

    public void a(Boolean bool) {
        for (s a : this.a) {
            a.a(bool.booleanValue());
        }
    }

    public void a(boolean z) {
        for (s b : this.a) {
            b.b(z);
        }
    }

    protected void a(Canvas canvas, e eVar) {
        f.b("TAG", "drawLayer");
        for (s a : this.a) {
            a.a(canvas, eVar);
        }
    }

    public boolean handleMessage(Message message) {
        for (s a : this.a) {
            a.a(message);
        }
        return false;
    }
}
