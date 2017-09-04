package com.hmt.analytics.objects;

import android.content.Context;
import android.os.Handler;
import com.hmt.analytics.common.AssembJSONObj;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.HMTConstants;
import com.hmt.analytics.common.HMTTool;
import com.hmt.analytics.interfaces.HMTCallback;
import com.hmt.analytics.util.HParams;
import com.tencent.open.SocialConstants;

public class ActionController {
    public static boolean postActionInfo(Handler handler, Context context, PostObjAction postObjAction, HParams hParams, HMTCallback hMTCallback) {
        try {
            if (postObjAction.verification()) {
                HMTTool.sendData(context, HMTTool.addProperty(AssembJSONObj.getActionJOSNobj(postObjAction, context), hParams), HMTConstants.f, "act_list", HMTConstants.n, SocialConstants.PARAM_ACT, hMTCallback);
                return true;
            }
            CommonUtil.printLog("HMTAgent", "Illegal value of acc in act_list");
            return false;
        } catch (Exception e) {
            CommonUtil.printLog("HMTAgent", "Exception occurred in act_list");
            e.printStackTrace();
            return false;
        }
    }
}
