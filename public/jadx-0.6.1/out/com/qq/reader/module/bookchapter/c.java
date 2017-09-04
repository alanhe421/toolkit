package com.qq.reader.module.bookchapter;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: ChapterParser */
public class c {
    private static ArrayList<Mark> m = null;
    private static c p;
    private final String a = "第\\s{0,6}([一二三四五六七八九十零百千万壹贰叁肆伍陆柒捌拾0-9１２３４５６７８９０]{1,9})\\s{0,6}[章节回卷篇]";
    private final String b = "第\\s{0,6}([0-9]{1,9})\\s{0,6}[章]";
    private String c = "第\\s{0,6}([一二三四五六七八九十零百千万壹贰叁肆伍陆柒捌拾0-9１２３４５６７８９０]{1,9})\\s{0,6}[章节回卷篇]";
    private int[][] d = new int[][]{new int[]{-25, -84, -84}, new int[]{44, Opcodes.NEG_INT}, new int[]{Opcodes.NEG_INT, 44}, new int[]{-75, -38}};
    private int e = 0;
    private Pattern f;
    private volatile boolean g = false;
    private long h = 0;
    private long i = 0;
    private int j;
    private String k;
    private String l;
    private a n;
    private a o;
    private final int q = 200;
    private volatile boolean r = false;
    private volatile boolean s = false;

    /* compiled from: ChapterParser */
    public interface a {
        void a(int i, Mark mark);
    }

    /* compiled from: ChapterParser */
    private class b extends Thread {
        final /* synthetic */ c a;

        private b(c cVar) {
            this.a = cVar;
        }

