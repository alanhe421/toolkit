package com.tencent.mid.b;

import android.content.Context;
import android.os.Environment;
import com.tencent.mid.util.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class c extends f {
    public c(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 2;
    }

    protected void a(a aVar) {
    }

    protected void a(String str) {
        BufferedWriter bufferedWriter;
        IOException e;
        Throwable th;
        synchronized (this) {
            b.b((Object) "write mid to InternalStorage");
            b.a(Environment.getExternalStorageDirectory() + "/" + e());
            File file = new File(Environment.getExternalStorageDirectory(), f());
            if (file != null) {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    try {
                        bufferedWriter.write(h() + "," + str);
                        bufferedWriter.write("\n");
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Exception e2) {
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            b.d(e.toString());
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Exception e4) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Exception e5) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                    bufferedWriter = null;
                    b.d(e.toString());
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw th;
                }
            }
        }
    }

    protected boolean b() {
        try {
            return Util.checkPermission(this.c, "android.permission.WRITE_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState());
        } catch (Throwable th) {
            b.b("checkPermission " + th);
            return false;
        }
    }

    protected String c() {
        String str = null;
        synchronized (this) {
            b.b((Object) "read mid from InternalStorage");
            File file = new File(Environment.getExternalStorageDirectory(), f());
            if (file != null) {
                try {
                    String str2;
                    for (String str22 : b.a(file)) {
                        String[] split = str22.split(",");
                        if (split.length == 2 && split[0].equals(h())) {
                            b.b("read mid from InternalStorage:" + split[1]);
                            str22 = split[1];
                            break;
                        }
                    }
                    str22 = null;
                    str = str22;
                } catch (IOException e) {
                    b.d(e.toString());
                }
            }
        }
        return str;
    }

    protected a d() {
        return null;
    }
}
