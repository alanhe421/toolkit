package com.qq.reader.common.readertask.protocol;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;

public class IllegalCommentReportTask extends ReaderProtocolJSONTask {
    public IllegalCommentReportTask(c cVar, Bundle bundle) {
        super(cVar);
        this.mUrl = e.h + "nativepage/comment/" + "report?";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bid=").append(bundle.get("COMMENT_REPORT_BID")).append("&").append("ctype=").append(bundle.get("COMMENT_REPORT_CTYPE")).append("&").append("commentid=").append(bundle.get("COMMENT_REPORT_COMMENTID")).append("&").append("reporttype=").append(bundle.get("COMMENT_REPORT_REPORTTYPE")).append("&").append("desc=").append(bundle.get("COMMENT_REPORT_DESC")).append("&").append("replyid=").append(bundle.get("COMMENT_REPORT_REPLYID"));
        this.mUrl += stringBuilder.toString();
        setFailedType(1);
    }

    protected String generateTaskKey() {
        return "nativepage/comment/";
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
