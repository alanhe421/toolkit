package com.qq.reader.module.bookstore.search;

import android.text.TextUtils;
import com.tencent.feedback.proguard.R;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.json.JSONObject;

public abstract class AbsSearchWords implements Serializable {
    public static final int DEFAULT_ICON_ID = 2130839337;
    public static final int SEARCH_HINT_TYPE_AUDIO = 6;
    public static final int SEARCH_HINT_TYPE_AUTHOR = 1;
    public static final int SEARCH_HINT_TYPE_BOOKSHELF = 13;
    public static final int SEARCH_HINT_TYPE_BOOKSHELF_AUDIO = 15;
    public static final int SEARCH_HINT_TYPE_BOOKSHELF_FOLDER = 14;
    public static final int SEARCH_HINT_TYPE_BOOK_DETAIL = 8;
    public static final int SEARCH_HINT_TYPE_CLASSIFY = 4;
    public static final int SEARCH_HINT_TYPE_CLEAR_HISTORY = 16;
    public static final int SEARCH_HINT_TYPE_COMIC = 102;
    public static final int SEARCH_HINT_TYPE_FAMOUS_AUTHOR = 9;
    public static final int SEARCH_HINT_TYPE_GAME = 103;
    public static final int SEARCH_HINT_TYPE_GO_READ = 5;
    public static final int SEARCH_HINT_TYPE_HOT_WOPD = 101;
    public static final int SEARCH_HINT_TYPE_LABEL = 3;
    public static final int SEARCH_HINT_TYPE_LABEL_FOR_REPORT = 23;
    public static final int SEARCH_HINT_TYPE_LINK = 7;
    public static final int SEARCH_HINT_TYPE_NORMAL = 0;
    public static final int SEARCH_HINT_TYPE_PUBLISHER = 2;
    public static final int SEARCH_HINT_TYPE_SPECIAL_AUDIO = 12;
    public static final int SEARCH_HINT_TYPE_SPECIAL_FREE = 10;
    public static final int SEARCH_HINT_TYPE_SPECIAL_VIP = 11;
    private static final long serialVersionUID = 0;
    private String mKeyWord = "";
    private String mQurl = "";
    public Object mTag;
    public int mType;

    public abstract AbsSearchWords parseJson(JSONObject jSONObject);

    public void setQurl(String str) {
        if (str != null) {
            this.mQurl = str;
        }
    }

    public void setKeyWord(String str) {
        if (str != null) {
            this.mKeyWord = str;
        }
    }

    public AbsSearchWords(String str, int i) {
        setKeyWord(str);
        this.mType = i;
    }

    public String getKeyWord() {
        return this.mKeyWord;
    }

    public String getWord() {
        return this.mKeyWord;
    }

    public int getType() {
        return this.mType;
    }

    public String getQurl() {
        if (TextUtils.isEmpty(this.mQurl)) {
            return getDefaultQurl();
        }
        return this.mQurl;
    }

    private String getDefaultQurl() {
        return "uniteqqreader://nativepage/search?encode_key=" + this.mKeyWord;
    }

    public int[] getIconResIds() {
        int i = R.drawable.search_hint_icon_author;
        int i2 = R.drawable.search_hint_author;
        switch (this.mType) {
            case 0:
                i2 = R.drawable.search_bar_icon;
                i = 0;
                break;
            case 1:
            case 9:
                break;
            case 2:
                i2 = R.drawable.search_hint_publisher;
                i = 0;
                break;
            case 3:
            case 10:
            case 11:
            case 12:
                i2 = R.drawable.search_hint_label;
                i = R.drawable.search_hint_icon_label;
                break;
            case 4:
                i2 = R.drawable.search_hint_classify;
                i = R.drawable.search_hint_icon_classify;
                break;
            case 5:
                i2 = R.drawable.search_hint_book;
                i = 0;
                break;
            case 6:
                i2 = R.drawable.search_hint_audio;
                i = 0;
                break;
            case 7:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
            case 8:
                i2 = R.drawable.search_book_go_detail;
                i = 0;
                break;
            case 13:
                i = R.drawable.search_hint_icon_already_in_bookshelf;
                i2 = R.drawable.search_book_linke;
                break;
            case 14:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
            case 15:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
            case 23:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
            case 101:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
            case 102:
                i2 = R.drawable.search_hint_icon_comic;
                i = 0;
                break;
            case 103:
                i2 = R.drawable.search_hint_icon_game;
                i = 0;
                break;
            default:
                i = 0;
                i2 = R.drawable.search_book_linke;
                break;
        }
        return new int[]{i2, i};
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(getKeyWord());
        objectOutputStream.writeInt(this.mType);
        objectOutputStream.writeUTF(getQurl());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        setKeyWord(objectInputStream.readUTF());
        this.mType = objectInputStream.readInt();
        setQurl(objectInputStream.readUTF());
    }
}
