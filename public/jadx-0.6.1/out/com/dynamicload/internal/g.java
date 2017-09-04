package com.dynamicload.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.dynamicload.Lib.IDLPluginActivity;
import com.dynamicload.Lib.IDLProxyActivity;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/* compiled from: DLProxyActivitySingleInsImpl */
public class g extends f {
    private static HashMap<String, IDLPluginActivity> j = new HashMap();
    private boolean k = false;

    public g(Context context) {
        super(context);
    }

    protected void a(Bundle bundle, Intent intent) {
        if (this.k) {
            this.a.peformOnNewIntent(intent);
        } else {
            super.a(bundle, intent);
        }
    }

    protected void b(Intent intent) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (j.containsKey(this.d)) {
            this.a = (IDLPluginActivity) j.get(this.d);
            this.a.attach((IDLProxyActivity) this.c, this.f, null);
            this.k = true;
            return;
        }
        super.b(intent);
    }

    public void a(Intent intent) {
        this.a.disAttach();
        super.a(intent, null);
    }

    protected void k() {
        j.put(this.d, this.a);
    }

    protected void g() {
        j.remove(this.d);
    }
}
