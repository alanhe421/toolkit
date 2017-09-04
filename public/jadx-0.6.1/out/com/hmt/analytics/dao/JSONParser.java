package com.hmt.analytics.dao;

import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.objects.MyMessage;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    public static MyMessage parse(String str) {
        MyMessage myMessage = new MyMessage();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (Integer.parseInt(jSONObject.getString("flag")) > 0) {
                myMessage.setFlag(true);
            } else {
                myMessage.setFlag(false);
            }
            myMessage.setMsg(jSONObject.getString(SocialConstants.PARAM_SEND_MSG));
        } catch (JSONException e) {
            CommonUtil.printLog("JSONParser", e.toString());
        } catch (NumberFormatException e2) {
            CommonUtil.printLog("JSONParser", e2.toString());
        } catch (Exception e3) {
            CommonUtil.printLog("JSONParser", e3.toString());
        }
        return myMessage;
    }
}
