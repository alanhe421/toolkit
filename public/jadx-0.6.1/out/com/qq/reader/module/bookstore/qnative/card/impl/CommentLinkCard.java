package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class CommentLinkCard extends BaseCommentCard {
    public long bid = -1;
    long mCommentCount = 0;
    public String mCommentid;
    public String uid;

    public CommentLinkCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.commentlink_content);
        textView.setText("去" + ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex) + "逛逛(" + this.mCommentCount + ")");
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentLinkCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                c cVar = new c(bundle);
                bundle.putLong("URL_BUILD_PERE_BOOK_ID", this.a.bid);
                bundle.putString("PARA_TYPE_BOOK_NAME", "" + this.a.bid);
                bundle.putString("KEY_JUMP_PAGENAME", "bookclubmain");
                bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
                bundle.putInt("function_type", 0);
                bundle.putInt("CTYPE", this.a.getCtype());
                bundle.putBoolean("newactivitywithresult", true);
                cVar.a(this.a.getEvnetListener());
                i.a("event_C75", null, ReaderApplication.getApplicationImp());
            }
        });
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_comment_link;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mCommentCount = jSONObject.optLong("commentcount");
        return true;
    }
}
