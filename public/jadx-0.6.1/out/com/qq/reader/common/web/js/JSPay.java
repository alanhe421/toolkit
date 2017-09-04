package com.qq.reader.common.web.js;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.WebBrowser;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.a.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.onlineread.d;
import com.qq.reader.module.bookstore.charge.NativeBookStoreChargeAcitivty;
import com.qq.reader.module.bookstore.charge.NativeBookStoreMonthlyChargeAcitivty;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.PlugInFontsActivity;
import com.qq.reader.plugin.audiobook.MusicActivity;
import com.qq.reader.plugin.audiobook.MusicOnlineTag;
import com.qq.reader.plugin.m;
import com.qq.reader.view.af;
import org.json.JSONException;
import org.json.JSONObject;

public class JSPay extends b {
    private Activity a;
    private WebView b;
    private ProgressDialog c;
    private b d;

    public JSPay(Activity activity, WebView webView) {
        this.a = activity;
        this.b = webView;
    }

    public JSPay(Activity activity) {
        this.a = activity;
    }

    @Deprecated
    public void openVip() {
        if (this.a != null) {
            Intent intent = new Intent();
            intent.setClass(this.a, NativeBookStoreMonthlyChargeAcitivty.class);
            intent.putExtra("LOCAL_STORE_IN_TITLE", "开通包月VIP");
            this.a.startActivityForResult(intent, 20002);
        }
    }

