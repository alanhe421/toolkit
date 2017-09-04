package com.qq.reader.readengine.model;

import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.d;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.readengine.fileparse.OnlineBookFile;
import com.qq.reader.readengine.fileparse.SingleBookFile;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MulitFile implements Serializable {
    private static final long serialVersionUID = 3081476519931929911L;
    private int LIST_BUFFERED_SIZE = 30;
    private d mBookInfo;
    private volatile boolean mChapterInited = false;
    private int mFileCount = 0;
    private List<SingleBookFile> mFileList = null;
    private int mIndex = 0;
    private String mPathRoot;

    public MulitFile(String str, int i) {
        this.mPathRoot = str;
        this.mFileCount = i;
        newFileList(i);
    }

    private void newFileList(int i) {
        this.mFileList = Collections.synchronizedList(new ArrayList(this.LIST_BUFFERED_SIZE + i));
        for (int i2 = 0; i2 < i; i2++) {
            this.mFileList.add(new OnlineBookFile(this.mPathRoot, null, i2 + 1));
        }
    }

    public List<SingleBookFile> getBookFileList() {
        return this.mFileList;
    }

    public int getListCount() {
        return this.mFileList.size();
    }

    public d getBookTailInfo() {
        return this.mBookInfo;
    }

    public void initFileList(OnlineTag onlineTag, boolean z) {
        if (z) {
            this.mChapterInited = false;
        }
        initFileList(onlineTag);
    }

    public void initFileList(OnlineTag onlineTag) {
        if (!this.mChapterInited) {
            File file = new File(onlineTag.d());
            if (file.exists() && file.length() > 0) {
                f fVar = new f(onlineTag);
                fVar.b();
                this.mBookInfo = fVar.y();
                List e = fVar.e();
                if (e != null && e.size() > 0) {
                    int size;
                    int i = this.mFileCount;
                    if (this.mFileCount > e.size()) {
                        size = e.size();
                    } else if (this.mFileCount < e.size()) {
                        this.mFileCount = e.size();
                        for (int i2 = i; i2 < this.mFileCount; i2++) {
                            this.mFileList.add(new OnlineBookFile(this.mPathRoot, (OnlineChapter) e.get(i2), i2 + 1));
                        }
                        size = this.mFileCount;
                    } else {
                        size = i;
                    }
                    for (int i3 = 0; i3 < size; i3++) {
                        ((OnlineBookFile) ((SingleBookFile) this.mFileList.get(i3))).setChapter((OnlineChapter) e.get(i3));
                    }
                }
                this.mChapterInited = true;
            }
        }
    }

    public long getTotalLength(long j) {
        return 0;
    }

    public boolean findNewFile(int i) {
        try {
            int size = this.mFileList.size();
            if (i > size) {
                while (size < i) {
                    this.mFileList.add(new OnlineBookFile(this.mPathRoot, null, size + 1));
                    size++;
                }
                this.mFileCount = this.mFileList.size();
            } else {
                ((SingleBookFile) this.mFileList.get(i - 1)).reInitFileLength();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close() {
        if (this.mFileList != null) {
            int size = this.mFileList.size();
            for (int i = 0; i < size; i++) {
                ((SingleBookFile) this.mFileList.get(i)).close();
            }
        }
        return true;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }

    public long getFileLength(int i) {
        if (!validChapterId(i + 1)) {
            return 0;
        }
        SingleBookFile singleBookFile = (SingleBookFile) this.mFileList.get(i);
        if (singleBookFile == null) {
            return 0;
        }
        return singleBookFile.getFileLength(-1);
    }

    public Chapter getChapterInfo(int i) {
        if (validChapterId(i)) {
            return ((SingleBookFile) this.mFileList.get(i - 1)).getChapter();
        }
        return null;
    }

    public boolean nextFileExist() {
        if (this.mIndex <= 0 || this.mIndex + 1 > this.mFileList.size()) {
            return false;
        }
        return ((SingleBookFile) this.mFileList.get(this.mIndex)).isExist();
    }

    public boolean currentFileExist() {
        return ((SingleBookFile) this.mFileList.get(this.mIndex - 1)).isExist();
    }

    public boolean checkExist(int i) {
        if (i == 0 || i > this.mFileList.size()) {
            return false;
        }
        return ((SingleBookFile) this.mFileList.get(i - 1)).isExist();
    }

    public boolean prevFileExist() {
        if (this.mIndex <= 1 || this.mIndex > this.mFileList.size() + 1) {
            return false;
        }
        return ((SingleBookFile) this.mFileList.get(this.mIndex - 2)).isExist();
    }

    public boolean isFirstFile() {
        return this.mIndex == 1;
    }

    public boolean isLastFile() {
        return this.mIndex >= this.mFileList.size();
    }

    public boolean validChapterId(int i) {
        int size = this.mFileList.size();
        if (this.mFileList == null || size == 0 || i <= 0 || i > size) {
            return false;
        }
        return true;
    }
}
