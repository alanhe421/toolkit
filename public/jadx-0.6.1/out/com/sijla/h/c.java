package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.sijla.common.b;
import com.sijla.j.a;
import com.sijla.j.a.d;
import com.sijla.j.f;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class c implements Runnable {
    private Context a;
    private String b = "";
    private String c = "";
    private a d = null;

    public c(Context context, Intent intent) {
        this.a = context.getApplicationContext();
        this.c = intent.getAction();
        this.b = intent.getDataString().substring(8);
        this.d = new a(context);
    }

    public void run() {
        try {
            f.a("action:" + this.c + " appid:" + this.b);
            String str = this.c;
            Object obj = -1;
            switch (str.hashCode()) {
                case -810471698:
                    if (str.equals("android.intent.action.PACKAGE_REPLACED")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 525384130:
                    if (str.equals("android.intent.action.PACKAGE_REMOVED")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1544582882:
                    if (str.equals("android.intent.action.PACKAGE_ADDED")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            List a;
            switch (obj) {
                case null:
                    a = a("1");
                    b.e().a("AS.csv-" + this.b + "_1", a);
                    f.a(ShareConstants.RES_ADD_TITLE + this.b);
                    return;
                case 1:
                    a = a("2");
                    b.e().a("AS.csv-" + this.b + "_2", a);
                    b(this.b);
                    f.a("update:" + this.b);
                    return;
                case 2:
                    if (!this.b.equals(this.a.getPackageName())) {
                        a = a("0");
                        b.e().a("AS.csv-" + this.b + "_0", a);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }

    private ArrayList<String> a(String str) {
        Object a = com.sijla.j.a.a.a(this.b, this.a);
        if (com.sijla.j.b.a((String) a)) {
            a = "";
        }
        Object obj = com.sijla.j.a.a.a(this.a, this.b) ? "1" : "0";
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(com.sijla.j.b.p(this.a));
        arrayList.add(str);
        arrayList.add(this.b);
        arrayList.add(obj);
        arrayList.add(a);
        if ("0".equals(str)) {
            arrayList.add("");
            arrayList.add("");
        } else {
            String[] c = c(this.b);
            arrayList.add(c[0]);
            arrayList.add(c[1]);
        }
        arrayList.add(com.sijla.j.b.g() + "");
        return arrayList;
    }

    private void b(String str) {
        try {
            File file = new File(com.sijla.j.b.j());
            if (file != null) {
                File[] listFiles = file.listFiles(new 1(this, str));
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            f.a("clean update data:" + file2.getName());
                            file2.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] c(String str) {
        String[] strArr = new String[]{"", ""};
        try {
            String toLowerCase = Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
            if (toLowerCase.contains("xiaomi")) {
                strArr[0] = this.a.getPackageManager().getInstallerPackageName(str);
                if ("adb".equals(strArr[0])) {
                    strArr[0] = "pc";
                }
                if (strArr[0] == null || "".equals(strArr[0])) {
                    strArr[0] = toLowerCase;
                }
                if (strArr[0].contains("/")) {
                    strArr[0] = this.d.a(str, this.c);
                }
            } else {
                strArr[1] = this.d.a(str, this.c);
                com.sijla.common.c a = com.sijla.j.b.a(this.a, "AK", str);
                if (a.b()) {
                    File file = new File((String) a.a());
                    if (file.exists()) {
                        Object a2 = com.sijla.j.b.a(d.b(new FileInputStream(file)));
                        if (!TextUtils.isEmpty(a2)) {
                            strArr[0] = a2.split("\t")[0];
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.a(str + " AKCHANNEL:" + strArr[0]);
        f.a(str + " SECHANNEL:" + strArr[1]);
        return strArr;
    }
}
