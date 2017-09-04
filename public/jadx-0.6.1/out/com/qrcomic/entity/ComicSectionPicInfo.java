package com.qrcomic.entity;

import android.graphics.Bitmap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ComicSectionPicInfo implements Serializable, Cloneable {
    public static final transient int DOWNLOAD_PIC_ERROR = 1;
    public static final transient int LOAD_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    private Long _id;
    public List<ComicBarrageInfo> barrageList;
    public transient Bitmap bitmap;
    @SerializedName("comicId")
    public String comicId;
    @Expose(deserialize = false, serialize = false)
    public int dstHeight;
    @SerializedName("height")
    public int height;
    @SerializedName("index")
    public int index;
    public float initScale;
    public boolean isVisible;
    public int left;
    public int listIndex;
    public ComicRecommendPageInfo mComicRecommendPageInfo;
    public int mState = 0;
    public boolean needDraw;
    public int pagerIndex;
    public List<String> payedList;
    @SerializedName("picId")
    public String picId;
    @SerializedName("picUrl")
    public String picUrl;
    public String preloadLocation;
    public int redundantXSpace;
    @SerializedName("sectionId")
    public String sectionId;
    public int top;
    @SerializedName("width")
    public int width;

    public ComicSectionPicInfo(String str, String str2, int i, int i2, int i3, String str3, String str4) {
        this.picUrl = str;
        this.picId = str2;
        this.width = i;
        this.height = i2;
        this.index = i3;
        this.comicId = str3;
        this.sectionId = str4;
    }

    public String toString() {
        return "{ picUrl = " + this.picUrl + " picIndex = " + this.index + ", isVisible = " + this.isVisible + " , top = " + this.top + " , sectionId = " + this.sectionId + " , comicId = " + this.comicId + " , picId = " + this.picId + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComicSectionPicInfo)) {
            return false;
        }
        ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) obj;
        if (this.comicId == null || !this.comicId.equals(comicSectionPicInfo.comicId) || this.sectionId == null || !this.sectionId.equals(comicSectionPicInfo.sectionId) || this.picId == null || !this.picId.equals(comicSectionPicInfo.picId)) {
            return false;
        }
        return true;
    }
}
