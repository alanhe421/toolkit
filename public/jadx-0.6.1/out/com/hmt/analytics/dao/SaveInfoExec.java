package com.hmt.analytics.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.util.HMTInfoService;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SaveInfoExec {
    public static void saveExec(Context context, JSONObject jSONObject, String str) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            try {
                HMTInfoService hMTInfoService = new HMTInfoService(context);
                JSONArray jSONArray = jSONObject.getJSONArray(str2);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    hMTInfoService.save(str2, jSONArray.get(i).toString(), str);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLiteException e2) {
                CommonUtil.printLog("HMT==SQLiteException", e2.getMessage());
            }
        }
    }
}
