package com.qq.reader.module.comic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigStackTabFragment;
import com.tencent.feedback.proguard.R;

public class NativeBookLibraryActivity extends ReaderBaseActivity {
    public static void a(Context context, String str) {
        Intent intent = new Intent(context, NativeBookLibraryActivity.class);
        intent.putExtra(NativeBookStoreConfigStackTabFragment.INTETNT_CATEGORY_TYPE_KEY, str);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.native_book_library_activity);
        b();
    }

    private void b() {
        NativeBookStoreConfigStackTabFragment nativeBookStoreConfigStackTabFragment = (NativeBookStoreConfigStackTabFragment) getSupportFragmentManager().a(R.id.fragment_book_library);
    }

    public String a() {
        return getIntent().getStringExtra(NativeBookStoreConfigStackTabFragment.INTETNT_CATEGORY_TYPE_KEY);
    }
}
