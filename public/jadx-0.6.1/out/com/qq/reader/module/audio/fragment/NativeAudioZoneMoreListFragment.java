package com.qq.reader.module.audio.fragment;

import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;

public class NativeAudioZoneMoreListFragment extends NativePageFragmentforOther {
    protected Boolean configCanPullLoadMore() {
        return Boolean.valueOf(true);
    }
}
