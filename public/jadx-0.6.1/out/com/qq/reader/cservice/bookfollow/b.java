package com.qq.reader.cservice.bookfollow;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.push.pushAction.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.QueryNewTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FollowNewContent */
public class b {
    private Context a;
    private a b;

    /* compiled from: FollowNewContent */
    public interface a {
        void onQueryNewResult(int i, Object obj);
    }

    /* compiled from: FollowNewContent */
    private class b implements Comparable<b> {
        String a = "";
        long b = 0;
        String c = "";
        int d = 0;
        long e;
        long f;
        final /* synthetic */ b g;

        public /* synthetic */ int compareTo(Object obj) {
            return a((b) obj);
        }

        public b(b bVar, String str, long j, String str2, int i, long j2, int i2) {
            this.g = bVar;
            this.a = str;
            this.b = j;
            this.c = str2;
            this.d = i;
            this.e = j2;
            this.f = (long) i2;
        }

        public int a(b bVar) {
            if (this.b > bVar.b) {
                return 1;
            }
            if (this.b < bVar.b) {
                return -1;
            }
            return 0;
        }
    }

    public b(Context context) {
        this.a = context;
    }

    public void a(String str) {
        g.a().a(b(str));
    }

    private QueryNewTask b(String str) {
        return new QueryNewTask(new c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    this.a.a(new JSONObject(str), readerProtocolTask);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (this.a.b != null) {
                        this.a.b.onQueryNewResult(8008, ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed));
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                try {
                    String string = ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed);
                    if (this.a.b != null) {
                        this.a.b.onQueryNewResult(8008, string);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, str);
    }

    public void a(String str, int i) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bookId", Long.parseLong(str));
            jSONObject.put("lastChapterId", i);
            jSONArray.put(jSONObject);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str2 = null;
        if (jSONArray.length() > 0) {
            str2 = jSONArray.toString();
        }
        if (str2 != null && str2.trim().length() > 0) {
            ReaderTask b = b(str2);
            b.setTid(-100);
            g.a().a(b);
        }
    }

