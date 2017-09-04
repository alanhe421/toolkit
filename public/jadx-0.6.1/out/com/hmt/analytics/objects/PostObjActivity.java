package com.hmt.analytics.objects;

import android.content.Context;
import com.hmt.analytics.common.CommonUtil;
import com.tencent.android.tpush.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class PostObjActivity {
    private Context a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;

    public void setInfo(String str, String str2, String str3, int i, String str4, String str5, String str6) {
        if (i == 1 || this.e == 0) {
            this.e = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.f = CommonUtil.getActivityName(this.a, 0);
            this.g = str4;
            this.h = str5;
            this.i = str6;
        }
    }

    public PostObjActivity(Context context, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = i;
        this.f = CommonUtil.getActivityName(context, 0);
        this.g = str4;
        this.h = str5;
        this.i = str6;
    }

    public Boolean contrast(Context context, int i, boolean z) {
        if (z) {
            return Boolean.valueOf(context.equals(this.a));
        }
        return (context.equals(this.a) && this.e == i) ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public JSONObject getJSONObject(Context context, String str) {
        JSONObject paramList;
        JSONException e;
        String stringBuilder = new StringBuilder(String.valueOf(Long.valueOf(str).longValue() - Long.valueOf(this.d).longValue())).toString();
        JSONObject jSONObject = new JSONObject();
        try {
            paramList = ParamList.getParamList(context, Constants.FLAG_ACTIVITY_NAME);
            try {
                paramList.put("session_id", this.c);
                paramList.put(Constants.FLAG_ACTIVITY_NAME, this.b);
                paramList.put("start_ts", this.d);
                paramList.put("end_ts", str);
                paramList.put("duration", stringBuilder);
                paramList.put("_activity", this.f);
                paramList.put("_mac", this.g);
                paramList.put("_imei", this.h);
                paramList.put("_androidid", this.i);
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return paramList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            paramList = jSONObject;
            e = jSONException;
            e.printStackTrace();
            return paramList;
        }
        return paramList;
    }
}
