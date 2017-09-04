package com.qq.reader.module.question.card;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;

public class AudioComCardOfQuestionQuiz extends AudioComBaseCardDisablePlay {
    public AudioComCardOfQuestionQuiz(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        super.attachView();
        if (this.mShowIndexOnPage == 2) {
            ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(8);
        } else {
            ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(0);
        }
    }

    protected void doClickAction(boolean z) {
        super.doClickAction(z);
        i.a("event_D188", null, ReaderApplication.getApplicationImp());
    }
}
