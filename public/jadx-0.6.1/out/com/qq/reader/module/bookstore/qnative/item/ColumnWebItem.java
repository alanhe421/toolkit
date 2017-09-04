package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qweb.channel.ColumnWebEntity;

public class ColumnWebItem extends ColumnWebEntity {
    private c mBindAction;

    public ColumnWebItem(int i, int i2, String str, int i3, int i4, String str2) {
        super(i, i2, str, i3, i4, str2);
    }

    public ColumnWebItem(ColumnWebEntity columnWebEntity) {
        this(columnWebEntity.getTitleid(), columnWebEntity.getWebid(), columnWebEntity.getTitleName(), columnWebEntity.getTitleType(), columnWebEntity.getSelect(), columnWebEntity.getLinkUrl());
    }

    public c getBindAction() {
        return this.mBindAction;
    }

    public void parseData(String str) {
        this.mBindAction = new c(null);
        Bundle a = this.mBindAction.a();
        a.putString("KEY_ACTIONTAG", str);
        a.putString("KEY_ACTIONID", String.valueOf(getWebid()));
        a.putString("LOCAL_STORE_IN_TITLE", getTitleName());
        a.putBoolean("LOCAL_STORE_INTERNAL_CATEGORY", true);
        a.putString("KEY_JUMP_PAGENAME", "classify");
    }
}
