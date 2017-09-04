package com.qq.reader.module.b;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.utils.ab;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;
import org.json.JSONObject;

/* compiled from: ProfileDataUtils */
public class b {
    private static final String a = (com.qq.reader.common.c.a.l + "/user");

    /* compiled from: ProfileDataUtils */
    static class a {
        private static b a = new b();
    }

    private b() {
    }

    public static b a() {
        return a.a;
    }

    private String c() {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        String str = null;
        FileInputStream fileInputStream;
        try {
            if (new File(a + "/" + d.R(ReaderApplication.getApplicationImp()) + "/profiledata").exists()) {
                fileInputStream = new FileInputStream(a + "/" + d.R(ReaderApplication.getApplicationImp()) + "/profiledata");
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    str = EncodingUtils.getString(bArr, "UTF-8");
                } catch (FileNotFoundException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e222 = e4;
                    e222.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    return str;
                }
            }
            Object obj = str;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream = str;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (IOException e6) {
            e22222 = e6;
            fileInputStream = str;
            e22222.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            fileInputStream = str;
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    private boolean a(File file) {
        if (!ab.a(file.getParentFile()) || file.exists()) {
            return false;
        }
        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized void a(String str) {
        OutputStream bufferedOutputStream;
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        if (!TextUtils.isEmpty(str)) {
            OutputStream outputStream = null;
            try {
                String str2 = a + "/" + d.R(ReaderApplication.getApplicationImp()) + "/profiledata";
                File file = new File(str2 + f.DOWNLOAD_FILE_TMP);
                if (file.exists()) {
                    file.delete();
                }
                if (a(file)) {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, false));
                    try {
                        bufferedOutputStream.write(str.getBytes("UTF-8"));
                        File file2 = new File(str2);
                        if (file2.exists()) {
                            file2.delete();
                        }
                        file.renameTo(file2);
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        outputStream = bufferedOutputStream;
                        try {
                            e.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e22 = e5;
                        outputStream = bufferedOutputStream;
                        e22.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = bufferedOutputStream;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw th;
                    }
                }
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                e.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e7) {
                e2222 = e7;
                e2222.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

    public synchronized JSONObject b() {
        JSONObject jSONObject = null;
        synchronized (this) {
            Object c = c();
            if (!TextUtils.isEmpty(c)) {
                try {
                    jSONObject = new JSONObject(c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject;
    }
}
