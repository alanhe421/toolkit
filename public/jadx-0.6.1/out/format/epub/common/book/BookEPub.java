package format.epub.common.book;

import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.model.IBook;
import format.epub.common.b.b;
import format.epub.common.b.d;
import format.epub.common.c.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookEPub extends IBook {
    private static final long serialVersionUID = 1;
    public final transient b File;
    private transient List<a> myAuthors;
    private String myEncoding;
    private long myId;
    private String myLanguage;
    private transient List<b> myTags;
    private String myTitle;

    BookEPub(long j, b bVar, String str, String str2, String str3) {
        this.myId = j;
        this.File = bVar;
        this.myTitle = str;
        this.myEncoding = str2;
        this.myLanguage = str3;
        this.mBookPath = bVar.c();
        this.mLength = bVar.h();
    }

    BookEPub(b bVar, long j) {
        this.myId = -1;
        this.File = bVar;
        this.mBookPath = bVar.c();
        setBookNetId(j);
        this.mLength = bVar.h();
    }

    public boolean readMetaInfo() {
        a a = format.epub.common.c.b.a().a(this.File);
        if (a == null || !a.a(this)) {
            return false;
        }
        if (this.mBookName == null || this.mBookName.length() == 0) {
            String k = this.File.k();
            int lastIndexOf = k.lastIndexOf(46);
            String str = this.myTitle;
            if (str == null || str.trim().length() == 0) {
                str = lastIndexOf > 0 ? k.substring(0, lastIndexOf) : k;
            }
            String str2 = "";
            k = ao.a(new StringBuffer(k));
            if (lastIndexOf != -1) {
                setBookName(str + k.substring(lastIndexOf));
            } else {
                setBookName(k);
            }
            if (lastIndexOf > 0) {
                str = k.substring(0, lastIndexOf);
            } else {
                str = k;
            }
            setTitle(str);
        }
        return true;
    }

    public static BookEPub createBookForFile(String str, long j) {
        b b = b.b(str);
        if (b == null) {
            return null;
        }
        BookEPub byFile = getByFile(b, j);
        if (byFile != null) {
            return byFile;
        }
        if (b.j()) {
            for (b byFile2 : b.n()) {
                byFile = getByFile(byFile2, j);
                if (byFile != null) {
                    return byFile;
                }
            }
        }
        return null;
    }

    public static BookEPub getByFile(b bVar, long j) {
        if (bVar == null) {
            return null;
        }
        d f = bVar.f();
        if (f == null || f.a()) {
            return new BookEPub(bVar, j);
        }
        return null;
    }

    public List<a> authors() {
        return this.myAuthors != null ? Collections.unmodifiableList(this.myAuthors) : Collections.emptyList();
    }

    void addAuthorWithNoCheck(a aVar) {
        if (this.myAuthors == null) {
            this.myAuthors = new ArrayList();
        }
        this.myAuthors.add(aVar);
    }

    public String getAuthor() {
        if (this.mAuthor == null || this.mAuthor.trim().length() == 0) {
            List authors = authors();
            this.mAuthor = authors.size() > 0 ? ((a) authors.get(0)).a : "";
        }
        return this.mAuthor;
    }

    private void addAuthor(a aVar) {
        if (aVar != null) {
            if (this.myAuthors == null) {
                this.myAuthors = new ArrayList();
                this.myAuthors.add(aVar);
            } else if (!this.myAuthors.contains(aVar)) {
                this.myAuthors.add(aVar);
            }
        }
    }

    public void addAuthor(String str) {
        addAuthor(str, "");
    }

    public void addAuthor(String str, String str2) {
        str.trim();
        if (str.length() != 0) {
            String str3;
            str2.trim();
            if (str2.length() == 0) {
                int lastIndexOf = str.lastIndexOf(32);
                if (lastIndexOf == -1) {
                    str3 = str;
                } else {
                    str2 = str.substring(lastIndexOf + 1);
                    while (lastIndexOf >= 0 && str.charAt(lastIndexOf) == ' ') {
                        lastIndexOf--;
                    }
                    str3 = str.substring(0, lastIndexOf + 1) + ' ' + str2;
                    str = str2;
                }
            } else {
                str3 = str;
                str = str2;
            }
            addAuthor(new a(str3, str));
        }
    }

    public long getId() {
        return this.myId;
    }

    public String getTitle() {
        return this.myTitle;
    }

    public void setTitle(String str) {
        if (!format.epub.common.utils.d.a(this.myTitle, str)) {
            this.myTitle = str;
        }
    }

    public String getLanguage() {
        return this.myLanguage;
    }

    public void setLanguage(String str) {
        if (!format.epub.common.utils.d.a(this.myLanguage, str)) {
            this.myLanguage = str;
        }
    }

    public void setEncoding(String str) {
        if (!format.epub.common.utils.d.a(this.myEncoding, str)) {
            this.myEncoding = str;
        }
    }

    public List<b> tags() {
        return this.myTags != null ? Collections.unmodifiableList(this.myTags) : Collections.emptyList();
    }

    void addTagWithNoCheck(b bVar) {
        if (this.myTags == null) {
            this.myTags = new ArrayList();
        }
        this.myTags.add(bVar);
    }

    public void addTag(b bVar) {
        if (bVar != null) {
            if (this.myTags == null) {
                this.myTags = new ArrayList();
            }
            if (!this.myTags.contains(bVar)) {
                this.myTags.add(bVar);
            }
        }
    }

    public void addTag(String str) {
        addTag(b.a(null, str));
    }

    public int hashCode() {
        return (int) this.myId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BookEPub)) {
            return false;
        }
        if (this.myId != ((BookEPub) obj).myId) {
            return false;
        }
        return true;
    }

    public boolean isAutoParserChapter() {
        return true;
    }
}
