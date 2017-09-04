package com.qq.reader.module.question.card;

import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ConfigurableEmptyCard extends BaseEmptyCard {
    private a mOnAttachListener;

    public interface a {
        void a(EmptyView emptyView);
    }

    public ConfigurableEmptyCard(b bVar, a aVar, com.qq.reader.module.bookstore.qnative.c.a aVar2) {
        super(bVar, ConfigurableEmptyCard.class.getSimpleName());
        this.mOnAttachListener = aVar;
        setEventListener(aVar2);
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.normal_empty_view_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
        if (this.mOnAttachListener != null) {
            this.mOnAttachListener.a((EmptyView) ap.a(getRootView(), R.id.empty_view));
        }
    }
}
