package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.f;
import com.qq.reader.common.readertask.protocol.PostTopicTask;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class BookClubFailedTopicCard extends BaseCommentCard {
    private long mBookid;
    private String mContent;
    private String mErrorMsg;
    private PostTopicTask mTask;

    public BookClubFailedTopicCard(b bVar, String str, int i) {
        super(bVar, str, i);
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_failedtopic;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void setErrorMsg(String str) {
        this.mErrorMsg = str;
    }

    public void setTask(ReaderTask readerTask, long j) {
        if (readerTask != null && (readerTask instanceof PostTopicTask)) {
            this.mTask = (PostTopicTask) readerTask;
            this.mContent = ((PostTopicTask) readerTask).getRequestContent();
            this.mBookid = j;
        }
    }

    public void attachView() {
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookClubFailedTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mBookid != 0 && !TextUtils.isEmpty(this.a.mContent)) {
                    Bundle bundle = new Bundle();
                    c cVar = new c(bundle);
                    bundle.putInt("CTYPE", this.a.getCtype());
                    bundle.putInt("function_type", 5);
                    bundle.putString("PARA_TYPE_TOPIC_CONTENT", this.a.mContent);
                    bundle.putLong("URL_BUILD_PERE_BOOK_ID", this.a.mBookid);
                    bundle.putString("KEY_TASK_KEY", this.a.mTask.getTaskKey());
                    cVar.a(this.a.getEvnetListener());
                    if (this.a.mTask != null) {
                        com.qq.reader.common.monitor.debug.c.a("cache", " remove " + this.a.mTask.getTaskKey());
                        f.b().b(this.a.mTask);
                        f.b().a(this.a.mTask.getTaskKey());
                    }
                }
            }
        });
        if (!TextUtils.isEmpty(this.mErrorMsg)) {
            ((TextView) ap.a(getRootView(), R.id.card_failedtopic_text)).setText(this.mErrorMsg);
        }
    }
}
