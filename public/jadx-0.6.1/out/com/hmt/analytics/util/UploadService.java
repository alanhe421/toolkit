package com.hmt.analytics.util;

import android.content.Context;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.NetworkUitlity;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadService {
    private JSONObject a = new JSONObject();
    private String b = "";

    public UploadService(Context context, List<HMTInfo> list, String str) {
        this.b = str;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HMTInfo hMTInfo = (HMTInfo) list.get(i);
            try {
                if (this.a.isNull(hMTInfo.getType())) {
                    this.a.put(hMTInfo.getType(), new JSONArray());
                }
                this.a.getJSONArray(hMTInfo.getType()).put(new JSONObject(hMTInfo.getInfo()));
            } catch (JSONException e) {
                CommonUtil.printLog("HMT", e.toString());
            }
        }
    }

    public boolean start() {
        try {
            return dispach();
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dispach() throws JSONException {
        return NetworkUitlity.Post(this.b, this.a.toString(), "all_data");
    }
}
