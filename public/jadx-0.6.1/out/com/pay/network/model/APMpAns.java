package com.pay.network.model;

import com.pay.http.APBaseHttpAns;
import com.pay.http.APBaseHttpReq;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APProductItem;
import com.tencent.midas.comm.APLog;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class APMpAns extends APBaseHttpAns {
    private List<String> a = new ArrayList();
    private List<String> b = new ArrayList();
    private List<String> c = new ArrayList();
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private List<APProductItem> h = new ArrayList();
    private String i = "";

    public APMpAns(APHttpHandle aPHttpHandle, IAPHttpAnsObserver iAPHttpAnsObserver, HashMap<String, APBaseHttpReq> hashMap, String str) {
        super(aPHttpHandle, iAPHttpAnsObserver, hashMap, str);
    }

    public String getBeginTime() {
        return this.f;
    }

    public String getEndTime() {
        return this.g;
    }

    public String getFirstsave_present_count() {
        return this.e;
    }

    public String getMpJson() {
        return this.i;
    }

    public List<String> getMpList() {
        return this.a;
    }

    public List<String> getMpPresentList() {
        return this.c;
    }

    public List<String> getMpValueList() {
        return this.b;
    }

    public List<APProductItem> getProductList() {
        return this.h;
    }

    public String getRate() {
        return this.d;
    }

    public void onErrorAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void onFinishAns(byte[] bArr, APBaseHttpReq aPBaseHttpReq) {
        super.onFinishAns(bArr, aPBaseHttpReq);
        String str = new String(bArr);
        this.i = str;
        APLog.i("APMpAns", "resultData=" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.resultCode = Integer.parseInt(jSONObject.getString("ret").toString());
            if (this.resultCode == 0) {
                if (jSONObject.has("product_list")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("product_list");
                    this.h.clear();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        APProductItem aPProductItem = new APProductItem();
                        try {
                            aPProductItem.name = jSONObject2.getString("name");
                            aPProductItem.productId = jSONObject2.getString("productid");
                            aPProductItem.price = jSONObject2.getString("price");
                            aPProductItem.num = jSONObject2.getString("num");
                            this.h.add(aPProductItem);
                        } catch (Exception e) {
                        }
                    }
                }
                this.d = jSONObject.getString("rate");
                APCommMethod.transformStrToList(jSONObject.getString("list"), this.a);
                this.e = jSONObject.getString("firstsave_present_count");
                APCommMethod.transformStrToMpInfoList(jSONObject.getString("present_level"), this.b, this.c);
                this.f = jSONObject.getString("begin_time");
                this.g = jSONObject.getString("end_time");
                return;
            }
            this.resultMsg = jSONObject.getString(SocialConstants.PARAM_SEND_MSG);
            str = jSONObject.getString("err_code").toString();
            if (!str.equals("")) {
                this.resultMsg = "系统繁忙,请稍后再试\n(" + str + ")";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onReceiveAns(byte[] bArr, int i, long j, APBaseHttpReq aPBaseHttpReq) {
    }

    public void onStartAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void onStopAns(APBaseHttpReq aPBaseHttpReq) {
    }

    public void setBeginTime(String str) {
        this.f = str;
    }

    public void setEndTime(String str) {
        this.g = str;
    }

    public void setFirstsave_present_count(String str) {
        this.e = str;
    }

    public void setMpList(List<String> list) {
        this.a = list;
    }

    public void setMpPresentList(List<String> list) {
        this.c = list;
    }

    public void setMpValueList(List<String> list) {
        this.b = list;
    }

    public void setRate(String str) {
        this.d = str;
    }
}
