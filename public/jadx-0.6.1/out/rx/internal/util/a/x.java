package rx.internal.util.a;

/* compiled from: SpscArrayQueue */
abstract class x<E> extends f<E> {
    private static final Integer e = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int d;

    public x(int i) {
        super(i);
        this.d = Math.min(i / 4, e.intValue());
    }
}
