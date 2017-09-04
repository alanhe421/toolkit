package com.qq.reader;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.a.a;
import android.text.TextUtils;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.SplashActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.u;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.io.UnsupportedEncodingException;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeContext extends Activity implements a, com.qq.reader.module.bookstore.qnative.c.a {
    u a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.a = new u(new u.a(this) {
            final /* synthetic */ TypeContext a;

            {
                this.a = r1;
            }

            public void a() {
                ao.a(this.a.getApplicationContext());
                Intent intent = this.a.getIntent();
                if (intent != null) {
                    try {
                        this.a.a(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.a.finish();
                    }
                }
            }
        });
        this.a.a((Activity) this);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.a.a(this, i, strArr, iArr);
    }

    private void a(Intent intent) throws Exception {
        com.qq.reader.common.c.a.a(false);
        String scheme = intent.getScheme();
        String dataString;
        String stringExtra;
        Intent intent2;
        if (scheme != null && scheme.equals("uniteqqreader")) {
            dataString = intent.getDataString();
            if (TextUtils.isEmpty(dataString) || !dataString.contains("promotionpage")) {
                JumpActivityParameter jumpActivityParameter = null;
                if (ReaderApplication.isAppInForegroundResumeCount <= 0) {
                    jumpActivityParameter = new JumpActivityParameter().a((int) Constants.ERRORCODE_UNKNOWN);
                }
                stringExtra = intent.getStringExtra(s.ORIGIN);
                if (stringExtra != null && "event_D204".equals(stringExtra)) {
                    i.a("event_D204", null, ReaderApplication.getApplicationImp());
                }
                c.a(this, dataString, jumpActivityParameter);
                if (jumpActivityParameter == null || jumpActivityParameter.c() == -1) {
                    finish();
                    return;
                }
                return;
            }
            scheme = dataString.substring(dataString.indexOf("text"), dataString.length());
            intent2 = new Intent();
            intent2.setClass(this, WebBrowserForContents.class);
            intent2.putExtra("com.qq.reader.WebContent", "http://pages.book.qq.com/yyb?" + scheme);
            intent2.setFlags(SigType.WLOGIN_QRPUSH);
            startActivity(intent2);
            finish();
        } else if (scheme != null && scheme.equals("androidqqreader50")) {
            if (intent.getData().getHost().equalsIgnoreCase("nativepage")) {
                scheme = intent.getData().getPath().substring(1);
                if (scheme != null && scheme.equals("LimitTimeDiscountBuy")) {
                    o.h(this, intent.getData().getQueryParameter("starttime"), intent.getData().getQueryParameter("bids"), null);
                } else if (scheme != null && scheme.equals("Feed")) {
                    o.d((Activity) this, null);
                } else if (scheme != null && scheme.equals("Book_Stacks")) {
                    o.e((Activity) this, null);
                } else if (scheme != null && scheme.equals("ReadGene")) {
                    r2 = new Intent(this, MyReadingGeneActivity.class);
                    com.qq.reader.common.c.a.a(false);
                    startActivity(r2);
                } else if (scheme.equals("Book_Classify_Detail")) {
                    o.a((Activity) this, intent.getData().getQueryParameter("actionTag"), intent.getData().getQueryParameter("actionId"), new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("Rank")) {
                    o.b((Activity) this, null, null, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("Rank_Detail")) {
                    o.b((Activity) this, intent.getData().getQueryParameter("mTitle"), intent.getData().getQueryParameter("mActionid"), intent.getData().getQueryParameter("mActiontag"), new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("ProfileLevel")) {
                    o.a((Activity) this, 0, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("MonthPrivilege")) {
                    o.h((Activity) this, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("TodayTask")) {
                    o.i(this, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("CommentSquare")) {
                    o.a((Activity) this, null, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("SpecialTopic")) {
                    o.d((Activity) this, null, intent.getData().getQueryParameter("mActiontag"), new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("MonthArea")) {
                    o.l(this, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("TodayFree")) {
                    o.b((Activity) this, null, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("HallOfFame")) {
                    Object queryParameter = intent.getData().getQueryParameter("currentItem");
                    r2 = 0;
                    if (!TextUtils.isEmpty(queryParameter)) {
                        r2 = Integer.valueOf(queryParameter).intValue();
                    }
                    o.a((Activity) this, null, r2, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("HallOfFameDetail")) {
                    o.a((com.qq.reader.module.bookstore.qnative.c.a) this, intent.getData().getQueryParameter("name"));
                } else if (scheme.equals("FeedBack")) {
                    o.m(this, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("OfficialClassifyComment")) {
                    Object queryParameter2 = intent.getData().getQueryParameter("bid");
                    r5 = intent.getData().getQueryParameter("title");
                    long j = 0;
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        j = (long) Integer.valueOf(queryParameter2).intValue();
                    }
                    o.a((Activity) this, j, r5, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("H5Game")) {
                    o.a((Activity) this, intent.getData().getQueryParameter(SocialConstants.PARAM_URL), true, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                }
            }
            finish();
        } else if (scheme == null || !(scheme.equals("AndroidQQReader") || scheme.equals("androidqqreader"))) {
            Bundle extras = intent.getExtras();
            CharSequence charSequence = null;
            if (extras != null) {
                charSequence = extras.getString("ERROR_STR");
                if (extras.getBoolean("widget")) {
                    intent.setFlags(268468224);
                    j.a(90, 0);
                }
            }
            if (charSequence == null) {
                b.a(intent, this);
                finish();
                return;
            }
            new Builder(this).setIcon(17301543).setTitle(R.string.dialog_readfailed_title).setMessage(charSequence).setPositiveButton(R.string.alert_dialog_ok, new OnClickListener(this) {
                final /* synthetic */ TypeContext a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    this.a.finish();
                }
            }).create().show();
        } else {
            dataString = intent.getData().getHost();
            if (dataString.equalsIgnoreCase("webpage")) {
                scheme = intent.getData().getPath() + "?" + intent.getData().getQuery();
                intent2 = new Intent(this, WebBrowserForContents.class);
                intent2.putExtra("com.qq.reader.WebContent", scheme.substring(1));
                startActivity(intent2);
            } else if (dataString.equalsIgnoreCase("nativepage")) {
                dataString = intent.getData().getQueryParameter("bid");
                scheme = intent.getData().getPath().substring(1);
                if (scheme.equals("LBStoreConfigDetailActivity")) {
                    o.a((Activity) this, dataString, intent.getData().getQueryParameter("statInfo"), null, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("ReaderPageActivity")) {
                    o.a((Activity) this, dataString, -1, -1, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("CommentsList")) {
                    intent.getData().getQueryParameter("bname");
                    r2 = Integer.valueOf(intent.getData().getQueryParameter("ctype")).intValue();
                    if (r2 == 0) {
                        o.a((Activity) this, Long.valueOf(dataString), null, 0, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                        i.a("event_C56", null, ReaderApplication.getApplicationImp());
                    } else if (r2 == 4) {
                        o.a((Activity) this, Long.valueOf(dataString).longValue(), intent.getData().getQueryParameter("title"), new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                    }
                } else if (scheme.equals("BookCommentDetail")) {
                    stringExtra = intent.getData().getQueryParameter("commentid");
                    int intValue = Integer.valueOf(intent.getData().getQueryParameter("ctype")).intValue();
                    int i = 2;
                    r8 = 20;
                    try {
                        i = Integer.valueOf(intent.getData().getQueryParameter("index")).intValue();
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("TypeContext", e.getMessage());
                    }
                    try {
                        r8 = Integer.valueOf(intent.getData().getQueryParameter("next")).intValue();
                    } catch (Exception e2) {
                        com.qq.reader.common.monitor.debug.c.e("TypeContext", e2.getMessage());
                    }
                    Object queryParameter3 = intent.getData().getQueryParameter("lcoate");
                    boolean z = !TextUtils.isEmpty(queryParameter3) && queryParameter3.equals("1");
                    o.a(this, Long.valueOf(dataString), stringExtra, intValue, null, i, r8, z, 0, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                } else if (scheme.equals("SelectedComment")) {
                    o.a((Activity) this, intent.getData().getQueryParameter("topicid"), intent.getData().getQueryParameter("ctype"), null, null, new JumpActivityParameter().b(SigType.WLOGIN_QRPUSH));
                }
            } else if (dataString.equalsIgnoreCase("main")) {
                o.b((Activity) this, null);
            } else {
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(com.qq.reader.common.utils.a.a.b(intent.getData().toString().substring(scheme.length() + "://".length())), "utf-8"));
                        scheme = (String) jSONObject.get("id");
                        dataString = (String) jSONObject.get("title");
                        stringExtra = (String) jSONObject.get("author");
                        r5 = (String) jSONObject.get("downloadurl");
                        String str = (String) jSONObject.get("contentUrl");
                        int i2 = jSONObject.getInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
                        int i3 = jSONObject.getInt("type");
                        int i4 = jSONObject.getInt("chapterId");
                        String str2 = (String) jSONObject.get("chapterTitle");
                        int i5 = jSONObject.getInt("is_real_url");
                        String string = jSONObject.getString("coverurl");
                        String string2 = jSONObject.getString("format");
                        r8 = jSONObject.getInt("drm");
                        if (scheme == null || scheme.length() == 0 || dataString == null || str == null || i2 == 0) {
                            throw new JSONException("no key para");
                        }
                        Parcelable onlineTag = new OnlineTag(scheme, str, 0);
                        onlineTag.a(dataString).e(stringExtra).f(r5).c(i4).b(str2).e(i3).d(i2).f(i5).h(string).k(string2).i(r8);
                        r2 = new Intent();
                        r2.setClass(this, ReaderPageActivity.class);
                        intent.setFlags(SigType.WLOGIN_QRPUSH);
                        r2.putExtra("com.qq.reader.OnlineTag", onlineTag);
                        r2.putExtra("com.qq.reader.fromonline", true);
                        startActivity(r2);
                    } catch (JSONException e3) {
                        f.a("TypeContext", "server onlineinfo error");
                    }
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                }
            }
            finish();
        }
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.ERRORCODE_UNKNOWN) {
            Intent intent2 = new Intent();
            intent2.setClass(this, SplashActivity.class);
            intent2.setFlags(335544320);
            startActivity(intent2);
            finish();
            return;
        }
        finish();
    }
}
