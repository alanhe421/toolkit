package com.qq.reader.module.feed.mypreference;

import com.qq.reader.module.feed.mypreference.CustomScrollView.a;
import com.tencent.feedback.proguard.R;

class MyReadingGeneActivity$3 implements a {
    final /* synthetic */ MyReadingGeneActivity a;

    MyReadingGeneActivity$3(MyReadingGeneActivity myReadingGeneActivity) {
        this.a = myReadingGeneActivity;
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i2 > 32) {
            MyReadingGeneActivity.c(this.a).setBackgroundResource(R.drawable.titler_bg);
        } else {
            MyReadingGeneActivity.c(this.a).setBackgroundDrawable(null);
        }
    }
}
