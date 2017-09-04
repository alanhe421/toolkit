package com.tencent.upload.network.a;

import android.content.Context;
import com.tencent.upload.common.a.a;
import com.tencent.upload.log.b;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class j {
    private static String a = "upload_recent_route";
    private HashMap<String, i> b = new HashMap();

    public j() {
        b();
    }

    private boolean a() {
        ObjectOutputStream objectOutputStream;
        Throwable e;
        a.b("RouteSetStorage", "save");
        Context b = b.b();
        if (b == null) {
            a.d("RouteSetStorage", "save() UploadGlobalConfig.getContext() == null");
            return false;
        }
        if (this.b != null) {
            Set keySet = this.b.keySet();
            if (keySet != null) {
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    i iVar = (i) this.b.get((String) it.next());
                    if (iVar != null) {
                        long c = iVar.c();
                        if (c != 0 && System.currentTimeMillis() > c + com.tencent.upload.common.j.l()) {
                            it.remove();
                        }
                    }
                }
            }
        }
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(b.openFileOutput(a, 0)));
            try {
                objectOutputStream.writeObject(this.b);
                c();
                try {
                    objectOutputStream.close();
                } catch (Throwable e2) {
                    b.c("RouteSetStorage", "closeObject Exception", e2);
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    b.c("RouteSetStorage", "writeObject Exception", e2);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable e22) {
                            b.c("RouteSetStorage", "closeObject Exception", e22);
                        }
                    }
                    return true;
                } catch (Throwable th) {
                    e22 = th;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Throwable e4) {
                            b.c("RouteSetStorage", "closeObject Exception", e4);
                        }
                    }
                    throw e22;
                }
            }
        } catch (Exception e5) {
            e22 = e5;
            objectOutputStream = null;
            b.c("RouteSetStorage", "writeObject Exception", e22);
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            return true;
        } catch (Throwable th2) {
            e22 = th2;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw e22;
        }
        return true;
    }

    private boolean b() {
        Throwable e;
        a.b("RouteSetStorage", "load");
        Context b = b.b();
        if (b == null) {
            a.d("RouteSetStorage", "load() UploadGlobalConfig.getContext() == null");
            return false;
        }
        try {
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(b.openFileInput(a));
                try {
                    Object readObject = objectInputStream.readObject();
                    if (readObject instanceof HashMap) {
                        this.b = (HashMap) readObject;
                    }
                    if (this.b == null) {
                        this.b = new HashMap();
                        try {
                            objectInputStream.close();
                        } catch (Throwable e2) {
                            b.c("RouteSetStorage", "closeObjectIn Exception", e2);
                        }
                        return false;
                    }
                    c();
                    try {
                        objectInputStream.close();
                    } catch (Throwable e22) {
                        b.c("RouteSetStorage", "closeObjectIn Exception", e22);
                    }
                    return true;
                } catch (Exception e3) {
                    e22 = e3;
                    b.c("RouteSetStorage", "load() readObject Exception", e22);
                    b.deleteFile(a);
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (Throwable e222) {
                            b.c("RouteSetStorage", "closeObjectIn Exception", e222);
                        }
                    }
                    return false;
                }
            } catch (Exception e4) {
                e222 = e4;
                objectInputStream = null;
                b.c("RouteSetStorage", "load() readObject Exception", e222);
                b.deleteFile(a);
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return false;
            }
        } catch (FileNotFoundException e5) {
            a.c("RouteSetStorage", "load() FileNotFoundException:" + a);
            return false;
        }
    }

    private void c() {
        for (String str : this.b.keySet()) {
            this.b.get(str);
        }
    }

    public final i a(String str) {
        return (i) this.b.get(str);
    }

    public final void a(String str, i iVar) {
        this.b.put(str, iVar);
        a();
    }
}
