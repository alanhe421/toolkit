package com.qq.reader.module.comic.e;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryComicDirectoryTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao.e;
import com.qq.reader.module.comic.entity.l;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import org.json.JSONObject;

/* compiled from: ComicDirHandle */
public class c {
    private static final String a = c.class.getSimpleName();
    private l b;
    private Handler c = null;
    private Context d = null;

    /* compiled from: ComicDirHandle */
    private class a extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected Void a(Object... objArr) {
            int i = 0;
            String str = (String) objArr[0];
            Boolean bool = (Boolean) objArr[1];
            l a = this.a.c(this.a.a(str));
            this.a.a(a);
            if (bool.booleanValue()) {
                long j = 0;
                if (a != null) {
                    j = a.n();
                    i = a.i();
                }
                g.a().a(this.a.a(str, j, i, e.a(this.a.d)));
            }
            return null;
        }
    }

    public c(Context context) {
        this.d = context;
    }

    public void a(String str, boolean z) {
        new a().execute(new Object[]{str, Boolean.valueOf(z)});
    }

    public void a(Handler handler) {
        this.c = handler;
    }

    private ReaderProtocolTask a(final String str, long j, int i, int i2) {
        return new QueryComicDirectoryTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ c b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.b.a(str, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.b.c != null) {
                    this.b.c.sendEmptyMessage(21001);
                }
            }
        }, str, j, i, i2);
    }

    private void a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int optInt = jSONObject.optInt("code");
            if (optInt == 0) {
                Gson gson = new Gson();
                Object obj = jSONObject.get("data");
                this.b = (l) gson.fromJson(obj.toString(), l.class);
                if (this.b == null) {
                    b();
                    return;
                }
                switch (Integer.parseInt(this.b.m())) {
                    case 0:
                        b(obj.toString());
                        a(this.b);
                        return;
                    case 1:
                        b(obj.toString());
                        a(this.b);
                        return;
                    case 2:
                        Collection h = this.b.h();
                        l c = c(a(this.b.e()));
                        if (c != null) {
                            c.h().addAll(h);
                            b(gson.toJson(c));
                            a(c);
                            return;
                        }
                        g.a().a(a(str, 0, 0, 0));
                        return;
                    default:
                        return;
                }
            } else if (this.c != null) {
                this.c.sendMessage(this.c.obtainMessage(21001, optInt, 0));
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(l lVar) {
        if (lVar != null && this.c != null) {
            Message obtain = Message.obtain();
            obtain.what = 21000;
            obtain.obj = lVar;
            this.c.sendMessage(obtain);
        }
    }

    private void b() {
        if (this.c != null) {
            Message obtain = Message.obtain();
            obtain.what = 21001;
            this.c.sendMessage(obtain);
        }
    }

    private void b(String str) {
        IOException e;
        Throwable th;
        Exception e2;
        RandomAccessFile randomAccessFile = null;
        synchronized (c.class) {
            if (this.b != null) {
                File file = new File(a(this.b.e()));
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
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (IOException e4) {
                        e3 = e4;
                        randomAccessFile = randomAccessFile2;
                        try {
                            e3.printStackTrace();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e2 = e6;
                        randomAccessFile = randomAccessFile2;
                        e2.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    e322 = e7;
                    e322.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Exception e8) {
                    e2 = e8;
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                }
            }
        }
    }

    private l c(String str) {
        RandomAccessFile randomAccessFile;
        Exception e;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        synchronized (c.class) {
            if (file.exists()) {
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        if (randomAccessFile.length() == 0) {
                            randomAccessFile.close();
                            RandomAccessFile randomAccessFile2 = null;
                            if (null != null) {
                                try {
                                    randomAccessFile2.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            return null;
                        }
                        byte[] bArr = new byte[((int) randomAccessFile.length())];
                        randomAccessFile.read(bArr);
                        l lVar = (l) new Gson().fromJson(new String(bArr), l.class);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return lVar;
                    } catch (Exception e4) {
                        e = e4;
                        try {
                            com.qq.reader.common.monitor.debug.c.e(a, e.getLocalizedMessage());
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    randomAccessFile = null;
                    com.qq.reader.common.monitor.debug.c.e(a, e.getLocalizedMessage());
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = null;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            }
            return null;
        }
    }

    public void a() {
        this.c = null;
    }

    public String a(String str) {
        String c = v.c(str);
        if (c == null) {
            return null;
        }
        return (c + File.separator) + "chapter.q";
    }
}
