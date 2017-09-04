package rx.internal.util.a;

/* compiled from: Pow2 */
public final class n {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
