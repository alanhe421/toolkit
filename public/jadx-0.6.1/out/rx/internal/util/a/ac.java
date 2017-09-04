package rx.internal.util.a;

/* compiled from: SpscArrayQueue */
abstract class ac<E> extends z<E> {
    protected static final long f = ae.a(ac.class, "producerIndex");
    protected long producerIndex;

    public ac(int i) {
        super(i);
    }
}
