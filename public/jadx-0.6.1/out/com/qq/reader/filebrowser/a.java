package com.qq.reader.filebrowser;

import android.database.DataSetObserver;
import android.util.SparseIntArray;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import java.text.Collator;

/* compiled from: AlphabetIndexer */
public class a extends DataSetObserver implements SectionIndexer {
    protected BaseAdapter a;
    protected CharSequence b;
    private int c;
    private SparseIntArray d;
    private Collator e;
    private String[] f = new String[this.c];

    public a(BaseAdapter baseAdapter, CharSequence charSequence) {
        this.a = baseAdapter;
        this.b = charSequence;
        this.c = charSequence.length();
        for (int i = 0; i < this.c; i++) {
            this.f[i] = Character.toString(this.b.charAt(i));
        }
        this.d = new SparseIntArray(this.c);
        this.a.registerDataSetObserver(this);
        this.e = Collator.getInstance();
        this.e.setStrength(0);
    }

    public Object[] getSections() {
        return this.f;
    }

    protected int a(String str, String str2) {
        return this.e.compare(b.b(str), str2);
    }

    public int getPositionForSection(int i) {
        int i2 = 0;
        SparseIntArray sparseIntArray = this.d;
        BaseAdapter baseAdapter = this.a;
        if (baseAdapter == null || this.b == null || i <= 0) {
            return 0;
        }
        int i3;
        if (i >= this.c) {
            i = this.c - 1;
        }
        int count = baseAdapter.getCount();
        char charAt = this.b.charAt(i);
        String ch = Character.toString(charAt);
        int i4 = sparseIntArray.get(charAt, Integer.MIN_VALUE);
        if (Integer.MIN_VALUE == i4) {
            i3 = count;
        } else if (i4 >= 0) {
            return i4;
        } else {
            i3 = -i4;
        }
        if (i > 0) {
            i4 = sparseIntArray.get(this.b.charAt(i - 1), Integer.MIN_VALUE);
            if (i4 != Integer.MIN_VALUE) {
                i2 = Math.abs(i4);
            }
        }
        i4 = (i3 + i2) / 2;
        int i5 = i3;
        i3 = i2;
        i2 = i5;
        while (i4 < i2) {
            String obj = baseAdapter.getItem(i4).toString();
            if (obj != null) {
                int a = a(obj, ch);
                if (a == 0) {
                    if (i3 == i4) {
                        break;
                    }
                    i2 = i4;
                    i4 = i3;
                } else if (a < 0) {
                    i4++;
                    if (i4 >= count) {
                        i4 = count;
                        break;
                    }
                } else {
                    i2 = i4;
                    i4 = i3;
                }
                i3 = i4;
                i4 = (i4 + i2) / 2;
            } else if (i4 == 0) {
                break;
            } else {
                i4--;
            }
        }
        sparseIntArray.put(charAt, i4);
        return i4;
    }

    public int getSectionForPosition(int i) {
        String obj = this.a.getItem(i).toString();
        for (int i2 = 0; i2 < this.c; i2++) {
            if (a(obj, Character.toString(this.b.charAt(i2))) == 0) {
                return i2;
            }
        }
        return 0;
    }

    public void onChanged() {
        super.onChanged();
        this.d.clear();
    }

    public void onInvalidated() {
        super.onInvalidated();
        this.d.clear();
    }
}
