package com.hmt.analytics.common;

import android.content.Context;
import com.hmt.analytics.dao.SaveInfo;
import com.hmt.analytics.objects.ParamList;
import com.tencent.android.tpush.common.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyCrashHandler implements UncaughtExceptionHandler {
    private static boolean e = true;
    private Context a;
    private Object b;
    private String c;
    private UncaughtExceptionHandler d;

    public MyCrashHandler(Context context) {
        if (e) {
            e = false;
            this.d = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.a = context;
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        CommonUtil.printLog("hmt-threadname", thread.getName());
        uncaughtExceptionGather(th);
        if (this.d != null && this.d != Thread.getDefaultUncaughtExceptionHandler()) {
            this.d.uncaughtException(thread, th);
        }
    }

    private void uncaughtExceptionGather(Throwable th) {
        String errorList = getErrorList(th);
        String[] split = errorList.split("\n\t");
        this.b = new StringBuilder(String.valueOf(split[0] + "\n\t" + split[1] + "\n\t" + split[2] + "\n\t")).append(errorList).toString();
        this.c = CommonUtil.getActivityName(this.a, 1);
        JSONObject errorInfoJSONString = getErrorInfoJSONString(this.a);
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONArray.put(0, errorInfoJSONString);
            jSONObject.put("error_list", jSONArray);
            new SaveInfo(this.a, jSONObject, HMTConstants.n).run();
        } catch (JSONException e) {
            CommonUtil.printLog("HMTAgent", "fail to post error_list");
        }
    }

    private JSONObject getErrorInfoJSONString(Context context) {
        JSONObject paramList;
        JSONException e;
        JSONObject jSONObject = new JSONObject();
        try {
            paramList = ParamList.getParamList(context, "error");
            try {
                paramList.put("stack_trace", this.b);
                paramList.put(Constants.FLAG_ACTIVITY_NAME, this.c);
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

    private String getErrorList(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
}
