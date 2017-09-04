package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.activity.ChapterBatDownloadActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.a.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class JSDownLoad extends b {
    private static Context b;
    private Activity a;
    private e c = ((e) l.b(1001));
    private boolean d;

    public JSDownLoad(Activity activity) {
        this.a = activity;
        b = activity.getApplicationContext();
    }

    public JSDownLoad(Activity activity, boolean z) {
        this.a = activity;
        b = activity.getApplicationContext();
        this.d = z;
    }

    public void batdownload(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.replace("\n", ""));
            long optLong = jSONObject.optLong("id");
            String valueOf = String.valueOf(optLong);
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadurl");
            String optString4 = jSONObject.optString("categoryInfoV4SlaveId");
            String str2 = "";
            int optInt = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            int optInt2 = jSONObject.optInt("chapterid");
            String d = v.d(jSONObject.optString("chaptertitle"));
            String g = ao.g(optLong);
            String optString5 = jSONObject.optString("format");
            int optInt3 = jSONObject.optInt("drm");
            String str3 = "";
            int optInt4 = jSONObject.optInt("finished");
            String optString6 = jSONObject.optString(s.STATPARAM_KEY);
            com.qq.reader.common.monitor.a.b.a(new a(valueOf, optString6));
            if (valueOf == null || valueOf.length() == 0) {
                throw new JSONException("no key para");
            }
            String optString7 = jSONObject.optString("bookfrom");
            int optInt5 = jSONObject.optInt("payed", 0);
            com.qq.reader.common.db.handle.e.a().a(valueOf, optString7);
            if (optString.contains(":")) {
                optString = optString.replace(":", " ");
            }
            OnlineTag onlineTag = new OnlineTag(valueOf, str2, 0);
            onlineTag.a(optString).e(optString2).f(optString3).c(optInt2).b(d).e(0).d(optInt).f(1).h(g).k(optString5).i(optInt3).g(str3).h(optInt4).i(optString6).m(optString4);
            onlineTag.a(0);
            onlineTag.e(optInt5 == 1);
            onlineTag.b(System.currentTimeMillis());
            Intent intent = new Intent();
            intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
            if (this.d) {
                j.a(56, 1);
                intent.putExtra("chaper_pay_orientation_vertical", false);
            } else {
                j.a(55, 1);
                i.a("event_B56", null, this.a.getApplicationContext());
                StatisticsManager.a().a("event_B56", null);
                intent.putExtra("chaper_pay_orientation_vertical", true);
            }
            intent.setClass(this.a, ChapterBatDownloadActivity.class);
            this.a.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
            f.a("JSDownLoad", "server onlineinfo error");
            a(e);
        }
    }

    public boolean download(String str) {
        return download(str, null, "");
    }

    public boolean download(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            long j = jSONObject.getLong("id");
            String optString = jSONObject.optString("title", "");
            String optString2 = jSONObject.optString("author", "");
            String optString3 = jSONObject.optString("downloadurl", "");
            String g = ao.g(j);
            int i = jSONObject.getInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            String optString4 = jSONObject.optString("format", "");
            int optInt = jSONObject.optInt("drm", 0);
            if (j == 0 || optString == null || optString3 == null) {
                throw new JSONException("no key para");
            }
            com.qq.reader.common.monitor.a.b.a(new a(j + "", jSONObject.optString(s.STATPARAM_KEY)));
            if (!TextUtils.isEmpty(str2)) {
                i = str3.hashCode();
                if (com.qq.reader.readengine.model.a.m(str2)) {
                    optInt = 1;
                    optString4 = str2;
                } else {
                    optString4 = str2;
                }
            }
            DownloadBookTask downloadBookTask = new DownloadBookTask(j, optString, optString2, optString3, g, i, optString4, optInt, -1);
            downloadBookTask.setNewVersion(i);
            return downLoadBook(downloadBookTask, this.c, this.a, true);
        } catch (JSONException e) {
            f.a("JSDownLoad", "server downloadinfo error");
            a(e);
            return false;
        }
    }

    public static boolean downLoadBook(final DownloadBookTask downloadBookTask, final e eVar, final Activity activity, boolean z) {
        boolean z2;
        if (!eVar.d()) {
            eVar.a(activity.getApplicationContext());
        }
        a(downloadBookTask.getId());
        final int a = a(eVar, downloadBookTask.getName(), downloadBookTask.getVersion(), downloadBookTask.getBookFormat());
        boolean equalsIgnoreCase = downloadBookTask.getBookFormat().equalsIgnoreCase("trial");
        if (equalsIgnoreCase) {
            z2 = false;
        } else {
            z2 = true;
        }
        switch (a) {
            case 0:
                if (eVar.a((g) downloadBookTask)) {
                    com.qq.reader.cservice.cloud.b.a(b).a(new com.qq.reader.cservice.cloud.a.b(downloadBookTask.getId(), 1, 0, 0, 1), false, null);
                    activity.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.ch));
                    if (z && z2) {
                        showMessage(activity, "《" + downloadBookTask.getName() + "》已开始下载");
                    }
                    if (TextUtils.isEmpty(com.qq.reader.common.monitor.a.b.a(String.valueOf(downloadBookTask.getId())))) {
                        com.qq.reader.common.monitor.a.b.a(new a(String.valueOf(downloadBookTask.getId()), downloadBookTask.getNetChannelId()));
                    }
                    return true;
                } else if (equalsIgnoreCase) {
                    return false;
                } else {
                    showMessage(activity, "下载失败，请稍后再试");
                    return false;
                }
            case 1:
                eVar.f(downloadBookTask);
                return true;
            case 2:
            case 4:
                if (equalsIgnoreCase) {
                    eVar.f(downloadBookTask);
                    return false;
                }
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        JSDownLoad.b(activity, downloadBookTask, a, eVar);
                    }
                });
                return false;
            case 3:
                if (!z2) {
                    return false;
                }
                showMessage(activity, "本书正在下载中");
                return false;
            case 5:
                break;
            case 6:
                eVar.e(downloadBookTask);
                if (z2) {
                    showMessage(activity, "《" + downloadBookTask.getName() + "》已开始下载");
                    break;
                }
                break;
            default:
                return false;
        }
        eVar.f(downloadBookTask);
        if (!z2) {
            return false;
        }
        showMessage(activity, "《" + downloadBookTask.getName() + "》已开始下载");
        return false;
    }

    public void showMessage(String str) {
        af.a(b, (CharSequence) str, 0).a();
    }

    public static void showMessage(final Activity activity, final String str) {
        if (activity != null && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    af.a(activity.getApplicationContext(), str, 0).a();
                }
            });
        }
    }

    public int needdownload(String str) {
        try {
            String replace;
            JSONObject jSONObject = new JSONObject(str);
            String str2 = (String) jSONObject.get("title");
            if (str2.contains(":")) {
                replace = str2.replace(":", " ");
            } else {
                replace = str2;
            }
            str2 = (String) jSONObject.get("format");
            return a(this.c, replace, jSONObject.getInt("lastchapter"), str2);
        } catch (JSONException e) {
            f.a("JSDownLoad", "server downloadinfo error");
            a(e);
            return 0;
        }
    }

    private static int a(e eVar, String str, int i, String str2) {
        DownloadBookTask b = eVar.b(str);
        if (b == null) {
            if (!eVar.a(str + "." + str2) || str2.equalsIgnoreCase("trial")) {
                return 0;
            }
            return 4;
        } else if (str2.equalsIgnoreCase("qteb") && b.getBookFormat().equalsIgnoreCase("trial")) {
            eVar.d(b);
            com.qq.reader.common.db.handle.i.c().c(b.getFilePath());
            return 0;
        } else if (b.getState() == TaskStateEnum.Paused || b.getState() == TaskStateEnum.Failed) {
            return 6;
        } else {
            if (!b.hasFinish()) {
                return 3;
            }
            if (b.getVersion() < i) {
                return 1;
            }
            if (b.getState() != TaskStateEnum.InstallCompleted || new File(b.getFilePath()).exists()) {
                return 2;
            }
            return 5;
        }
    }

    private void a(JSONException jSONException) {
        if (jSONException instanceof JSONException) {
            showMessage("获取下载信息失败");
        } else {
            showMessage("获取下载信息失败");
        }
    }

    public String getDeviceID() {
        return d.i(b);
    }

    private static void b(final Activity activity, final DownloadBookTask downloadBookTask, final int i, final e eVar) {
        new AlertDialog.a(activity).a((int) R.string.redownlaod_title).b((int) R.string.redownlaod_msg).a((int) R.string.alert_dialog_ok, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 2) {
                    eVar.f(downloadBookTask);
                    activity.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.ch));
                } else if (eVar.a(downloadBookTask)) {
                    activity.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.ch));
                } else {
                    JSDownLoad.showMessage(activity, "下载失败，请稍后再试");
                }
            }
        }).b((int) R.string.alert_dialog_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).a().show();
    }

    private static void a(long j) {
        com.qq.reader.common.db.handle.i.c().c(String.valueOf(j));
    }
}