    @Deprecated
    public void charge(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("coincount", "");
            final String optString2 = jSONObject.optString("paychannel", "");
            if (ao.s(optString)) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ JSPay a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.startCharge(this.a.a, 0);
                        this.a.payCancel();
                    }
                });
            } else {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ JSPay c;

                    public void run() {
                        com.qq.reader.common.charge.b.a(this.c.a, optString, optString2);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean startCharge(Activity activity, int i) {
        return startCharge(activity, i, "");
    }

    public void startChargeDirectly(Activity activity, int i) {
        if (!(activity instanceof ReaderBaseActivity) || ((ReaderBaseActivity) activity).isOnResume) {
            com.qq.reader.common.charge.b.a(activity, String.valueOf(i), null);
        }
    }

    public boolean startCharge(Activity activity, int i, String str) {
        if (activity == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(this.a, NativeBookStoreChargeAcitivty.class);
            intent.putExtra("LOCAL_STORE_IN_TITLE", "书币充值");
            intent.putExtra("chargetype", str);
            if (i > 0) {
                intent.putExtra("charge_prevalue", i);
            }
            if (this.a instanceof ReaderBaseActivity) {
                ((ReaderBaseActivity) this.a).disableUseAnimation();
            }
            this.a.startActivityForResult(intent, 20001);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean startCharge(Activity activity, int i, boolean z) {
        if (activity == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(this.a, NativeBookStoreChargeAcitivty.class);
            if (i > 0) {
                intent.putExtra("charge_prevalue", i);
            }
            intent.putExtra("LOCAL_STORE_IN_TITLE", "书币充值");
            if (z) {
                intent.putExtra("charge_redPacketValue", i);
            }
            ((ReaderBaseActivity) this.a).disableUseAnimation();
            this.a.startActivityForResult(intent, 20001);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Deprecated
    public void beforepay(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("id");
            com.qq.reader.common.monitor.a.b.a(new a(string, jSONObject.optString(s.ORIGIN)));
            if (this.a instanceof WebBrowserForContents) {
                ((WebBrowserForContents) this.a).e(string);
            } else if (this.a instanceof WebBrowser) {
                ((WebBrowser) this.a).d(string);
            }
        } catch (JSONException e) {
            f.a("JSPay", "server beforepay error");
            a(e);
        }
    }

    @Deprecated
    public void afterpay(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("code");
            jSONObject.getString("message");
            if (i == 0) {
                if (this.c != null && this.c.isShowing()) {
                    this.c.dismiss();
                }
                if (this.a instanceof WebBrowserForContents) {
                    ((WebBrowserForContents) this.a).u();
                } else if (this.a instanceof WebBrowser) {
                    ((WebBrowser) this.a).h();
                }
            } else if (this.c != null && this.c.isShowing()) {
                this.c.dismiss();
            }
        } catch (JSONException e) {
            f.a("JSPay", "server afterpay error");
            if (this.c != null && this.c.isShowing()) {
                this.c.dismiss();
            }
            a(e);
        }
    }

    private void a(JSONException jSONException) {
        if (jSONException instanceof JSONException) {
            showMessage(this.a.getApplicationContext(), "图书购买失败");
        } else {
            showMessage(this.a.getApplicationContext(), "图书购买失败");
        }
    }

    public static void showMessage(Context context, String str) {
        af.a(context, (CharSequence) str, 0).a();
    }

    @Deprecated
    public void doSucceed(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("message");
            String string2 = jSONObject.getString("id");
            if (string2 == null || string2.length() <= 0) {
                showMessage(this.a.getApplicationContext(), "客户端处理失败");
                return;
            }
            m.c().a(string2).b().c(0);
            showMessage(this.a.getApplicationContext(), string);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public void doDone(String str) {
        if (this.a instanceof PlugInFontsActivity) {
            ((PlugInFontsActivity) this.a).a(str);
        }
    }

    public void setPayOption(String str) {
        try {
            if (new JSONObject(str).getInt("autopay") == 1) {
                d.a = true;
            } else {
                d.a = false;
            }
        } catch (JSONException e) {
            d.a = false;
            e.printStackTrace();
        } catch (Exception e2) {
            d.a = false;
            e2.printStackTrace();
        }
    }

    @Deprecated
    public void payCancel() {
        if (this.a instanceof ReaderPageActivity) {
            ((ReaderPageActivity) this.a).z();
        } else if (this.a instanceof WebBrowserForContents) {
            ((WebBrowserForContents) this.a).v();
        } else if (this.a instanceof WebBrowser) {
            ((WebBrowser) this.a).i();
        } else if (this.a instanceof MusicActivity) {
            ((MusicActivity) this.a).f();
        } else if (this.a instanceof PlugInFontsActivity) {
            ((PlugInFontsActivity) this.a).b();
        }
    }

    public void payMonthVip(String str) {
        boolean z = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("month");
            if (jSONObject.optInt("autopay", 0) > 0) {
                z = true;
            }
            String optString = jSONObject.optString("servicecode");
            String optString2 = jSONObject.optString("productid");
            if (c.b()) {
                com.qq.reader.common.login.b.a c = c.c();
                com.qq.reader.common.charge.b bVar = new com.qq.reader.common.charge.b();
                if (c == null) {
                    return;
                }
                if (c.d() == 1) {
                    com.qq.reader.common.charge.b.a(this.a, z, optString, optString2);
                } else if (c.d() == 2) {
                    openVip();
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    @Deprecated
    public void payDone(String str) {
        try {
            String string;
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("type");
            String str2 = "";
            switch (i) {
                case 1:
                    string = jSONObject.getString("downloadurl");
                    break;
                case 2:
                    string = str2;
                    break;
                case 3:
                    if (this.a instanceof PlugInFontsActivity) {
                        ((PlugInFontsActivity) this.a).a(jSONObject.getString("id"));
                        return;
                    }
                    return;
                case 4:
                    if (this.a instanceof WebBrowserForContents) {
                        try {
                            long j = jSONObject.getLong("bid");
                            String string2 = jSONObject.getString("bname");
                            String string3 = jSONObject.getString("author");
                            String string4 = jSONObject.getString("reader");
                            String string5 = jSONObject.getString("btime");
                            String string6 = jSONObject.getString("price");
                            String string7 = jSONObject.getString("csize");
                            String string8 = jSONObject.getString("ctime");
                            long j2 = jSONObject.getLong("cid");
                            String string9 = jSONObject.getString("cname");
                            MusicOnlineTag musicOnlineTag = new MusicOnlineTag(j);
                            musicOnlineTag.setBname(string2).setAuthor(string3).setReader(string4).setBtime(string5).setPrice(string6).setCsize(string7).setCtime(string8).setCid(j2).setCname(string9);
                            ((WebBrowserForContents) this.a).a(musicOnlineTag);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 5:
                    if (this.a instanceof WebBrowserForContents) {
                        ((WebBrowserForContents) this.a).o();
                        return;
                    } else if (this.a instanceof MusicActivity) {
                        ((MusicActivity) this.a).e();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            if (this.a instanceof ReaderPageActivity) {
                ((ReaderPageActivity) this.a).a(i, string);
            } else if ((this.a instanceof WebBrowserForContents) || (this.a instanceof WebBrowser)) {
                payCancel();
                if (i == 1) {
                    jSONObject.put("id", jSONObject.getString("bid"));
                    new JSDownLoad(this.a).download(jSONObject.toString());
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    public void buyBook(String str) {
        if (this.d == null) {
            this.d = new b(this.a, this.b);
        }
        this.d.a(str);
    }
}