        public void run() {
            RandomAccessFile randomAccessFile;
            Mark localMark;
            Throwable th;
            Exception e;
            if (c.m.size() != 0) {
                c.m.clear();
            }
            int i = 0;
            switch (this.a.j) {
                case 1:
                    i = 1;
                    break;
                case 2:
                case 12:
                case 14:
                    i = 3;
                    break;
                case 4:
                case 10:
                    i = 0;
                    break;
                case 8:
                    i = 2;
                    break;
            }
            RandomAccessFile randomAccessFile2 = null;
            long j = 0;
            long j2 = 0;
            int i2 = 0;
            Object obj = new byte[200];
            try {
                randomAccessFile = new RandomAccessFile(this.a.l, "r");
                try {
                    this.a.i = randomAccessFile.length();
                    byte[] bArr = new byte[Math.min((int) randomAccessFile.length(), (int) 153600)];
                    while (j2 < randomAccessFile.length()) {
                        int min = (int) Math.min((long) bArr.length, randomAccessFile.length() - j2);
                        randomAccessFile.read(bArr, 0, min);
                        long j3 = j2 + ((long) min);
                        int i3 = 0;
                        while (i3 < min) {
                            if (this.a.g) {
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                    if (this.a.g) {
                                        try {
                                            if (this.a.n != null) {
                                                this.a.s = false;
                                                this.a.n.a(302, null);
                                            }
                                        } catch (Exception e3) {
                                            return;
                                        }
                                    }
                                    if (c.m.size() == 0) {
                                        localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                                        localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                                        try {
                                            if (this.a.n != null) {
                                                this.a.s = false;
                                                this.a.n.a(301, localMark);
                                            }
                                            c.m.add(localMark);
                                        } catch (Exception e4) {
                                            return;
                                        }
                                    }
                                    i.c().a(this.a.l, this.a.e(), true);
                                    try {
                                        if (this.a.n != null) {
                                            this.a.s = false;
                                            this.a.n.a(300, null);
                                        }
                                        if (this.a.o != null) {
                                            this.a.o.a(300, null);
                                        }
                                    } catch (Exception e5) {
                                        return;
                                    }
                                }
                                this.a.r = false;
                                return;
                            }
                            int i4;
                            if (bArr[i3] == (byte) 10) {
                                if (i2 < obj.length && i2 > 1 && this.a.e == this.a.d[i].length) {
                                    Object obj2 = new byte[i2];
                                    System.arraycopy(obj, 0, obj2, 0, obj2.length);
                                    String a = com.qq.reader.common.utils.c.b.a(obj2, this.a.j);
                                    if (this.a.a(a)) {
                                        localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                                        localMark.setStartPoint(j).setEncoding(this.a.j).setDescriptionStr(ao.a(a.replaceAll("||||\t|||", ""), true));
                                        try {
                                            if (this.a.n != null) {
                                                if (this.a.s) {
                                                    this.a.s = false;
                                                    this.a.n.a(303, null);
                                                }
                                                this.a.n.a(301, localMark);
                                            }
                                            c.m.add(localMark);
                                        } catch (Exception e6) {
                                            if (randomAccessFile != null) {
                                                try {
                                                    randomAccessFile.close();
                                                } catch (IOException e22) {
                                                    e22.printStackTrace();
                                                }
                                                if (this.a.g) {
                                                    try {
                                                        if (this.a.n != null) {
                                                            this.a.s = false;
                                                            this.a.n.a(302, null);
                                                        }
                                                    } catch (Exception e7) {
                                                        return;
                                                    }
                                                }
                                                if (c.m.size() == 0) {
                                                    localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                                                    localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                                                    try {
                                                        if (this.a.n != null) {
                                                            this.a.s = false;
                                                            this.a.n.a(301, localMark);
                                                        }
                                                        c.m.add(localMark);
                                                    } catch (Exception e8) {
                                                        return;
                                                    }
                                                }
                                                i.c().a(this.a.l, this.a.e(), true);
                                                try {
                                                    if (this.a.n != null) {
                                                        this.a.s = false;
                                                        this.a.n.a(300, null);
                                                    }
                                                    if (this.a.o != null) {
                                                        this.a.o.a(300, null);
                                                    }
                                                } catch (Exception e9) {
                                                    return;
                                                }
                                            }
                                            this.a.r = false;
                                            return;
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                }
                                this.a.e = 0;
                                i2 = 0;
                                if (i == 1) {
                                    i4 = i3 + 1;
                                    this.a.h = 1 + this.a.h;
                                } else {
                                    i4 = i3;
                                }
                                j2 = this.a.h + 1;
                            } else {
                                if (i2 < obj.length) {
                                    this.a.a(bArr[i3], i);
                                    obj[i2] = bArr[i3];
                                }
                                i2++;
                                j2 = j;
                                i4 = i3;
                            }
                            this.a.h = 1 + this.a.h;
                            i3 = i4 + 1;
                            j = j2;
                        }
                        j2 = j3;
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        if (this.a.g) {
                            try {
                                if (this.a.n != null) {
                                    this.a.s = false;
                                    this.a.n.a(302, null);
                                }
                            } catch (Exception e10) {
                                return;
                            }
                        }
                        if (c.m.size() == 0) {
                            localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                            localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                            try {
                                if (this.a.n != null) {
                                    this.a.s = false;
                                    this.a.n.a(301, localMark);
                                }
                                c.m.add(localMark);
                            } catch (Exception e11) {
                                return;
                            }
                        }
                        i.c().a(this.a.l, this.a.e(), true);
                        try {
                            if (this.a.n != null) {
                                this.a.s = false;
                                this.a.n.a(300, null);
                            }
                            if (this.a.o != null) {
                                this.a.o.a(300, null);
                            }
                        } catch (Exception e12) {
                            return;
                        }
                    }
                    this.a.r = false;
                } catch (Exception e13) {
                    e = e13;
                    randomAccessFile2 = randomAccessFile;
                } catch (Throwable th22) {
                    th = th22;
                }
            } catch (Exception e14) {
                e = e14;
                try {
                    e.printStackTrace();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                        if (this.a.g) {
                            try {
                                if (this.a.n != null) {
                                    this.a.s = false;
                                    this.a.n.a(302, null);
                                }
                            } catch (Exception e15) {
                                return;
                            }
                        }
                        if (c.m.size() == 0) {
                            localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                            localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                            try {
                                if (this.a.n != null) {
                                    this.a.s = false;
                                    this.a.n.a(301, localMark);
                                }
                                c.m.add(localMark);
                            } catch (Exception e16) {
                                return;
                            }
                        }
                        i.c().a(this.a.l, this.a.e(), true);
                        try {
                            if (this.a.n != null) {
                                this.a.s = false;
                                this.a.n.a(300, null);
                            }
                            if (this.a.o != null) {
                                this.a.o.a(300, null);
                            }
                        } catch (Exception e17) {
                            return;
                        }
                    }
                    this.a.r = false;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e18) {
                            e18.printStackTrace();
                        }
                        if (this.a.g) {
                            if (c.m.size() == 0) {
                                localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                                localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                                try {
                                    if (this.a.n != null) {
                                        this.a.s = false;
                                        this.a.n.a(301, localMark);
                                    }
                                    c.m.add(localMark);
                                } catch (Exception e19) {
                                    return;
                                }
                            }
                            i.c().a(this.a.l, this.a.e(), true);
                            try {
                                if (this.a.n != null) {
                                    this.a.s = false;
                                    this.a.n.a(300, null);
                                }
                                if (this.a.o != null) {
                                    this.a.o.a(300, null);
                                }
                            } catch (Exception e20) {
                                return;
                            }
                        }
                        try {
                            if (this.a.n != null) {
                                this.a.s = false;
                                this.a.n.a(302, null);
                            }
                        } catch (Exception e21) {
                            return;
                        }
                    }
                    this.a.r = false;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                    if (this.a.g) {
                        if (c.m.size() == 0) {
                            localMark = new LocalMark(this.a.k, this.a.l, this.a.i, 2, false);
                            localMark.setStartPoint(0).setEncoding(this.a.j).setDescriptionStr(ao.a("首页", false));
                            if (this.a.n != null) {
                                this.a.s = false;
                                this.a.n.a(301, localMark);
                            }
                            c.m.add(localMark);
                        }
                        i.c().a(this.a.l, this.a.e(), true);
                        if (this.a.n != null) {
                            this.a.s = false;
                            this.a.n.a(300, null);
                        }
                        if (this.a.o != null) {
                            this.a.o.a(300, null);
                        }
                    } else if (this.a.n != null) {
                        this.a.s = false;
                        this.a.n.a(302, null);
                    }
                }
                this.a.r = false;
                throw th;
            }
        }
    }

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (p == null) {
                p = new c();
                m = new ArrayList();
            }
            cVar = p;
        }
        return cVar;
    }

    public static void b() {
        p = null;
        m.clear();
    }

    public boolean c() {
        return this.r;
    }

    public synchronized void a(int i, String str, String str2, boolean z) {
        if (this.r) {
            try {
                if (this.n != null && this.s) {
                    this.s = false;
                    this.n.a(303, null);
                }
            } catch (Exception e) {
            }
        } else {
            this.r = true;
            this.h = 0;
            this.i = 0;
            this.j = i;
            this.l = str;
            this.k = str2;
            this.g = false;
            this.c = "第\\s{0,6}([一二三四五六七八九十零百千万壹贰叁肆伍陆柒捌拾0-9１２３４５６７８９０]{1,9})\\s{0,6}[章节回卷篇]";
            this.f = Pattern.compile(this.c);
            new b().start();
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public List<Mark> d() {
        return m;
    }

    public Mark[] e() {
        if (m.size() <= 0) {
            return null;
        }
        Mark[] markArr = new Mark[m.size()];
        m.toArray(markArr);
        return markArr;
    }

    public void a(Mark[] markArr) {
        if (markArr != null && markArr.length > 0 && m.size() == 0) {
            for (Object add : markArr) {
                m.add(add);
            }
        }
    }

    private boolean a(String str) {
        return this.f.matcher(str).find();
    }

    private void a(byte b, int i) {
        if (this.e < this.d[i].length && this.d[i][this.e] == b) {
            this.e++;
        } else if (this.e < this.d[i].length && this.e > 0) {
            this.e--;
        }
    }

    public double f() {
        return ((double) this.h) / ((double) this.i);
    }

    public synchronized void a(a aVar) {
        this.s = true;
        this.n = aVar;
    }

    public synchronized void b(a aVar) {
        this.o = aVar;
    }

    public synchronized void g() {
        this.o = null;
    }
}
