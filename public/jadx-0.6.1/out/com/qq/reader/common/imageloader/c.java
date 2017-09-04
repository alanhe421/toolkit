package com.qq.reader.common.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.d;
import com.bumptech.glide.g;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.a;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: Imageloader */
public class c {
    private static final String a = c.class.getSimpleName();
    private i b;

    private c(Context context) {
        try {
            this.b = g.b(context);
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        }
    }

    private c(Fragment fragment) {
        try {
            this.b = g.a(fragment);
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        }
    }

    public i a() {
        return this.b;
    }

    public static c a(Context context) {
        return new c(context);
    }

    public static c a(Fragment fragment) {
        return new c(fragment);
    }

    public j<b> a(String str, ImageView imageView) {
        return a(str, null, imageView, 0, null, null);
    }

    public j<b> a(String str, ImageView imageView, int i) {
        return a(str, null, imageView, i, null, null);
    }

    public j<b> a(String str, ImageView imageView, e<String, b> eVar) {
        return a(str, null, imageView, 0, null, eVar);
    }

    public j<b> a(String str, com.bumptech.glide.request.b.g gVar) {
        return a(str, gVar, null, 0, null, null);
    }

    public j<b> a(String str, com.bumptech.glide.request.b.g gVar, b bVar) {
        return a(str, gVar, null, 0, bVar, null);
    }

    public j<b> a(String str, com.bumptech.glide.request.b.g gVar, e<String, b> eVar) {
        return a(str, gVar, null, 0, null, eVar);
    }

    public j<b> a(String str, ImageView imageView, b bVar) {
        return a(str, null, imageView, 0, bVar, null);
    }

    public j<b> a(String str, ImageView imageView, b bVar, e<String, b> eVar) {
        return a(str, null, imageView, 0, bVar, eVar);
    }

    public j<b> a(String str, com.bumptech.glide.request.b.g gVar, ImageView imageView, int i, b bVar, e<String, b> eVar) {
        if (this.b == null) {
            return null;
        }
        d a;
        if (bVar != null) {
            if (bVar.i() != null) {
                a = this.b.a(bVar.i()).a((Object) str);
            } else {
                a = this.b.a(str);
            }
            if (i != 0) {
                a.c(i);
            } else {
                if (bVar.d() > 0) {
                    a.g(bVar.d());
                } else if (bVar.c() != null) {
                    a.b(bVar.c());
                }
                if (bVar.b() > 0) {
                    a.c(bVar.b());
                } else if (bVar.a() != null) {
                    a.a(bVar.a());
                }
            }
            if (bVar.f()) {
                a.d();
            } else if (bVar.e() != 0) {
                a.b(bVar.e());
            }
            a.a(bVar.g());
            if (ScaleType.CENTER_CROP == bVar.j()) {
                a.a();
            } else if (ScaleType.FIT_CENTER == bVar.j()) {
                a.b();
            }
            if (bVar.k() != null) {
                a.a(bVar.k());
            }
            if (!(bVar.m() == 0 && bVar.l() == 0)) {
                a.a(bVar.m(), bVar.l());
            }
            if (bVar.h() != 0) {
                a.a(bVar.h());
            }
        } else {
            a = this.b.a(str);
            a.a(DiskCacheStrategy.SOURCE);
        }
        if (eVar != null) {
            a.a((e) eVar);
        }
        a.d();
        if (imageView != null) {
            return a.a(imageView);
        }
        if (gVar != null) {
            return a.b((j) gVar);
        }
        return a.b(new com.bumptech.glide.request.b.g<b>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(b bVar, com.bumptech.glide.request.a.c<? super b> cVar) {
            }
        });
    }

    public Bitmap a(String str) {
        return b(str, 0, TimeUnit.SECONDS);
    }

    public File b(String str) {
        return a(str, 0, TimeUnit.SECONDS);
    }

    public File a(String str, int i, TimeUnit timeUnit) {
        a c = this.b.a(new d()).a((Object) str).c(Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (i <= 0) {
            return (File) c.get();
        }
        try {
            return (File) c.get((long) i, timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public Bitmap b(String str, int i, TimeUnit timeUnit) {
        a d = this.b.a(str).j().d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (i <= 0) {
            return (Bitmap) d.get();
        }
        try {
            return (Bitmap) d.get((long) i, timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void a(final String str, final b bVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                this.c.a(str, null, null, 0, bVar, null);
            }
        });
    }

    public void c(String str) {
        b(str, null);
    }

    public void b(final String str, final com.bumptech.glide.request.b.g<File> gVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                if (this.c.b != null) {
                    d a = this.c.b.a(str);
                    a.a(DiskCacheStrategy.ALL);
                    a.d();
                    if (gVar != null) {
                        a.a(gVar);
                    } else {
                        a.a(new com.bumptech.glide.request.b.g<File>(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(File file, com.bumptech.glide.request.a.c<? super File> cVar) {
                                com.qq.reader.common.monitor.debug.c.a(c.a, "downloadOnly onResourceReady = " + str);
                            }
                        });
                    }
                }
            }
        });
    }

    public void a(String str, e<String, b> eVar) {
        if (this.b != null) {
            com.bumptech.glide.i.c a = this.b.a(new d());
            if (eVar != null) {
                a.a((Object) str).a(b.a).a((e) eVar).d(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
        }
    }

    public void a(String str, int i, int i2, e<String, b> eVar) {
        if (this.b != null) {
            com.bumptech.glide.i.c a = this.b.a(new d());
            if (eVar != null) {
                a.a((Object) str).a(b.a).a((e) eVar).d(i, i2);
            }
        }
    }

    public void a(final String str, final InputStream inputStream) {
        if (this.b != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    this.c.b.a(new com.qq.reader.common.imageloader.b.b(inputStream)).a(str).a(b.a).b(new com.bumptech.glide.request.b.g<b>(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a(b bVar, com.bumptech.glide.request.a.c<? super b> cVar) {
                            com.qq.reader.common.monitor.debug.c.a(c.a, "onResourceReady = " + str);
                        }
                    });
                }
            });
        }
    }

    public static void d(final String str) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ((File) g.b(ReaderApplication.getApplicationImp()).a(str).c(Integer.MIN_VALUE, Integer.MIN_VALUE).get()).delete();
                } catch (InterruptedException e) {
                    Log.d(c.a, "InterruptedException = " + e.getMessage());
                    e.printStackTrace();
                } catch (ExecutionException e2) {
                    Log.d(c.a, "ExecutionException = " + e2.getMessage());
                    e2.printStackTrace();
                } catch (Exception e3) {
                    Log.d(c.a, "Exception = " + e3.getMessage());
                }
            }
        }).start();
    }
}
