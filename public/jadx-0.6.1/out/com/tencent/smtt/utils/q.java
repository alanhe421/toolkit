package com.tencent.smtt.utils;

import android.content.Context;
import android.provider.Settings.System;
import android.text.TextUtils;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class q {
    private static q a = null;
    private static Context b = null;

    private q() {
    }

    public static q a(Context context) {
        b = context.getApplicationContext();
        if (a == null) {
            a = new q();
        }
        return a;
    }

    private void b(String str) {
        Exception e;
        Throwable th;
        if (i.b()) {
            DataOutputStream dataOutputStream = null;
            try {
                File file = new File(i.c(), "ins.dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                DataOutputStream dataOutputStream2 = new DataOutputStream(i.b(file));
                try {
                    dataOutputStream2.writeUTF(str);
                    dataOutputStream2.flush();
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    dataOutputStream = dataOutputStream2;
                    try {
                        e.printStackTrace();
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            }
        }
    }

    private void c(String str) {
        try {
            System.putString(b.getContentResolver(), "sys_setting_tbs_qb_installer", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
            c(str);
        }
    }
}
