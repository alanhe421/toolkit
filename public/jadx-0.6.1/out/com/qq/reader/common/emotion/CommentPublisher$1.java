package com.qq.reader.common.emotion;

import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.common.emotion.a.a;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.PostTopicTask;

class CommentPublisher$1 extends ReaderShortTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ Intent val$data;

    CommentPublisher$1(a aVar, Intent intent) {
        this.this$0 = aVar;
        this.val$data = intent;
    }

    public String getTaskName() {
        return "addcommentthread";
    }

    public void run() {
        int i;
        long longExtra = this.val$data.getLongExtra("URL_BUILD_PERE_BOOK_ID", 0);
        String stringExtra = this.val$data.getStringExtra("KEY_TASK_KEY");
        int intExtra = this.val$data.getIntExtra("CTYPE", 0);
        if (longExtra <= 3) {
            i = 4;
        } else {
            i = intExtra;
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            this.this$0.c = (PostTopicTask) f.b().b(stringExtra);
            if (this.this$0.c != null) {
                this.this$0.c.registerNetTaskListener(new a(this.this$0.a));
            }
        }
        ReaderTask a = this.this$0.a(longExtra, i);
        final String stringExtra2 = this.val$data.getStringExtra("COMMIT_COMMENT_CONTENT");
        a.setRequest(stringExtra2);
        String fakeCommentId = a.getFakeCommentId();
        if (TextUtils.isEmpty(fakeCommentId)) {
            fakeCommentId = this.val$data.getStringExtra("COMMIT_COMMENT_FAKECOMMITID");
            a.setFakeCommentId(fakeCommentId);
        }
        g.a().a(a);
        this.this$0.a.post(new Runnable(this) {
            final /* synthetic */ CommentPublisher$1 c;

            public void run() {
                this.c.this$0.a(stringExtra2, fakeCommentId);
            }
        });
    }
}
