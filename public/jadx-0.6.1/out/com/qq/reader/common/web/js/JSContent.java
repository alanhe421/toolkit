package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.OneBookNoteActivity;
import com.qq.reader.activity.ProfileLevelActivity;
import com.qq.reader.activity.RankWebActivity;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.VIPBrowser;
import com.qq.reader.activity.WebBookDetailActivity;
import com.qq.reader.activity.WebBookRewardActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.tesla.soload.SoLoadCore;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSContent extends b {
    private Activity a;
    private a b;

    public interface a {
        void f(String str);

        void g(String str);
    }

    public JSContent(Activity activity) {
        this.a = activity;
    }

    public void setStatInfo(String str) {
        try {
            String optString = new JSONObject(str).optString("pagename");
            if (this.a instanceof WebBrowserForContents) {
                WebBrowserForContents webBrowserForContents = (WebBrowserForContents) this.a;
                webBrowserForContents.setStatPageName(optString);
                webBrowserForContents.statPageResume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIsBackToPage(String str) {
        if (this.a instanceof WebBrowserForContents) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    ((WebBrowserForContents) this.a).a("true".equals(str.toLowerCase()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setBackKeyCallback(String str) {
        if (this.a instanceof WebBrowserForContents) {
            try {
                ((WebBrowserForContents) this.a).a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setWebTitlebarIcon(String str) {
        c.e("jscontent", str);
        if (this.a instanceof WebBrowserForContents) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                WebBrowserForContents webBrowserForContents = (WebBrowserForContents) this.a;
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("title");
                    String optString2 = jSONObject.optString("callback");
                    if (!(TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2))) {
                        if (optString.equals("addFavor")) {
                            webBrowserForContents.a(optString2, jSONObject.optInt("status"), jSONObject.optBoolean("favorfaild"));
                        } else if (optString.equals("shareTopic")) {
                            webBrowserForContents.h(optString2);
                        }
                    }
                }
            } catch (Exception e) {
                c.e("JSContent", e.getMessage());
            }
        }
    }

    public void setWebTitlebarCheckIcon(String str) {
        c.e("jscontent", str);
        if (this.a instanceof WebBrowserForContents) {
            try {
                ((WebBrowserForContents) this.a).a(Boolean.valueOf(str.equals("1")));
            } catch (Exception e) {
                c.e("JSContent", e.getMessage());
            }
        }
    }

    @Deprecated
    public void openTopicComment(String str, String str2) {
        o.e(this.a, str, str2, null);
    }

    @Deprecated
    public void openTopicDiscuss(String str, String str2) {
        o.f(this.a, str, str2, null);
    }

    @Deprecated
    public boolean openDetail(String str) {
        if (com.qq.reader.qurl.c.a(str)) {
            try {
                com.qq.reader.qurl.c.a(this.a, str);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
        boolean z;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("from");
            int optInt2 = jSONObject.optInt("pagecode", 1000);
            Intent intent = new Intent();
            String optString;
            long optLong;
            String optString2;
            long optLong2;
            switch (optInt2) {
                case 1000:
                    optString = jSONObject.optString(SocialConstants.PARAM_URL, "");
                    if (this.a instanceof WebBrowserForContents) {
                        ((WebBrowserForContents) this.a).c(optString);
                    } else {
                        intent.setClass(this.a, WebBrowserForContents.class);
                        intent.putExtra("com.qq.reader.WebContent", optString);
                        intent.putExtra("com.qq.reader.WebContent.from", optInt);
                        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                        this.a.startActivity(intent);
                    }
                    if (this.a instanceof ReaderPageActivity) {
                        ((ReaderPageActivity) this.a).v();
                        break;
                    }
                    break;
                case 1001:
                    optLong = jSONObject.optLong("bid");
                    optString2 = jSONObject.optString(s.STATPARAM_KEY, "");
                    if (this.a instanceof WebBookDetailActivity) {
                        Bundle bundle = new Bundle();
                        bundle.putLong("bid", optLong);
                        bundle.putString("extraurl", optString2);
                        ((WebBookDetailActivity) this.a).b(bundle);
                    } else {
                        intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
                        intent.putExtra("URL_BUILD_PERE_BOOK_ID", optLong);
                        intent.putExtra(s.STATPARAM_KEY, optString2);
                        intent.putExtra("com.qq.reader.WebContent.from", optInt);
                        intent.setClass(this.a, NativeBookStoreConfigDetailActivity.class);
                        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                        this.a.startActivity(intent);
                    }
                    if (this.a instanceof ReaderPageActivity) {
                        ((ReaderPageActivity) this.a).v();
                        break;
                    }
                    break;
                case 1002:
                    optLong = jSONObject.optLong("bid");
                    optInt = jSONObject.optInt("tabindex");
                    optString = jSONObject.optString("extraurl", "");
                    optString2 = jSONObject.optString("title");
                    intent.setClass(this.a, WebBookRewardActivity.class);
                    intent.putExtra("URL_BUILD_PERE_BOOK_ID", optLong);
                    intent.putExtra("PARA_TYPE_REWARD_TAB_INDEX", optInt);
                    intent.putExtra("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", optString);
                    intent.putExtra("PARA_TYPE_BOOK_TITLE", optString2);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    this.a.startActivity(intent);
                    if (this.a instanceof ReaderPageActivity) {
                        ((ReaderPageActivity) this.a).v();
                        break;
                    }
                    break;
                case 1003:
                    optInt = jSONObject.optInt("selected");
                    JSONArray optJSONArray = jSONObject.optJSONArray("urls");
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("titles");
                    String optString3 = jSONObject.optString("pagetitle");
                    Intent intent2 = new Intent();
                    Serializable arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("book_url", optJSONArray.opt(i).toString());
                        arrayList.add(new TabInfo(WebBrowserFragment.class, "", optJSONArray2.optString(i).toString(), hashMap));
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("tablist", arrayList);
                    bundle2.putString("title", optString3);
                    bundle2.putInt("select", optInt);
                    intent2.putExtra("info", bundle2);
                    intent2.setClass(this.a, ProfileLevelActivity.class);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    this.a.startActivity(intent2);
                    break;
                case 1004:
                    optInt = jSONObject.optInt("tabindex");
                    if (this.a instanceof WebBookDetailActivity) {
                        ((WebBookDetailActivity) this.a).b(optInt);
                        break;
                    }
                    break;
                case 1005:
                    optString2 = jSONObject.optString(SocialConstants.PARAM_URL, "");
                    intent.setClass(this.a, RankWebActivity.class);
                    intent.putExtra("com.qq.reader.WebContent", optString2);
                    intent.putExtra("need_tip", true);
                    intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    this.a.startActivity(intent);
                    break;
                case 1006:
                    optString2 = jSONObject.optString(SocialConstants.PARAM_URL, "");
                    intent.setClass(this.a, RankWebActivity.class);
                    intent.putExtra("com.qq.reader.WebContent", optString2);
                    intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    this.a.startActivity(intent);
                    break;
                case 1007:
                    intent.setClass(this.a, OneBookNoteActivity.class);
                    intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
                    com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                    this.a.startActivity(intent);
                    j.a(7, 3);
                    break;
                case 1009:
                    optLong2 = jSONObject.optLong("bid");
                    optInt = jSONObject.optInt("ctype");
                    if (optInt != 0) {
                        if (optInt == 4) {
                            o.a(this.a, optLong2, jSONObject.optString("title"), new JumpActivityParameter());
                            break;
                        }
                    }
                    o.a(this.a, Long.valueOf(optLong2), null, 0, new JumpActivityParameter());
                    i.a("event_C56", null, ReaderApplication.getApplicationImp());
                    break;
                    break;
                case 1010:
                    boolean z2;
                    long optLong3 = jSONObject.optLong("bid");
                    optString = jSONObject.optString("commentid");
                    int optInt3 = jSONObject.optInt("ctype");
                    int optInt4 = jSONObject.optInt("index", 2);
                    int optInt5 = jSONObject.optInt("next", 20);
                    if (jSONObject.optInt("lcoate") == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    o.a(this.a, Long.valueOf(optLong3), optString, optInt3, null, optInt4, optInt5, z2, 0, null);
                    break;
                case 1011:
                    i.a("event_C56", null, ReaderApplication.getApplicationImp());
                    o.a(this.a, Long.valueOf(jSONObject.optLong("bid")), new JumpActivityParameter().a(1002));
                    break;
                case 1012:
                    o.b(this.a, null);
                    break;
                case com.tencent.qalsdk.base.a.c /*1013*/:
                    o.d(this.a, null);
                    break;
                case com.tencent.qalsdk.base.a.d /*1014*/:
                    o.e(this.a, null);
                    break;
                case com.tencent.qalsdk.base.a.e /*1015*/:
                    o.a(this.a, jSONObject.optString("bid"), 1, 0, null);
                    break;
                case com.tencent.qalsdk.base.a.f /*1016*/:
                    String optString4 = jSONObject.optString("mTitle");
                    optLong2 = jSONObject.optLong("mActionid");
                    o.b(this.a, optString4, String.valueOf(optLong2), jSONObject.optString("mActiontag"), null);
                    break;
                case 1018:
                    o.a(this.a, null, new JumpActivityParameter());
                    break;
                case 1019:
                    o.d(this.a, null, jSONObject.optString("actionTag"), null);
                    break;
                case 1020:
                    o.l(this.a, null);
                    break;
                case 1021:
                    o.b(this.a, null, null);
                    break;
                case 1022:
                    o.a(this.a, null, 0, null);
                    break;
                case 1023:
                    o.a(this.a, null, jSONObject.optInt("currentItem"), null);
                    break;
            }
            z = true;
        } catch (Exception e2) {
            e2.printStackTrace();
            z = false;
        }
        return z;
    }

    @Deprecated
    public void openSoSo(String str) {
        this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    @Deprecated
    public void openExternal(String str) {
        try {
            String string = new JSONObject(str).getString(SocialConstants.PARAM_URL);
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ JSContent a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                        this.a.a.getWindow().setWindowAnimations(16973824);
                    }
                }
            });
            Intent intent = new Intent();
            intent.setClass(this.a, VIPBrowser.class);
            intent.putExtra("com.qq.reader.webbrowser.url", a(string));
            intent.putExtra("com.qq.reader.webbrowser.title", R.string.dialog_vip);
            this.a.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String a(String str) {
        if (str == null) {
            return e.c;
        }
        return (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("file://")) ? str : e.c + str;
    }

    @Deprecated
    public void openMonthly(String str) {
        i.a("event_C76", null, this.a);
        j.a(75, 2);
        f.d("jscontent", "openMonthly " + str);
        if (this.b != null) {
            this.b.g(str);
        }
    }

    public void setDialogCloseCallBack(a aVar) {
        this.b = aVar;
    }

    public void closeDialog(String str) {
        f.d("jscontent", "closeDialog " + str);
        if (this.b != null) {
            this.b.f(str);
        }
    }

    public String getUserReadBookId() {
        List g = com.qq.reader.common.db.handle.i.c().g();
        if (g != null) {
            return a((Mark[]) g.toArray(new Mark[g.size()]));
        }
        return "";
    }

    private String a(Mark[] markArr) {
        int i;
        long j = 0;
        if (markArr != null) {
            int length = markArr.length;
            i = -1;
            for (int i2 = 0; i2 < length; i2++) {
                Mark mark = markArr[i2];
                mark.setLastRead(false);
                if (mark.getReadTime() > j) {
                    j = mark.getReadTime();
                    mark.setLastRead(true);
                    i = i2;
                }
            }
        } else {
            i = -1;
        }
        if (i != -1) {
            return markArr[i].getId();
        }
        return "";
    }

    public String getUsedSkinId() {
        return d.bS(ReaderApplication.getApplicationImp());
    }

    public void executeQurl(final String str) {
        if (this.a != null && !this.a.isFinishing()) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ JSContent b;

                public void run() {
                    if (!TextUtils.isEmpty(str)) {
                        String str;
                        try {
                            str = new String(com.qq.reader.common.utils.a.a.b(str), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            str = null;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            String substring;
                            int lastIndexOf = str.lastIndexOf("&need_login=1");
                            int i;
                            if (lastIndexOf != -1) {
                                substring = str.substring(0, lastIndexOf);
                                i = 1;
                            } else {
                                substring = str;
                                i = 0;
                            }
                            final com.qq.reader.qurl.b anonymousClass1 = new com.qq.reader.qurl.b(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public boolean a(Message message) {
                                    switch (message.what) {
                                        case 1:
                                            return com.qq.reader.module.rookie.presenter.a.a().a(message);
                                        default:
                                            return false;
                                    }
                                }
                            };
                            if ((!com.qq.reader.common.login.c.b() && r0 != 0) || this.b.a == null || this.b.a.isFinishing()) {
                                com.qq.reader.common.login.a anonymousClass2 = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass2 c;

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                try {
                                                    if (this.c.b.a != null && !this.c.b.a.isFinishing()) {
                                                        com.qq.reader.qurl.c.a(this.c.b.a, substring, anonymousClass1, null);
                                                        return;
                                                    }
                                                    return;
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    return;
                                                }
                                            default:
                                                return;
                                        }
                                    }
                                };
                                if ((this.b.a instanceof ReaderBaseActivity) && !this.b.a.isFinishing()) {
                                    ((ReaderBaseActivity) this.b.a).setLoginNextTask(anonymousClass2);
                                    ((ReaderBaseActivity) this.b.a).startLogin();
                                    return;
                                }
                                return;
                            }
                            try {
                                com.qq.reader.qurl.c.a(this.b.a, substring, anonymousClass1, null);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public void obtainGift(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                com.qq.reader.module.rookie.presenter.a.a().a(new JSONObject(str).optInt("id"), true);
            }
        } catch (Exception e) {
            c.e("JSContent", e.getMessage());
        }
    }
}
