package com.qq.reader.module.bookstore.qnative;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.activity.CommitCommentActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.bookstore.qnative.activity.NativeAuthorPageActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.tencent.android.tpush.common.Constants;

/* compiled from: NativeAction */
public class c {
    private Bundle a = null;

    public c(Bundle bundle) {
        if (bundle != null) {
            this.a = bundle;
        } else {
            this.a = new Bundle();
        }
    }

    public synchronized Bundle a() {
        if (this.a == null) {
            this.a = new Bundle();
        }
        return this.a;
    }

    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if ("hall".equals(str)) {
            return "HallOfFamePage";
        }
        if ("detail".equals(str)) {
            return "DetailPage";
        }
        if ("categoryV3".equals(str)) {
            return "classify";
        }
        if ("topicstream".equals(str)) {
            return "topicpage";
        }
        if ("webpage".equals(str)) {
            return "webpage";
        }
        if ("topic".equals(str)) {
            return "bookclubmain";
        }
        return "";
    }

    public void a(a aVar) {
        if (aVar != null) {
            switch (this.a.getInt("function_type")) {
                case 0:
                    b(aVar);
                    return;
                case 1:
                    aVar.doFunction(this.a);
                    return;
                case 2:
                    aVar.doFunction(this.a);
                    b(aVar);
                    return;
                case 3:
                    aVar.doFunction(this.a);
                    return;
                default:
                    aVar.doFunction(this.a);
                    return;
            }
        }
    }

    private boolean b(a aVar) {
        if (aVar == null) {
            return false;
        }
        String string = this.a.getString("KEY_JUMP_PAGENAME");
        String string2 = this.a.getString("KEY_ACTION");
        Intent intent = new Intent();
        Context fromActivity = aVar.getFromActivity();
        if (fromActivity == null) {
            return false;
        }
        Class cls;
        if (string == null || string.length() == 0) {
            a().putString("KEY_JUMP_PAGENAME", a(string2));
        }
        if ("DetailPage".equals(string)) {
            cls = NativeBookStoreConfigDetailActivity.class;
        } else if ("bookclubtopic".equals(string)) {
            cls = CommitCommentActivity.class;
        } else if ("webpage".equals(string)) {
            cls = WebBrowserForContents.class;
            this.a.putString("com.qq.reader.WebContent", this.a.getString("com.qq.reader.WebContent"));
            intent.putExtra("com.qq.reader.WebContent", this.a.getString("com.qq.reader.WebContent"));
        } else if ("GoodWriter_MainPage".equals(string)) {
            cls = NativeAuthorPageActivity.class;
        } else {
            cls = NativeBookStoreTwoLevelActivity.class;
        }
        intent.setClass(fromActivity, cls);
        intent.putExtras(this.a);
        boolean z = this.a.getBoolean("newactivitywithresult", false);
        int i = this.a.getInt("newactivitywithresult_requestcode", Constants.ERRORCODE_UNKNOWN);
        if (z) {
            ((Activity) fromActivity).startActivityForResult(intent, i);
        } else {
            fromActivity.startActivity(intent);
        }
        if (!"DetailPage".equals(string)) {
            StatisticsManager.a().a(1).a(this.a).c();
        }
        return true;
    }

    public String b(String str) {
        return a(e.h, str);
    }

    public String a(String str, String str2) {
        String str3;
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append(str2);
        if ((d.c() + "").equals("0")) {
            str3 = "";
        }
        str3 = this.a.getString("URL_BUILD_PERE_COLS");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&cids=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_LCOLS");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&lcids=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ADVS");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&adids=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("KEY_ACTIONTAG");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&actionTag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("KEY_ACTIONID");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&actionId=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ACTION_FLAG");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&actionFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_FINISH_COUNT");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&finishCount=");
            stringBuilder.append(str3);
        }
        long j = this.a.getLong("URL_BUILD_PERE_BOOK_ID", 0);
        if (j != 0) {
            stringBuilder.append(GetVoteUserIconsTask.BID);
            stringBuilder.append(j);
        }
        int i = this.a.getInt("URL_BUILD_PERE_CLASSIFY_AREA", -1);
        if (i > 0) {
            stringBuilder.append("&area=");
            stringBuilder.append(i);
        }
        i = this.a.getInt("URL_BUILD_PERE_CHAPTER_ID", 0);
        if (i != 0) {
            stringBuilder.append("&chapterid=");
            stringBuilder.append(i);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ISJZQMCIDS");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&jzqmcids=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ISLMTCIDS");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&lmtcids=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_REALTIME_REC");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&realTimeRec=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ISSTRRECFLAG");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&strRecFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ISRECFLAG");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&recFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_ISRANKFLAG");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&rankFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("KEY_ACTION");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&action=");
            stringBuilder.append(str3);
        }
        j = this.a.getLong("KEY_PAGEINDEX", 1);
        if (j > 0) {
            stringBuilder.append("&pagestamp=");
            stringBuilder.append(j);
        }
        str3 = this.a.getString("URL_BUILD_PERE_CATEGORY");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&categoryFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_AUDIO_CATEGORY");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&audioCategoryFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_RANK");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&rankFlag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_BOOK_COLLECT");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&hastopic=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_TAG_REC");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&recTag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_BOOK_PACK");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&hasbag=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("COMMENT_ID");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&commentid=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_SIGNAL");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&signal=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_PAGESIZE");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&ps=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_PAGENUMBER");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append("&pn=");
            stringBuilder.append(str3);
        }
        str3 = this.a.getString("URL_BUILD_PERE_END_TIME");
        if (str3 != null && str3.length() > 0) {
            stringBuilder.append(GetVoteUserIconsTask.TIME);
            stringBuilder.append(str3);
        }
        Object string = this.a.getString("topiccomments_tid");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&tid=");
            stringBuilder.append(string);
        }
        i = this.a.getInt("CTYPE");
        if (i > 0) {
            stringBuilder.append("&ctype=");
            stringBuilder.append(i);
        }
        string = this.a.getString("TOPIC_ID");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&tid=");
            stringBuilder.append(string);
        }
        string = this.a.getString("ABTEST_PARAM");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&plan=");
            stringBuilder.append(string);
        }
        string = l.b(this.a.getString(s.STATPARAM_KEY));
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&");
            stringBuilder.append(string);
        }
        string = this.a.getString("URL_BUILD_PERE_DISCOUNT_BUY");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&discount=");
            stringBuilder.append(string);
        }
        string = this.a.getString("URL_BUILD_PERE_RENT");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&rentcids=");
            stringBuilder.append(string);
        }
        string = this.a.getString("bids");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&bids=");
            stringBuilder.append(string);
        }
        string = this.a.getString("URL_BUILD_VIP_FREE");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&vipcids=").append(string);
        }
        string = this.a.getString("bidsincid");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&bidsincid=");
            stringBuilder.append(string);
        }
        string = this.a.getString("cidincate");
        if (!TextUtils.isEmpty(string)) {
            stringBuilder.append("&cidincate=");
            stringBuilder.append(string);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.indexOf("?&") != -1) {
            stringBuilder2 = stringBuilder2.replace("?&", "?");
        }
        com.qq.reader.common.monitor.debug.c.a("native", "url " + stringBuilder2);
        return stringBuilder2;
    }
}