    public void a() {
        new Thread(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                List p = i.c().p();
                Object obj = null;
                if (p.size() > 0) {
                    Mark[] markArr = new Mark[p.size()];
                    p.toArray(markArr);
                    String a = b.a(markArr);
                    if (a != null) {
                        obj = 1;
                        this.a.a(a);
                    }
                }
                if (obj == null && this.a.b != null) {
                    this.a.b.onQueryNewResult(8016, null);
                }
            }
        }).start();
    }

    public static String a(List<String> list, boolean z) {
        String str = "";
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str2 = (String) list.get(i2);
            OnlineTag a = v.b().a(str2);
            if (a != null && a.q() == 0) {
                if (a.w() == 1) {
                    if (i < 10) {
                        i++;
                    }
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("bookId", Long.parseLong(str2));
                    jSONObject.put("lastChapterId", a.n());
                    jSONObject.put("lasttime", a.H());
                    jSONArray.put(jSONObject);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (jSONArray.length() > 0) {
            return jSONArray.toString();
        }
        return null;
    }

    public static String a(Mark[] markArr) {
        int i = 0;
        JSONArray jSONArray = new JSONArray();
        for (Mark mark : markArr) {
            if (mark != null) {
                String id = mark.getId();
                OnlineTag a = v.b().a(id);
                if (a != null && a.q() == 0) {
                    if (a.w() == 1) {
                        if (i < 10) {
                            i++;
                        }
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("bookId", Long.parseLong(id));
                        jSONObject.put("lastChapterId", a.n());
                        jSONObject.put("lasttime", a.H());
                        jSONArray.put(jSONObject);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        if (jSONArray.length() > 0) {
            return jSONArray.toString();
        }
        return null;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(JSONObject jSONObject, ReaderProtocolTask readerProtocolTask) {
        String str;
        Exception e;
        Throwable th;
        int i;
        Object obj = null;
        String str2 = "";
        Object obj2 = null;
        try {
            Object obj3;
            Object obj4;
            Object obj5;
            String str3;
            if (jSONObject.getInt("code") == 0) {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                int length = jSONArray.length();
                List arrayList = new ArrayList();
                if (length > 0) {
                    long optLong;
                    int i2 = 0;
                    str = "";
                    List<b> arrayList2 = new ArrayList();
                    HashMap hashMap = new HashMap();
                    int i3 = 0;
                    while (i3 < length) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i3);
                        optLong = jSONObject2.optLong("bookId");
                        long optLong2 = jSONObject2.optLong("lastChapterId");
                        long optLong3 = jSONObject2.optLong("lastuploadtime");
                        String optString = jSONObject2.optString("lastcname");
                        int optInt = jSONObject2.optInt("isfinished");
                        int optInt2 = jSONObject2.optInt("index");
                        int optInt3 = jSONObject2.optInt("resType");
                        OnlineTag a = v.b().a(String.valueOf(optLong));
                        if (a != null) {
                            if (optInt3 == 3 || optInt3 == 2) {
                                optInt3 = optInt2;
                            } else {
                                optInt3 = (int) optLong2;
                            }
                            a.c(optLong3);
                            a.l(optString);
                            a.h(optInt);
                            int n = a.n();
                            a.d(optInt3);
                            if (optInt3 > n) {
                                arrayList.add(a);
                            }
                            arrayList2.add(new b(this, a.k(), optLong3, optString, optInt, (long) optInt3, n));
                            v.b().b(a);
                            if (readerProtocolTask == null || readerProtocolTask.getTid() != -100) {
                                optInt3 = i2 + 1;
                                i3++;
                                i2 = optInt3;
                            }
                        }
                        optInt3 = i2;
                        i3++;
                        i2 = optInt3;
                    }
                    Object obj6 = new OnlineTag[arrayList.size()];
                    try {
                        arrayList.toArray(obj6);
                        Collections.sort(arrayList2);
                        for (b bVar : arrayList2) {
                            boolean z;
                            i c = i.c();
                            str3 = bVar.a;
                            optLong = bVar.b;
                            String str4 = bVar.c;
                            if (bVar.e > bVar.f) {
                                z = true;
                            } else {
                                z = false;
                            }
                            c.a(str3, optLong, str4, z, bVar.d);
                        }
                        if (i2 > 0) {
                            d.p(this.a.getApplicationContext(), i2);
                        }
                        obj3 = obj6;
                    } catch (Exception e2) {
                        e = e2;
                        obj2 = obj6;
                        try {
                            e.printStackTrace();
                            obj = 1;
                            str = ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed);
                            if (this.b == null) {
                                this.b.onQueryNewResult(8008, str);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (this.b != null) {
                                if (obj != null) {
                                    this.b.onQueryNewResult(8008, str2);
                                } else {
                                    this.b.onQueryNewResult(8007, obj2);
                                    new e(this.a).a(jSONObject);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        obj2 = obj6;
                        if (this.b != null) {
                            if (obj != null) {
                                this.b.onQueryNewResult(8007, obj2);
                                new e(this.a).a(jSONObject);
                            } else {
                                this.b.onQueryNewResult(8008, str2);
                            }
                        }
                        throw th;
                    }
                }
                obj3 = null;
                obj4 = str2;
                obj5 = null;
            } else {
                try {
                    int i4 = 1;
                    str3 = jSONObject.getString("message");
                    obj3 = null;
                } catch (Exception e3) {
                    e = e3;
                    i = 1;
                    e.printStackTrace();
                    obj = 1;
                    str = ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed);
                    if (this.b == null) {
                        this.b.onQueryNewResult(8008, str);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    i = 1;
                    if (this.b != null) {
                        if (obj != null) {
                            this.b.onQueryNewResult(8008, str2);
                        } else {
                            this.b.onQueryNewResult(8007, obj2);
                            new e(this.a).a(jSONObject);
                        }
                    }
                    throw th;
                }
            }
            if (this.b == null) {
                return;
            }
            if (obj5 != null) {
                this.b.onQueryNewResult(8008, obj4);
                return;
            }
            this.b.onQueryNewResult(8007, obj3);
            new e(this.a).a(jSONObject);
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            obj = 1;
            str = ReaderApplication.getApplicationImp().getResources().getString(R.string.pulldownview_failed);
            if (this.b == null) {
                this.b.onQueryNewResult(8008, str);
            }
        }
    }
}
