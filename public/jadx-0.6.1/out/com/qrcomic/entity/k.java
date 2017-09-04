package com.qrcomic.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: QRBuyComicSectionResult */
public class k {
    @SerializedName("comicId")
    public String a;
    @SerializedName("price")
    public int b;
    @SerializedName("buyType")
    public int c;
    @SerializedName("sectionIdListSuccess")
    public List<String> d;
    @SerializedName("sectionIdListRemain")
    public List<String> e;
    @Expose(deserialize = false, serialize = false)
    public boolean f;
}
