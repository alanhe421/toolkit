package com.qq.reader.module.comic.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ComicWatchingFocusItem implements Serializable {
    @SerializedName("img")
    public String bigUrl;
    public int id;
    @SerializedName("imgPreview")
    public String smallUrl;
    @SerializedName("sortnum")
    public String sortnum;
}
