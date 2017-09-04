package com.hmt.analytics.common;

import android.annotation.SuppressLint;
import android.content.Context;
import com.hmt.analytics.HMTAgent;
import com.hmt.analytics.dao.GetInfoFromFile;
import com.hmt.analytics.objects.PostObjAction;
import com.hmt.analytics.util.HmtUtils;
import com.hmt.analytics.util.SPUtils;
import com.hmt.analytics.util.ThreadPoolUtils;
import com.tencent.android.tpush.common.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendPolicyRunnable implements Runnable {
    public static AtomicBoolean a = new AtomicBoolean(false);
    private static final String c = SendPolicyRunnable.class.getSimpleName();
    private Context b;
    private int d;

    public SendPolicyRunnable(Context context, int i) {
        this.b = context.getApplicationContext();
        this.d = i;
    }

    public void setType(int i) {
        this.d = i;
    }

    public void run() {
        if (a.compareAndSet(false, true)) {
            chooseDataPolicy();
            a.compareAndSet(true, false);
        }
    }

    @SuppressLint({"NewApi"})
    private void chooseDataPolicy() {
        String deviceID = CommonUtil.getDeviceID(this.b);
        if (deviceID == null || deviceID.isEmpty()) {
            sendAllData();
            return;
        }
        sendData(prepareClientData(), prepareAdvActionData());
        sendLaunchActivityData();
        sendAllData();
    }

    private void sendData(JSONArray jSONArray, JSONArray jSONArray2) {
        JSONObject jSONObject = new JSONObject();
        if (jSONArray != null) {
            try {
                jSONObject.put("client_data_list", jSONArray);
            } catch (JSONException e) {
                CommonUtil.printLog(c, "failed to add JsonObj 2 JsonArray");
            }
        }
        if (jSONArray2 != null) {
            try {
                jSONObject.put("act_list", jSONArray2);
            } catch (JSONException e2) {
                CommonUtil.printLog(c, "failed to add JsonObj 2 JsonArray");
            }
        }
        if (!(jSONArray == null && jSONArray2 == null) && NetworkUitlity.Post(HMTConstants.f, jSONObject.toString(), "client_adv")) {
            if (jSONArray != null) {
                SPUtils.put(this.b, "hmt_client_data_send_time", Long.valueOf(System.currentTimeMillis()));
            }
            if (jSONArray2 != null) {
                SPUtils.put(this.b, "hmt_adv_upload_time", Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    private void sendAllData() {
        if (CommonUtil.isNetworkAvailable(this.b)) {
            long longValue = ((Long) SPUtils.get(this.b, "hmt_all_data_send_time", Long.valueOf(0))).longValue();
            boolean checkGap = HmtUtils.checkGap(longValue);
            boolean checkToday = HmtUtils.checkToday(longValue);
            boolean booleanValue = ((Boolean) SPUtils.get(this.b, "hmt_send_all_data_success_once", Boolean.valueOf(false))).booleanValue();
            String str = c;
            StringBuilder stringBuilder = new StringBuilder("all_data need send  = ");
            boolean z = (booleanValue && !checkGap && checkToday) ? false : true;
            CommonUtil.printLog(str, stringBuilder.append(z).toString());
            if (!booleanValue || checkGap || !checkToday) {
                ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(this.b));
            }
        }
    }

    private void sendLaunchActivityData() {
        long longValue = ((Long) SPUtils.get(this.b, "hmt_first_activity_send_time", Long.valueOf(0))).longValue();
        if (!HMTAgent.b || HmtUtils.checkGap(longValue) || !HmtUtils.checkToday(longValue)) {
            HMTTool.sendData(this.b, AssembJSONObj.getLaunchActivityJSONObj(this.b), HMTConstants.f, "activity_list", HMTConstants.n, Constants.FLAG_ACTIVITY_NAME, null);
            HMTAgent.b = true;
            SPUtils.put(this.b, "hmt_send_all_data_success_once", Boolean.valueOf(false));
            SPUtils.put(this.b, "hmt_first_activity_send_time", Long.valueOf(System.currentTimeMillis()));
        }
    }

    private JSONArray prepareAdvActionData() {
        boolean checkGap = HmtUtils.checkGap(((Long) SPUtils.get(this.b, "hmt_adv_upload_time", Long.valueOf(0))).longValue(), ((Long) SPUtils.get(this.b, "hmt_agent_online_setting", "hmt_adv_upload_gap_time", Long.valueOf(HMTConstants.b))).longValue());
        CommonUtil.printLog(c, "adv_action need send ?=" + checkGap);
        if (!checkGap) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, AssembJSONObj.getActionJOSNobj(new PostObjAction("advFirst", "1", CommonUtil.getTime(), "hmt_launch", CommonUtil.getAppVersion(this.b), CommonUtil.getAppKey(this.b)), this.b));
            return jSONArray;
        } catch (JSONException e) {
            CommonUtil.printLog(c, "failed to create advDataArray");
            return jSONArray;
        }
    }

    private JSONArray prepareClientData() {
        boolean checkClientDataSendState = HmtUtils.checkClientDataSendState(this.b);
        CommonUtil.printLog(c, "client_data need send =" + (!checkClientDataSendState));
        if (checkClientDataSendState) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(HMTTool.getClientDataJSONObject(this.b));
        return jSONArray;
    }
}
