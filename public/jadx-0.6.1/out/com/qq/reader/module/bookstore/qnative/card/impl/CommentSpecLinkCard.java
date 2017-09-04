package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class CommentSpecLinkCard extends BaseCommentCard {
    private long bid = -1;
    StringBuilder builder = new StringBuilder();
    private long mCommentCount = 0;
    private String title = "书评区";

    public CommentSpecLinkCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_comment_link;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.commentlink_content);
        this.builder.setLength(0);
        this.builder.append("去").append(this.title).append("逛逛").append("（").append(this.mCommentCount).append("）");
        textView.setText(this.builder.toString());
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentSpecLinkCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.bid, this.a.title, new JumpActivityParameter());
                Map hashMap = new HashMap();
                if (this.a.title.contains("书荒")) {
                    hashMap.put("type", "0");
                } else {
                    hashMap.put("type", "1");
                }
                i.a("event_C75", hashMap, ReaderApplication.getApplicationImp());
            }
        });
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mCommentCount = jSONObject.optLong("commentcount");
        JSONObject optJSONObject = jSONObject.optJSONObject("bddetail");
        if (optJSONObject != null) {
            this.bid = optJSONObject.optLong("bid");
            this.title = optJSONObject.optString("title");
        }
        return true;
    }
}
