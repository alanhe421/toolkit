package com.tencent.beacon.e;

import android.content.Context;
import android.os.Environment;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.f;
import com.tencent.beacon.net.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ProGuard */
public final class b {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    public final String a() {
        String a = com.tencent.beacon.b.b.a(this.a, "QIMEI_DENGTA", "qimei_v2", "");
        a.b("Load QIMEI from share preference, QIMEI is %s.", a);
        if (a == null || a.trim().equals("")) {
            f.a(this.a);
            if ((Environment.getExternalStorageState().equals("mounted") ? 1 : 0) != 0) {
                a = d();
                a.b("Load QIMEI from SDCard, QIMEI is %s.", a);
                if (!(a == null || a.trim().equals(""))) {
                    a.b("Save QIMEI to shared prefs: %s.", a);
                    com.tencent.beacon.b.b.c(this.a, "qimei_v2", a);
                }
            }
        }
        if (a == null) {
            a = "";
        }
        a.b("Return QIMEI %s.", a);
        return a;
    }

    public final void a(String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (str != null && !str.trim().equals("")) {
            f.a(this.a);
            if ((Environment.getExternalStorageState().equals("mounted") ? 1 : 0) != 0) {
                File file = new File(Environment.getExternalStorageDirectory(), "tencent/beacon/meta.dat");
                FileOutputStream fileOutputStream2 = null;
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(a.a(str.getBytes(), 3, "@&(*#HNKJg12!@)"));
                        fileOutputStream.flush();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                        }
                    } catch (FileNotFoundException e2) {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        a.b("Save QIMEI to SDCard, QIMEI is %s.", str);
                        com.tencent.beacon.b.b.c(this.a, "qimei_v2", str);
                        a.b("Save QIMEI to share preference, QIMEI is %s.", str);
                    } catch (IOException e4) {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        a.b("Save QIMEI to SDCard, QIMEI is %s.", str);
                        com.tencent.beacon.b.b.c(this.a, "qimei_v2", str);
                        a.b("Save QIMEI to share preference, QIMEI is %s.", str);
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        fileOutputStream2 = fileOutputStream;
                        th = th3;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e7) {
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    a.b("Save QIMEI to SDCard, QIMEI is %s.", str);
                    com.tencent.beacon.b.b.c(this.a, "qimei_v2", str);
                    a.b("Save QIMEI to share preference, QIMEI is %s.", str);
                } catch (IOException e8) {
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    a.b("Save QIMEI to SDCard, QIMEI is %s.", str);
                    com.tencent.beacon.b.b.c(this.a, "qimei_v2", str);
                    a.b("Save QIMEI to share preference, QIMEI is %s.", str);
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
                a.b("Save QIMEI to SDCard, QIMEI is %s.", str);
            }
            com.tencent.beacon.b.b.c(this.a, "qimei_v2", str);
            a.b("Save QIMEI to share preference, QIMEI is %s.", str);
        }
    }

    public final boolean b() {
        if (!a.d().equals(com.tencent.beacon.b.b.b(this.a, "GEN_QIMEI", "")) || com.tencent.beacon.b.b.a(this.a, "GEN_QIMEI_TIMES") < e.a().h()) {
            return false;
        }
        return true;
    }

    public final void c() {
        int a = com.tencent.beacon.b.b.a(this.a, "GEN_QIMEI_TIMES");
        if (!a.d().equals(com.tencent.beacon.b.b.b(this.a, "GEN_QIMEI", ""))) {
            com.tencent.beacon.b.b.a(this.a, "GEN_QIMEI", a.d());
            a = 0;
        }
        com.tencent.beacon.b.b.a(this.a, "GEN_QIMEI_TIMES", a + 1);
    }

    private static String d() {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(Environment.getExternalStorageDirectory(), "tencent/beacon/meta.dat");
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[((int) file.length())];
                fileInputStream2.read(bArr);
                fileInputStream2.close();
                byte[] b = a.b(bArr, 3, "@&(*#HNKJg12!@)");
                if (b != null) {
                    String str = new String(b);
                    try {
                        fileInputStream2.close();
                        return str;
                    } catch (IOException e) {
                        return str;
                    }
                }
                try {
                    fileInputStream2.close();
                } catch (IOException e2) {
                }
                return null;
            } catch (FileNotFoundException e3) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return null;
            } catch (IOException e5) {
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e6) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e7) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e8) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return null;
        } catch (IOException e9) {
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }
}
