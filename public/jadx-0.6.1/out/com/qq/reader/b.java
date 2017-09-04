package com.qq.reader;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.qq.reader.activity.PluginBrigeActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.h;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.plugin.d;
import com.qq.reader.plugin.k;
import com.qq.reader.plugin.l;
import com.qq.reader.plugin.m;
import com.qq.reader.view.af;
import com.tencent.connect.common.Constants;
import format.archive.ArchiveFileBrowser;
import format.chm.ChmReaderPage;
import java.io.File;
import tencent.tls.platform.SigType;

/* compiled from: OpenBook */
public class b {
    public static void a(Intent intent, Context context) {
        String path;
        String str;
        Bundle bundle;
        String action = intent.getAction();
        if ("android.intent.action.VIEW".equals(action) || "android.intent.action.SEND".equals(action)) {
            Uri data = intent.getData();
            if (data == null) {
                data = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            }
            path = data.getPath();
            if (data.toString().startsWith("content://media")) {
                path = ao.a(data);
            }
            if (path == null || path.equals("")) {
                action = null;
            } else {
                action = path.substring(path.lastIndexOf("/") + 1, path.length());
                if (!path.startsWith("/mnt") && a.l.startsWith("/mnt")) {
                    path = "/mnt" + path;
                }
            }
            if (path.startsWith("/root")) {
                path = path.substring(5, path.length());
            }
            str = path;
            path = action;
            bundle = null;
        } else {
            bundle = intent.getExtras();
            if (bundle != null) {
                str = bundle.getString("filepath");
                path = bundle.getString("filename");
            } else {
                return;
            }
        }
        if (str != null && !str.equals("")) {
            String toLowerCase = str.toLowerCase();
            j.d = ao.l(toLowerCase);
            if ((bundle == null ? 0 : bundle.getInt("marktype")) == 8) {
                o.e((Activity) context, str, null);
            } else if (toLowerCase.endsWith(".chm")) {
                a(intent, str, path, context);
            } else if (toLowerCase.endsWith(".pdf")) {
                c(intent, str, path, context);
            } else if (toLowerCase.endsWith(".teb") || toLowerCase.endsWith(".qteb") || toLowerCase.endsWith(".trial")) {
                d(intent, str, path, context);
            } else if (toLowerCase.endsWith(".rar") || toLowerCase.endsWith(".zip")) {
                b(intent, context);
            } else if (toLowerCase.endsWith(".doc") || toLowerCase.endsWith(".docx") || toLowerCase.endsWith(".ppt") || toLowerCase.endsWith(".pptx") || toLowerCase.endsWith(".xlsx") || toLowerCase.endsWith(".xls")) {
                b(intent, str, path, context);
            } else {
                c(intent, context);
            }
        }
    }

