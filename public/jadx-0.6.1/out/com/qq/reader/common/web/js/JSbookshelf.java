package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSbookshelf extends b {
    private Activity a;

    public JSbookshelf(Activity activity) {
        this.a = activity;
    }

    public String bookAction(String str) {
        String str2 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (jSONObject.getInt("actionCode")) {
                case 1000:
                    return getBookList(jSONObject.getString("range"));
                case 1001:
                    onClickBook(jSONObject.getString("bid"));
                    return str2;
                case 1004:
                    return getBookList("onlylast");
                default:
                    return str2;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return str2;
        }
        e.printStackTrace();
        return str2;
    }

    public String getBookList(String str) throws JSONException {
        int i;
        Object obj;
        int i2;
        int min;
        o c;
        JSONArray jSONArray;
        int i3;
        Mark mark;
        Object valueOf;
        int type;
        String str2;
        String str3;
        Object obj2;
        Object obj3;
        int i4;
        DownloadBookTask c2;
        TaskStateEnum state;
        List g = i.c().g();
        a(g);
        int size = g.size();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("size", size);
        if (str != null) {
            if ("0-".equals(str)) {
                i = 0;
                obj = null;
                i2 = size;
            } else if ("onlylast".equals(str)) {
                i = 0;
                int i5 = 1;
                i2 = size;
            } else {
                String[] split = str.split("-");
                if (split.length == 2) {
                    i = Integer.parseInt(split[0]);
                    i2 = Integer.parseInt(split[1]);
                    obj = null;
                }
            }
            min = Math.min(i2, size);
            c = o.c();
            jSONArray = new JSONArray();
            for (i3 = i; i3 < min; i3++) {
                mark = (Mark) g.get(i3);
                if (obj != null || mark.isLastRead()) {
                    valueOf = String.valueOf(mark.getBookId());
                    type = mark.getType();
                    size = -1;
                    str2 = "";
                    str3 = "";
                    if (type == 4) {
                        OnlineTag f = v.b().f(String.valueOf(valueOf));
                        f.u();
                        i = f.g();
                        if (i <= 0) {
                            i = 1;
                        }
                        obj2 = null;
                        str2 = mark.getLastReadChapterName();
                        if (str2 != null && str2.matches(".*第.*\\d+.*章.*")) {
                            obj2 = 1;
                        }
                        if (obj2 == null) {
                            str2 = "第" + i + "章 " + str2;
                        }
                        obj3 = str2;
                        i4 = -1;
                        size = f.w();
                    } else if (type != 1) {
                        valueOf = String.valueOf(mark.getBookId());
                        str3 = mark.getPercentStr();
                        i4 = -1;
                        size = mark.getIsFinish();
                    } else {
                        c2 = c.c(Long.valueOf(valueOf).longValue());
                        if (c2 == null) {
                            continue;
                        } else if (mark instanceof DownloadMark) {
                            i4 = 1000;
                            str3 = mark.getPercentStr();
                            size = 0;
                        } else {
                            state = c2.getState();
                            if (state == TaskStateEnum.Paused) {
                                size = 1002;
                            } else if (state != TaskStateEnum.Failed) {
                                size = 1001;
                            } else if (state == TaskStateEnum.Started || state == TaskStateEnum.DeactiveStarted) {
                                size = 1003;
                            }
                            str3 = str2;
                            i4 = size;
                            size = 0;
                        }
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("bid", valueOf);
                    jSONObject2.put("bname", mark.getBookShortName());
                    jSONObject2.put("author", mark.getAuthor());
                    jSONObject2.put("downloadstate", i4);
                    jSONObject2.put("hasNew", mark.hasNewContent() ? 1 : 0);
                    jSONObject2.put("finished", size);
                    jSONObject2.put("progress", obj3);
                    jSONObject2.put("isRead", mark.getReadTime() > 0 ? 0 : 1);
                    jSONObject2.put("isLastRead", mark.isLastRead() ? 1 : 0);
                    jSONObject2.put("updatetime", mark.getLastUpdateTime());
                    jSONObject2.put("updatechapter", mark.getLastUpdateChapter());
                    jSONArray.put(jSONObject2);
                    if (obj != null) {
                        break;
                    }
                }
            }
            jSONObject.put("blist", jSONArray);
            return jSONObject.toString();
        }
        i = 0;
        obj = null;
        i2 = size;
        min = Math.min(i2, size);
        c = o.c();
        jSONArray = new JSONArray();
        for (i3 = i; i3 < min; i3++) {
            mark = (Mark) g.get(i3);
            if (obj != null) {
            }
            valueOf = String.valueOf(mark.getBookId());
            type = mark.getType();
            size = -1;
            str2 = "";
            str3 = "";
            if (type == 4) {
                OnlineTag f2 = v.b().f(String.valueOf(valueOf));
                f2.u();
                i = f2.g();
                if (i <= 0) {
                    i = 1;
                }
                obj2 = null;
                str2 = mark.getLastReadChapterName();
                obj2 = 1;
                if (obj2 == null) {
                    str2 = "第" + i + "章 " + str2;
                }
                obj3 = str2;
                i4 = -1;
                size = f2.w();
            } else if (type != 1) {
                c2 = c.c(Long.valueOf(valueOf).longValue());
                if (c2 == null) {
                    continue;
                } else if (mark instanceof DownloadMark) {
                    i4 = 1000;
                    str3 = mark.getPercentStr();
                    size = 0;
                } else {
                    state = c2.getState();
                    if (state == TaskStateEnum.Paused) {
                        size = 1002;
                    } else if (state != TaskStateEnum.Failed) {
                        size = 1003;
                    } else {
                        size = 1001;
                    }
                    str3 = str2;
                    i4 = size;
                    size = 0;
                }
            } else {
                valueOf = String.valueOf(mark.getBookId());
                str3 = mark.getPercentStr();
                i4 = -1;
                size = mark.getIsFinish();
            }
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("bid", valueOf);
            jSONObject22.put("bname", mark.getBookShortName());
            jSONObject22.put("author", mark.getAuthor());
            jSONObject22.put("downloadstate", i4);
            if (mark.hasNewContent()) {
            }
            jSONObject22.put("hasNew", mark.hasNewContent() ? 1 : 0);
            jSONObject22.put("finished", size);
            jSONObject22.put("progress", obj3);
            if (mark.getReadTime() > 0) {
            }
            jSONObject22.put("isRead", mark.getReadTime() > 0 ? 0 : 1);
            if (mark.isLastRead()) {
            }
            jSONObject22.put("isLastRead", mark.isLastRead() ? 1 : 0);
            jSONObject22.put("updatetime", mark.getLastUpdateTime());
            jSONObject22.put("updatechapter", mark.getLastUpdateChapter());
            jSONArray.put(jSONObject22);
            if (obj != null) {
                break;
            }
        }
        jSONObject.put("blist", jSONArray);
        return jSONObject.toString();
    }

    private void a(List<Mark> list) {
        long j = 0;
        if (list != null) {
            int size = list.size();
            Mark mark = null;
            int i = 0;
            while (i < size) {
                Mark mark2 = (Mark) list.get(i);
                mark2.setLastRead(false);
                if (mark2.getReadTime() > j) {
                    j = mark2.getReadTime();
                    if (mark != null) {
                        mark.setLastRead(false);
                    }
                    mark2.setLastRead(true);
                } else {
                    mark2 = mark;
                }
                i++;
                mark = mark2;
            }
        }
    }

    public void onClickBook(String str) {
        Mark e = i.c().e(str);
        if (e instanceof LocalMark) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            if (new File(e.getId()).exists() && 4 != e.getType()) {
                bundle.putString("filepath", e.getId());
                bundle.putString("filename", e.getBookName());
                bundle.putString("fileauthor", e.getAuthor());
                bundle.putInt("fileencode", e.getEncoding());
                intent.putExtras(bundle);
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                com.qq.reader.b.a(intent, this.a);
            } else if (4 == e.getType()) {
                Parcelable a = v.b().a(e.getId());
                bundle.putString("filepath", e.getId());
                bundle.putString("filename", e.getBookName());
                intent.putExtras(bundle);
                intent.putExtra("com.qq.reader.OnlineTag", a);
                intent.putExtra("com.qq.reader.fromonline", true);
                intent.putExtra("com.qq.reader.inheadpage", e.getStarPointStr());
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                com.qq.reader.b.a(intent, this.a);
            } else {
                af.a(this.a.getApplicationContext(), (CharSequence) "没有找到本书，请检查SDCard", 0).a();
            }
        }
    }
}
