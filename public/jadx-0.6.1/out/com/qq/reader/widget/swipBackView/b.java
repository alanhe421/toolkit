package com.qq.reader.widget.swipBackView;

import com.qq.reader.common.monitor.debug.c;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* compiled from: TranslucentListener */
public class b implements InvocationHandler {
    private WeakReference<c> a;

    public void a(c cVar) {
        this.a = new WeakReference(cVar);
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (this.a != null) {
            c cVar = (c) this.a.get();
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Boolean)) {
                boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
                c.e("TranslucentListener", "drawComplete->" + booleanValue);
                if (cVar != null) {
                    cVar.a(booleanValue);
                }
            }
        }
        return null;
    }
}
