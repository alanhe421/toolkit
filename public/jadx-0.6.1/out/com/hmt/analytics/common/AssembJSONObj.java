package com.hmt.analytics.common;

import android.content.Context;
import com.hmt.analytics.HMTAgent;
import com.hmt.analytics.objects.ParamList;
import com.hmt.analytics.objects.PostObjAction;
import com.tencent.android.tpush.common.Constants;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class AssembJSONObj {
    public static JSONObject getErrorListJSONObj(String str, Context context) {
        JSONObject paramList;
        JSONException e;
        String activityName = CommonUtil.getActivityName(context, 1);
        JSONObject jSONObject = new JSONObject();
        try {
            paramList = ParamList.getParamList(context, "error");
            try {
                paramList.put("stack_trace", str);
                paramList.put(Constants.FLAG_ACTIVITY_NAME, activityName);
                paramList.put("type", "error");
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return paramList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            paramList = jSONObject;
            e = jSONException;
            e.printStackTrace();
            return paramList;
        }
        return paramList;
    }

    public static JSONObject getLaunchActivityJSONObj(Context context) {
        JSONObject paramList;
        JSONException e;
        String sessionId = HMTAgent.getSessionId(context);
        try {
            paramList = ParamList.getParamList(context, Constants.FLAG_ACTIVITY_NAME);
            try {
                paramList.put("session_id", sessionId);
                paramList.put(Constants.FLAG_ACTIVITY_NAME, "hmt_launch");
                String time = CommonUtil.getTime();
                paramList.put("start_ts", time);
                paramList.put("end_ts", time);
                paramList.put("duration", "0");
                paramList.put("_activity", "hmt_launch");
                String[] unTracked = CommonUtil.getUnTracked(context);
                if (!CommonUtil.isUnTracked(unTracked, "_imei").booleanValue()) {
                    paramList.put("_imei", CommonUtil.get_imei(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_androidid").booleanValue()) {
                    paramList.put("_androidid", CommonUtil.getAndroidId(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_mac").booleanValue()) {
                    paramList.put("_mac", CommonUtil.get_mac(context));
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return paramList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            paramList = null;
            e = jSONException;
            e.printStackTrace();
            return paramList;
        }
        return paramList;
    }

    public static JSONObject getActionJOSNobj(PostObjAction postObjAction, Context context) {
        JSONObject paramList;
        JSONException e;
        JSONObject jSONObject = new JSONObject();
        try {
            paramList = ParamList.getParamList(context, SocialConstants.PARAM_ACT);
            try {
                paramList.put("act_name", postObjAction.getActName());
                paramList.put("act_count", postObjAction.getActCount());
                paramList.put(Constants.FLAG_ACTIVITY_NAME, postObjAction.getActivity());
                String[] unTracked = CommonUtil.getUnTracked(context);
                if (!CommonUtil.isUnTracked(unTracked, "_imei").booleanValue()) {
                    paramList.put("_imei", CommonUtil.get_imei(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_androidid").booleanValue()) {
                    paramList.put("_androidid", CommonUtil.getAndroidId(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_mac").booleanValue()) {
                    paramList.put("_mac", CommonUtil.get_mac(context));
                }
            } catch (JSONException e2) {
                e = e2;
                CommonUtil.printLog("HMTAgent", "json error in emitCustomLogReport");
                e.printStackTrace();
                return paramList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            paramList = jSONObject;
            e = jSONException;
            CommonUtil.printLog("HMTAgent", "json error in emitCustomLogReport");
            e.printStackTrace();
            return paramList;
        }
        return paramList;
    }

    public static JSONObject getReqJOSNobj(String str, String str2, Context context) {
        JSONObject reqParamList;
        JSONException e;
        JSONObject jSONObject = new JSONObject();
        try {
            reqParamList = ParamList.getReqParamList(context);
            try {
                reqParamList.put(SocialConstants.PARAM_URL, str);
                reqParamList.put("method", str2);
            } catch (JSONException e2) {
                e = e2;
                CommonUtil.printLog("HMTAgent", "json error in emitCustomLogReport");
                e.printStackTrace();
                return reqParamList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            reqParamList = jSONObject;
            e = jSONException;
            CommonUtil.printLog("HMTAgent", "json error in emitCustomLogReport");
            e.printStackTrace();
            return reqParamList;
        }
        return reqParamList;
    }
}
