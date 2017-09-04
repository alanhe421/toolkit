package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;

/* compiled from: NativeServerBookClubPageOfReward */
public class y extends v {
    public y(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).b("nativepage/comment/list?sort=1&subtype=1&ctype=" + bundle.getInt("CTYPE"));
    }
}
