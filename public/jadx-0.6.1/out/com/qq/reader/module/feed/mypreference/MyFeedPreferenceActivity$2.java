package com.qq.reader.module.feed.mypreference;

class MyFeedPreferenceActivity$2 implements MyFeedPreferenceActivity$c {
    final /* synthetic */ MyFeedPreferenceActivity a;

    MyFeedPreferenceActivity$2(MyFeedPreferenceActivity myFeedPreferenceActivity) {
        this.a = myFeedPreferenceActivity;
    }

    public void a(int i) {
        if (i > 0) {
            MyFeedPreferenceActivity.e(this.a).setEnabled(true);
        } else {
            MyFeedPreferenceActivity.e(this.a).setEnabled(false);
        }
    }
}
