package org.jsoup.parser;

import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.helper.b;
import org.jsoup.nodes.Document.QuirksMode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.f;
import org.jsoup.nodes.g;

enum HtmlTreeBuilderState {
    Initial {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.i()) {
                bVar.a(token.j());
            } else if (token.c()) {
                c d = token.d();
                bVar.f().a(new f(bVar.j.a(d.n()), d.o(), d.p(), d.q(), bVar.g()));
                if (d.r()) {
                    bVar.f().a(QuirksMode.quirks);
                }
                bVar.a(BeforeHtml);
            } else {
                bVar.a(BeforeHtml);
                return bVar.a(token);
            }
            return true;
        }
    },
    BeforeHtml {
        boolean process(Token token, b bVar) {
            if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            }
            if (token.i()) {
                bVar.a(token.j());
            } else if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            } else {
                if (token.e() && token.f().r().equals("html")) {
                    bVar.a(token.f());
                    bVar.a(BeforeHead);
                } else {
                    if (token.g()) {
                        if (b.a(token.h().r(), "head", "body", "html", "br")) {
                            return anythingElse(token, bVar);
                        }
                    }
                    if (!token.g()) {
                        return anythingElse(token, bVar);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, b bVar) {
            bVar.a("html");
            bVar.a(BeforeHead);
            return bVar.a(token);
        }
    },
    BeforeHead {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.i()) {
                bVar.a(token.j());
                return true;
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return InBody.process(token, bVar);
            } else {
                if (token.e() && token.f().r().equals("head")) {
                    bVar.g(bVar.a(token.f()));
                    bVar.a(InHead);
                    return true;
                }
                if (token.g()) {
                    if (b.a(token.h().r(), "head", "body", "html", "br")) {
                        bVar.l("head");
                        return bVar.a(token);
                    }
                }
                if (token.g()) {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
                bVar.l("head");
                return bVar.a(token);
            }
        }
    },
    InHead {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                bVar.a(token.l());
                return true;
            }
            switch (token.a) {
                case Comment:
                    bVar.a(token.j());
                    return true;
                case Doctype:
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                case StartTag:
                    f f = token.f();
                    String r = f.r();
                    if (r.equals("html")) {
                        return InBody.process(token, bVar);
                    }
                    if (b.a(r, "base", "basefont", "bgsound", "command", "link")) {
                        g b = bVar.b(f);
                        if (!r.equals("base") || !b.r("href")) {
                            return true;
                        }
                        bVar.a(b);
                        return true;
                    } else if (r.equals("meta")) {
                        bVar.b(f);
                        return true;
                    } else if (r.equals("title")) {
                        HtmlTreeBuilderState.handleRcData(f, bVar);
                        return true;
                    } else {
                        if (b.a(r, "noframes", "style")) {
                            HtmlTreeBuilderState.handleRawtext(f, bVar);
                            return true;
                        } else if (r.equals("noscript")) {
                            bVar.a(f);
                            bVar.a(InHeadNoscript);
                            return true;
                        } else if (r.equals("script")) {
                            bVar.d.a(TokeniserState.ScriptData);
                            bVar.c();
                            bVar.a(Text);
                            bVar.a(f);
                            return true;
                        } else if (!r.equals("head")) {
                            return anythingElse(token, bVar);
                        } else {
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        }
                    }
                case EndTag:
                    String r2 = token.h().r();
                    if (r2.equals("head")) {
                        bVar.i();
                        bVar.a(AfterHead);
                        return true;
                    }
                    if (b.a(r2, "body", "html", "br")) {
                        return anythingElse(token, bVar);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                default:
                    return anythingElse(token, bVar);
            }
        }

        private boolean anythingElse(Token token, i iVar) {
            iVar.m("head");
            return iVar.a(token);
        }
    },
    InHeadNoscript {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        boolean process(org.jsoup.parser.Token r8, org.jsoup.parser.b r9) {
            /*
            r7 = this;
            r6 = 2;
            r1 = 1;
            r0 = 0;
            r2 = r8.c();
            if (r2 == 0) goto L_0x000e;
        L_0x0009:
            r9.b(r7);
        L_0x000c:
            r0 = r1;
        L_0x000d:
            return r0;
        L_0x000e:
            r2 = r8.e();
            if (r2 == 0) goto L_0x002c;
        L_0x0014:
            r2 = r8.f();
            r2 = r2.r();
            r3 = "html";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x002c;
        L_0x0025:
            r0 = InBody;
            r0 = r9.a(r8, r0);
            goto L_0x000d;
        L_0x002c:
            r2 = r8.g();
            if (r2 == 0) goto L_0x004c;
        L_0x0032:
            r2 = r8.h();
            r2 = r2.r();
            r3 = "noscript";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x004c;
        L_0x0043:
            r9.i();
            r0 = InHead;
            r9.a(r0);
            goto L_0x000c;
        L_0x004c:
            r2 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace(r8);
            if (r2 != 0) goto L_0x0090;
        L_0x0052:
            r2 = r8.i();
            if (r2 != 0) goto L_0x0090;
        L_0x0058:
            r2 = r8.e();
            if (r2 == 0) goto L_0x0098;
        L_0x005e:
            r2 = r8.f();
            r2 = r2.r();
            r3 = 6;
            r3 = new java.lang.String[r3];
            r4 = "basefont";
            r3[r0] = r4;
            r4 = "bgsound";
            r3[r1] = r4;
            r4 = "link";
            r3[r6] = r4;
            r4 = 3;
            r5 = "meta";
            r3[r4] = r5;
            r4 = 4;
            r5 = "noframes";
            r3[r4] = r5;
            r4 = 5;
            r5 = "style";
            r3[r4] = r5;
            r2 = org.jsoup.helper.b.a(r2, r3);
            if (r2 == 0) goto L_0x0098;
        L_0x0090:
            r0 = InHead;
            r0 = r9.a(r8, r0);
            goto L_0x000d;
        L_0x0098:
            r2 = r8.g();
            if (r2 == 0) goto L_0x00b5;
        L_0x009e:
            r2 = r8.h();
            r2 = r2.r();
            r3 = "br";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00b5;
        L_0x00af:
            r0 = r7.anythingElse(r8, r9);
            goto L_0x000d;
        L_0x00b5:
            r2 = r8.e();
            if (r2 == 0) goto L_0x00d5;
        L_0x00bb:
            r2 = r8.f();
            r2 = r2.r();
            r3 = new java.lang.String[r6];
            r4 = "head";
            r3[r0] = r4;
            r4 = "noscript";
            r3[r1] = r4;
            r1 = org.jsoup.helper.b.a(r2, r3);
            if (r1 != 0) goto L_0x00db;
        L_0x00d5:
            r1 = r8.g();
            if (r1 == 0) goto L_0x00e0;
        L_0x00db:
            r9.b(r7);
            goto L_0x000d;
        L_0x00e0:
            r0 = r7.anythingElse(r8, r9);
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.5.process(org.jsoup.parser.Token, org.jsoup.parser.b):boolean");
        }

        private boolean anythingElse(Token token, b bVar) {
            bVar.b((HtmlTreeBuilderState) this);
            bVar.a(new a().a(token.toString()));
            return true;
        }
    },
    AfterHead {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                bVar.a(token.l());
            } else if (token.i()) {
                bVar.a(token.j());
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
            } else if (token.e()) {
                f f = token.f();
                String r = f.r();
                if (r.equals("html")) {
                    return bVar.a(token, InBody);
                }
                if (r.equals("body")) {
                    bVar.a(f);
                    bVar.a(false);
                    bVar.a(InBody);
                } else if (r.equals("frameset")) {
                    bVar.a(f);
                    bVar.a(InFrameset);
                } else {
                    if (b.a(r, "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "title")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        g o = bVar.o();
                        bVar.c(o);
                        bVar.a(token, InHead);
                        bVar.e(o);
                    } else if (r.equals("head")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    } else {
                        anythingElse(token, bVar);
                    }
                }
            } else if (token.g()) {
                if (b.a(token.h().r(), "body", "html")) {
                    anythingElse(token, bVar);
                } else {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            } else {
                anythingElse(token, bVar);
            }
            return true;
        }

        private boolean anythingElse(Token token, b bVar) {
            bVar.l("body");
            bVar.a(true);
            return bVar.a(token);
        }
    },
    InBody {
        boolean process(Token token, b bVar) {
            int size;
            g gVar;
            switch (token.a) {
                case Comment:
                    bVar.a(token.j());
                    break;
                case Doctype:
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                case StartTag:
                    f f = token.f();
                    String r = f.r();
                    if (!r.equals("a")) {
                        if (!b.b(r, a.i)) {
                            if (!b.b(r, a.b)) {
                                if (!r.equals("span")) {
                                    ArrayList j;
                                    if (!r.equals("li")) {
                                        Iterator it;
                                        org.jsoup.nodes.a aVar;
                                        if (!r.equals("html")) {
                                            if (!b.b(r, a.a)) {
                                                ArrayList j2;
                                                if (!r.equals("body")) {
                                                    if (!r.equals("frameset")) {
                                                        if (!b.b(r, a.c)) {
                                                            if (!b.b(r, a.d)) {
                                                                if (!r.equals("form")) {
                                                                    if (!b.b(r, a.f)) {
                                                                        if (!r.equals("plaintext")) {
                                                                            if (!r.equals("button")) {
                                                                                if (!b.b(r, a.g)) {
                                                                                    if (!r.equals("nobr")) {
                                                                                        if (!b.b(r, a.h)) {
                                                                                            if (!r.equals("table")) {
                                                                                                if (!r.equals("input")) {
                                                                                                    if (!b.b(r, a.j)) {
                                                                                                        if (!r.equals("hr")) {
                                                                                                            if (!r.equals("image")) {
                                                                                                                if (!r.equals("isindex")) {
                                                                                                                    if (!r.equals("textarea")) {
                                                                                                                        if (!r.equals("xmp")) {
                                                                                                                            if (!r.equals("iframe")) {
                                                                                                                                if (!r.equals("noembed")) {
                                                                                                                                    if (!r.equals("select")) {
                                                                                                                                        if (!b.b(r, a.l)) {
                                                                                                                                            if (!b.b(r, a.m)) {
                                                                                                                                                if (!r.equals("math")) {
                                                                                                                                                    if (!r.equals("svg")) {
                                                                                                                                                        if (!b.b(r, a.n)) {
                                                                                                                                                            bVar.w();
                                                                                                                                                            bVar.a(f);
                                                                                                                                                            break;
                                                                                                                                                        }
                                                                                                                                                        bVar.b((HtmlTreeBuilderState) this);
                                                                                                                                                        return false;
                                                                                                                                                    }
                                                                                                                                                    bVar.w();
                                                                                                                                                    bVar.a(f);
                                                                                                                                                    bVar.d.b();
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                                bVar.w();
                                                                                                                                                bVar.a(f);
                                                                                                                                                bVar.d.b();
                                                                                                                                                break;
                                                                                                                                            } else if (bVar.e("ruby")) {
                                                                                                                                                bVar.t();
                                                                                                                                                if (!bVar.A().a().equals("ruby")) {
                                                                                                                                                    bVar.b((HtmlTreeBuilderState) this);
                                                                                                                                                    bVar.d("ruby");
                                                                                                                                                }
                                                                                                                                                bVar.a(f);
                                                                                                                                                break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        if (bVar.A().a().equals("option")) {
                                                                                                                                            bVar.m("option");
                                                                                                                                        }
                                                                                                                                        bVar.w();
                                                                                                                                        bVar.a(f);
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    bVar.w();
                                                                                                                                    bVar.a(f);
                                                                                                                                    bVar.a(false);
                                                                                                                                    HtmlTreeBuilderState b = bVar.b();
                                                                                                                                    if (!b.equals(InTable) && !b.equals(InCaption) && !b.equals(InTableBody) && !b.equals(InRow) && !b.equals(InCell)) {
                                                                                                                                        bVar.a(InSelect);
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    bVar.a(InSelectInTable);
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                                HtmlTreeBuilderState.handleRawtext(f, bVar);
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            bVar.a(false);
                                                                                                                            HtmlTreeBuilderState.handleRawtext(f, bVar);
                                                                                                                            break;
                                                                                                                        }
                                                                                                                        if (bVar.g("p")) {
                                                                                                                            bVar.m("p");
                                                                                                                        }
                                                                                                                        bVar.w();
                                                                                                                        bVar.a(false);
                                                                                                                        HtmlTreeBuilderState.handleRawtext(f, bVar);
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    bVar.a(f);
                                                                                                                    bVar.d.a(TokeniserState.Rcdata);
                                                                                                                    bVar.c();
                                                                                                                    bVar.a(false);
                                                                                                                    bVar.a(Text);
                                                                                                                    break;
                                                                                                                }
                                                                                                                bVar.b((HtmlTreeBuilderState) this);
                                                                                                                if (bVar.q() == null) {
                                                                                                                    bVar.d.b();
                                                                                                                    bVar.l("form");
                                                                                                                    if (f.e.d("action")) {
                                                                                                                        bVar.q().a("action", f.e.a("action"));
                                                                                                                    }
                                                                                                                    bVar.l("hr");
                                                                                                                    bVar.l("label");
                                                                                                                    bVar.a(new a().a(f.e.d("prompt") ? f.e.a("prompt") : "This is a searchable index. Enter search keywords: "));
                                                                                                                    org.jsoup.nodes.b bVar2 = new org.jsoup.nodes.b();
                                                                                                                    it = f.e.iterator();
                                                                                                                    while (it.hasNext()) {
                                                                                                                        org.jsoup.nodes.a aVar2 = (org.jsoup.nodes.a) it.next();
                                                                                                                        if (!b.b(aVar2.a(), a.k)) {
                                                                                                                            bVar2.a(aVar2);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    bVar2.a("name", "isindex");
                                                                                                                    bVar.a("input", bVar2);
                                                                                                                    bVar.m("label");
                                                                                                                    bVar.l("hr");
                                                                                                                    bVar.m("form");
                                                                                                                    break;
                                                                                                                }
                                                                                                                return false;
                                                                                                            } else if (bVar.b("svg") != null) {
                                                                                                                bVar.a(f);
                                                                                                                break;
                                                                                                            } else {
                                                                                                                return bVar.a(f.a(SocialConstants.PARAM_IMG_URL));
                                                                                                            }
                                                                                                        }
                                                                                                        if (bVar.g("p")) {
                                                                                                            bVar.m("p");
                                                                                                        }
                                                                                                        bVar.b(f);
                                                                                                        bVar.a(false);
                                                                                                        break;
                                                                                                    }
                                                                                                    bVar.b(f);
                                                                                                    break;
                                                                                                }
                                                                                                bVar.w();
                                                                                                if (!bVar.b(f).q("type").equalsIgnoreCase("hidden")) {
                                                                                                    bVar.a(false);
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            if (bVar.f().f() != QuirksMode.quirks && bVar.g("p")) {
                                                                                                bVar.m("p");
                                                                                            }
                                                                                            bVar.a(f);
                                                                                            bVar.a(false);
                                                                                            bVar.a(InTable);
                                                                                            break;
                                                                                        }
                                                                                        bVar.w();
                                                                                        bVar.a(f);
                                                                                        bVar.y();
                                                                                        bVar.a(false);
                                                                                        break;
                                                                                    }
                                                                                    bVar.w();
                                                                                    if (bVar.e("nobr")) {
                                                                                        bVar.b((HtmlTreeBuilderState) this);
                                                                                        bVar.m("nobr");
                                                                                        bVar.w();
                                                                                    }
                                                                                    bVar.i(bVar.a(f));
                                                                                    break;
                                                                                }
                                                                                bVar.w();
                                                                                bVar.i(bVar.a(f));
                                                                                break;
                                                                            } else if (!bVar.g("button")) {
                                                                                bVar.w();
                                                                                bVar.a(f);
                                                                                bVar.a(false);
                                                                                break;
                                                                            } else {
                                                                                bVar.b((HtmlTreeBuilderState) this);
                                                                                bVar.m("button");
                                                                                bVar.a((Token) f);
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (bVar.g("p")) {
                                                                            bVar.m("p");
                                                                        }
                                                                        bVar.a(f);
                                                                        bVar.d.a(TokeniserState.PLAINTEXT);
                                                                        break;
                                                                    }
                                                                    bVar.a(false);
                                                                    j = bVar.j();
                                                                    size = j.size() - 1;
                                                                    while (size > 0) {
                                                                        gVar = (g) j.get(size);
                                                                        if (b.b(gVar.a(), a.f)) {
                                                                            bVar.m(gVar.a());
                                                                        } else if (!bVar.h(gVar) || b.b(gVar.a(), a.e)) {
                                                                            size--;
                                                                        }
                                                                        if (bVar.g("p")) {
                                                                            bVar.m("p");
                                                                        }
                                                                        bVar.a(f);
                                                                        break;
                                                                    }
                                                                    if (bVar.g("p")) {
                                                                        bVar.m("p");
                                                                    }
                                                                    bVar.a(f);
                                                                } else if (bVar.q() == null) {
                                                                    if (bVar.g("p")) {
                                                                        bVar.m("p");
                                                                    }
                                                                    bVar.a(f, true);
                                                                    break;
                                                                } else {
                                                                    bVar.b((HtmlTreeBuilderState) this);
                                                                    return false;
                                                                }
                                                            }
                                                            if (bVar.g("p")) {
                                                                bVar.m("p");
                                                            }
                                                            bVar.a(f);
                                                            bVar.a(false);
                                                            break;
                                                        }
                                                        if (bVar.g("p")) {
                                                            bVar.m("p");
                                                        }
                                                        if (b.b(bVar.A().a(), a.c)) {
                                                            bVar.b((HtmlTreeBuilderState) this);
                                                            bVar.i();
                                                        }
                                                        bVar.a(f);
                                                        break;
                                                    }
                                                    bVar.b((HtmlTreeBuilderState) this);
                                                    j2 = bVar.j();
                                                    if (j2.size() != 1 && (j2.size() <= 2 || ((g) j2.get(1)).a().equals("body"))) {
                                                        if (bVar.e()) {
                                                            gVar = (g) j2.get(1);
                                                            if (gVar.m() != null) {
                                                                gVar.N();
                                                            }
                                                            while (j2.size() > 1) {
                                                                j2.remove(j2.size() - 1);
                                                            }
                                                            bVar.a(f);
                                                            bVar.a(InFrameset);
                                                            break;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                bVar.b((HtmlTreeBuilderState) this);
                                                j2 = bVar.j();
                                                if (j2.size() != 1 && (j2.size() <= 2 || ((g) j2.get(1)).a().equals("body"))) {
                                                    bVar.a(false);
                                                    gVar = (g) j2.get(1);
                                                    it = f.t().iterator();
                                                    while (it.hasNext()) {
                                                        aVar = (org.jsoup.nodes.a) it.next();
                                                        if (!gVar.r(aVar.a())) {
                                                            gVar.F().a(aVar);
                                                        }
                                                    }
                                                    break;
                                                }
                                                return false;
                                            }
                                            return bVar.a(token, InHead);
                                        }
                                        bVar.b((HtmlTreeBuilderState) this);
                                        gVar = (g) bVar.j().get(0);
                                        it = f.t().iterator();
                                        while (it.hasNext()) {
                                            aVar = (org.jsoup.nodes.a) it.next();
                                            if (!gVar.r(aVar.a())) {
                                                gVar.F().a(aVar);
                                            }
                                        }
                                        break;
                                    }
                                    bVar.a(false);
                                    j = bVar.j();
                                    size = j.size() - 1;
                                    while (size > 0) {
                                        gVar = (g) j.get(size);
                                        if (gVar.a().equals("li")) {
                                            bVar.m("li");
                                        } else if (!bVar.h(gVar) || b.b(gVar.a(), a.e)) {
                                            size--;
                                        }
                                        if (bVar.g("p")) {
                                            bVar.m("p");
                                        }
                                        bVar.a(f);
                                        break;
                                    }
                                    if (bVar.g("p")) {
                                        bVar.m("p");
                                    }
                                    bVar.a(f);
                                } else {
                                    bVar.w();
                                    bVar.a(f);
                                    break;
                                }
                            }
                            if (bVar.g("p")) {
                                bVar.m("p");
                            }
                            bVar.a(f);
                            break;
                        }
                        bVar.w();
                        bVar.b(f);
                        bVar.a(false);
                        break;
                    }
                    if (bVar.k("a") != null) {
                        bVar.b((HtmlTreeBuilderState) this);
                        bVar.m("a");
                        gVar = bVar.b("a");
                        if (gVar != null) {
                            bVar.j(gVar);
                            bVar.e(gVar);
                        }
                    }
                    bVar.w();
                    bVar.i(bVar.a(f));
                    break;
                    break;
                case EndTag:
                    Token h = token.h();
                    String r2 = h.r();
                    if (b.b(r2, a.p)) {
                        int i = 0;
                        while (i < 8) {
                            g k = bVar.k(r2);
                            if (k == null) {
                                return anyOtherEndTag(token, bVar);
                            }
                            if (!bVar.d(k)) {
                                bVar.b((HtmlTreeBuilderState) this);
                                bVar.j(k);
                                return true;
                            } else if (bVar.e(k.a())) {
                                if (bVar.A() != k) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                g gVar2 = null;
                                g gVar3 = null;
                                Object obj = null;
                                ArrayList j3 = bVar.j();
                                int size2 = j3.size();
                                int i2 = 0;
                                while (i2 < size2 && i2 < 64) {
                                    g gVar4;
                                    Object obj2;
                                    gVar = (g) j3.get(i2);
                                    if (gVar == k) {
                                        gVar4 = (g) j3.get(i2 - 1);
                                        obj2 = 1;
                                    } else if (obj == null || !bVar.h(gVar)) {
                                        obj2 = obj;
                                        gVar4 = gVar3;
                                    } else {
                                        gVar2 = gVar;
                                    }
                                    i2++;
                                    gVar3 = gVar4;
                                    obj = obj2;
                                }
                                if (gVar2 == null) {
                                    bVar.c(k.a());
                                    bVar.j(k);
                                    return true;
                                }
                                int i3 = 0;
                                Node node = gVar2;
                                gVar = gVar2;
                                while (i3 < 3) {
                                    g gVar5;
                                    Node node2;
                                    if (bVar.d(gVar)) {
                                        gVar = bVar.f(gVar);
                                    }
                                    if (!bVar.k(gVar)) {
                                        bVar.e(gVar);
                                        gVar5 = gVar;
                                        node2 = node;
                                    } else if (gVar == k) {
                                        if (b.b(gVar3.a(), a.q)) {
                                            if (node.m() != null) {
                                                node.N();
                                            }
                                            gVar3.a(node);
                                        } else {
                                            if (node.m() != null) {
                                                node.N();
                                            }
                                            bVar.a(node);
                                        }
                                        gVar3 = new g(k.j(), bVar.g());
                                        gVar3.F().a(k.F());
                                        for (Node a : (Node[]) gVar2.H().toArray(new Node[gVar2.I()])) {
                                            gVar3.a(a);
                                        }
                                        gVar2.a((Node) gVar3);
                                        bVar.j(k);
                                        bVar.e(k);
                                        bVar.a(gVar2, gVar3);
                                        i++;
                                    } else {
                                        Node gVar6 = new g(f.a(gVar.a(), d.b), bVar.g());
                                        bVar.c(gVar, gVar6);
                                        bVar.b(gVar, gVar6);
                                        if (node == gVar2) {
                                        }
                                        if (node.m() != null) {
                                            node.N();
                                        }
                                        gVar6.a(node);
                                        node2 = gVar6;
                                    }
                                    i3++;
                                    node = node2;
                                    gVar = gVar5;
                                }
                                if (b.b(gVar3.a(), a.q)) {
                                    if (node.m() != null) {
                                        node.N();
                                    }
                                    gVar3.a(node);
                                } else {
                                    if (node.m() != null) {
                                        node.N();
                                    }
                                    bVar.a(node);
                                }
                                gVar3 = new g(k.j(), bVar.g());
                                gVar3.F().a(k.F());
                                while (size < i2) {
                                    gVar3.a(a);
                                }
                                gVar2.a((Node) gVar3);
                                bVar.j(k);
                                bVar.e(k);
                                bVar.a(gVar2, gVar3);
                                i++;
                            } else {
                                bVar.b((HtmlTreeBuilderState) this);
                                return false;
                            }
                        }
                        break;
                    } else if (b.b(r2, a.o)) {
                        if (bVar.e(r2)) {
                            bVar.t();
                            if (!bVar.A().a().equals(r2)) {
                                bVar.b((HtmlTreeBuilderState) this);
                            }
                            bVar.c(r2);
                            break;
                        }
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    } else if (r2.equals("span")) {
                        return anyOtherEndTag(token, bVar);
                    } else {
                        if (r2.equals("li")) {
                            if (bVar.f(r2)) {
                                bVar.j(r2);
                                if (!bVar.A().a().equals(r2)) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                bVar.c(r2);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        } else if (r2.equals("body")) {
                            if (bVar.e("body")) {
                                bVar.a(AfterBody);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        } else if (r2.equals("html")) {
                            if (bVar.m("body")) {
                                return bVar.a(h);
                            }
                        } else if (r2.equals("form")) {
                            gVar = bVar.q();
                            bVar.a(null);
                            if (gVar != null && bVar.e(r2)) {
                                bVar.t();
                                if (!bVar.A().a().equals(r2)) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                bVar.e(gVar);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        } else if (r2.equals("p")) {
                            if (bVar.g(r2)) {
                                bVar.j(r2);
                                if (!bVar.A().a().equals(r2)) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                bVar.c(r2);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            bVar.l(r2);
                            return bVar.a(h);
                        } else if (b.b(r2, a.f)) {
                            if (bVar.e(r2)) {
                                bVar.j(r2);
                                if (!bVar.A().a().equals(r2)) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                bVar.c(r2);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        } else if (b.b(r2, a.c)) {
                            if (bVar.b(a.c)) {
                                bVar.j(r2);
                                if (!bVar.A().a().equals(r2)) {
                                    bVar.b((HtmlTreeBuilderState) this);
                                }
                                bVar.a(a.c);
                                break;
                            }
                            bVar.b((HtmlTreeBuilderState) this);
                            return false;
                        } else if (r2.equals("sarcasm")) {
                            return anyOtherEndTag(token, bVar);
                        } else {
                            if (b.b(r2, a.h)) {
                                if (!bVar.e("name")) {
                                    if (bVar.e(r2)) {
                                        bVar.t();
                                        if (!bVar.A().a().equals(r2)) {
                                            bVar.b((HtmlTreeBuilderState) this);
                                        }
                                        bVar.c(r2);
                                        bVar.x();
                                        break;
                                    }
                                    bVar.b((HtmlTreeBuilderState) this);
                                    return false;
                                }
                            } else if (!r2.equals("br")) {
                                return anyOtherEndTag(token, bVar);
                            } else {
                                bVar.b((HtmlTreeBuilderState) this);
                                bVar.l("br");
                                return false;
                            }
                        }
                    }
                    break;
                case Character:
                    a l = token.l();
                    if (!l.n().equals(HtmlTreeBuilderState.nullString)) {
                        if (!bVar.e() || !HtmlTreeBuilderState.isWhitespace((Token) l)) {
                            bVar.w();
                            bVar.a(l);
                            bVar.a(false);
                            break;
                        }
                        bVar.w();
                        bVar.a(l);
                        break;
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
            }
            return true;
        }

        boolean anyOtherEndTag(Token token, b bVar) {
            String q = token.h().q();
            ArrayList j = bVar.j();
            int size = j.size() - 1;
            while (size >= 0) {
                g gVar = (g) j.get(size);
                if (gVar.a().equals(q)) {
                    bVar.j(q);
                    if (!q.equals(bVar.A().a())) {
                        bVar.b((HtmlTreeBuilderState) this);
                    }
                    bVar.c(q);
                    return true;
                } else if (bVar.h(gVar)) {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                } else {
                    size--;
                }
            }
            return true;
        }
    },
    Text {
        boolean process(Token token, b bVar) {
            if (token.k()) {
                bVar.a(token.l());
            } else if (token.m()) {
                bVar.b((HtmlTreeBuilderState) this);
                bVar.i();
                bVar.a(bVar.d());
                return bVar.a(token);
            } else if (token.g()) {
                bVar.i();
                bVar.a(bVar.d());
            }
            return true;
        }
    },
    InTable {
        boolean process(Token token, b bVar) {
            if (token.k()) {
                bVar.r();
                bVar.c();
                bVar.a(InTableText);
                return bVar.a(token);
            } else if (token.i()) {
                bVar.a(token.j());
                return true;
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e()) {
                f f = token.f();
                String r = f.r();
                if (r.equals("caption")) {
                    bVar.k();
                    bVar.y();
                    bVar.a(f);
                    bVar.a(InCaption);
                    return true;
                } else if (r.equals("colgroup")) {
                    bVar.k();
                    bVar.a(f);
                    bVar.a(InColumnGroup);
                    return true;
                } else if (r.equals("col")) {
                    bVar.l("colgroup");
                    return bVar.a(token);
                } else {
                    if (b.a(r, "tbody", "tfoot", "thead")) {
                        bVar.k();
                        bVar.a(f);
                        bVar.a(InTableBody);
                        return true;
                    }
                    if (b.a(r, "td", "th", "tr")) {
                        bVar.l("tbody");
                        return bVar.a(token);
                    } else if (r.equals("table")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        if (bVar.m("table")) {
                            return bVar.a(token);
                        }
                        return true;
                    } else {
                        if (b.a(r, "style", "script")) {
                            return bVar.a(token, InHead);
                        }
                        if (r.equals("input")) {
                            if (!f.e.a("type").equalsIgnoreCase("hidden")) {
                                return anythingElse(token, bVar);
                            }
                            bVar.b(f);
                            return true;
                        } else if (!r.equals("form")) {
                            return anythingElse(token, bVar);
                        } else {
                            bVar.b((HtmlTreeBuilderState) this);
                            if (bVar.q() != null) {
                                return false;
                            }
                            bVar.a(f, false);
                            return true;
                        }
                    }
                }
            } else if (token.g()) {
                String r2 = token.h().r();
                if (!r2.equals("table")) {
                    if (!b.a(r2, "body", "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                        return anythingElse(token, bVar);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                } else if (bVar.h(r2)) {
                    bVar.c("table");
                    bVar.n();
                    return true;
                } else {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            } else if (!token.m()) {
                return anythingElse(token, bVar);
            } else {
                if (!bVar.A().a().equals("html")) {
                    return true;
                }
                bVar.b((HtmlTreeBuilderState) this);
                return true;
            }
        }

        boolean anythingElse(Token token, b bVar) {
            bVar.b((HtmlTreeBuilderState) this);
            if (!b.a(bVar.A().a(), "table", "tbody", "tfoot", "thead", "tr")) {
                return bVar.a(token, InBody);
            }
            bVar.b(true);
            boolean a = bVar.a(token, InBody);
            bVar.b(false);
            return a;
        }
    },
    InTableText {
        boolean process(Token token, b bVar) {
            switch (token.a) {
                case Character:
                    a l = token.l();
                    if (l.n().equals(HtmlTreeBuilderState.nullString)) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    bVar.s().add(l.n());
                    return true;
                default:
                    if (bVar.s().size() > 0) {
                        for (String str : bVar.s()) {
                            if (HtmlTreeBuilderState.isWhitespace(str)) {
                                bVar.a(new a().a(str));
                            } else {
                                bVar.b((HtmlTreeBuilderState) this);
                                if (b.a(bVar.A().a(), "table", "tbody", "tfoot", "thead", "tr")) {
                                    bVar.b(true);
                                    bVar.a(new a().a(str), InBody);
                                    bVar.b(false);
                                } else {
                                    bVar.a(new a().a(str), InBody);
                                }
                            }
                        }
                        bVar.r();
                    }
                    bVar.a(bVar.d());
                    return bVar.a(token);
            }
        }
    },
    InCaption {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        boolean process(org.jsoup.parser.Token r10, org.jsoup.parser.b r11) {
            /*
            r9 = this;
            r8 = 4;
            r7 = 3;
            r6 = 2;
            r1 = 1;
            r0 = 0;
            r2 = r10.g();
            if (r2 == 0) goto L_0x0055;
        L_0x000b:
            r2 = r10.h();
            r2 = r2.r();
            r3 = "caption";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0055;
        L_0x001c:
            r2 = r10.h();
            r2 = r2.r();
            r2 = r11.h(r2);
            if (r2 != 0) goto L_0x002e;
        L_0x002a:
            r11.b(r9);
        L_0x002d:
            return r0;
        L_0x002e:
            r11.t();
            r0 = r11.A();
            r0 = r0.a();
            r2 = "caption";
            r0 = r0.equals(r2);
            if (r0 != 0) goto L_0x0045;
        L_0x0042:
            r11.b(r9);
        L_0x0045:
            r0 = "caption";
            r11.c(r0);
            r11.x();
            r0 = InTable;
            r11.a(r0);
        L_0x0053:
            r0 = r1;
            goto L_0x002d;
        L_0x0055:
            r2 = r10.e();
            if (r2 == 0) goto L_0x009f;
        L_0x005b:
            r2 = r10.f();
            r2 = r2.r();
            r3 = 9;
            r3 = new java.lang.String[r3];
            r4 = "caption";
            r3[r0] = r4;
            r4 = "col";
            r3[r1] = r4;
            r4 = "colgroup";
            r3[r6] = r4;
            r4 = "tbody";
            r3[r7] = r4;
            r4 = "td";
            r3[r8] = r4;
            r4 = 5;
            r5 = "tfoot";
            r3[r4] = r5;
            r4 = 6;
            r5 = "th";
            r3[r4] = r5;
            r4 = 7;
            r5 = "thead";
            r3[r4] = r5;
            r4 = 8;
            r5 = "tr";
            r3[r4] = r5;
            r2 = org.jsoup.helper.b.a(r2, r3);
            if (r2 != 0) goto L_0x00b6;
        L_0x009f:
            r2 = r10.g();
            if (r2 == 0) goto L_0x00c8;
        L_0x00a5:
            r2 = r10.h();
            r2 = r2.r();
            r3 = "table";
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x00c8;
        L_0x00b6:
            r11.b(r9);
            r0 = "caption";
            r0 = r11.m(r0);
            if (r0 == 0) goto L_0x0053;
        L_0x00c2:
            r0 = r11.a(r10);
            goto L_0x002d;
        L_0x00c8:
            r2 = r10.g();
            if (r2 == 0) goto L_0x011e;
        L_0x00ce:
            r2 = r10.h();
            r2 = r2.r();
            r3 = 10;
            r3 = new java.lang.String[r3];
            r4 = "body";
            r3[r0] = r4;
            r4 = "col";
            r3[r1] = r4;
            r1 = "colgroup";
            r3[r6] = r1;
            r1 = "html";
            r3[r7] = r1;
            r1 = "tbody";
            r3[r8] = r1;
            r1 = 5;
            r4 = "td";
            r3[r1] = r4;
            r1 = 6;
            r4 = "tfoot";
            r3[r1] = r4;
            r1 = 7;
            r4 = "th";
            r3[r1] = r4;
            r1 = 8;
            r4 = "thead";
            r3[r1] = r4;
            r1 = 9;
            r4 = "tr";
            r3[r1] = r4;
            r1 = org.jsoup.helper.b.a(r2, r3);
            if (r1 == 0) goto L_0x011e;
        L_0x0119:
            r11.b(r9);
            goto L_0x002d;
        L_0x011e:
            r0 = InBody;
            r0 = r11.a(r10, r0);
            goto L_0x002d;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.11.process(org.jsoup.parser.Token, org.jsoup.parser.b):boolean");
        }
    },
    InColumnGroup {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                bVar.a(token.l());
                return true;
            }
            switch (token.a) {
                case Comment:
                    bVar.a(token.j());
                    return true;
                case Doctype:
                    bVar.b((HtmlTreeBuilderState) this);
                    return true;
                case StartTag:
                    f f = token.f();
                    String r = f.r();
                    if (r.equals("html")) {
                        return bVar.a(token, InBody);
                    }
                    if (!r.equals("col")) {
                        return anythingElse(token, bVar);
                    }
                    bVar.b(f);
                    return true;
                case EndTag:
                    if (!token.h().r().equals("colgroup")) {
                        return anythingElse(token, bVar);
                    }
                    if (bVar.A().a().equals("html")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    bVar.i();
                    bVar.a(InTable);
                    return true;
                case EOF:
                    if (bVar.A().a().equals("html")) {
                        return true;
                    }
                    return anythingElse(token, bVar);
                default:
                    return anythingElse(token, bVar);
            }
        }

        private boolean anythingElse(Token token, i iVar) {
            if (iVar.m("colgroup")) {
                return iVar.a(token);
            }
            return true;
        }
    },
    InTableBody {
        boolean process(Token token, b bVar) {
            switch (token.a) {
                case StartTag:
                    Token f = token.f();
                    String r = f.r();
                    if (r.equals("tr")) {
                        bVar.l();
                        bVar.a((f) f);
                        bVar.a(InRow);
                        break;
                    }
                    if (b.a(r, "th", "td")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        bVar.l("tr");
                        return bVar.a(f);
                    }
                    if (b.a(r, "caption", "col", "colgroup", "tbody", "tfoot", "thead")) {
                        return exitTableBody(token, bVar);
                    }
                    return anythingElse(token, bVar);
                case EndTag:
                    String r2 = token.h().r();
                    if (b.a(r2, "tbody", "tfoot", "thead")) {
                        if (bVar.h(r2)) {
                            bVar.l();
                            bVar.i();
                            bVar.a(InTable);
                            break;
                        }
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    } else if (r2.equals("table")) {
                        return exitTableBody(token, bVar);
                    } else {
                        if (!b.a(r2, "body", "caption", "col", "colgroup", "html", "td", "th", "tr")) {
                            return anythingElse(token, bVar);
                        }
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                default:
                    return anythingElse(token, bVar);
            }
            return true;
        }

        private boolean exitTableBody(Token token, b bVar) {
            if (bVar.h("tbody") || bVar.h("thead") || bVar.e("tfoot")) {
                bVar.l();
                bVar.m(bVar.A().a());
                return bVar.a(token);
            }
            bVar.b((HtmlTreeBuilderState) this);
            return false;
        }

        private boolean anythingElse(Token token, b bVar) {
            return bVar.a(token, InTable);
        }
    },
    InRow {
        boolean process(Token token, b bVar) {
            if (token.e()) {
                f f = token.f();
                String r = f.r();
                if (b.a(r, "th", "td")) {
                    bVar.m();
                    bVar.a(f);
                    bVar.a(InCell);
                    bVar.y();
                } else {
                    if (b.a(r, "caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr")) {
                        return handleMissingTr(token, bVar);
                    }
                    return anythingElse(token, bVar);
                }
            } else if (!token.g()) {
                return anythingElse(token, bVar);
            } else {
                String r2 = token.h().r();
                if (r2.equals("tr")) {
                    if (bVar.h(r2)) {
                        bVar.m();
                        bVar.i();
                        bVar.a(InTableBody);
                    } else {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                } else if (r2.equals("table")) {
                    return handleMissingTr(token, bVar);
                } else {
                    if (!b.a(r2, "tbody", "tfoot", "thead")) {
                        if (!b.a(r2, "body", "caption", "col", "colgroup", "html", "td", "th")) {
                            return anythingElse(token, bVar);
                        }
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    } else if (bVar.h(r2)) {
                        bVar.m("tr");
                        return bVar.a(token);
                    } else {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, b bVar) {
            return bVar.a(token, InTable);
        }

        private boolean handleMissingTr(Token token, i iVar) {
            if (iVar.m("tr")) {
                return iVar.a(token);
            }
            return false;
        }
    },
    InCell {
        boolean process(Token token, b bVar) {
            if (token.g()) {
                String r = token.h().r();
                if (!b.a(r, "td", "th")) {
                    if (b.a(r, "body", "caption", "col", "colgroup", "html")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    if (!b.a(r, "table", "tbody", "tfoot", "thead", "tr")) {
                        return anythingElse(token, bVar);
                    }
                    if (bVar.h(r)) {
                        closeCell(bVar);
                        return bVar.a(token);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                } else if (bVar.h(r)) {
                    bVar.t();
                    if (!bVar.A().a().equals(r)) {
                        bVar.b((HtmlTreeBuilderState) this);
                    }
                    bVar.c(r);
                    bVar.x();
                    bVar.a(InRow);
                    return true;
                } else {
                    bVar.b((HtmlTreeBuilderState) this);
                    bVar.a(InRow);
                    return false;
                }
            }
            if (token.e()) {
                if (b.a(token.f().r(), "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                    if (bVar.h("td") || bVar.h("th")) {
                        closeCell(bVar);
                        return bVar.a(token);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            }
            return anythingElse(token, bVar);
        }

        private boolean anythingElse(Token token, b bVar) {
            return bVar.a(token, InBody);
        }

        private void closeCell(b bVar) {
            if (bVar.h("td")) {
                bVar.m("td");
            } else {
                bVar.m("th");
            }
        }
    },
    InSelect {
        boolean process(Token token, b bVar) {
            switch (token.a) {
                case Comment:
                    bVar.a(token.j());
                    break;
                case Doctype:
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                case StartTag:
                    Token f = token.f();
                    String r = f.r();
                    if (r.equals("html")) {
                        return bVar.a(f, InBody);
                    }
                    if (r.equals("option")) {
                        if (bVar.A().a().equals("option")) {
                            bVar.m("option");
                        }
                        bVar.a((f) f);
                        break;
                    } else if (r.equals("optgroup")) {
                        if (bVar.A().a().equals("option")) {
                            bVar.m("option");
                        } else if (bVar.A().a().equals("optgroup")) {
                            bVar.m("optgroup");
                        }
                        bVar.a((f) f);
                        break;
                    } else if (r.equals("select")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return bVar.m("select");
                    } else {
                        if (b.a(r, "input", "keygen", "textarea")) {
                            bVar.b((HtmlTreeBuilderState) this);
                            if (!bVar.i("select")) {
                                return false;
                            }
                            bVar.m("select");
                            return bVar.a(f);
                        } else if (r.equals("script")) {
                            return bVar.a(token, InHead);
                        } else {
                            return anythingElse(token, bVar);
                        }
                    }
                case EndTag:
                    String r2 = token.h().r();
                    if (r2.equals("optgroup")) {
                        if (bVar.A().a().equals("option") && bVar.f(bVar.A()) != null && bVar.f(bVar.A()).a().equals("optgroup")) {
                            bVar.m("option");
                        }
                        if (!bVar.A().a().equals("optgroup")) {
                            bVar.b((HtmlTreeBuilderState) this);
                            break;
                        }
                        bVar.i();
                        break;
                    } else if (r2.equals("option")) {
                        if (!bVar.A().a().equals("option")) {
                            bVar.b((HtmlTreeBuilderState) this);
                            break;
                        }
                        bVar.i();
                        break;
                    } else if (r2.equals("select")) {
                        if (bVar.i(r2)) {
                            bVar.c(r2);
                            bVar.n();
                            break;
                        }
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    } else {
                        return anythingElse(token, bVar);
                    }
                case Character:
                    a l = token.l();
                    if (!l.n().equals(HtmlTreeBuilderState.nullString)) {
                        bVar.a(l);
                        break;
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                case EOF:
                    if (!bVar.A().a().equals("html")) {
                        bVar.b((HtmlTreeBuilderState) this);
                        break;
                    }
                    break;
                default:
                    return anythingElse(token, bVar);
            }
            return true;
        }

        private boolean anythingElse(Token token, b bVar) {
            bVar.b((HtmlTreeBuilderState) this);
            return false;
        }
    },
    InSelectInTable {
        boolean process(Token token, b bVar) {
            if (token.e()) {
                if (b.a(token.f().r(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                    bVar.b((HtmlTreeBuilderState) this);
                    bVar.m("select");
                    return bVar.a(token);
                }
            }
            if (token.g()) {
                if (b.a(token.h().r(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                    bVar.b((HtmlTreeBuilderState) this);
                    if (!bVar.h(token.h().r())) {
                        return false;
                    }
                    bVar.m("select");
                    return bVar.a(token);
                }
            }
            return bVar.a(token, InSelect);
        }
    },
    AfterBody {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return bVar.a(token, InBody);
            }
            if (token.i()) {
                bVar.a(token.j());
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return bVar.a(token, InBody);
            } else {
                if (token.g() && token.h().r().equals("html")) {
                    if (bVar.h()) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    bVar.a(AfterAfterBody);
                } else if (!token.m()) {
                    bVar.b((HtmlTreeBuilderState) this);
                    bVar.a(InBody);
                    return bVar.a(token);
                }
            }
            return true;
        }
    },
    InFrameset {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                bVar.a(token.l());
            } else if (token.i()) {
                bVar.a(token.j());
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e()) {
                Token f = token.f();
                String r = f.r();
                if (r.equals("html")) {
                    return bVar.a(f, InBody);
                }
                if (r.equals("frameset")) {
                    bVar.a((f) f);
                } else if (r.equals("frame")) {
                    bVar.b((f) f);
                } else if (r.equals("noframes")) {
                    return bVar.a(f, InHead);
                } else {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            } else if (token.g() && token.h().r().equals("frameset")) {
                if (bVar.A().a().equals("html")) {
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
                bVar.i();
                if (!(bVar.h() || bVar.A().a().equals("frameset"))) {
                    bVar.a(AfterFrameset);
                }
            } else if (!token.m()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (!bVar.A().a().equals("html")) {
                bVar.b((HtmlTreeBuilderState) this);
                return true;
            }
            return true;
        }
    },
    AfterFrameset {
        boolean process(Token token, b bVar) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                bVar.a(token.l());
            } else if (token.i()) {
                bVar.a(token.j());
            } else if (token.c()) {
                bVar.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return bVar.a(token, InBody);
            } else {
                if (token.g() && token.h().r().equals("html")) {
                    bVar.a(AfterAfterFrameset);
                } else if (token.e() && token.f().r().equals("noframes")) {
                    return bVar.a(token, InHead);
                } else {
                    if (!token.m()) {
                        bVar.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                }
            }
            return true;
        }
    },
    AfterAfterBody {
        boolean process(Token token, b bVar) {
            if (token.i()) {
                bVar.a(token.j());
            } else if (token.c() || HtmlTreeBuilderState.isWhitespace(token) || (token.e() && token.f().r().equals("html"))) {
                return bVar.a(token, InBody);
            } else {
                if (!token.m()) {
                    bVar.b((HtmlTreeBuilderState) this);
                    bVar.a(InBody);
                    return bVar.a(token);
                }
            }
            return true;
        }
    },
    AfterAfterFrameset {
        boolean process(Token token, b bVar) {
            if (token.i()) {
                bVar.a(token.j());
            } else if (token.c() || HtmlTreeBuilderState.isWhitespace(token) || (token.e() && token.f().r().equals("html"))) {
                return bVar.a(token, InBody);
            } else {
                if (!token.m()) {
                    if (token.e() && token.f().r().equals("noframes")) {
                        return bVar.a(token, InHead);
                    }
                    bVar.b((HtmlTreeBuilderState) this);
                    return false;
                }
            }
            return true;
        }
    },
    ForeignContent {
        boolean process(Token token, b bVar) {
            return true;
        }
    };
    
    private static String nullString;

    private static final class a {
        private static final String[] a = null;
        private static final String[] b = null;
        private static final String[] c = null;
        private static final String[] d = null;
        private static final String[] e = null;
        private static final String[] f = null;
        private static final String[] g = null;
        private static final String[] h = null;
        private static final String[] i = null;
        private static final String[] j = null;
        private static final String[] k = null;
        private static final String[] l = null;
        private static final String[] m = null;
        private static final String[] n = null;
        private static final String[] o = null;
        private static final String[] p = null;
        private static final String[] q = null;

        static {
            a = new String[]{"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "title"};
            b = new String[]{"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", "p", "section", "summary", "ul"};
            c = new String[]{"h1", "h2", "h3", "h4", "h5", "h6"};
            d = new String[]{"pre", "listing"};
            e = new String[]{"address", "div", "p"};
            f = new String[]{"dd", "dt"};
            g = new String[]{"b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong", "tt", "u"};
            h = new String[]{"applet", "marquee", "object"};
            i = new String[]{"area", "br", "embed", SocialConstants.PARAM_IMG_URL, "keygen", "wbr"};
            j = new String[]{"param", SocialConstants.PARAM_SOURCE, "track"};
            k = new String[]{"name", "action", "prompt"};
            l = new String[]{"optgroup", "option"};
            m = new String[]{"rp", "rt"};
            n = new String[]{"caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr"};
            o = new String[]{"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
            p = new String[]{"a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small", "strike", "strong", "tt", "u"};
            q = new String[]{"table", "tbody", "tfoot", "thead", "tr"};
        }
    }

    abstract boolean process(Token token, b bVar);

    static {
        nullString = String.valueOf('\u0000');
    }

    private static boolean isWhitespace(Token token) {
        if (token.k()) {
            return isWhitespace(token.l().n());
        }
        return false;
    }

    private static boolean isWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!b.b(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static void handleRcData(f fVar, b bVar) {
        bVar.a(fVar);
        bVar.d.a(TokeniserState.Rcdata);
        bVar.c();
        bVar.a(Text);
    }

    private static void handleRawtext(f fVar, b bVar) {
        bVar.a(fVar);
        bVar.d.a(TokeniserState.Rawtext);
        bVar.c();
        bVar.a(Text);
    }
}
