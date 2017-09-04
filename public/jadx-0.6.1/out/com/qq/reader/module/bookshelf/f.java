package com.qq.reader.module.bookshelf;

import com.qq.reader.common.db.handle.a;
import com.qq.reader.framework.mark.Mark;
import java.util.Comparator;

/* compiled from: BookShelfComparators */
public class f {
    public static final Comparator<Mark> a = new Comparator<Mark>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Mark) obj, (Mark) obj2);
        }

        public int a(Mark mark, Mark mark2) {
            long sortValue = mark2.getSortValue();
            long sortValue2 = mark.getSortValue();
            if (sortValue > sortValue2) {
                return 1;
            }
            if (sortValue < sortValue2) {
                return -1;
            }
            return 0;
        }
    };
    public static final Comparator<Mark> b = new Comparator<Mark>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Mark) obj, (Mark) obj2);
        }

        public int a(Mark mark, Mark mark2) {
            int sortIndex = mark2.getSortIndex();
            int sortIndex2 = mark.getSortIndex();
            if (sortIndex == sortIndex2) {
                long operateTime = mark2.getOperateTime();
                long operateTime2 = mark.getOperateTime();
                if (operateTime > operateTime2) {
                    return 1;
                }
                if (operateTime < operateTime2) {
                    return -1;
                }
                return 0;
            } else if (sortIndex <= sortIndex2) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    public static final Comparator<Mark> c = new Comparator<Mark>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Mark) obj, (Mark) obj2);
        }

        public int a(Mark mark, Mark mark2) {
            b c = a.a().c();
            if (c == null) {
                return 0;
            }
            long b = c.b();
            long bookId = mark2.getBookId();
            long bookId2 = mark.getBookId();
            if (bookId == b) {
                return 1;
            }
            if (bookId2 == b) {
                return -1;
            }
            return 0;
        }
    };
}
