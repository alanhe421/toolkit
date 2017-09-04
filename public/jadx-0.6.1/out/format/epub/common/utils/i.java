package format.epub.common.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import format.epub.common.b.b;
import format.epub.common.b.e;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ZLAndroidLibrary */
public final class i {
    private static i b;
    private final Context a;

    /* compiled from: ZLAndroidLibrary */
    private final class a extends e {
        final /* synthetic */ i a;
        private final a b;

        a(i iVar, a aVar, String str) {
            this.a = iVar;
            if (aVar.c().length() != 0) {
                str = aVar.c() + '/' + str;
            }
            super(str);
            this.b = aVar;
        }

        a(i iVar, String str) {
            this.a = iVar;
            super(str);
            if (str.length() == 0) {
                this.b = null;
            } else {
                this.b = new a(iVar, str.lastIndexOf(47) >= 0 ? str.substring(0, str.lastIndexOf(47)) : "");
            }
        }

        protected List<b> m() {
            try {
                String[] list = this.a.a.getAssets().list(c());
                if (!(list == null || list.length == 0)) {
                    List<b> arrayList = new ArrayList(list.length);
                    for (String aVar : list) {
                        arrayList.add(new a(this.a, this, aVar));
                    }
                    return arrayList;
                }
            } catch (IOException e) {
            }
            return Collections.emptyList();
        }

        public boolean b() {
            try {
                InputStream open = this.a.a.getAssets().open(c());
                if (open == null) {
                    return true;
                }
                open.close();
                return false;
            } catch (IOException e) {
                return true;
            }
        }

        public boolean a() {
            try {
                InputStream open = this.a.a.getAssets().open(c());
                if (open != null) {
                    open.close();
                    return true;
                }
            } catch (IOException e) {
            }
            try {
                String[] list = this.a.a.getAssets().list(c());
                if (!(list == null || list.length == 0)) {
                    return true;
                }
            } catch (IOException e2) {
            }
            return false;
        }

        public long h() {
            try {
                AssetFileDescriptor openFd = this.a.a.getAssets().openFd(c());
                if (openFd == null) {
                    return 0;
                }
                long length = openFd.getLength();
                openFd.close();
                return length;
            } catch (IOException e) {
                return 0;
            }
        }

        public InputStream i() throws IOException {
            return this.a.a.getAssets().open(c());
        }

        public b e() {
            return this.b;
        }
    }

    public static i a() {
        return b;
    }

    public i(Context context) {
        b = this;
        this.a = context;
    }

    public e a(String str) {
        return new a(this, str);
    }

    public e a(e eVar, String str) {
        return new a(this, (a) eVar, str);
    }
}
