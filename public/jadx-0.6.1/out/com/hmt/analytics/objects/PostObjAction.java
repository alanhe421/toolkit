package com.hmt.analytics.objects;

import android.content.Context;
import com.hmt.analytics.common.CommonUtil;
import com.tencent.midas.api.APMidasPayAPI;

public class PostObjAction {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Context g;

    public PostObjAction(PostObjAction postObjAction) {
        if (postObjAction == null) {
            this.b = "";
            this.a = "";
            return;
        }
        this.b = postObjAction.getActCount();
        this.a = postObjAction.getActName();
    }

    public PostObjAction(String str, String str2, Context context) {
        this.a = str;
        this.b = str2;
        this.g = context;
        this.c = CommonUtil.getTime();
        this.d = CommonUtil.getActivityName(context, 1);
        this.f = CommonUtil.getAppKey(context);
        this.e = CommonUtil.getAppVersion(context);
    }

    public PostObjAction(String str, String str2, String str3, String str4, String str5, String str6) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
    }

    public boolean verification() {
        if (!getActCount().contains("-") && getActCount() != null && !getActCount().equals("")) {
            return true;
        }
        CommonUtil.printLog(APMidasPayAPI.ENV_TEST, getActCount());
        return false;
    }

    public String getTs() {
        return this.c;
    }

    public void setTs(String str) {
        this.c = str;
    }

    public String getActivity() {
        return this.d;
    }

    public void setActivity(String str) {
        this.d = str;
    }

    public String getAppVersion() {
        return this.e;
    }

    public void setAppVersion(String str) {
        this.e = str;
    }

    public String getUa() {
        return this.f;
    }

    public void setUa(String str) {
        this.f = str;
    }

    public String getActName() {
        return this.a;
    }

    public void setActName(String str) {
        this.a = str;
    }

    public String getActCount() {
        return this.b;
    }

    public void setActCount(String str) {
        this.b = str;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.c == null ? 0 : this.c.hashCode()) + (((this.a == null ? 0 : this.a.hashCode()) + (((this.f == null ? 0 : this.f.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.e != null) {
            i = this.e.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PostObjAction postObjAction = (PostObjAction) obj;
        if (this.b == null) {
            if (postObjAction.b != null) {
                return false;
            }
        } else if (!this.b.equals(postObjAction.b)) {
            return false;
        }
        if (this.d == null) {
            if (postObjAction.d != null) {
                return false;
            }
        } else if (!this.d.equals(postObjAction.d)) {
            return false;
        }
        if (this.f == null) {
            if (postObjAction.f != null) {
                return false;
            }
        } else if (!this.f.equals(postObjAction.f)) {
            return false;
        }
        if (this.a == null) {
            if (postObjAction.a != null) {
                return false;
            }
        } else if (!this.a.equals(postObjAction.a)) {
            return false;
        }
        if (this.c == null) {
            if (postObjAction.c != null) {
                return false;
            }
        } else if (!this.c.equals(postObjAction.c)) {
            return false;
        }
        if (this.e == null) {
            if (postObjAction.e != null) {
                return false;
            }
            return true;
        } else if (this.e.equals(postObjAction.e)) {
            return true;
        } else {
            return false;
        }
    }
}
