package com.qq.reader.cservice.adv;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.common.readertask.protocol.GetReaderPageAdvTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BookAdvertisementHandle */
public class e {
    private static e b;
    private Map<String, a> a = new HashMap();

    /* compiled from: BookAdvertisementHandle */
    public class a {
        final /* synthetic */ e a;
        private List<a> b = new ArrayList();
        private int c = 7;

        public a(e eVar) {
            this.a = eVar;
        }

        public void a(List<a> list) {
            this.b.addAll(list);
        }

        public List<a> a() {
            return this.b;
        }

        public void a(int i) {
            if (i > 0) {
                this.c = i;
            }
        }

        public int b() {
            return this.c;
        }
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e();
            }
            eVar = b;
        }
        return eVar;
    }

    private void c(final String str) {
        g.a().a(new GetReaderPageAdvTask(new c(this) {
            final /* synthetic */ e b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.b.a(readerProtocolTask, str, str, true);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }, str));
    }

    public void a(ReaderProtocolTask readerProtocolTask, String str, String str2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("code") == 0) {
                int length;
                JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                if (optJSONArray != null) {
                    length = optJSONArray.length();
                } else {
                    length = 0;
                }
                int i = -1;
                List arrayList = new ArrayList();
                if (length > 0) {
                    String str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    str3 = "";
                    String str4 = "";
                    long j = 0;
                    int i2 = -1;
                    for (int i3 = 0; i3 < length; i3++) {
                        jSONObject = (JSONObject) optJSONArray.get(i3);
                        long longValue = Long.valueOf(jSONObject.optString("id")).longValue();
                        String optString = jSONObject.optString("type");
                        int optInt = jSONObject.optInt("valuetype", 5);
                        String optString2 = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
                        String optString3 = jSONObject.optString("title");
                        String optString4 = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_DES);
                        String optString5 = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                        String optString6 = jSONObject.optString("link_url");
                        String optString7 = jSONObject.optString("read_online");
                        String optString8 = jSONObject.optString("image_url");
                        long longValue2 = Long.valueOf(jSONObject.optString("expire_date")).longValue();
                        try {
                            j = Long.valueOf(jSONObject.optString("start_date")).longValue();
                        } catch (Exception e) {
                        }
                        a aVar = new a(longValue, optString);
                        aVar.b(optInt);
                        aVar.a(optString2);
                        aVar.d(optString3);
                        aVar.g(optString4);
                        aVar.b(optString5);
                        aVar.f(a(ReaderApplication.getApplicationImp(), optString6));
                        aVar.c(optString7);
                        aVar.e(optString8);
                        aVar.a(longValue2);
                        aVar.b(j);
                        aVar.i(str4);
                        jSONObject = jSONObject.optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
                        if (jSONObject != null) {
                            aVar.h(jSONObject.toString());
                            if (i2 < 0) {
                                i2 = aVar.v();
                            }
                        }
                        arrayList.add(aVar);
                    }
                    i = i2;
                } else if (length <= 0) {
                    e(str2);
                }
                if (i < 0) {
                    i = 7;
                }
                a aVar2 = new a(this);
                aVar2.a(i);
                aVar2.a(arrayList);
                this.a.put(str2, aVar2);
                if (z) {
                    a(str, str2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public a a(String str) {
        return (a) this.a.get(str);
    }

    private String d(String str) {
        if (str == null || str.trim().length() <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(v.c(str));
        stringBuilder.append(File.separator);
        stringBuilder.append("adv");
        stringBuilder.append(".m");
        return stringBuilder.toString();
    }

    public synchronized void b(String str) {
        RandomAccessFile randomAccessFile;
        Throwable th;
        RandomAccessFile randomAccessFile2 = null;
        synchronized (this) {
            String d = d(str);
            if (!TextUtils.isEmpty(d) && new File(d).exists()) {
                try {
                    randomAccessFile = new RandomAccessFile(d, "r");
                    if (randomAccessFile != null) {
                        try {
                            if (randomAccessFile.length() != 0) {
                                byte[] bArr = new byte[((int) randomAccessFile.length())];
                                randomAccessFile.read(bArr);
                                a(null, new String(bArr), str, false);
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            randomAccessFile2 = randomAccessFile;
                            th = th3;
                            if (randomAccessFile2 != null) {
                                try {
                                    randomAccessFile2.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    } else {
                        randomAccessFile2 = randomAccessFile;
                    }
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Exception e5) {
                    randomAccessFile = null;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            }
        }
    }

    private synchronized void e(String str) {
        File file = new File(d(str));
        if (file.exists() && file.length() > 0) {
            file.delete();
        }
    }

    private synchronized void a(String str, String str2) {
        IOException e;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        synchronized (this) {
            File file = new File(d(str2));
            if (file.exists() && file.length() > 0) {
                file.delete();
            }
            try {
                if (ab.a(file.getParentFile())) {
                    file.createNewFile();
                }
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                try {
                    randomAccessFile2.write(str.getBytes());
                    randomAccessFile2.close();
                    RandomAccessFile randomAccessFile3 = null;
                    if (null != null) {
                        try {
                            randomAccessFile3.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    randomAccessFile = randomAccessFile2;
                    try {
                        e2.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e22 = e5;
                e22.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            }
        }
    }

    private String a(Context context, String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (str.indexOf("?") != -1) {
            stringBuffer.append("&");
        } else {
            stringBuffer.append("?");
        }
        stringBuffer.append("timi=");
        stringBuffer.append(d.z(context));
        stringBuffer.append(FeedDataTask.MS_SEX);
        stringBuffer.append(d.aU(context));
        return stringBuffer.toString();
    }
}
