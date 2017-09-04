package com.qrcomic.entity;

import android.graphics.Color;
import android.text.TextUtils;
import com.tencent.theme.SkinEngine;
import java.io.Serializable;

public class ComicBarrageInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String comicId;
    public String content;
    public int coordinateX;
    public int coordinateY;
    public String danmuId;
    private int fontColor = 0;
    public String frontColor;
    public int frontSize;
    public int percentCoordinateY;
    public String picId;
    public long postTs;
    public String sectionId;
    public String uin = "";

    public ComicBarrageInfo(String str, String str2, String str3, String str4, int i, int i2) {
        this.comicId = str;
        this.sectionId = str2;
        this.picId = str3;
        this.content = str4;
        this.coordinateX = i;
        this.coordinateY = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ComicBarrageInfo) {
            ComicBarrageInfo comicBarrageInfo = (ComicBarrageInfo) obj;
            if (!(TextUtils.isEmpty(this.danmuId) || TextUtils.isEmpty(comicBarrageInfo.danmuId))) {
                return this.danmuId.equals(comicBarrageInfo.danmuId);
            }
        }
        return false;
    }

    public int getFontColor(int i) {
        if (this.fontColor == 0 && !TextUtils.isEmpty(this.frontColor)) {
            try {
                this.fontColor = Color.parseColor(this.frontColor);
            } catch (Exception e) {
                this.fontColor = 0;
            }
        }
        if (this.fontColor == 0) {
            this.fontColor = -1;
        }
        return (int) (((long) (this.fontColor & SkinEngine.TYPE_FILE)) | ((long) ((i & 255) << 24)));
    }
}
