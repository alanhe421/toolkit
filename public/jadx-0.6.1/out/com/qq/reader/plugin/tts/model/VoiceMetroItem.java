package com.qq.reader.plugin.tts.model;

import com.qq.reader.view.metro.MetroItem;

public class VoiceMetroItem extends MetroItem {
    private static final long serialVersionUID = 8585388320123961162L;
    private String mShowName;
    public int mType;

    public VoiceMetroItem(int i, String str, String str2, int i2) {
        super(i, str);
        this.mShowName = str2;
        this.mType = i2;
    }

    public String getDisplayName() {
        return this.mShowName;
    }
}
