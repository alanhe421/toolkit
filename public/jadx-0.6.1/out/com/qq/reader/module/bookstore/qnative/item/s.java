package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.tencent.imsdk.QLogImpl;
import com.tencent.upload.log.trace.TracerConfig;
import org.json.JSONObject;

/* compiled from: Item */
public abstract class s {
    public static final String ALG = "alg";
    public static final String ORIGIN = "origin";
    public static final String STATPARAM_KEY = "stat_params";
    protected String mAlg;
    protected String mOrigin;
    protected d mRankInfoItem;
    public String mStatParamString = "";
    protected JSONObject mStatParams = null;

    public abstract void parseData(JSONObject jSONObject);

    public static String countTransform(long j) {
        return j.a(j);
    }

    public static String countTransform2(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        if (j < TracerConfig.LOG_FLUSH_DURATION) {
            stringBuffer.append(j);
            stringBuffer.append("");
        } else if (j < TracerConfig.LOG_FLUSH_DURATION || j >= 1000000) {
            stringBuffer.append((5000 + j) / TracerConfig.LOG_FLUSH_DURATION);
            stringBuffer.append(QLogImpl.TAG_REPORTLEVEL_COLORUSER);
        } else {
            stringBuffer.append((j + 500) / TracerConfig.LOG_FLUSH_DURATION);
            stringBuffer.append(".");
            stringBuffer.append(((j + 500) % TracerConfig.LOG_FLUSH_DURATION) / 1000);
            stringBuffer.append(QLogImpl.TAG_REPORTLEVEL_COLORUSER);
        }
        return stringBuffer.toString();
    }

    private void getStatParams(JSONObject jSONObject) {
        this.mStatParams = jSONObject.optJSONObject(STATPARAM_KEY);
        if (this.mStatParams != null) {
            this.mStatParamString = this.mStatParams.toString();
            this.mAlg = this.mStatParams.optString(ALG);
            this.mOrigin = this.mStatParams.optString(ORIGIN);
        }
    }

    public void setStatisic(JSONObject jSONObject, Bundle bundle) {
        getStatParams(jSONObject);
        bundle.putString(STATPARAM_KEY, this.mStatParamString);
    }

    public void setPageInfo(d dVar) {
        this.mRankInfoItem = dVar;
    }

    public String getAlg() {
        return this.mAlg;
    }

    public void setAlg(String str) {
        this.mAlg = str;
    }

    public String getOrigin() {
        return this.mOrigin;
    }
}
