package com.qq.reader.common.readertask.protocol;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.tencent.connect.common.Constants;
import java.util.List;

public class ReportUserTagTask extends ReaderProtocolJSONTask {
    public List<String> mPrefer_id_list;

    public ReportUserTagTask(c cVar, List<String> list) {
        this(cVar, list, null);
    }

    public ReportUserTagTask(c cVar, List<String> list, Bundle bundle) {
        super(cVar);
        this.mPrefer_id_list = list;
        StringBuilder stringBuilder = new StringBuilder(e.bJ + "prefer=");
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append((String) list.get(i));
                if (i < size - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        this.mUrl = e.h;
        this.mUrl = stringBuilder.toString();
        this.mUrl += FeedDataTask.MS_SEX + d.aS(ReaderApplication.getApplicationImp());
        this.mUrl += "&newtag=1";
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.mUrl += "&" + str + "=" + bundle.getString(str);
            }
        }
        setFailedType(1);
    }

    protected String generateTaskKey() {
        return e.bJ;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public boolean isNeedLogin() {
        return true;
    }
}