    private static void b(Intent intent, Context context) {
        com.qq.reader.plugin.a aVar = null;
        l b = k.b().b(Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
        if (b != null) {
            aVar = m.c().b(context, b);
        }
        if (aVar != null && aVar.i() && aVar.n()) {
            intent.setClass(context, ArchiveFileBrowser.class);
            context.startActivity(intent);
            j.a(34, 0);
            return;
        }
        intent.setClass(context, PlugInDefaultActivity.class);
        intent.putExtra("PLUGIN_TYPE", "5");
        context.startActivity(intent);
    }

    private static void a(Intent intent, String str, String str2, Context context) {
        if (!(str == null || str.equals("") || i.c().b(str, false) != null)) {
            Mark localMark = new LocalMark(str2, str, 100, 1, true);
            localMark.setStartPoint(0);
            localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
            if (localMark != null) {
                i.c().a(localMark, true);
            }
        }
        intent.setClass(context, ChmReaderPage.class);
        context.startActivity(intent);
    }

    private static void c(Intent intent, Context context) {
        intent.setClass(context, ReaderPageActivity.class);
        context.startActivity(intent);
    }

    private static void b(Intent intent, String str, String str2, Context context) {
        com.qq.reader.plugin.wps.a.b bVar;
        d dVar = null;
        if (str == null || str.equals("")) {
            bVar = null;
        } else {
            Mark b = i.c().b(str, true);
            if (b == null) {
                b = new LocalMark(str2, str, 100, 1, true);
                b.setStartPoint(0);
                if (b != null) {
                    i.c().a(b, true);
                }
                bVar = null;
            } else {
                String id = b.getId();
                b.setReadTime(System.currentTimeMillis());
                b.setOperateTime(System.currentTimeMillis());
                i.c().a(b, true);
                bVar = com.qq.reader.plugin.wps.a.a().a(id);
            }
        }
        l b2 = k.b().b("25");
        if (b2 != null) {
            dVar = (d) m.c().b(context, b2);
        }
        if (dVar == null || !dVar.i()) {
            intent.setClass(context, PlugInDefaultActivity.class);
            intent.putExtra("PLUGIN_TYPE", Constants.VIA_SHARE_TYPE_INFO);
            context.startActivity(intent);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("OpenMode", "ReadMode");
        bundle.putBoolean("SendCloseBroad", true);
        bundle.putString("ThirdPackage", "com.qq.reader");
        bundle.putBoolean("AutoJump", true);
        if (bVar != null) {
            bundle.putFloat("ViewProgress", bVar.b);
            bundle.putFloat("ViewScale", bVar.a);
            bundle.putInt("ViewScrollX", bVar.c);
            bundle.putInt("ViewScrollY", bVar.d);
        }
        Intent intent2 = new Intent();
        intent2.addFlags(SigType.TLS);
        intent2.setAction("android.intent.action.VIEW");
        intent2.setClassName("cn.wps.moffice_eng", "cn.wps.moffice.documentmanager.PreStartActivity");
        intent2.setData(Uri.fromFile(new File(str)));
        intent2.putExtras(bundle);
        try {
            h.a().a(6);
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
        j.a(33, 0);
    }

    private static void c(Intent intent, String str, String str2, Context context) {
        com.qq.reader.plugin.b bVar;
        Activity activity = (Activity) context;
        if (!(str == null || str.equals("") || i.c().b(str, false) != null)) {
            Mark localMark = new LocalMark(str2, str, 100, 1, true);
            localMark.setStartPoint(0);
            localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
            if (localMark != null) {
                i.c().a(localMark, true);
            }
        }
        l b = k.b().b("1");
        if (b != null) {
            bVar = (com.qq.reader.plugin.b) m.c().b(activity, b);
        } else {
            bVar = null;
        }
        if (bVar != null && bVar.i() && bVar.n()) {
            intent.setClass(context, PluginBrigeActivity.class);
            intent.putExtra("pluginname", "pdf");
            Bundle bundle = new Bundle();
            bundle.putString("uri", Uri.encode(str));
            intent.putExtras(bundle);
            context.startActivity(intent);
            com.qq.reader.common.monitor.i.a("event_B55", null, context);
            j.a(65, 0);
            return;
        }
        intent.setClass(context, PlugInDefaultActivity.class);
        bundle = new Bundle();
        bundle.putString("filepath", Uri.encode(str));
        bundle.putString("filename", str2);
        bundle.putString("PLUGIN_TYPE", "1");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static void d(Intent intent, String str, String str2, Context context) {
        com.qq.reader.common.drm.teb.a.a a = com.qq.reader.common.drm.teb.a.a(str);
        if (a != null) {
            int b = a.b();
            int a2 = a.a();
            String c = a.c();
            if (b == 0) {
                intent.putExtra("fileencrypt", b);
                intent.putExtra("fileid", c);
            }
            if (a2 == 101) {
                c(intent, context);
            } else if (a2 == 102) {
                c(intent, str, str2, context);
            } else {
                af.a(context.getApplicationContext(), (CharSequence) "无法识别的文件格式", 0).a();
                f.a("OpenBook", "无法识别的文件格式:" + a2);
            }
        } else if (intent.getBooleanExtra("detailpage_trial_read", false)) {
            intent.putExtra("fileencrypt", 0);
            intent.putExtra("fileid", intent.getStringExtra("fileid"));
            c(intent, context);
        }
    }
}
