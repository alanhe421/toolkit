package com.hmt.analytics.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HParams {
    private JSONObject a = new JSONObject();

    public void setParams(String str, String str2) {
        try {
            this.a.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setParams(String str, String[] strArr) {
        if (strArr != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (Object put : strArr) {
                    jSONArray.put(put);
                }
                this.a.put(str, jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject getParams() {
        return this.a;
    }
}
