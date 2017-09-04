package com.qq.reader.module.bookstore.qnative.activity;

import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.d;

public class NativeBackRefreshTwoLevelActivity extends NativeBookStoreTwoLevelActivity {
    public static String k;
    private int w = 0;
    private int x;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.x = getIntent().getIntExtra(k, -1);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.x) {
            if (this.w != -1) {
                this.w = i2;
                if (i2 == -1) {
                    setResult(-1);
                }
            }
            if (i2 == -1) {
                d.b().a(getContext(), this.m, this.mHandler, false);
            }
        }
    }

    public void finish() {
        if (this.w == -1) {
            setResult(-1);
        }
        super.finish();
    }
}
