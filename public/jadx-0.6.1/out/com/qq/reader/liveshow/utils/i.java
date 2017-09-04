package com.qq.reader.liveshow.utils;

import android.content.Context;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.e;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.l;
import com.qq.reader.liveshow.c.m;
import com.qq.reader.liveshow.model.f;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: LiveUserActionHelper */
public class i {

    /* compiled from: LiveUserActionHelper */
    public interface a {
        void a();

        void a(boolean z);
    }

    public static void a(Context context, String str, final int i, final a aVar) {
        try {
            final WeakReference weakReference = new WeakReference(context);
            e c = n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            l.a().a(l.a(com.qq.reader.liveshow.model.a.i(), str, i), new m<f>() {
                public void a(int i, f fVar) {
                    if (fVar != null) {
                        Context context = (Context) weakReference.get();
                        if (context == null) {
                            return;
                        }
                        if (fVar.a != 0) {
                            com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                            if (aVar != null) {
                                aVar.a();
                            }
                        } else if (i == 0) {
                            com.qq.reader.liveshow.b.m.a(context, h.free_user_talk, 0);
                            if (aVar != null) {
                                aVar.a(false);
                            }
                        } else {
                            com.qq.reader.liveshow.b.m.a(context, h.shut_up_already, 0);
                            if (aVar != null) {
                                aVar.a(true);
                            }
                        }
                    }
                }

                public void a(int i, String str) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }

                public void a(Exception exception) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, final a aVar) {
        try {
            final WeakReference weakReference = new WeakReference(context);
            e c = n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            l.a().a(l.a(com.qq.reader.liveshow.model.a.i(), str), new m<f>() {
                public void a(int i, f fVar) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        if (fVar.a == 0) {
                            com.qq.reader.liveshow.b.m.a(context, h.kick_out_of_room_already, 0);
                            if (aVar != null) {
                                aVar.a(true);
                                return;
                            }
                            return;
                        }
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a(true);
                        }
                    }
                }

                public void a(int i, String str) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }

                public void a(Exception exception) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void b(Context context, String str, final a aVar) {
        try {
            final WeakReference weakReference = new WeakReference(context);
            e c = n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            l.a().a(l.c(com.qq.reader.liveshow.model.a.i(), str), new m<f>() {
                public void a(int i, f fVar) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        if (fVar.a == 0) {
                            com.qq.reader.liveshow.b.m.a(context, h.report_success, 0);
                            if (aVar != null) {
                                aVar.a(true);
                            }
                        } else if (fVar.a == -5) {
                            com.qq.reader.liveshow.b.m.a(context, h.report_already, 0);
                            if (aVar != null) {
                                aVar.a(true);
                            }
                        } else {
                            com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                            if (aVar != null) {
                                aVar.a();
                            }
                        }
                    }
                }

                public void a(int i, String str) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }

                public void a(Exception exception) {
                    Context context = (Context) weakReference.get();
                    if (context != null) {
                        com.qq.reader.liveshow.b.m.a(context, h.error_happen_try_later, 0);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
