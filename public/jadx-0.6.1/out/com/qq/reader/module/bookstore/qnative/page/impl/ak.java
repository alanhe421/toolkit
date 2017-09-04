package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforLabel;

/* compiled from: NativeServerPageOfLabel */
public class ak extends ag {
    public ak(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        if (!"comicTag".equalsIgnoreCase(bundle.getString("KEY_ACTION"))) {
            stringBuffer.append("action=tagV2");
        }
        return cVar.b(stringBuffer.toString());
    }

    public Class c() {
        return NativePageFragmentforLabel.class;
    }
}
