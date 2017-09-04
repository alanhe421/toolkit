package com.qq.reader.common.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PermissionUtil */
public class u {
    private static String[] a = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private a b;

    /* compiled from: PermissionUtil */
    public interface a {
        void a();
    }

    public u(a aVar) {
        this.b = aVar;
    }

    public void a(Activity activity, int i, String[] strArr, int[] iArr) {
        if (i != 100) {
            return;
        }
        if (iArr.length < 1) {
            a(activity, strArr);
            return;
        }
        for (int i2 : iArr) {
            if (i2 != 0) {
                a(activity, strArr);
                return;
            }
        }
        a();
    }

    private String[] a(Context context, String[] strArr) {
        List arrayList = new ArrayList();
        for (String str : strArr) {
            if (android.support.v4.content.a.a(context, str) != 0) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean a(final Activity activity) {
        if (d.bJ(activity) && com.qq.reader.b.a.a.a()) {
            View inflate = activity.getLayoutInflater().inflate(R.layout.querydialog_layout, null);
            ((TextView) inflate.findViewById(R.id.first_section)).setText(String.format(activity.getResources().getString(R.string.querydialogcontent1), new Object[]{activity.getResources().getString(R.string.product_name)}));
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.dialogcheckbox);
            AlertDialog create = new Builder(activity).setTitle("温馨提示").setPositiveButton("取消", new OnClickListener(this) {
                final /* synthetic */ u a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    System.exit(0);
                }
            }).setNegativeButton("确定", new OnClickListener(this) {
                final /* synthetic */ u c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean z = false;
                    f.d("QueryDialogActivity", "onSureClick " + (!checkBox.isChecked()));
                    Context context = activity;
                    if (!checkBox.isChecked()) {
                        z = true;
                    }
                    d.K(context, z);
                    dialogInterface.dismiss();
                    ReaderApplication.isAllowNet = true;
                    ReaderApplication.getInstance().appNetworkStart();
                    this.c.b(activity);
                }
            }).setView(inflate).create();
            create.setCanceledOnTouchOutside(false);
            create.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ u b;

                public void onCancel(DialogInterface dialogInterface) {
                    d.K(activity, true);
                    dialogInterface.dismiss();
                    System.exit(0);
                }
            });
            create.show();
            f.d("QueryDialogActivity", "whearthShow Dialog " + d.bJ(activity));
            return true;
        }
        ReaderApplication.timeLog.addSplit("SplashActivity 2");
        b(activity);
        ReaderApplication.timeLog.addSplit("SplashActivity requestPermissionInList");
        return false;
    }

    private void b(Activity activity) {
        if (VERSION.SDK_INT < 23) {
            a();
            return;
        }
        String[] a = a((Context) activity, a);
        if (a.length > 0) {
            android.support.v4.app.a.a(activity, a, 100);
        } else {
            a();
        }
    }

    private void a() {
        if (this.b != null) {
            this.b.a();
        }
    }

    private ArrayList<String> a(String[] strArr) {
        int i = 0;
        ArrayList<String> arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            List asList = Arrays.asList(ReaderApplication.getApplicationImp().getResources().getStringArray(R.array.permission_list));
            List asList2 = Arrays.asList(ReaderApplication.getApplicationImp().getResources().getStringArray(R.array.permission_text_description_list));
            HashMap hashMap = new HashMap();
            int i2 = 0;
            while (i2 < asList.size() && i2 < asList2.size()) {
                hashMap.put(asList.get(i2), asList2.get(i2));
                i2++;
            }
            while (i < strArr.length) {
                String str = (String) hashMap.get(strArr[i]);
                if (!(TextUtils.isEmpty(str) || arrayList.contains(str))) {
                    arrayList.add(str);
                }
                i++;
            }
        }
        return arrayList;
    }

    private String b(String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = a(strArr).iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next()).append("\n");
        }
        return stringBuilder.toString();
    }

    private void a(final Activity activity, String[] strArr) {
        String[] a = a((Context) activity, strArr);
        if (a.length > 0) {
            new Bundle().putStringArray("permission", a);
            if (VERSION.SDK_INT >= 23) {
                com.qq.reader.view.AlertDialog a2 = new com.qq.reader.view.AlertDialog.a(activity).c(R.drawable.alert_dialog_icon).a((CharSequence) "权限获取失败").b("需授予以下权限才可以正常使用QQ阅读：\n" + b(a)).a((CharSequence) "授予权限", new OnClickListener(this) {
                    final /* synthetic */ u b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        o.t(activity, null);
                        activity.finish();
                    }
                }).b((CharSequence) "退出应用", new OnClickListener(this) {
                    final /* synthetic */ u b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                }).a();
                a2.setCanceledOnTouchOutside(false);
                a2.setCancelable(false);
                a2.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ u a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == 84 || i == 4) {
                            return true;
                        }
                        return false;
                    }
                });
                a2.b(51);
                a2.show();
            }
        }
    }
}
