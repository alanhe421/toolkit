package jadx.core.utils;

import java.util.BitSet;

public final class EmptyBitSet extends BitSet {
    public static final BitSet EMPTY = new EmptyBitSet();
    private static final long serialVersionUID = -1194884945157778639L;

    public EmptyBitSet() {
        super(0);
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public int nextSetBit(int fromIndex) {
        return -1;
    }

    public int length() {
        return 0;
    }

    public int size() {
        return 0;
    }

    public void set(int bitIndex) {
        throw new UnsupportedOperationException();
    }

    public void set(int bitIndex, boolean value) {
        throw new UnsupportedOperationException();
    }

    public void set(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public void set(int fromIndex, int toIndex, boolean value) {
        throw new UnsupportedOperationException();
    }

    public boolean get(int bitIndex) {
        return false;
    }

    public BitSet get(int fromIndex, int toIndex) {
        return EMPTY;
    }

    public void and(BitSet set) {
        throw new UnsupportedOperationException();
    }

    public void or(BitSet set) {
        throw new UnsupportedOperationException();
    }

    public void xor(BitSet set) {
        throw new UnsupportedOperationException();
    }

    public void andNot(BitSet set) {
        throw new UnsupportedOperationException();
    }

    public Object clone() {
        return this;
    }
}
