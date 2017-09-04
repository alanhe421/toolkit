package com.tencent.upload.common;

import com.tencent.upload.Const.FileType;

final /* synthetic */ class f {
    static final /* synthetic */ int[] a = new int[FileType.values().length];

    static {
        try {
            a[FileType.Photo.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[FileType.Audio.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[FileType.Video.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[FileType.File.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
