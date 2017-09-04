package com.qq.reader.common.readertask.protocol;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.adv.b;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.sdk.v;
import java.util.HashMap;

public class GetAdvTask extends ReaderProtocolJSONTask {
    private b mAdvHandle;

    public GetAdvTask(c cVar, b bVar) {
        super(cVar);
        this.mAdvHandle = bVar;
        this.mUrl = e.aT + "?channel=" + ao.h(getContext());
        this.mUrl += FeedDataTask.MS_SEX + d.aS(ReaderApplication.getApplicationImp());
    }

    public HashMap<String, String> getBasicHeader() {
        String str = VoiceWakeuperAidl.PARAMS_SEPARATE;
        str = "_";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mAdvHandle.a("100100"));
        stringBuffer.append(this.mAdvHandle.a("100101"));
        stringBuffer.append(this.mAdvHandle.a("100111"));
        stringBuffer.append(this.mAdvHandle.a("102438"));
        stringBuffer.append(this.mAdvHandle.a("102425"));
        stringBuffer.append(this.mAdvHandle.a("102597"));
        stringBuffer.append(this.mAdvHandle.a("100126"));
        stringBuffer.append(this.mAdvHandle.a("103170"));
        stringBuffer.append(this.mAdvHandle.a("102542"));
        stringBuffer.append(this.mAdvHandle.a("103490"));
        stringBuffer.append(this.mAdvHandle.a("102870"));
        stringBuffer.append(this.mAdvHandle.a("102879"));
        stringBuffer.append(this.mAdvHandle.a("103583"));
        stringBuffer.append(this.mAdvHandle.a("103584"));
        stringBuffer.append(this.mAdvHandle.a("103585"));
        stringBuffer.append(this.mAdvHandle.a("103586"));
        stringBuffer.append(this.mAdvHandle.a("103578"));
        stringBuffer.append(this.mAdvHandle.a("103579"));
        stringBuffer.append(this.mAdvHandle.a("103580"));
        stringBuffer.append(this.mAdvHandle.a("103581"));
        stringBuffer.append(this.mAdvHandle.a("103582"));
        stringBuffer.append(this.mAdvHandle.a("102925"));
        stringBuffer.append(this.mAdvHandle.a("102668"));
        stringBuffer.append(this.mAdvHandle.a("103096"));
        stringBuffer.append(this.mAdvHandle.a("103100"));
        stringBuffer.append(this.mAdvHandle.a("103117"));
        stringBuffer.append(this.mAdvHandle.a("103118"));
        stringBuffer.append(this.mAdvHandle.a("103187"));
        stringBuffer.append(this.mAdvHandle.a("103172"));
        stringBuffer.append(this.mAdvHandle.a("103173"));
        stringBuffer.append(this.mAdvHandle.a("103180"));
        stringBuffer.append(this.mAdvHandle.a("103182"));
        stringBuffer.append(this.mAdvHandle.a("103183"));
        stringBuffer.append(this.mAdvHandle.a("103185"));
        stringBuffer.append(this.mAdvHandle.a("103190"));
        stringBuffer.append(this.mAdvHandle.a("103191"));
        stringBuffer.append(this.mAdvHandle.a("103469"));
        stringBuffer.append(this.mAdvHandle.a("103484"));
        stringBuffer.append(this.mAdvHandle.a("103486"));
        stringBuffer.append(this.mAdvHandle.a("103520"));
        stringBuffer.append(this.mAdvHandle.a("103521"));
        stringBuffer.append(this.mAdvHandle.a("103536"));
        this.mHeaders.put("type", stringBuffer.toString());
        this.mHeaders.put("resolution", a.bU + v.n + a.bT);
        this.mHeaders.put("density", "" + a.bZ);
        return this.mHeaders;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }
}
