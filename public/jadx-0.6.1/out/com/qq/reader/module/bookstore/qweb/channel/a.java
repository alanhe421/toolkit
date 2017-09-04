package com.qq.reader.module.bookstore.qweb.channel;

import android.content.Context;
import android.widget.GridView;
import com.qq.reader.appconfig.a.d;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ColumnConfig */
public class a {
    public static String b = "current_sel";
    public static String c = "current_index";
    public static final int[] d = new int[]{10003, 10004};
    public static final int[] e = new int[]{10006, Constants.CODE_SERVICE_DISABLED, 10008, 10009, 10010, 10011};
    public static final int[] f = new int[]{10018, 10019, 10020, 10021, 10022};
    public static final int[] g = new int[]{10023, 10024, 10025, 10026, 10027};
    public boolean a = false;
    private c h;
    private c i;
    private c j;
    private c k;
    private GridView l;
    private GridView m;
    private GridView n;
    private GridView o;
    private final int p = 10003;
    private final int[] q = new int[]{10004, 10017, 10022, 10027};

    public void a(c cVar, c cVar2, c cVar3, c cVar4) {
        this.h = cVar;
        this.i = cVar2;
        this.j = cVar3;
        this.k = cVar4;
    }

    public void a(GridView gridView, GridView gridView2, GridView gridView3, GridView gridView4) {
        this.l = gridView;
        this.m = gridView2;
        this.n = gridView3;
        this.o = gridView4;
    }

    private int c(int i) {
        if (i <= 10003) {
            return -1;
        }
        for (int i2 = 0; i2 < this.q.length; i2++) {
            if (i <= this.q[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public c a(int i) {
        switch (c(i)) {
            case 0:
                return this.h;
            case 1:
                return this.i;
            case 2:
                return this.j;
            case 3:
                return this.k;
            default:
                return null;
        }
    }

    public GridView b(int i) {
        switch (c(i)) {
            case 0:
                return this.l;
            case 1:
                return this.m;
            case 2:
                return this.n;
            case 3:
                return this.o;
            default:
                return null;
        }
    }

    private static int[] b(Context context) {
        switch (d.aS(context)) {
            case 0:
                return d;
            case 1:
                return e;
            case 2:
                return f;
            case 3:
                return g;
            default:
                return null;
        }
    }

    private static int[] b(Context context, int i) {
        switch (i) {
            case 0:
                return d;
            case 1:
                return e;
            case 2:
                return f;
            case 3:
                return g;
            default:
                return null;
        }
    }

    public static void a(Context context) {
        int[] b = b(context);
        if (b != null) {
            ArrayList b2 = d.a().b(Boolean.valueOf(false));
            if (b2 != null) {
                Iterator it = b2.iterator();
                while (it.hasNext()) {
                    ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
                    int titleid = columnWebEntity.getTitleid();
                    if (titleid != 10003 && titleid != 10004) {
                        for (int i : b) {
                            if (columnWebEntity.getTitleid() == i) {
                                columnWebEntity.setSelected(1);
                                break;
                            }
                        }
                    }
                }
                d.a().a(b2);
            }
        }
    }

    public static ArrayList<ColumnWebEntity> a(Context context, int i) {
        int[] b = b(context, i);
        ArrayList<ColumnWebEntity> arrayList = new ArrayList();
        if (b != null) {
            ArrayList b2 = d.a().b(Boolean.valueOf(false));
            if (b2 != null) {
                Iterator it = b2.iterator();
                while (it.hasNext()) {
                    ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
                    int titleid = columnWebEntity.getTitleid();
                    if (titleid == 10003 || titleid == 10004) {
                        arrayList.add(columnWebEntity);
                    }
                    for (int i2 : b) {
                        if (columnWebEntity.getTitleid() == i2) {
                            arrayList.add(columnWebEntity);
                            break;
                        }
                    }
                }
                return arrayList;
            }
        }
        return null;
    }
}
