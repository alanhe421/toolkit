package com.qq.reader.module.bookstore.qnative.model;

import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONObject;

public class SearchToolItem extends s implements Serializable {
    public String mFinish;
    public String mFinishDesc;
    public String mFreeCharge;
    public String mFreeChargeDes;
    public int mIndex;
    public String mTotalwords;
    public String mTotalwordsDesc;
    public String mUpdatetime;
    public String mUpdatetimeDesc;
    public ArrayList<String> mVCateDescList = new ArrayList();
    public ArrayList<String> mVCateIdList = new ArrayList();
    public ArrayList<String> mVTagDescList = new ArrayList();
    public ArrayList<String> mVTagIdList = new ArrayList();

    public String getConditionParam() {
        return "categories=" + arrayToParamString(this.mVCateIdList) + "&tags=" + arrayToParamString(this.mVTagIdList) + "&totalwords=" + this.mTotalwords + "&updatetime=" + this.mUpdatetime + "&finished=" + this.mFinish + "&free=" + this.mFreeCharge;
    }

    public String getFlowText() {
        int i = 0;
        String str = "";
        ArrayList filteredCondition = getFilteredCondition(this.mVCateDescList, this.mVCateIdList);
        ArrayList filteredCondition2 = getFilteredCondition(this.mVTagDescList, this.mVTagIdList);
        int i2 = 0;
        while (filteredCondition != null && i2 < filteredCondition.size()) {
            str = str + ((String) filteredCondition.get(i2));
            if (i2 != filteredCondition.size() - 1) {
                str = str + "、";
            }
            i2++;
        }
        if (!str.equals("")) {
            str = str + "&";
        }
        while (filteredCondition2 != null && i < filteredCondition2.size()) {
            str = str + ((String) filteredCondition2.get(i));
            if (i != filteredCondition2.size() - 1) {
                str = str + "、";
            }
            i++;
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mTotalwordsDesc, this.mTotalwords))) {
            if (!str.equals("")) {
                str = str + "&";
            }
            str = str + this.mTotalwordsDesc;
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mUpdatetimeDesc, this.mUpdatetime))) {
            if (!str.equals("")) {
                str = str + "&";
            }
            str = str + this.mUpdatetimeDesc;
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mFinishDesc, this.mFinish))) {
            if (!str.equals("")) {
                str = str + "&";
            }
            str = str + this.mFinishDesc;
        }
        if (TextUtils.isEmpty(getFilteredCondition(this.mFreeChargeDes, this.mFreeCharge))) {
            return str;
        }
        if (!str.equals("")) {
            str = str + "&";
        }
        return str + this.mFreeChargeDes;
    }

    public ArrayList<String> getConditionTextArray() {
        ArrayList<String> arrayList = new ArrayList();
        Collection filteredCondition = getFilteredCondition(this.mVCateDescList, this.mVCateIdList);
        Collection filteredCondition2 = getFilteredCondition(this.mVTagDescList, this.mVTagIdList);
        arrayList.addAll(filteredCondition);
        arrayList.addAll(filteredCondition2);
        if (!TextUtils.isEmpty(getFilteredCondition(this.mTotalwordsDesc, this.mTotalwords))) {
            arrayList.add(this.mTotalwordsDesc);
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mUpdatetimeDesc, this.mUpdatetime))) {
            arrayList.add(this.mUpdatetimeDesc);
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mFinishDesc, this.mFinish))) {
            arrayList.add(this.mFinishDesc);
        }
        if (!TextUtils.isEmpty(getFilteredCondition(this.mFreeChargeDes, this.mFreeCharge))) {
            arrayList.add(this.mFreeChargeDes);
        }
        return arrayList;
    }

    private String arrayToParamString(ArrayList<String> arrayList) {
        String str = "";
        if (arrayList == null || arrayList.size() == 0) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        String str2 = str;
        for (int i = 0; i < arrayList.size(); i++) {
            str2 = str2 + ((String) arrayList.get(i));
            if (i != arrayList.size() - 1) {
                str2 = str2 + ",";
            }
        }
        return str2;
    }

    private ArrayList<String> getFilteredCondition(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ArrayList<String> arrayList3 = new ArrayList();
        if (arrayList == null || arrayList2 == null || arrayList.size() == 0 || arrayList2.size() == 0 || arrayList.size() != arrayList2.size()) {
            return arrayList3;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(arrayList2.get(i))) {
                arrayList3.add(arrayList.get(i));
            }
        }
        return arrayList3;
    }

    private String getFilteredCondition(String str, String str2) {
        return !WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(str2) ? str : null;
    }

    private ArrayList<String> getArrayData(JSONObject jSONObject, String str) {
        ArrayList<String> arrayList = new ArrayList();
        String[] split = jSONObject.optJSONArray(str).toString().replace("[", "").replace("]", "").replace("\"", "").trim().split(",");
        if (split != null && split.length > 0) {
            for (CharSequence charSequence : split) {
                if (!TextUtils.isEmpty(charSequence)) {
                    arrayList.add(charSequence);
                }
            }
        }
        return arrayList;
    }

    public void parseData(JSONObject jSONObject) {
        try {
            this.mFinish = jSONObject.optString("finished");
            this.mFinishDesc = jSONObject.optString("finishedDesc");
            this.mFreeCharge = jSONObject.optString("freecharge");
            this.mFreeChargeDes = jSONObject.optString("freechargeDesc");
            this.mTotalwords = jSONObject.optString("totalwords");
            this.mTotalwordsDesc = jSONObject.optString("totalwordsDesc");
            this.mUpdatetime = jSONObject.optString("updatetime");
            this.mUpdatetimeDesc = jSONObject.optString("updatetimeDesc");
            this.mVCateDescList = getArrayData(jSONObject, "vCateDesc");
            this.mVCateIdList = getArrayData(jSONObject, "vCateId");
            this.mVTagDescList = getArrayData(jSONObject, "vTagDesc");
            this.mVTagIdList = getArrayData(jSONObject, "vTagId");
            this.mIndex = jSONObject.optInt("pageIndex");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
