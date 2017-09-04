package com.dynamicload.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Parcelable;
import com.dynamicload.DLProxyActivity;
import com.dynamicload.DLProxyActivitySingleIns;
import com.dynamicload.DLProxyActivityTransparent;
import com.dynamicload.DLProxyFragmentActivity;
import com.dynamicload.DLProxyFragmentActivitySingleIns;
import com.dynamicload.DLProxyFragmentActivityTransparent;
import com.dynamicload.Lib.DLBasePluginActivity;
import com.dynamicload.Lib.DLBasePluginFragmentActivity;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginPackage;
import com.dynamicload.Lib.IDLPluginActivity;
import com.qq.reader.ReaderApplication;
import java.util.ArrayList;
import java.util.Iterator;
import oicq.wlogin_sdk.request.WtloginHelper;
import tencent.tls.platform.SigType;

/* compiled from: DLActivityManager */
public class a {
    private final c a;
    private ArrayList<IDLPluginActivity> b = new ArrayList();

    public a(c cVar) {
        this.a = cVar;
    }

    public int a(Context context, DLIntent dLIntent, int i) {
        boolean z;
        boolean z2 = true;
        String a = dLIntent.a();
        String a2 = this.a.a(a, dLIntent.b(), Activity.class);
        DLPluginPackage a3 = this.a.a(a);
        Class a4 = this.a.a(a3.classLoader, a2);
        Parcelable a5 = a(a3, a2);
        IDLPluginActivity iDLPluginActivity = null;
        if (a5.launchMode == 2) {
            dLIntent.addFlags(SigType.TLS);
            iDLPluginActivity = b(a4);
        }
        if (a5.launchMode == 1) {
            iDLPluginActivity = a(a4);
        }
        if (iDLPluginActivity != null) {
            dLIntent.setFlags(603979776);
        } else if ((dLIntent.getFlags() & WtloginHelper.SigType.WLOGIN_QRPUSH) != 0 && b(a4) == null) {
            dLIntent.setFlags(dLIntent.getFlags() ^ WtloginHelper.SigType.WLOGIN_QRPUSH);
        }
        if (a5.theme == 16973839 || a5.theme == 16973840 || a5.theme == 16973841) {
            z = true;
        } else {
            z = false;
        }
        if (a5.launchMode != 3) {
            z2 = false;
        }
        Class a6 = a(a4, z2, z);
        if (a6 == null) {
            throw new DLException("proxy class not found", 204);
        }
        dLIntent.putExtra(DLConstants.EXTRA_ACTIVITY_INFO, a5);
        dLIntent.setClass(ReaderApplication.getApplicationImp(), a6);
        a(context, (Intent) dLIntent, i);
        return 200;
    }

    private IDLPluginActivity a(Class<?> cls) {
        if (this.b.size() > 0) {
            IDLPluginActivity iDLPluginActivity = (IDLPluginActivity) this.b.get(0);
            if (iDLPluginActivity.getClass().equals(cls)) {
                return iDLPluginActivity;
            }
        }
        return null;
    }

    private IDLPluginActivity b(Class<?> cls) {
        int i;
        IDLPluginActivity iDLPluginActivity;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            IDLPluginActivity iDLPluginActivity2 = (IDLPluginActivity) this.b.get(i2);
            if (iDLPluginActivity2.getClass().equals(cls)) {
                i = i2;
                iDLPluginActivity = iDLPluginActivity2;
                break;
            }
        }
        iDLPluginActivity = null;
        i = -1;
        while (i > 0) {
            ((IDLPluginActivity) this.b.remove(i - 1)).finish();
            i--;
        }
        return iDLPluginActivity;
    }

    public ActivityInfo a(DLPluginPackage dLPluginPackage, String str) {
        int i = 0;
        PackageInfo packageInfo = dLPluginPackage.packageInfo;
        if (packageInfo.activities != null && packageInfo.activities.length > 0) {
            if (str == null) {
                str = packageInfo.activities[0].name;
            }
            ActivityInfo[] activityInfoArr = packageInfo.activities;
            int length = activityInfoArr.length;
            while (i < length) {
                ActivityInfo activityInfo = activityInfoArr[i];
                if (activityInfo.name.equals(str)) {
                    return activityInfo;
                }
                i++;
            }
        }
        throw new ActivityNotFoundException("Unable to find explicit activity class {" + str + "} in plugin package {" + packageInfo.packageName + "}; have you declared this activity in your AndroidManifest.xml?");
    }

    private Class<? extends Activity> a(Class<?> cls, boolean z, boolean z2) {
        if (DLBasePluginActivity.class.isAssignableFrom(cls)) {
            if (z2) {
                return DLProxyActivityTransparent.class;
            }
            if (z) {
                return DLProxyActivitySingleIns.class;
            }
            return DLProxyActivity.class;
        } else if (!DLBasePluginFragmentActivity.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (z2) {
                return DLProxyFragmentActivityTransparent.class;
            }
            if (z) {
                return DLProxyFragmentActivitySingleIns.class;
            }
            return DLProxyFragmentActivity.class;
        }
    }

    public void a(IDLPluginActivity iDLPluginActivity) {
        this.b.add(0, iDLPluginActivity);
    }

    public void b(IDLPluginActivity iDLPluginActivity) {
        this.b.remove(iDLPluginActivity);
    }

    public void a(Context context, Intent intent, int i) {
        if (context instanceof Activity) {
            intent.putExtra(DLConstants.FROM_AM, true);
            ((Activity) context).startActivityForResult(intent, i);
            return;
        }
        intent.addFlags(SigType.TLS);
        context.startActivity(intent);
    }

    public void a(DLPluginPackage dLPluginPackage) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            IDLPluginActivity iDLPluginActivity = (IDLPluginActivity) it.next();
            if (iDLPluginActivity.getPackage() == dLPluginPackage) {
                iDLPluginActivity.finish();
            }
        }
    }
}
