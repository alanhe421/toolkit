package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;

/* compiled from: NativeServerBookClubPageOfMain */
public class x extends v {
    public x(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).b("nativepage/comment/index?ctype=" + bundle.getInt("CTYPE"));
    }
}
