package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class IdentifyTask extends ReaderProtocolTask {
    static String PARA_BOOKD_ID = "bookid=";
    static String PARA_IMEI = "authInfo=";
    static String PARA_TRIAL = "onlytrial=1";

    public IdentifyTask(d dVar, String str) {
        super(dVar);
        try {
            this.mUrl = e.aM + PARA_BOOKD_ID + str + "&" + PARA_IMEI + URLEncoder.encode(a.d.i(getContext()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setTrial(boolean z) {
        if (z) {
            this.mUrl += "&" + PARA_TRIAL;
        }
    }
}
