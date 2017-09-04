package com.qq.reader.module.rookie.view;

import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class RookieGeneCommitCard extends a {
    private int num;

    public RookieGeneCommitCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.rookiegenecommitcard;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.num = jSONObject.optInt("giftMoney");
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.rookie_gene_commit_title)).setText(getEvnetListener().getFromActivity().getString(R.string.rookie_gene_commit_success, new Object[]{Integer.valueOf(this.num)}));
    }
}
