package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;

/* compiled from: NativeServerBookClubPageOfHot */
public class w extends v {
    public w(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).b("nativepage/comment/list?sort=2&subtype=0&ctype=" + bundle.getInt("CTYPE"));
    }
}
