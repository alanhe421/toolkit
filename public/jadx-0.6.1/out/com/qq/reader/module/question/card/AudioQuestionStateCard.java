package com.qq.reader.module.question.card;

import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioStateEmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioQuestionStateCard extends BaseEmptyCard {
    private String mAvatar;
    private int mCode;
    private String mMsg;

    public AudioQuestionStateCard(b bVar, int i, String str, String str2) {
        super(bVar, "");
        this.mCode = i;
        this.mMsg = str;
        this.mAvatar = str2;
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.audio_question_detail_statecard;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
        AudioStateEmptyView audioStateEmptyView = (AudioStateEmptyView) getRootView();
        switch (this.mCode) {
            case 0:
                audioStateEmptyView.setViewsVisibility(false, true, true, false, true);
                audioStateEmptyView.b("等待作者回答");
                audioStateEmptyView.a((CharSequence) "作者拒绝回答或超过48小时未回答\n书币将全额退款到账户");
                audioStateEmptyView.c("在个人主页，查看发起的提问");
                return;
            case 2:
                audioStateEmptyView.setViewsVisibility(false, true, false, true, false);
                audioStateEmptyView.b("未在48小时内回答，提问自动拒绝");
                audioStateEmptyView.d("发起提问的书币已全额退款");
                return;
            case 3:
                audioStateEmptyView.a(this.mAvatar);
                audioStateEmptyView.b("抱歉无法回答该问题");
                if (TextUtils.isEmpty(this.mMsg)) {
                    audioStateEmptyView.setViewsVisibility(true, true, false, true, false);
                } else {
                    audioStateEmptyView.a("拒绝理由：" + this.mMsg);
                    audioStateEmptyView.setViewsVisibility(true, true, true, true, false);
                }
                audioStateEmptyView.d("发起提问的书币已全额退款");
                return;
            case 4:
                audioStateEmptyView.setViewsVisibility(false, true, false, true, false);
                audioStateEmptyView.b("提问正在审核中");
                audioStateEmptyView.d("如有疑问，请联系客服");
                return;
            case 5:
                audioStateEmptyView.setViewsVisibility(false, true, true, true, false);
                audioStateEmptyView.b("提问内容违规，已自动关闭");
                audioStateEmptyView.a((CharSequence) "书币不退款，已发放至作者账户");
                audioStateEmptyView.d("如有疑问，请联系客服");
                return;
            case 8:
                audioStateEmptyView.setViewsVisibility(false, true, true, false, true);
                audioStateEmptyView.b("提问待审核");
                audioStateEmptyView.a((CharSequence) "审核通过后作者可见，审核时间\n最长不超过18小时");
                audioStateEmptyView.c("在个人主页，查看发起的提问");
                return;
            case 9:
                audioStateEmptyView.setViewsVisibility(false, true, true, true, false);
                audioStateEmptyView.b("本提问内容违规，已关闭");
                audioStateEmptyView.a((CharSequence) "书币已退款至账户");
                audioStateEmptyView.d("如有疑问，请联系客服");
                return;
            default:
                return;
        }
    }
}
