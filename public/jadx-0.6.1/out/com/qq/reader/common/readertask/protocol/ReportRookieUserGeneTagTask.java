package com.qq.reader.common.readertask.protocol;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import java.util.List;

public class ReportRookieUserGeneTagTask extends ReaderProtocolJSONTask {
    public List<String> mPrefer_id_list;

    public ReportRookieUserGeneTagTask(c cVar, List<String> list) {
        super(cVar);
        this.mPrefer_id_list = list;
        StringBuilder stringBuilder = new StringBuilder(e.bK + "prefer=");
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append((String) list.get(i));
                if (i < size - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        this.mUrl = stringBuilder.toString();
        this.mUrl += FeedDataTask.MS_SEX + d.aS(ReaderApplication.getApplicationImp());
        this.mUrl += "&newtag=1";
        this.mUrl += "&giftId=12";
    }

    protected String generateTaskKey() {
        return e.bK;
    }

    public String getRequestMethod() {
        return Constants.HTTP_GET;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
