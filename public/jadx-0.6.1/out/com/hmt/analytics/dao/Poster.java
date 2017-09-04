package com.hmt.analytics.dao;

import android.content.Context;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.objects.PostObjAction;

public class Poster {
    public static PostObjAction GenerateEventObj(Context context, PostObjAction postObjAction) {
        PostObjAction postObjAction2 = new PostObjAction(postObjAction);
        postObjAction2.setActivity(CommonUtil.getActivityName(context, 1));
        postObjAction2.setUa(CommonUtil.getAppKey(context));
        postObjAction2.setTs(CommonUtil.getTime());
        postObjAction2.setAppVersion(CommonUtil.getAppVersion(context));
        return postObjAction2;
    }
}
