package com.qq.reader.module.readpage.voteview.net;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a.a.a.a.c;
import com.qq.reader.common.imageloader.a.a.b.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import oicq.wlogin_sdk.tools.MD5;
import org.apache.http.util.EncodingUtils;

/* compiled from: VoteDataCache */
public class b {
    private c a;

    /* compiled from: VoteDataCache */
    static class a {
        private static b a = new b();
    }

    public static b a() {
        return a.a;
    }

    private b() {
        b();
    }

    private void b() {
        try {
            this.a = (c) com.qq.reader.common.imageloader.a.a.b.a.a(ReaderApplication.getApplicationImp(), new e(), 10485760, 0, new File(ReaderApplication.getApplicationImp().getExternalCacheDir(), com.qq.reader.common.c.a.l).getAbsolutePath());
        } catch (IOException e) {
            f.a("VoteDataCache", e.getMessage());
        }
    }

    public String a(String str) {
        IOException e;
        FileInputStream fileInputStream;
        FileNotFoundException e2;
        Throwable th;
        String str2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            if (this.a == null) {
                b();
            }
            if (this.a != null) {
                File a = this.a.a(str);
                if (a == null || !a.exists()) {
                    if (str2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e3) {
                            f.a("VoteDataCache", e3.getMessage());
                        }
                    }
                    return str2;
                }
                fileInputStream = new FileInputStream(a);
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    str2 = EncodingUtils.getString(bArr, "UTF-8");
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    try {
                        f.a("VoteDataCache", e2.getMessage());
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e32) {
                                f.a("VoteDataCache", e32.getMessage());
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e322) {
                                f.a("VoteDataCache", e322.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e322 = e5;
                    f.a("VoteDataCache", e322.getMessage());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3222) {
                            f.a("VoteDataCache", e3222.getMessage());
                        }
                    }
                    return str2;
                }
            }
            Object obj = str2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e32222) {
                    f.a("VoteDataCache", e32222.getMessage());
                }
            }
        } catch (FileNotFoundException e6) {
            e2 = e6;
            fileInputStream = str2;
            f.a("VoteDataCache", e2.getMessage());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str2;
        } catch (IOException e7) {
            e32222 = e7;
            fileInputStream = str2;
            f.a("VoteDataCache", e32222.getMessage());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str2;
        } catch (Throwable th3) {
            fileInputStream = str2;
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str2;
    }

    public void a(String str, String str2) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            if (this.a == null) {
                b();
            }
            if (!(this.a == null || this.a.a(str2, byteArrayInputStream, null))) {
                com.qq.reader.common.monitor.debug.c.a("VoteDataCache", "PerferenceCache save Data Error ");
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    f.a("VoteDataCache", e.getMessage());
                }
            }
        } catch (IOException e2) {
            f.a("VoteDataCache", e2.getMessage());
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e22) {
                    f.a("VoteDataCache", e22.getMessage());
                }
            }
        } catch (Throwable th) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e3) {
                    f.a("VoteDataCache", e3.getMessage());
                }
            }
        }
    }

    public static void a(String str, ViewType viewType, String str2) {
        a().a(str, MD5.toMD5("VoteIconList" + viewType + str2).toLowerCase());
    }

    public static String a(ViewType viewType, String str) {
        return a().a(MD5.toMD5("VoteIconList" + viewType + str).toLowerCase());
    }

    public static void b(String str, ViewType viewType, String str2) {
        a().a(str, MD5.toMD5("VoteLastTime" + viewType + str2).toLowerCase());
    }

    public static String b(ViewType viewType, String str) {
        return a().a(MD5.toMD5("VoteLastTime" + viewType + str).toLowerCase());
    }

    public static void c(String str, ViewType viewType, String str2) {
        a().a(str, MD5.toMD5("VoteCount" + viewType + str2).toLowerCase());
    }

    public static String c(ViewType viewType, String str) {
        return a().a(MD5.toMD5("VoteCount" + viewType + str).toLowerCase());
    }

    public static void a(String str, String str2, String str3) {
        a().a(str, MD5.toMD5("VoteDialogVoteInfo" + str2 + str3).toLowerCase());
    }

    public static String b(String str, String str2) {
        return a().a(MD5.toMD5("VoteDialogVoteInfo" + str + str2).toLowerCase());
    }
}
