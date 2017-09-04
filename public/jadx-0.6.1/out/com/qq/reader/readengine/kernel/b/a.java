package com.qq.reader.readengine.kernel.b;

import com.qq.reader.ReaderApplication;
import com.qq.reader.readengine.kernel.e;
import com.tencent.feedback.proguard.R;

/* compiled from: QTextAdvLine */
public class a extends e {
    private com.qq.reader.cservice.adv.a a;

    public a(String str) {
        super(str);
        a(104);
        a(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.readerpage_chapter_adv_line_height) + 1.0f);
        c(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.readerpage_chapter_adv_line_margintop));
    }

    public com.qq.reader.cservice.adv.a k() {
        return this.a;
    }

    public void a(com.qq.reader.cservice.adv.a aVar) {
        this.a = aVar;
    }
}
