package com.yw.game.sdk.login.util;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class NetResult implements Serializable {
    private int ReturnCode = -1;
    private String ReturnData = "";
    private String ReturnMessage = "";
    private JSONObject dateString = null;

    public int getReturnCode() {
        return this.ReturnCode;
    }

    public void setReturnCode(int i) {
        this.ReturnCode = i;
    }

    public String getReturnMessage() {
        return this.ReturnMessage;
    }

    public void setReturnMessage(String str) {
        this.ReturnMessage = str;
    }

    public String getReturnData() {
        return this.ReturnData;
    }

    public void setReturnData(String str) {
        this.ReturnData = str;
    }

    public boolean isSuccessed() {
        return this.ReturnCode == 0;
    }

    public JSONObject getDateString() {
        if (TextUtils.isEmpty(this.ReturnData)) {
            return this.dateString;
        }
        return c.c(this.ReturnData);
    }
}
