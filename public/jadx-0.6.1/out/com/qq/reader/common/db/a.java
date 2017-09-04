package com.qq.reader.common.db;

import android.content.ContentValues;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: BaseDBDataHandler */
public abstract class a {
    public final String a = "DBHandler";

    public synchronized boolean a(c cVar, String str, ContentValues contentValues) {
        boolean z = false;
        synchronized (this) {
            try {
                if (cVar.a().insert(str, null, contentValues) > 0) {
                    z = true;
                }
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                c.e("DBHandler", str + " addItem with exception : " + e2.getMessage());
                cVar.c();
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e4) {
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(c cVar, String str, String str2) {
        boolean z = false;
        synchronized (this) {
            try {
                if (((long) cVar.a().delete(str, str2, null)) > 0) {
                    z = true;
                }
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                c.e("DBHandler", str + " delItem with exception : " + e2.getMessage());
                cVar.c();
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e4) {
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(c cVar, String str, ContentValues contentValues, String str2) {
        boolean z = false;
        synchronized (this) {
            try {
                if (((long) cVar.a().update(str, contentValues, str2, null)) > 0) {
                    z = true;
                }
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                c.e("DBHandler", str + " delItem with exception : " + e2.getMessage());
                cVar.c();
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (cVar != null) {
                    try {
                        cVar.c();
                    } catch (Exception e4) {
                    }
                }
            }
        }
        return z;
    }
}
