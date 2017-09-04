package com.qq.reader.common.web.js;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.RemoteException;
import android.widget.DatePicker;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.TypeContext;
import com.qq.reader.activity.H5GameActivity;
import com.qq.reader.activity.NewLoginActivity;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.readertask.protocol.H5GameGetBalanceTask;
import com.qq.reader.common.readertask.protocol.H5GameGetOpenidTask;
import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;
import com.qq.reader.common.utils.ak;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.b.b;
import com.qq.reader.cservice.download.game.DownloadGameBroadcastReceiver;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.dicovery.a.a;
import com.qq.reader.module.dicovery.a.c;
import com.qq.reader.module.dicovery.a.d;
import com.qq.reader.view.af;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class JSGame extends b {
    private a a;
    private c b;
    private com.qq.reader.module.dicovery.a.b c;
    private d d;
    private d e;
    private H5GameActivity f;
    private JSPay g = new JSPay(this.f);
    private com.qq.reader.common.login.a h;
    private com.qq.reader.common.web.a.a i;

    public JSGame(H5GameActivity h5GameActivity, com.qq.reader.common.web.a.a aVar) {
        this.f = h5GameActivity;
        this.i = aVar;
    }

    public void showToast(String str) {
        af.a(this.f.getApplicationContext(), (CharSequence) str, 0).a();
    }

    public void setGameGetOpenidHandler(c cVar) {
        this.b = cVar;
    }

    public void setGameChargeHandler(a aVar) {
        this.a = aVar;
    }

    public void setGameGrantTicketHandler(d dVar) {
        this.d = dVar;
    }

    public void setGameGrantCoinHandler(d dVar) {
        this.e = dVar;
    }

    public void setNextLoginTask(com.qq.reader.common.login.a aVar) {
        this.h = aVar;
    }

    public String getuserid(String str, String str2) {
        f.d("JSGAME_LOG", "JS调getuserid成功");
        if (com.qq.reader.common.login.c.b()) {
            f.d("JSGAME_LOG", "relogin = " + str);
            if ("0".equals(str)) {
                f.d("JSGAME_LOG", "openid = " + this.f.a(0, ""));
                return this.f.a(0, "");
            } else if ("1".equals(str)) {
                ReaderTask h5GameGetOpenidTask = new H5GameGetOpenidTask(this.i);
                h5GameGetOpenidTask.setListener(this.b);
                h5GameGetOpenidTask.setWebJSCallBack(str2);
                g.a().a(h5GameGetOpenidTask);
            }
        }
        return "nologin";
    }

    public String getuserids(String str, String str2) {
        f.d("JSGAME_LOG", "JS调getuserids成功");
        if (com.qq.reader.common.login.c.b()) {
            f.d("JSGAME_LOG", "relogin = " + str);
            if ("0".equals(str)) {
                String b = this.f.b(0, "");
                f.d("JSGAME_LOG", "res string = " + b);
                return b;
            } else if ("1".equals(str)) {
                ReaderTask h5GameGetOpenidTask = new H5GameGetOpenidTask(this.i);
                h5GameGetOpenidTask.setListener(this.b);
                h5GameGetOpenidTask.setWebJSCallBack(str2);
                g.a().a(h5GameGetOpenidTask);
            }
        }
        return "nologin";
    }

    public String getnickname() {
        f.d("JSGAME_LOG", "JS调getnickname成功");
        if (com.qq.reader.common.login.c.b()) {
            return this.f.b();
        }
        return "";
    }

    public void getuserbalance_new(String str, String str2) {
        f.d("JSGAME_LOG", "JS调getuserbalnceasync成功");
        String str3 = "";
        if (this.i != null) {
            try {
                str3 = this.i.f();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        ReaderTask h5GameGetBalanceTask = new H5GameGetBalanceTask(this.i, str3, str, str2);
        h5GameGetBalanceTask.setHandler(this.c);
        g.a().a(h5GameGetBalanceTask);
    }

    public String getuserbalance() {
        f.d("JSGAME_LOG", "JS调getuserbalance成功");
        try {
            if (this.i.j()) {
                return this.f.c();
            }
            return "0";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Deprecated
    public void consume(String str, String str2, String str3, String str4, String str5) {
        f.d("JSGAME_LOG", "JS调consume old成功");
        a(str, str2, str3, str4, null, null, str5, 1001);
    }

    public void consume_new(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        a(str, str2, str3, str4, str5, str6, str7, 1002);
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        f.d("JSGAME_LOG", "JS调consume成功");
        String str8 = "";
        try {
            str8 = this.i.f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ReaderTask h5GameChargeTask = new H5GameChargeTask(str8, str, str2, str3, str4, str5, str6, this.i, i);
        try {
            this.a.b = Integer.parseInt(str3);
        } catch (NumberFormatException e2) {
            f.a("JSGAME_LOG", "money is null");
        }
        h5GameChargeTask.setListener(this.a);
        h5GameChargeTask.setWebJSCallBack(str7);
        g.a().a(h5GameChargeTask);
    }

    public void chargeBookMoney(String str) {
        f.d("JSGAME_LOG", "JS调chargeBookMoney成功");
        this.g.startCharge(this.f, 0);
    }

    public void goback() {
        f.d("JSGAME_LOG", "JS调goback成功");
        this.f.g();
    }

    public String get_version() {
        String d = this.f.d();
        f.d("JSGAME_LOG", "JS调get_version成功 " + d);
        return d;
    }

    public void setScreenDirection(String str) {
        f.d("JSGAME_LOG", "JS调setScreenDirection成功 " + str);
        if ("1".equals(str) && this.f.getRequestedOrientation() != 0) {
            this.f.setRequestedOrientation(0);
        } else if ("2".equals(str) && this.f.getRequestedOrientation() != 1) {
            this.f.setRequestedOrientation(1);
        }
    }

    public void share(String str, String str2, String str3, String str4) {
        f.d("JSGAME_LOG", "JS调share成功");
        this.f.a(str, str2, str3, str4);
    }

    public String ticket_balance() {
        f.d("JSGAME_LOG", "JS调ticket_balance成功");
        try {
            if (this.i == null || !this.i.j()) {
                return "0";
            }
            return this.f.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void grant_ticket(String str, String str2, String str3) {
        f.d("JSGAME_LOG", "JS调grant_ticket成功");
        Map hashMap = new HashMap();
        hashMap.put(H5GameGrantTicketTask.COMMON_BATCHID, str);
        hashMap.put(H5GameGrantTicketTask.COMMON_COUNT, str2);
        ReaderTask h5GameGrantTicketTask = new H5GameGrantTicketTask(hashMap, this.i, 110);
        try {
            this.d.b = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            f.a("JSGAME_LOG", "money is null");
        }
        h5GameGrantTicketTask.setListener(this.d);
        h5GameGrantTicketTask.setWebJSCallBack(str3);
        g.a().a(h5GameGrantTicketTask);
    }

    public void grant_game_coin(String str, String str2, String str3, String str4, String str5) {
        f.d("JSGAME_LOG", "JS调grant_ticket成功");
        Map hashMap = new HashMap();
        hashMap.put(H5GameGrantTicketTask.COMMON_BATCHID, str);
        hashMap.put(H5GameGrantTicketTask.COMMON_COUNT, str2);
        hashMap.put(H5GameGrantTicketTask.COIN_CLIENTORDERID, str3);
        hashMap.put(H5GameGrantTicketTask.COIN_MEMO, str4);
        ReaderTask h5GameGrantTicketTask = new H5GameGrantTicketTask(hashMap, this.i, 120);
        try {
            this.e.b = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            f.a("JSGAME_LOG", "money is null");
        }
        h5GameGrantTicketTask.setListener(this.e);
        h5GameGrantTicketTask.setWebJSCallBack(str5);
        g.a().a(h5GameGrantTicketTask);
    }

    public void login() {
        if (this.h != null && (this.f instanceof ReaderBaseActivity)) {
            this.f.setLoginNextTask(this.h);
        }
        Intent intent = new Intent();
        intent.setClass(this.f, NewLoginActivity.class);
        this.f.startActivityForResult(intent, 4098);
    }

    public void loginOut() {
        this.f.b("登录失败");
        try {
            if (this.i != null) {
                this.i.k();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void changeAccount() {
        f.d("JSGAME_LOG", "changeAccount 回调成功");
        com.qq.reader.appconfig.a.d.z = true;
        com.qq.reader.appconfig.a.d.A = true;
    }

    public String getusertype() {
        try {
            if (this.i == null || !this.i.j()) {
                return "";
            }
            return this.i.i() + "";
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean openDetail(String str) {
        try {
            if (com.qq.reader.qurl.c.a(str)) {
                com.qq.reader.qurl.c.a(this.f, str);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkInstalled(String str) {
        boolean z = true;
        if (str == null) {
            return false;
        }
        try {
            this.f.getPackageManager().getPackageInfo(str, 1);
        } catch (NameNotFoundException e) {
            z = false;
        }
        return z;
    }

    public String getUserAvatarUrl() {
        if (this.i != null) {
            try {
                return this.i.e();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void setTitleBarShowState(String str, String str2) {
        if (this.f != null) {
            f.a("testjs", "setTitleBarShowState " + str + " " + str2);
            this.f.a(str, str2);
        }
    }

    public String getUsedSkinId() {
        try {
            return this.i.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Constants.DEFAULT_UIN;
        }
    }

    public boolean copy(String str) {
        if (str == null) {
            return false;
        }
        ((ClipboardManager) this.f.getApplication().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str.trim()));
        f.d("JSGAME_LOG", "复制成功");
        return true;
    }

    public boolean showQQDialog(String str) {
        return ao.a(this.f, str, "");
    }

    public String getDownloadState(String str) {
        int i;
        RemoteException e;
        String str2;
        Object exception;
        Map hashMap;
        Exception e2;
        int i2 = -1;
        JSONObject jSONObject = new JSONObject();
        try {
            int i3;
            if (this.f.getPackageManager().getLaunchIntentForPackage(str) != null) {
                i3 = 0;
                i = 5;
            } else {
                i = this.i.b(str);
                try {
                    i3 = this.i.a(str);
                } catch (RemoteException e3) {
                    e = e3;
                    e.printStackTrace();
                    str2 = "RemoteException";
                    jSONObject.put("STATE", i);
                    jSONObject.put("VALUE", i2);
                    if (exception != null) {
                        jSONObject.put("DEBUG_MESSAGE", exception);
                    }
                    if (i == 5) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, str);
                        i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
                    }
                    return jSONObject.toString();
                } catch (Exception e4) {
                    e2 = e4;
                    exception = e2.toString();
                    jSONObject.put("STATE", i);
                    jSONObject.put("VALUE", i2);
                    if (exception != null) {
                        jSONObject.put("DEBUG_MESSAGE", exception);
                    }
                    if (i == 5) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, str);
                        i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
                    }
                    return jSONObject.toString();
                }
            }
            i2 = i3;
            exception = null;
        } catch (RemoteException e5) {
            e = e5;
            i = -1;
            e.printStackTrace();
            str2 = "RemoteException";
            jSONObject.put("STATE", i);
            jSONObject.put("VALUE", i2);
            if (exception != null) {
                jSONObject.put("DEBUG_MESSAGE", exception);
            }
            if (i == 5) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, str);
                i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
            }
            return jSONObject.toString();
        } catch (Exception e6) {
            e2 = e6;
            i = -1;
            exception = e2.toString();
            jSONObject.put("STATE", i);
            jSONObject.put("VALUE", i2);
            if (exception != null) {
                jSONObject.put("DEBUG_MESSAGE", exception);
            }
            if (i == 5) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, str);
                i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
            }
            return jSONObject.toString();
        }
        try {
            jSONObject.put("STATE", i);
            jSONObject.put("VALUE", i2);
            if (exception != null) {
                jSONObject.put("DEBUG_MESSAGE", exception);
            }
            if (i == 5) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, str);
                i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
            }
        } catch (Exception e22) {
            try {
                jSONObject.put("DEBUG_MESSAGE", e22.toString());
            } catch (JSONException e7) {
                e7.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public void doclickButton(String str, String str2, String str3, String str4, String str5) {
        int i = 0;
        try {
            long hashCode = (long) str2.hashCode();
            if (this.f == null || !ao.a.a(this.f, str2)) {
                try {
                    i = com.qq.reader.module.game.a.a(this.i.b(str2));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                com.qq.reader.module.game.a.a(this.f, str, str2, str3, str4, str5, i, hashCode);
                return;
            }
            ao.a.a(this.f, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean startPackage(String str) {
        if (str == null) {
            return false;
        }
        Intent launchIntentForPackage = this.f.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.f.startActivity(launchIntentForPackage);
        return true;
    }

    public void setData(final String str) {
        new DatePickerDialog(this.f, new OnDateSetListener(this) {
            final /* synthetic */ JSGame b;

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                if (i < 0 || i2 < 0 || i3 < 0) {
                    this.b.f.a(-1, str, "1990-1-1");
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append("-");
                stringBuilder.append(i2 + 1);
                stringBuilder.append("-");
                stringBuilder.append(i3);
                this.b.f.a(0, str, stringBuilder.toString());
            }
        }, 1990, 1, 1).show();
    }

    public void download(String str, String str2, String str3, String str4) {
        if (str != null && str2 != null) {
            Intent intent = new Intent(this.f, DownloadGameBroadcastReceiver.class);
            intent.putExtra("action", 2);
            intent.putExtra("gameName", str);
            intent.putExtra(com.tencent.android.tpush.common.Constants.FLAG_PACKAGE_NAME, str);
            intent.putExtra("downloadUrl", str2);
            intent.putExtra("iconUrl", str3);
            intent.putExtra("jumpBackUrl", str4);
            this.f.sendBroadcast(intent);
        }
    }

    public void createGameShortCut(final String str, final String str2, String str3) {
        if (!ak.a(ReaderApplication.getApplicationImp(), str)) {
            com.qq.reader.common.imageloader.c.a(this.f).a(str3, new com.bumptech.glide.request.b.g(this) {
                final /* synthetic */ JSGame a;

                {
                    this.a = r1;
                }

                public void a(Object obj, com.bumptech.glide.request.a.c cVar) {
                }
            }, new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ JSGame c;

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    this.c.a(str, str2, null);
                    return true;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                        com.bumptech.glide.load.resource.bitmap.j jVar2 = (com.bumptech.glide.load.resource.bitmap.j) bVar;
                        if (jVar2.b() != null) {
                            this.c.a(str, str2, jVar2.b());
                        }
                    }
                    return true;
                }
            });
        }
    }

    private void a(final String str, final String str2, final Bitmap bitmap) {
        g.a().a(new ReaderShortTask() {
            public void run() {
                super.run();
                Intent intent = new Intent(ReaderApplication.getApplicationImp(), TypeContext.class);
                intent.setAction("android.intent.action.MAIN");
                intent.setFlags(SigType.TLS);
                intent.setData(Uri.parse("uniteqqreader://webpage/game/" + str2));
                if (bitmap != null) {
                    int dimension = (int) ReaderApplication.getApplicationImp().getResources().getDimension(17104896);
                    ak.a(ReaderApplication.getApplicationImp(), str, intent, Bitmap.createScaledBitmap(bitmap, dimension, dimension, false), false);
                    return;
                }
                ak.a(ReaderApplication.getApplicationImp(), str, intent, ShortcutIconResource.fromContext(JSGame.this.f, R.drawable.icon), false);
            }
        });
    }

    public void unBindAIDL() {
        this.i = null;
    }

    public void setGameGetBalanceHandler(com.qq.reader.module.dicovery.a.b bVar) {
        this.c = bVar;
    }
}
