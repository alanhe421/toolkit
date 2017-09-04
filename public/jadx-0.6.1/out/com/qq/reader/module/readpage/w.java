package com.qq.reader.module.readpage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.qq.reader.common.c.a;
import com.qq.reader.module.readpage.ReaderPageSwither.f;

/* compiled from: ReaderViewFactory */
public class w implements f {
    private Context a;
    private Activity b;

    public w(Context context, Activity activity) {
        this.a = context;
        this.b = activity;
    }

    public View a(ReaderPageSwither readerPageSwither) {
        View readerTextPageView = new ReaderTextPageView(this.a, this.b, readerPageSwither.getmPageContext(), readerPageSwither, readerPageSwither);
        int i = a.bW + 14;
        readerTextPageView.setPadding(i, i, i, a.bX);
        return readerTextPageView;
    }
}
