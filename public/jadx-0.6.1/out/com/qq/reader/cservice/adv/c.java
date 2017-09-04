package com.qq.reader.cservice.adv;

import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.a.b;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AdvertisementRedPointHandler */
public class c {
    private static List<String> a = Collections.synchronizedList(new ArrayList());

    public static boolean a(boolean z) {
        List c = b.a(ReaderApplication.getApplicationImp().getApplicationContext()).c("102597");
        if (c != null && c.size() > 0) {
            a aVar = (a) c.get(0);
            if (aVar != null && aVar.l() == 1) {
                if (z) {
                    aVar.a(0);
                    b.a(ReaderApplication.getApplicationImp().getApplicationContext()).a(aVar, false);
                }
                return true;
            }
        }
        return false;
    }

    public static a a() {
        List c = b.a(ReaderApplication.getApplicationImp().getApplicationContext()).c("102542");
        if (c != null && c.size() > 0) {
            a aVar = (a) c.get(0);
            if (aVar != null) {
                return aVar;
            }
        }
        return null;
    }

    public static a b() {
        return a(0);
    }

    public static a a(int i) {
        List c = b.a(ReaderApplication.getApplicationImp()).c("102425");
        if (c != null && c.size() > 0) {
            a aVar = (a) c.get(i % c.size());
            if (aVar != null) {
                return aVar;
            }
        }
        return null;
    }

    private static int c(String str) {
        if ("TYPE_SKIN_LIST_UPDATE".equals(str)) {
            return 2;
        }
        return -1;
    }

    private static int d(String str) {
        if ("102425".equals(str)) {
            return 1;
        }
        return -1;
    }

    public static boolean a(Object obj) {
        if (obj != null) {
            if (obj instanceof a) {
                return d.c(ReaderApplication.getApplicationImp(), d(((a) obj).f()));
            } else if (obj instanceof String) {
                return d.c(ReaderApplication.getApplicationImp(), c((String) obj));
            }
        }
        return false;
    }

    public static void a(Object obj, boolean z) {
        if (obj != null) {
            if (obj instanceof a) {
                d.a(ReaderApplication.getApplicationImp(), d(((a) obj).f()), z);
            }
            if (obj instanceof String) {
                d.a(ReaderApplication.getApplicationImp(), c((String) obj), z);
            }
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List c = b.a(ReaderApplication.getApplicationImp()).c(str);
        if (c == null || c.size() <= 0) {
            return false;
        }
        a aVar = (a) c.get(0);
        if (aVar == null) {
            return false;
        }
        return aVar.l() == 1;
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            List c = b.a(ReaderApplication.getApplicationImp()).c(str);
            if (c != null && c.size() > 0) {
                a aVar = (a) c.get(0);
                if (aVar != null && aVar.l() != 0) {
                    aVar.a(0);
                    b.a(ReaderApplication.getApplicationImp()).a(aVar, false);
                }
            }
        }
    }

    public static synchronized void a(List<String> list) {
        synchronized (c.class) {
            a((List) list, true);
        }
    }

    public static synchronized void a(List<String> list, boolean z) {
        synchronized (c.class) {
            a.clear();
            if (list != null) {
                a.addAll(list);
            }
            if (z) {
                c();
            }
        }
    }

    public static void c() {
        Intent intent = new Intent();
        intent.setAction(a.cz);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(intent);
    }

    public static void d() {
        if (a.size() > 0) {
            c();
            return;
        }
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c() {
            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                c.b(str, readerProtocolTask.getUrl(), false);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Throwable th;
                File a = com.qq.reader.module.bookstore.qnative.d.b().a(readerProtocolTask.getUrl());
                InputStream inputStream = null;
                if (a != null) {
                    InputStream bufferedInputStream;
                    try {
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(a));
                        try {
                            b.a(bufferedInputStream, byteArrayOutputStream);
                            c.b(new String(byteArrayOutputStream.toByteArray(), "UTF-8"), readerProtocolTask.getUrl(), true);
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception e2) {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            inputStream = bufferedInputStream;
                            th = th3;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        bufferedInputStream = null;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                }
            }
        });
        readerProtocolJSONTask.setUrl(e.bR);
        g.a().a(readerProtocolJSONTask);
    }

    private static void b(String str, String str2, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        int i = 0;
        try {
            List arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str);
            if (!z) {
                jSONObject.put("expireTime", (System.currentTimeMillis() + BuglyBroadcastRecevier.UPLOADLIMITED) / 1000);
                ByteArrayInputStream byteArrayInputStream;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byteArrayOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
                        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayInputStream = null;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Throwable th3) {
                                throw th;
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                    try {
                        com.qq.reader.module.bookstore.qnative.d.b().a(str2, byteArrayInputStream, null);
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Throwable th4) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    byteArrayInputStream = null;
                    byteArrayOutputStream = null;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("topList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2).optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
                    if (optJSONObject != null) {
                        CharSequence optString = optJSONObject.optString("adPosition");
                        if (!TextUtils.isEmpty(optString)) {
                            arrayList.add(optString);
                        }
                    }
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                while (i < optJSONArray2.length()) {
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i).optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
                    if (optJSONObject2 != null) {
                        CharSequence optString2 = optJSONObject2.optString("adPosition");
                        if (!TextUtils.isEmpty(optString2)) {
                            arrayList.add(optString2);
                        }
                    }
                    i++;
                }
            }
            a(arrayList);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("AdvertisementRedPointHandler", e.getMessage());
            a(null);
        }
    }

    public static synchronized boolean e() {
        boolean z;
        synchronized (c.class) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                if (a((String) a.get(i))) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }
}
