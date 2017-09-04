package com.qq.reader.module.bookstore.qnative.fragment;

import android.text.TextUtils;
import com.tencent.mm.performance.WxPerformanceHandle;
import org.json.JSONArray;
import org.json.JSONObject;

public class NativePageFragmentforLabel extends NativePageFragmentforClassify {
    protected int getSelectId(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(WxPerformanceHandle.MESSAGE_TAG);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return 0;
        }
        JSONObject optJSONObject = optJSONArray.optJSONObject(0);
        if (optJSONObject == null) {
            return 0;
        }
        Object optString = optJSONObject.optString("id");
        if (TextUtils.isEmpty(optString)) {
            return 0;
        }
        return Integer.parseInt(optString);
    }

    protected int getSudId() {
        return 0;
    }

    protected String getReportSelectId() {
        return "event_C273";
    }

    protected String getReportSearchId() {
        return "event_C274";
    }
}
