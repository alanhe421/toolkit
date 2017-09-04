package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.bookstore.qnative.card.impl.AuthorWXInfoBindCard.WXBasicInfoItem;
import com.tencent.connect.common.Constants;
import java.net.URLEncoder;
import java.util.HashMap;

public class SubmitAuthorWXInfoTask extends ReaderProtocolJSONTask {
    private WXBasicInfoItem mItem;

    public SubmitAuthorWXInfoTask(c cVar, WXBasicInfoItem wXBasicInfoItem) {
        super(cVar);
        this.mUrl += e.ac;
        this.mItem = wXBasicInfoItem;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public HashMap<String, String> getBasicHeader() {
        addAllParam(this.mItem);
        return super.getBasicHeader();
    }

    private void addAllParam(WXBasicInfoItem wXBasicInfoItem) {
        addHeader("authorId", wXBasicInfoItem.authorId);
        addHeader("openid", wXBasicInfoItem.openId);
        addHeader("unionid", wXBasicInfoItem.unionId);
        addHeader("serviceContractVersion", wXBasicInfoItem.labourContractVersion);
        addHeader("authorWXiconUrl", wXBasicInfoItem.avatarUrl);
        try {
            addHeader("authorWXnickName", URLEncoder.encode(wXBasicInfoItem.nickName, "utf-8"));
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    public boolean isRequestGzip() {
        return true;
    }
}
