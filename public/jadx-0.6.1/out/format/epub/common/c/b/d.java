package format.epub.common.c.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import format.epub.common.b.b;
import format.epub.common.core.a.c;
import format.epub.common.core.a.g;

/* compiled from: OEBCoverBackgroundReader */
class d extends g {
    private format.epub.common.image.a a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private String g;

    /* compiled from: OEBCoverBackgroundReader */
    private class a extends g {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        public boolean g() {
            return true;
        }

        public boolean a(String str, c cVar) {
            String toLowerCase = str.toLowerCase();
            String str2 = null;
            if (SocialConstants.PARAM_IMG_URL.equals(toLowerCase)) {
                str2 = cVar.a("src");
            } else if ("image".equals(toLowerCase)) {
                str2 = a(cVar, "http://www.w3.org/1999/xlink", "href");
            }
            if (str2 == null) {
                return false;
            }
            this.a.a = new format.epub.common.image.a("image/auto", b.b(this.a.c + format.epub.common.utils.d.b(str2)));
            return true;
        }
    }

    d() {
    }

    public format.epub.common.image.a b(b bVar) {
        this.b = format.epub.common.utils.d.a(bVar);
        this.e = false;
        this.f = false;
        this.a = null;
        this.d = null;
        a(bVar);
        if (this.d == null && this.a == null) {
            this.d = this.b + format.epub.common.utils.d.b("images/cover.jpg");
        }
        if (this.d != null) {
            b b = b.b(this.d);
            if (b == null || b.h() <= 0) {
                this.d = this.b.substring(0, this.b.lastIndexOf(58) + 1) + format.epub.common.utils.d.b("iTunesArtwork");
                b = b.b(this.d);
                if (b != null && b.h() > 0) {
                    this.a = new format.epub.common.image.a("image/auto", b);
                }
            } else {
                String l = b.l();
                if ("gif".equals(l) || "jpg".equals(l) || "jpeg".equals(l) || "png".equals(l)) {
                    this.a = new format.epub.common.image.a("image/auto", b);
                } else {
                    this.c = format.epub.common.utils.d.a(b);
                    new a().a(b);
                }
            }
        }
        return this.a;
    }

    public boolean a(String str, c cVar) {
        String intern = str.toLowerCase().intern();
        if ("guide" == intern) {
            this.e = true;
        } else if (this.e && "reference" == intern) {
            intern = cVar.a("type");
            if ("cover" == intern) {
                intern = cVar.a("href");
                if (intern != null) {
                    this.d = this.b + format.epub.common.utils.d.b(intern);
                    return true;
                }
            } else if ("other.ms-coverimage-standard" == intern) {
                intern = cVar.a("href");
                if (intern != null) {
                    this.a = new format.epub.common.image.a("image/auto", b.b(this.b + format.epub.common.utils.d.b(intern)));
                    return true;
                }
            }
        } else if ("manifest" == intern) {
            this.f = true;
        } else if (this.f && "item" == intern) {
            intern = cVar.a("id");
            if ("cover" == intern || "cover-image" == intern || "book-cover" == intern || (this.g != null && this.g.equals(intern))) {
                intern = cVar.a("href");
                if (intern != null) {
                    if (intern.startsWith("/")) {
                        intern = intern.substring(1);
                    }
                    this.d = this.b + format.epub.common.utils.d.b(intern);
                    return true;
                }
            }
        } else if ("meta" == intern) {
            if ("cover".equals(cVar.a("name"))) {
                this.g = cVar.a(MessageKey.MSG_CONTENT);
            }
        }
        return false;
    }

    public boolean c(String str) {
        String toLowerCase = str.toLowerCase();
        if ("guide" == toLowerCase) {
            this.e = false;
            return true;
        } else if ("manifest" != toLowerCase) {
            return false;
        } else {
            this.f = false;
            return false;
        }
    }
}
