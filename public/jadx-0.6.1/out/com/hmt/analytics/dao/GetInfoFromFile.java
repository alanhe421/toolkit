package com.hmt.analytics.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.HMTConstants;
import com.hmt.analytics.common.HMTTool;
import com.hmt.analytics.common.NetworkUitlity;
import com.hmt.analytics.interfaces.HMTCallback;
import com.hmt.analytics.util.HMTInfo;
import com.hmt.analytics.util.HMTInfoService;
import com.hmt.analytics.util.SPUtils;
import com.hmt.analytics.util.UploadService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetInfoFromFile implements Runnable {
    public static Object a = new Object();
    private static final String e = GetInfoFromFile.class.getSimpleName();
    private Context b;
    private int c = 1;
    private HMTCallback d = null;
    private boolean f = true;
    private boolean g = true;

    public GetInfoFromFile(Context context, HMTCallback hMTCallback) {
        this.b = context;
        this.d = hMTCallback;
    }

    public GetInfoFromFile(Context context) {
        this.b = context;
    }

    public GetInfoFromFile(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public void run() {
        synchronized (a) {
            if (this.c != 0) {
                sendUploadData();
            } else if (HMTTool.isSendEveryDayClientData(this.b)) {
                sendEveryDayClientData();
            }
        }
    }

    private void sendEveryDayClientData() {
        String deviceID = CommonUtil.getDeviceID(this.b);
        if (deviceID != null && !deviceID.equals("")) {
            JSONObject clientDataJSONObject = HMTTool.getClientDataJSONObject(this.b);
            JSONArray jSONArray = new JSONArray();
            String str = "client_data_list";
            try {
                jSONArray.put(0, clientDataJSONObject);
                clientDataJSONObject = new JSONObject();
                clientDataJSONObject.put(str, jSONArray);
                if (NetworkUitlity.Post(HMTConstants.f, clientDataJSONObject.toString(), HMTConstants.n)) {
                    saveUploadTime();
                }
            } catch (JSONException e) {
                CommonUtil.printLog(e, "fail to post " + str);
            }
        }
    }

    private void sendDataAll(HMTInfoService hMTInfoService, String str, String str2) {
        ArrayList scrollData;
        do {
            try {
                scrollData = hMTInfoService.getScrollData(str2, HMTConstants.r);
                if (scrollData == null || scrollData.size() == 0) {
                    CommonUtil.printLog(e, "no data 4 upload");
                    break;
                }
                CommonUtil.printLog(e, "tableName = " + str2 + "___list.size = " + scrollData.size());
                if (new UploadService(this.b, scrollData, str).start()) {
                    hMTInfoService.deleteData(str2, ((HMTInfo) scrollData.get(scrollData.size() - 1)).getId().intValue());
                } else if (str2.equals("hmtInfo")) {
                    this.f = false;
                } else if (str2.equals("reqInfo")) {
                    this.f = false;
                }
            } catch (SQLiteException e) {
                CommonUtil.printLog(e, e.getMessage());
                return;
            }
        } while (scrollData.size() >= HMTConstants.r);
        try {
            hMTInfoService.emptyTable(str2);
        } catch (SQLiteException e2) {
            CommonUtil.printLog(e, e2.getMessage());
        }
    }

    public void saveUploadTime() {
        CommonUtil.printLog(e, "save upload time");
        SPUtils.put(this.b, "hmt_init_savetime", "upload_save_time", Long.valueOf(System.currentTimeMillis()));
        SPUtils.put(this.b, "hmt_send_all_data_success_once", Boolean.valueOf(true));
        SPUtils.put(this.b, "hmt_all_data_send_time", Long.valueOf(System.currentTimeMillis()));
    }

    private void sendUploadData() {
        try {
            HMTInfoService hMTInfoService = new HMTInfoService(this.b);
            sendDataAll(hMTInfoService, HMTConstants.f, "hmtInfo");
            sendDataAll(hMTInfoService, HMTConstants.g, "reqInfo");
            if (this.f && this.g) {
                saveUploadTime();
            }
            if (this.d != null) {
                this.d.callback();
            }
        } catch (SQLiteException e) {
            CommonUtil.printLog(e, e.getMessage());
        }
    }
}
