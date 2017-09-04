package com.qq.reader.common.utils;

import android.content.Context;
import android.os.Environment;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.tencent.av.config.ConfigBaseParser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* compiled from: SysUtil */
public class am {
    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String b() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static synchronized String a(Context context) {
        DataInputStream dataInputStream;
        String readUTF;
        String str;
        Exception e;
        Throwable th;
        DataInputStream dataInputStream2 = null;
        synchronized (am.class) {
            File c = ao.c(a.bh);
            if (c != null) {
                try {
                    if (c.exists() && c.length() > 0) {
                        dataInputStream = new DataInputStream(new FileInputStream(c));
                        try {
                            readUTF = dataInputStream.readUTF();
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (IOException e2) {
                                    f.a("getUserTid finally Exception :", e2.toString());
                                    str = readUTF;
                                }
                            }
                            str = readUTF;
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                f.a("getUserTid Exception :", e.toString());
                                if (c != null) {
                                    c.delete();
                                }
                                if (dataInputStream != null) {
                                    try {
                                        dataInputStream.close();
                                    } catch (IOException e22) {
                                        f.a("getUserTid finally Exception :", e22.toString());
                                        str = dataInputStream2;
                                    }
                                }
                                str = dataInputStream2;
                                if (str == null) {
                                    str = b(context);
                                }
                                return str;
                            } catch (Throwable th2) {
                                th = th2;
                                dataInputStream2 = dataInputStream;
                                if (dataInputStream2 != null) {
                                    try {
                                        dataInputStream2.close();
                                    } catch (IOException e4) {
                                        f.a("getUserTid finally Exception :", e4.toString());
                                    }
                                }
                                throw th;
                            }
                        }
                        if (str == null) {
                            str = b(context);
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    dataInputStream = dataInputStream2;
                    f.a("getUserTid Exception :", e.toString());
                    if (c != null) {
                        c.delete();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    str = dataInputStream2;
                    if (str == null) {
                        str = b(context);
                    }
                    return str;
                } catch (Throwable th3) {
                    th = th3;
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    throw th;
                }
            }
            dataInputStream = dataInputStream2;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            str = readUTF;
            if (str == null) {
                str = b(context);
            }
        }
        return str;
    }

    private static String d() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", ConfigBaseParser.DEFAULT_VALUE});
        } catch (Exception e) {
            return "Android";
        }
    }

    private static String b(Context context) {
        DataOutputStream dataOutputStream;
        Exception e;
        Throwable th;
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(d.i(context));
        stringBuffer.append(d());
        str = stringBuffer.toString();
        try {
            str = com.qq.reader.common.utils.a.d.b(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            str = com.qq.reader.common.utils.a.d.b(str.getBytes());
        }
        String str2 = str + System.currentTimeMillis();
        File c = ao.c(a.bh);
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(c));
            try {
                dataOutputStream.writeUTF(str2.trim());
                dataOutputStream.flush();
                str = str2.trim();
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    f.a("createAndWriteUserTid Exception :", e.toString());
                    if (c != null) {
                        c.delete();
                    }
                    str = d.j + str.trim();
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            dataOutputStream = null;
            f.a("createAndWriteUserTid Exception :", e.toString());
            if (c != null) {
                c.delete();
            }
            str = d.j + str.trim();
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            throw th;
        }
        return str;
    }

    public static long c() {
        DataInputStream dataInputStream;
        Exception e;
        Throwable th;
        long j = 0;
        File c = ao.c(a.bK);
        DataInputStream dataInputStream2 = null;
        if (c != null) {
            try {
                if (c.exists() && c.length() > j) {
                    dataInputStream = new DataInputStream(new FileInputStream(c));
                    try {
                        j = dataInputStream.readLong();
                        if (dataInputStream != null) {
                            try {
                                dataInputStream.close();
                            } catch (IOException e2) {
                                f.a("getUserID finally Exception :", e2.toString());
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            f.a("getUserID Exception :", e.toString());
                            if (c != null) {
                                c.delete();
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (IOException e22) {
                                    f.a("getUserID finally Exception :", e22.toString());
                                }
                            }
                            return j;
                        } catch (Throwable th2) {
                            th = th2;
                            dataInputStream2 = dataInputStream;
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e4) {
                                    f.a("getUserID finally Exception :", e4.toString());
                                }
                            }
                            throw th;
                        }
                    }
                    return j;
                }
            } catch (Exception e5) {
                e = e5;
                dataInputStream = dataInputStream2;
                f.a("getUserID Exception :", e.toString());
                if (c != null) {
                    c.delete();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th;
            }
        }
        if (dataInputStream2 != null) {
            try {
                dataInputStream2.close();
            } catch (IOException e222) {
                f.a("getUserID finally Exception :", e222.toString());
            }
        }
        return j;
    }

    public static boolean a(long j) {
        DataOutputStream dataOutputStream;
        boolean z;
        Exception e;
        Throwable th;
        File c = ao.c(a.bK);
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(c));
            try {
                dataOutputStream.writeLong(j);
                dataOutputStream.flush();
                z = true;
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    f.a("writeAccount Exception :", e.toString());
                    if (c != null) {
                        c.delete();
                    }
                    z = false;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            dataOutputStream = null;
            f.a("writeAccount Exception :", e.toString());
            if (c != null) {
                c.delete();
            }
            z = false;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            throw th;
        }
        return z;
    }
}
