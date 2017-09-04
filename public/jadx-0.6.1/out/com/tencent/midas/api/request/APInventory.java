package com.tencent.midas.api.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class APInventory {
    public ArrayList<APPurchase> mPurchaseList = new ArrayList();
    public Map<String, APPurchase> mPurchaseMap = new HashMap();

    public APInventory(String str) {
        a(str);
    }

    private void a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                APPurchase aPPurchase = new APPurchase((String) jSONArray.getJSONObject(i).get("data"), (String) jSONArray.getJSONObject(i).get("sign"));
                this.mPurchaseMap.put(aPPurchase.getSku(), aPPurchase);
                this.mPurchaseList.add(aPPurchase);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void erasePurchase(String str) {
        if (this.mPurchaseMap.containsKey(str)) {
            this.mPurchaseMap.remove(str);
        }
    }

    public APPurchase getPurchase(String str) {
        return (APPurchase) this.mPurchaseMap.get(str);
    }

    public List<APPurchase> getPurchaseList() {
        return this.mPurchaseList;
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }
}
