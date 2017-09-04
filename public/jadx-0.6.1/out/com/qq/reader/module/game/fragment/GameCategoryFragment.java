package com.qq.reader.module.game.fragment;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;

public class GameCategoryFragment extends NativePageFragmentforOther {
    protected Boolean configCanPullLoadMore() {
        return Boolean.valueOf(true);
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if ("go_h5_game".equals(bundle.getString("function_type")) && (getActivity() instanceof a)) {
            ((a) getActivity()).doFunction(bundle);
        }
    }
}
