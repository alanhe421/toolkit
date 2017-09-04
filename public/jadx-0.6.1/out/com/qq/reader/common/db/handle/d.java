package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.g;
import com.qq.reader.framework.mark.Mark;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BookMarkCacheHandle */
class d {
    private Map<String, Mark> a;
    private List<Mark> b;
    private volatile boolean c = false;

    public void a(List<Mark> list) {
        if (!this.c && list != null) {
            int size = list.size();
            this.a = Collections.synchronizedMap(new HashMap(size));
            this.b = Collections.synchronizedList(new ArrayList(size));
            for (Mark mark : list) {
                this.b.add(mark);
                this.a.put(mark.getId(), mark);
            }
            this.c = true;
        }
    }

    public List<Mark> a() {
        List<Mark> arrayList = new ArrayList();
        Collections.sort(this.b, new Comparator<Mark>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((Mark) obj, (Mark) obj2);
            }

            public int a(Mark mark, Mark mark2) {
                try {
                    long operateTime = mark2.getOperateTime();
                    long operateTime2 = mark.getOperateTime();
                    if (operateTime > operateTime2) {
                        return 1;
                    }
                    if (operateTime < operateTime2) {
                        return -1;
                    }
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        arrayList.addAll(this.b);
        return arrayList;
    }

    public void a(Mark mark) {
        if (mark != null) {
            if (this.a.containsKey(mark.getId())) {
                Mark mark2 = (Mark) this.a.get(mark.getId());
                this.b.remove(mark2);
                a(mark2, mark);
            }
            this.a.put(mark.getId(), mark);
            this.b.add(mark);
            g.a().a(new BookMarkCacheHandle$2(this, mark));
        }
    }

    public void a(String str, long j, long j2, String str2, boolean z, int i) {
        Mark mark = (Mark) this.a.get(str);
        if (mark != null) {
            if (mark.getOperateTime() < j) {
                mark.setOperateTime(j);
            }
            if (mark.getLastUpdateTime() < j2) {
                mark.setLastUpdateTime(j2);
            }
            if (z) {
                mark.setHasNewContent(true);
            }
            if (str2 != null && str2.trim().length() > 0) {
                mark.setLastUpdateChapter(str2);
            }
            mark.setFinished(i);
        }
    }

    public void a(String str, int i) {
        Mark mark = (Mark) this.a.get(str);
        if (mark != null) {
            mark.setSortIndex(i);
        }
    }

    public void b(String str, int i) {
        Mark mark = (Mark) this.a.get(str);
        if (mark != null) {
            mark.setPrivateProperty(i);
        }
    }

    public void a(String str) {
        if (str != null && this.a.containsKey(str)) {
            this.b.remove((Mark) this.a.remove(str));
        }
    }

    public void b(String str) {
        if (str != null && this.a.containsKey(str)) {
            this.a.remove(str);
        }
    }

    public void b(Mark mark) {
        if (mark != null) {
            this.a.put(mark.getId(), mark);
        }
    }

    public void a(int i) {
        try {
            for (Mark mark : this.b) {
                if (mark != null && i == mark.getCategoryID()) {
                    mark.setCategoryID(i.b);
                }
            }
        } catch (Exception e) {
        }
    }

    public void c(String str, int i) {
        Mark mark = (Mark) this.a.get(str);
        if (mark != null) {
            mark.setCategoryID(i);
        }
    }

    public int b() {
        return this.b.size();
    }

    public void c() {
        this.a.clear();
        this.b.clear();
    }

    public Mark c(String str) {
        Mark mark = (Mark) this.a.get(str);
        if (mark == null) {
            mark = i.c().f(str);
            if (mark != null) {
                this.a.put(str, mark);
                this.b.add(mark);
            }
        }
        return mark;
    }

    public Mark d(String str) {
        try {
            long parseLong = Long.parseLong(str);
            for (Mark mark : this.b) {
                if (mark != null && mark.getBookId() == parseLong) {
                    return mark;
                }
            }
            return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(long j, int i) {
        Mark d = d(String.valueOf(j));
        if (d != null) {
            d.setSynBook(i);
        }
    }

    public void a(long j) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            if (j == ((Mark) it.next()).getBookId()) {
                it.remove();
                return;
            }
        }
    }

    private void a(Mark mark, Mark mark2) {
        if (mark.getSynBook() == 1) {
            mark2.setSynBook(1);
        }
        if (mark2.getLastUpdateTime() < mark.getLastUpdateTime()) {
            mark2.setLastUpdateTime(mark.getLastUpdateTime());
            mark2.setLastUpdateChapter(mark.getLastUpdateChapter());
        }
        mark2.setFinished(mark.getIsFinish());
        mark2.setSortIndex(mark.getSortIndex());
        mark2.setCoverUrl(mark.getImageURI());
        if (mark.getPrivateProperty() == 0) {
            mark2.setPrivateProperty(mark.getPrivateProperty());
        }
    }

    public List<Mark> b(List<Mark> list) {
        for (Entry value : this.a.entrySet()) {
            Mark mark = (Mark) value.getValue();
            int i = 0;
            while (i < list.size()) {
                if (mark.equals(list.get(i))) {
                    Mark mark2 = (Mark) list.get(i);
                    mark.setCategoryID(mark2.getCategoryID());
                    mark.setLastRead(false);
                    if (mark2.hasNewContent()) {
                        mark.setHasNewContent(true);
                    }
                    mark.setLastUpdateChapter(mark2.getLastUpdateChapter());
                    mark.setLastUpdateTime(mark2.getLastUpdateTime());
                    a(mark2, mark);
                    if (i != -1) {
                        list.remove(i);
                        list.add(i, mark);
                    }
                } else {
                    i++;
                }
            }
            i = -1;
            if (i != -1) {
                list.remove(i);
                list.add(i, mark);
            }
        }
        return list;
    }
}
