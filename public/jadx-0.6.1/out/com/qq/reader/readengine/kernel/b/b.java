package com.qq.reader.readengine.kernel.b;

import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.readengine.kernel.e;
import com.tencent.feedback.proguard.R;

/* compiled from: QTextChapterCommentLine */
public class b extends e {
    public static final int a = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.read_endpage_comment_layer_discusscount_height);
    public static final int b = (a + ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.read_endpage_comment_layer_popup_height));
    public static final int c = (a + ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.read_endpage_comment_layer_question_height));
    public static final int d = (a + ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.read_endpage_comment_layer_author_height));
    private OnlineChapter e;
    private boolean f;

    public b(String str) {
        super(str);
        a(105);
        c(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.readerpage_chapter_comment_line_margintop));
    }

    public OnlineChapter k() {
        return this.e;
    }

    public void a(OnlineChapter onlineChapter) {
        this.e = onlineChapter;
    }

    public boolean l() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }
}
