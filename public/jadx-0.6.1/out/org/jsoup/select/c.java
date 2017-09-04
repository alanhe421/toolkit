package org.jsoup.select;

import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/* compiled from: Evaluator */
public abstract class c {

    /* compiled from: Evaluator */
    public static final class a extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return true;
        }

        public String toString() {
            return com.tencent.qalsdk.sdk.v.n;
        }
    }

    /* compiled from: Evaluator */
    public static abstract class o extends c {
        protected final int a;
        protected final int b;

        protected abstract String a();

        protected abstract int b(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2);

        public o(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            org.jsoup.nodes.g m = gVar2.m();
            if (m == null || (m instanceof Document)) {
                return false;
            }
            int b = b(gVar, gVar2);
            if (this.a == 0) {
                if (b != this.b) {
                    return false;
                }
                return true;
            } else if ((b - this.b) * this.a < 0 || (b - this.b) % this.a != 0) {
                return false;
            } else {
                return true;
            }
        }

        public String toString() {
            if (this.a == 0) {
                return String.format(":%s(%d)", new Object[]{a(), Integer.valueOf(this.b)});
            } else if (this.b == 0) {
                return String.format(":%s(%dn)", new Object[]{a(), Integer.valueOf(this.a)});
            } else {
                return String.format(":%s(%dn%+d)", new Object[]{a(), Integer.valueOf(this.a), Integer.valueOf(this.b)});
            }
        }
    }

    /* compiled from: Evaluator */
    public static final class aa extends o {
        public aa(int i, int i2) {
            super(i, i2);
        }

        protected int b(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.m().o().size() - gVar2.u();
        }

        protected String a() {
            return "nth-last-child";
        }
    }

    /* compiled from: Evaluator */
    public static class ab extends o {
        public ab(int i, int i2) {
            super(i, i2);
        }

        protected int b(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            Elements o = gVar2.m().o();
            int i = 0;
            for (int u = gVar2.u(); u < o.size(); u++) {
                if (((org.jsoup.nodes.g) o.get(u)).j().equals(gVar2.j())) {
                    i++;
                }
            }
            return i;
        }

        protected String a() {
            return "nth-last-of-type";
        }
    }

    /* compiled from: Evaluator */
    public static class ac extends o {
        public ac(int i, int i2) {
            super(i, i2);
        }

        protected int b(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            Iterator it = gVar2.m().o().iterator();
            int i = 0;
            while (it.hasNext()) {
                org.jsoup.nodes.g gVar3 = (org.jsoup.nodes.g) it.next();
                if (gVar3.j().equals(gVar2.j())) {
                    i++;
                    continue;
                }
                if (gVar3 == gVar2) {
                    break;
                }
            }
            return i;
        }

        protected String a() {
            return "nth-of-type";
        }
    }

    /* compiled from: Evaluator */
    public static final class ad extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            org.jsoup.nodes.g m = gVar2.m();
            return (m == null || (m instanceof Document) || gVar2.r().size() != 0) ? false : true;
        }

        public String toString() {
            return ":only-child";
        }
    }

    /* compiled from: Evaluator */
    public static final class ae extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            org.jsoup.nodes.g m = gVar2.m();
            if (m == null || (m instanceof Document)) {
                return false;
            }
            Iterator it = m.o().iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                if (((org.jsoup.nodes.g) it.next()).j().equals(gVar2.j())) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            if (i == 1) {
                return true;
            }
            return false;
        }

        public String toString() {
            return ":only-of-type";
        }
    }

    /* compiled from: Evaluator */
    public static final class af extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            if (gVar instanceof Document) {
                gVar = gVar.a(0);
            }
            if (gVar2 == gVar) {
                return true;
            }
            return false;
        }

        public String toString() {
            return ":root";
        }
    }

    /* compiled from: Evaluator */
    public static final class ag extends c {
        private Pattern a;

        public ag(Pattern pattern) {
            this.a = pattern;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return this.a.matcher(gVar2.w()).find();
        }

        public String toString() {
            return String.format(":matches(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class ah extends c {
        private Pattern a;

        public ah(Pattern pattern) {
            this.a = pattern;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return this.a.matcher(gVar2.x()).find();
        }

        public String toString() {
            return String.format(":matchesOwn(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class ai extends c {
        private String a;

        public ai(String str) {
            this.a = str;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.i().equalsIgnoreCase(this.a);
        }

        public String toString() {
            return String.format("%s", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class aj extends c {
        private String a;

        public aj(String str) {
            this.a = str;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.i().endsWith(this.a);
        }

        public String toString() {
            return String.format("%s", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class b extends c {
        private String a;

        public b(String str) {
            this.a = str;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a);
        }

        public String toString() {
            return String.format("[%s]", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static abstract class c extends c {
        String a;
        String b;

        public c(String str, String str2) {
            org.jsoup.helper.c.a(str);
            org.jsoup.helper.c.a(str2);
            this.a = org.jsoup.a.a.b(str);
            if ((str2.startsWith("\"") && str2.endsWith("\"")) || (str2.startsWith("'") && str2.endsWith("'"))) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            this.b = org.jsoup.a.a.b(str2);
        }
    }

    /* compiled from: Evaluator */
    public static final class d extends c {
        private String a;

        public d(String str) {
            org.jsoup.helper.c.a(str);
            this.a = org.jsoup.a.a.a(str);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            for (org.jsoup.nodes.a a : gVar2.F().b()) {
                if (org.jsoup.a.a.a(a.a()).startsWith(this.a)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format("[^%s]", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class e extends c {
        public e(String str, String str2) {
            super(str, str2);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a) && this.b.equalsIgnoreCase(gVar2.q(this.a).trim());
        }

        public String toString() {
            return String.format("[%s=%s]", new Object[]{this.a, this.b});
        }
    }

    /* compiled from: Evaluator */
    public static final class f extends c {
        public f(String str, String str2) {
            super(str, str2);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a) && org.jsoup.a.a.a(gVar2.q(this.a)).contains(this.b);
        }

        public String toString() {
            return String.format("[%s*=%s]", new Object[]{this.a, this.b});
        }
    }

    /* compiled from: Evaluator */
    public static final class g extends c {
        public g(String str, String str2) {
            super(str, str2);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a) && org.jsoup.a.a.a(gVar2.q(this.a)).endsWith(this.b);
        }

        public String toString() {
            return String.format("[%s$=%s]", new Object[]{this.a, this.b});
        }
    }

    /* compiled from: Evaluator */
    public static final class h extends c {
        String a;
        Pattern b;

        public h(String str, Pattern pattern) {
            this.a = org.jsoup.a.a.b(str);
            this.b = pattern;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a) && this.b.matcher(gVar2.q(this.a)).find();
        }

        public String toString() {
            return String.format("[%s~=%s]", new Object[]{this.a, this.b.toString()});
        }
    }

    /* compiled from: Evaluator */
    public static final class i extends c {
        public i(String str, String str2) {
            super(str, str2);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return !this.b.equalsIgnoreCase(gVar2.q(this.a));
        }

        public String toString() {
            return String.format("[%s!=%s]", new Object[]{this.a, this.b});
        }
    }

    /* compiled from: Evaluator */
    public static final class j extends c {
        public j(String str, String str2) {
            super(str, str2);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.r(this.a) && org.jsoup.a.a.a(gVar2.q(this.a)).startsWith(this.b);
        }

        public String toString() {
            return String.format("[%s^=%s]", new Object[]{this.a, this.b});
        }
    }

    /* compiled from: Evaluator */
    public static final class k extends c {
        private String a;

        public k(String str) {
            this.a = str;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.h(this.a);
        }

        public String toString() {
            return String.format(".%s", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class l extends c {
        private String a;

        public l(String str) {
            this.a = org.jsoup.a.a.a(str);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return org.jsoup.a.a.a(gVar2.z()).contains(this.a);
        }

        public String toString() {
            return String.format(":containsData(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class m extends c {
        private String a;

        public m(String str) {
            this.a = org.jsoup.a.a.a(str);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return org.jsoup.a.a.a(gVar2.x()).contains(this.a);
        }

        public String toString() {
            return String.format(":containsOwn(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class n extends c {
        private String a;

        public n(String str) {
            this.a = org.jsoup.a.a.a(str);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return org.jsoup.a.a.a(gVar2.w()).contains(this.a);
        }

        public String toString() {
            return String.format(":contains(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static final class p extends c {
        private String a;

        public p(String str) {
            this.a = str;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return this.a.equals(gVar2.l());
        }

        public String toString() {
            return String.format("#%s", new Object[]{this.a});
        }
    }

    /* compiled from: Evaluator */
    public static abstract class r extends c {
        int a;

        public r(int i) {
            this.a = i;
        }
    }

    /* compiled from: Evaluator */
    public static final class q extends r {
        public q(int i) {
            super(i);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.u() == this.a;
        }

        public String toString() {
            return String.format(":eq(%d)", new Object[]{Integer.valueOf(this.a)});
        }
    }

    /* compiled from: Evaluator */
    public static final class s extends r {
        public s(int i) {
            super(i);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.u() > this.a;
        }

        public String toString() {
            return String.format(":gt(%d)", new Object[]{Integer.valueOf(this.a)});
        }
    }

    /* compiled from: Evaluator */
    public static final class t extends r {
        public t(int i) {
            super(i);
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.u() < this.a;
        }

        public String toString() {
            return String.format(":lt(%d)", new Object[]{Integer.valueOf(this.a)});
        }
    }

    /* compiled from: Evaluator */
    public static final class u extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            for (Node node : gVar2.H()) {
                if (!(node instanceof org.jsoup.nodes.d) && !(node instanceof org.jsoup.nodes.j) && !(node instanceof org.jsoup.nodes.f)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return ":empty";
        }
    }

    /* compiled from: Evaluator */
    public static final class v extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            org.jsoup.nodes.g m = gVar2.m();
            return (m == null || (m instanceof Document) || gVar2.u() != 0) ? false : true;
        }

        public String toString() {
            return ":first-child";
        }
    }

    /* compiled from: Evaluator */
    public static final class w extends ac {
        public w() {
            super(0, 1);
        }

        public String toString() {
            return ":first-of-type";
        }
    }

    /* compiled from: Evaluator */
    public static final class x extends c {
        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            org.jsoup.nodes.g m = gVar2.m();
            return (m == null || (m instanceof Document) || gVar2.u() != m.o().size() - 1) ? false : true;
        }

        public String toString() {
            return ":last-child";
        }
    }

    /* compiled from: Evaluator */
    public static final class y extends ab {
        public y() {
            super(0, 1);
        }

        public String toString() {
            return ":last-of-type";
        }
    }

    /* compiled from: Evaluator */
    public static final class z extends o {
        public z(int i, int i2) {
            super(i, i2);
        }

        protected int b(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar2.u() + 1;
        }

        protected String a() {
            return "nth-child";
        }
    }

    public abstract boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2);

    protected c() {
    }
}
