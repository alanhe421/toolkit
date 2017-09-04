package com.tencent.theme;

final class AndroidXmlResourceParser$NamespaceStack {
    private int m_count;
    private int[] m_data = new int[32];
    private int m_dataLength;
    private int m_depth;

    public final void reset() {
        this.m_dataLength = 0;
        this.m_count = 0;
        this.m_depth = 0;
    }

    public final int getTotalCount() {
        return this.m_count;
    }

    public final int getCurrentCount() {
        if (this.m_dataLength == 0) {
            return 0;
        }
        return this.m_data[this.m_dataLength - 1];
    }

    public final int getAccumulatedCount(int i) {
        int i2 = 0;
        if (this.m_dataLength != 0 && i >= 0) {
            if (i > this.m_depth) {
                i = this.m_depth;
            }
            int i3 = 0;
            while (i != 0) {
                int i4 = this.m_data[i3];
                i--;
                i3 = ((i4 * 2) + 2) + i3;
                i2 += i4;
            }
        }
        return i2;
    }

    public final void push(int i, int i2) {
        if (this.m_depth == 0) {
            increaseDepth();
        }
        ensureDataCapacity(2);
        int i3 = this.m_dataLength - 1;
        int i4 = this.m_data[i3];
        this.m_data[(i3 - 1) - (i4 * 2)] = i4 + 1;
        this.m_data[i3] = i;
        this.m_data[i3 + 1] = i2;
        this.m_data[i3 + 2] = i4 + 1;
        this.m_dataLength += 2;
        this.m_count++;
    }

    public final boolean pop(int i, int i2) {
        if (this.m_dataLength == 0) {
            return false;
        }
        int i3 = this.m_dataLength - 1;
        int i4 = this.m_data[i3];
        int i5 = i3 - 2;
        int i6 = 0;
        while (i6 != i4) {
            if (this.m_data[i5] == i && this.m_data[i5 + 1] == i2) {
                int i7 = i4 - 1;
                if (i6 == 0) {
                    this.m_data[i5] = i7;
                    this.m_data[i5 - ((i7 * 2) + 1)] = i7;
                } else {
                    this.m_data[i3] = i7;
                    this.m_data[i3 - ((i7 * 2) + 3)] = i7;
                    System.arraycopy(this.m_data, i5 + 2, this.m_data, i5, this.m_dataLength - i5);
                }
                this.m_dataLength -= 2;
                this.m_count--;
                return true;
            }
            i6++;
            i5 -= 2;
        }
        return false;
    }

    public final boolean pop() {
        if (this.m_dataLength == 0) {
            return false;
        }
        int i = this.m_dataLength - 1;
        int i2 = this.m_data[i];
        if (i2 == 0) {
            return false;
        }
        int i3 = i2 - 1;
        i -= 2;
        this.m_data[i] = i3;
        this.m_data[i - ((i3 * 2) + 1)] = i3;
        this.m_dataLength -= 2;
        this.m_count--;
        return true;
    }

    public final int getPrefix(int i) {
        return get(i, true);
    }

    public final int getUri(int i) {
        return get(i, false);
    }

    public final int findPrefix(int i) {
        return find(i, false);
    }

    public final int findUri(int i) {
        return find(i, true);
    }

    public final int getDepth() {
        return this.m_depth;
    }

    public final void increaseDepth() {
        ensureDataCapacity(2);
        int i = this.m_dataLength;
        this.m_data[i] = 0;
        this.m_data[i + 1] = 0;
        this.m_dataLength += 2;
        this.m_depth++;
    }

    public final void decreaseDepth() {
        if (this.m_dataLength != 0) {
            int i = this.m_dataLength - 1;
            int i2 = this.m_data[i];
            if ((i - 1) - (i2 * 2) != 0) {
                this.m_dataLength -= (i2 * 2) + 2;
                this.m_count -= i2;
                this.m_depth--;
            }
        }
    }

    private void ensureDataCapacity(int i) {
        int length = this.m_data.length - this.m_dataLength;
        if (length <= i) {
            Object obj = new int[((length + this.m_data.length) * 2)];
            System.arraycopy(this.m_data, 0, obj, 0, this.m_dataLength);
            this.m_data = obj;
        }
    }

    private final int find(int i, boolean z) {
        if (this.m_dataLength == 0) {
            return -1;
        }
        int i2 = this.m_dataLength - 1;
        for (int i3 = this.m_depth; i3 != 0; i3--) {
            i2 -= 2;
            for (int i4 = this.m_data[i2]; i4 != 0; i4--) {
                if (z) {
                    if (this.m_data[i2] == i) {
                        return this.m_data[i2 + 1];
                    }
                } else if (this.m_data[i2 + 1] == i) {
                    return this.m_data[i2];
                }
                i2 -= 2;
            }
        }
        return -1;
    }

    private final int get(int i, boolean z) {
        if (this.m_dataLength == 0 || i < 0) {
            return -1;
        }
        int i2 = 0;
        int i3 = this.m_depth;
        while (i3 != 0) {
            int i4 = this.m_data[i2];
            if (i >= i4) {
                i -= i4;
                i2 += (i4 * 2) + 2;
                i3--;
            } else {
                int i5 = ((i * 2) + 1) + i2;
                if (!z) {
                    i5++;
                }
                return this.m_data[i5];
            }
        }
        return -1;
    }
}
