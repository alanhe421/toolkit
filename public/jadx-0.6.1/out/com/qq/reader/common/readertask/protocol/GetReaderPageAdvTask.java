package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.sdk.v;
import java.util.HashMap;

public class GetReaderPageAdvTask extends ReaderProtocolJSONTask {
    private String mBid;

    public GetReaderPageAdvTask(c cVar, String str) {
        super(cVar);
        this.mBid = "";
        this.mUrl = e.cl + "channel=" + ao.h(getContext());
        this.mUrl += FeedDataTask.MS_SEX + d.aS(ReaderApplication.getApplicationImp());
        this.mBid = str;
        this.mUrl += GetVoteUserIconsTask.BID + this.mBid;
    }

    public HashMap<String, String> getBasicHeader() {
        this.mHeaders.put("resolution", a.bU + v.n + a.bT);
        this.mHeaders.put("density", "" + a.bZ);
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}
