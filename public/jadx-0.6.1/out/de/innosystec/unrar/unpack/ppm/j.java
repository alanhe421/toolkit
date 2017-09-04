package de.innosystec.unrar.unpack.ppm;

/* compiled from: StateRef */
public class j {
    private int a;
    private int b;
    private int c;

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i & 255;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i & 255;
    }

    public void c(int i) {
        this.b = (this.b - i) & 255;
    }

    public void a(i iVar) {
        b(iVar.b());
        d(iVar.c());
        a(iVar.a());
    }

    public int c() {
        return this.c;
    }

    public void a(c cVar) {
        d(cVar.e());
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State[");
        stringBuilder.append("\n  symbol=");
        stringBuilder.append(a());
        stringBuilder.append("\n  freq=");
        stringBuilder.append(b());
        stringBuilder.append("\n  successor=");
        stringBuilder.append(c());
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
