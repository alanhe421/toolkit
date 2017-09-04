package com.qq.reader.plugin.audiobook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.plugin.audiobook.core.n;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MusicMarkHandle */
public class i {
    private static c b;
    private static i c;
    private String a = "MusicMarkHandle";

    public static i a() {
        if (c == null) {
            c = new i();
        }
        return c;
    }

    public boolean a(long j) {
        int delete;
        Exception e;
        if (j < 0) {
            return false;
        }
        try {
            SQLiteDatabase a = b.a();
            delete = a.delete("lbook", "bookid= '" + j + "'", null);
            if (delete > 0) {
                try {
                    a.delete("lchapter", "all_id= '" + j + "'", null);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("MusicMarkDB", "delBookMark with exception : " + e.getMessage());
                        if (b != null) {
                            b.c();
                        }
                        if (delete > 0) {
                            return true;
                        }
                        return false;
                    } catch (Throwable th) {
                        if (b != null) {
                            b.c();
                        }
                    }
                }
            }
            if (b != null) {
                b.c();
            }
        } catch (Exception e3) {
            e = e3;
            delete = 0;
            f.a("MusicMarkDB", "delBookMark with exception : " + e.getMessage());
            if (b != null) {
                b.c();
            }
            if (delete > 0) {
                return false;
            }
            return true;
        }
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean a(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        int delete;
        try {
            delete = b.a().delete("lchapter", "path= '" + str.replace("'", "''") + "'", null);
            if (b != null) {
                b.c();
            }
        } catch (Exception e) {
            f.a("MusicMarkDB", "delBookMark with exception : " + e.getMessage());
            if (b != null) {
                b.c();
                delete = 0;
            } else {
                delete = 0;
            }
        } catch (Throwable th) {
            if (b != null) {
                b.c();
            }
        }
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean a(long j, long j2) {
        if (j < 0 || j2 < 0) {
            return false;
        }
        int delete;
        try {
            delete = b.a().delete("lchapter", "all_id= '" + j + "' and " + "chapter_id" + "= '" + j2 + "'", null);
            if (b != null) {
                b.c();
            }
        } catch (Exception e) {
            f.a("MusicMarkDB", "delBookMark with exception : " + e.getMessage());
            if (b != null) {
                b.c();
                delete = 0;
            } else {
                delete = 0;
            }
        } catch (Throwable th) {
            if (b != null) {
                b.c();
            }
        }
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public boolean a(MusicDownloadMark musicDownloadMark, int i) {
        Cursor query;
        Exception e;
        Throwable th;
        if (musicDownloadMark == null) {
            return false;
        }
        Cursor cursor = null;
        long j = -1;
        try {
            SQLiteDatabase a = b.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("path", musicDownloadMark.getId());
            contentValues.put("all_id", Long.valueOf(musicDownloadMark.getBookId()));
            contentValues.put("chapter_id", Long.valueOf(musicDownloadMark.getChapterId()));
            contentValues.put("name", musicDownloadMark.getBookName());
            contentValues.put("chapter_name", musicDownloadMark.getChapterName());
            contentValues.put("read_all_time", musicDownloadMark.getCtime());
            contentValues.put("read_cur_time", Long.valueOf(musicDownloadMark.getStartPoint()));
            contentValues.put(MessageKey.MSG_DATE, musicDownloadMark.getDataStr());
            contentValues.put(SocialConstants.PARAM_COMMENT, musicDownloadMark.getDescriptionStr());
            contentValues.put("author", musicDownloadMark.getAuthor());
            contentValues.put("time", Long.valueOf(musicDownloadMark.getReadTime()));
            contentValues.put("chapter_size", musicDownloadMark.getCsize());
            contentValues.put("type", Integer.valueOf(musicDownloadMark.getType()));
            long insert = a.insert("lchapter", null, contentValues);
            if (insert >= 0) {
                try {
                    query = a.query("lbook", new String[]{"bookid"}, "bookid= '" + musicDownloadMark.getBookId() + "'", null, null, null, null);
                } catch (Exception e2) {
                    e = e2;
                    j = insert;
                    query = null;
                    try {
                        f.a("MusicMarkDB", "addBookMark with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        if (j < 0) {
                            return false;
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        if (j >= 0) {
                            return false;
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    j = insert;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                    if (j >= 0) {
                        return false;
                    }
                    throw th;
                }
                try {
                    if (query.getCount() <= 0) {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("bookid", Long.valueOf(musicDownloadMark.getBookId()));
                        contentValues2.put("bookname", musicDownloadMark.getBookName());
                        contentValues2.put("bookauthor", musicDownloadMark.getAuthor());
                        contentValues2.put("chaptercount", Integer.valueOf(i));
                        a.insert("lbook", null, contentValues2);
                    }
                } catch (Exception e3) {
                    e = e3;
                    j = insert;
                    f.a("MusicMarkDB", "addBookMark with exception : " + e.getMessage());
                    if (query != null) {
                        query.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                    if (j < 0) {
                        return false;
                    }
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    j = insert;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                    if (j >= 0) {
                        return false;
                    }
                    throw th;
                }
            }
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            if (insert < 0) {
                return false;
            }
            return true;
        } catch (Exception e4) {
            e = e4;
            query = null;
            f.a("MusicMarkDB", "addBookMark with exception : " + e.getMessage());
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            if (j < 0) {
                return false;
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            if (cursor != null) {
                cursor.close();
            }
            if (b != null) {
                b.c();
            }
            if (j >= 0) {
                return false;
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.qq.reader.framework.mark.Mark b(java.lang.String r22) {
        /*
        r21 = this;
        monitor-enter(r21);
        if (r22 == 0) goto L_0x0009;
    L_0x0003:
        r2 = r22.length();	 Catch:{ all -> 0x010a }
        if (r2 != 0) goto L_0x000c;
    L_0x0009:
        r2 = 0;
    L_0x000a:
        monitor-exit(r21);
        return r2;
    L_0x000c:
        r15 = 0;
        r10 = 0;
        r2 = b;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r2 = r2.a();	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 13;
        r4 = new java.lang.String[r3];	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 0;
        r5 = "path";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 1;
        r5 = "all_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 2;
        r5 = "chapter_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 3;
        r5 = "name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 4;
        r5 = "chapter_name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 5;
        r5 = "read_all_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 6;
        r5 = "read_cur_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 7;
        r5 = "date";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 8;
        r5 = "description";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 9;
        r5 = "author";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 10;
        r5 = "time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 11;
        r5 = "chapter_size";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = 12;
        r5 = "type";
        r4[r3] = r5;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r3 = "lchapter";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r5.<init>();	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r6 = "path= '";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r0 = r22;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r6 = "'";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r5 = r5.toString();	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r14 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x010d, all -> 0x013f }
        r2 = r14.moveToFirst();	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        if (r2 == 0) goto L_0x015d;
    L_0x0099:
        r2 = 0;
        r3 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 1;
        r6 = r14.getLong(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 2;
        r8 = r14.getLong(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 3;
        r4 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 4;
        r5 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 5;
        r10 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 6;
        r12 = r14.getLong(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 7;
        r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 8;
        r16 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 9;
        r17 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 10;
        r18 = r14.getLong(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 11;
        r11 = r14.getString(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2 = 12;
        r2 = r14.getInt(r2);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r20 = 3;
        r0 = r20;
        if (r2 != r0) goto L_0x015d;
    L_0x00e4:
        r2 = new com.qq.reader.plugin.audiobook.MusicDownloadMark;	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r2.<init>(r3, r4, r5, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x0155, all -> 0x014f }
        r0 = r16;
        r3 = r2.setDescriptionStr(r0);	 Catch:{ Exception -> 0x015a, all -> 0x014f }
        r0 = r17;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x015a, all -> 0x014f }
        r0 = r18;
        r3.setReadTime(r0);	 Catch:{ Exception -> 0x015a, all -> 0x014f }
    L_0x00fa:
        if (r14 == 0) goto L_0x00ff;
    L_0x00fc:
        r14.close();	 Catch:{ all -> 0x010a }
    L_0x00ff:
        r3 = b;	 Catch:{ all -> 0x010a }
        if (r3 == 0) goto L_0x000a;
    L_0x0103:
        r3 = b;	 Catch:{ all -> 0x010a }
        r3.c();	 Catch:{ all -> 0x010a }
        goto L_0x000a;
    L_0x010a:
        r2 = move-exception;
        monitor-exit(r21);
        throw r2;
    L_0x010d:
        r2 = move-exception;
        r3 = r2;
        r4 = r10;
        r2 = r15;
    L_0x0111:
        r5 = "MusicMarkDB";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0152 }
        r6.<init>();	 Catch:{ all -> 0x0152 }
        r7 = "getBookMark with exception : ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0152 }
        r3 = r3.getMessage();	 Catch:{ all -> 0x0152 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x0152 }
        r3 = r3.toString();	 Catch:{ all -> 0x0152 }
        com.qq.reader.common.monitor.f.a(r5, r3);	 Catch:{ all -> 0x0152 }
        if (r4 == 0) goto L_0x0134;
    L_0x0131:
        r4.close();	 Catch:{ all -> 0x010a }
    L_0x0134:
        r3 = b;	 Catch:{ all -> 0x010a }
        if (r3 == 0) goto L_0x000a;
    L_0x0138:
        r3 = b;	 Catch:{ all -> 0x010a }
        r3.c();	 Catch:{ all -> 0x010a }
        goto L_0x000a;
    L_0x013f:
        r2 = move-exception;
    L_0x0140:
        if (r10 == 0) goto L_0x0145;
    L_0x0142:
        r10.close();	 Catch:{ all -> 0x010a }
    L_0x0145:
        r3 = b;	 Catch:{ all -> 0x010a }
        if (r3 == 0) goto L_0x014e;
    L_0x0149:
        r3 = b;	 Catch:{ all -> 0x010a }
        r3.c();	 Catch:{ all -> 0x010a }
    L_0x014e:
        throw r2;	 Catch:{ all -> 0x010a }
    L_0x014f:
        r2 = move-exception;
        r10 = r14;
        goto L_0x0140;
    L_0x0152:
        r2 = move-exception;
        r10 = r4;
        goto L_0x0140;
    L_0x0155:
        r2 = move-exception;
        r3 = r2;
        r4 = r14;
        r2 = r15;
        goto L_0x0111;
    L_0x015a:
        r3 = move-exception;
        r4 = r14;
        goto L_0x0111;
    L_0x015d:
        r2 = r15;
        goto L_0x00fa;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.audiobook.i.b(java.lang.String):com.qq.reader.framework.mark.Mark");
    }

    public synchronized ArrayList<Mark> b() {
        return d();
    }

    public List<MusicBookGroup> c() {
        Exception e;
        Throwable th;
        List<MusicBookGroup> arrayList = new ArrayList();
        Cursor query;
        try {
            query = b.a().query("lbook", new String[]{"bookid", "bookname", "bookauthor", "lasttime", "lastsong", "lastseek", "chaptercount"}, null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0 && query.moveToFirst()) {
                        do {
                            MusicBookGroup musicBookGroup = new MusicBookGroup(query.getLong(0), query.getString(1));
                            String string = query.getString(2);
                            if (string != null && string.trim().length() == 0) {
                                string = "匿名";
                            }
                            musicBookGroup.setAuthor("作者 : " + string);
                            musicBookGroup.setLasttime(query.getLong(3));
                            musicBookGroup.setLastSongId(query.getString(4));
                            musicBookGroup.setLastSeekTime(query.getLong(5));
                            musicBookGroup.setChapterCount(query.getInt(6));
                            arrayList.add(0, musicBookGroup);
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            e.printStackTrace();
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            throw th;
        }
        return arrayList;
    }

    public boolean b(long j) {
        boolean z = false;
        if (j != 0) {
            try {
                SQLiteDatabase a = b.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("lasttime", Long.valueOf(System.currentTimeMillis()));
                if (a.update("lbook", contentValues, "bookid= '" + j + "'", null) > 0) {
                    z = true;
                }
                if (b != null) {
                    b.c();
                }
            } catch (Exception e) {
                f.a("MusicmarkHandle", "error in updateBookLastTime : " + e.toString());
                if (b != null) {
                    b.c();
                }
            } catch (Throwable th) {
                if (b != null) {
                    b.c();
                }
            }
        }
        return z;
    }

    public boolean a(long j, long j2, String str) {
        boolean z = false;
        if (!(j == 0 || str == null || j2 < 0)) {
            try {
                SQLiteDatabase a = b.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("lasttime", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("lastseek", Long.valueOf(j2));
                contentValues.put("lastsong", str);
                if (a.update("lbook", contentValues, "bookid= '" + j + "'", null) > 0) {
                    z = true;
                }
                if (b != null) {
                    b.c();
                }
            } catch (Exception e) {
                f.a("MusicmarkHandle", "error in updateBook : " + e.toString());
                if (b != null) {
                    b.c();
                }
            } catch (Throwable th) {
                if (b != null) {
                    b.c();
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<com.qq.reader.framework.mark.Mark> c(long r22) {
        /*
        r21 = this;
        monitor-enter(r21);
        r11 = 0;
        r10 = 0;
        r2 = b;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r2 = r2.a();	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 13;
        r4 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 0;
        r5 = "path";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 1;
        r5 = "all_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 2;
        r5 = "chapter_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 3;
        r5 = "name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 4;
        r5 = "chapter_name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 5;
        r5 = "read_all_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 6;
        r5 = "read_cur_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 7;
        r5 = "date";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 8;
        r5 = "description";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 9;
        r5 = "author";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 10;
        r5 = "time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 11;
        r5 = "chapter_size";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = 12;
        r5 = "type";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r3 = "lchapter";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r5.<init>();	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r6 = "all_id= '";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r0 = r22;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r6 = "'";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "chapter_id";
        r14 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0120, all -> 0x0139 }
        if (r14 == 0) goto L_0x0159;
    L_0x008c:
        r2 = r14.getCount();	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
        if (r2 <= 0) goto L_0x0159;
    L_0x0092:
        r15 = new java.util.ArrayList;	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
        r15.<init>();	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
        r2 = r14.moveToFirst();	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        if (r2 == 0) goto L_0x010f;
    L_0x009d:
        r2 = 0;
        r3 = r14.getString(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 1;
        r6 = r14.getLong(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 2;
        r8 = r14.getLong(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 3;
        r4 = r14.getString(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 4;
        r5 = r14.getString(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 5;
        r10 = r14.getString(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 6;
        r12 = r14.getLong(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2 = 7;
        r2 = r14.getString(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r11 = 8;
        r16 = r14.getString(r11);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r11 = 9;
        r17 = r14.getString(r11);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r11 = 10;
        r18 = r14.getLong(r11);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r11 = 11;
        r11 = r14.getString(r11);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r20 = 12;
        r0 = r20;
        r20 = r14.getInt(r0);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        if (r3 == 0) goto L_0x0109;
    L_0x00e7:
        if (r4 == 0) goto L_0x0109;
    L_0x00e9:
        if (r2 == 0) goto L_0x0109;
    L_0x00eb:
        r2 = 3;
        r0 = r20;
        if (r0 != r2) goto L_0x0109;
    L_0x00f0:
        r2 = new com.qq.reader.plugin.audiobook.MusicDownloadMark;	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r2.<init>(r3, r4, r5, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r0 = r16;
        r3 = r2.setDescriptionStr(r0);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r0 = r17;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r0 = r18;
        r3.setReadTime(r0);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        r15.add(r2);	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
    L_0x0109:
        r2 = r14.moveToNext();	 Catch:{ Exception -> 0x0154, all -> 0x0149 }
        if (r2 != 0) goto L_0x009d;
    L_0x010f:
        r2 = r15;
    L_0x0110:
        if (r14 == 0) goto L_0x0115;
    L_0x0112:
        r14.close();	 Catch:{ all -> 0x0136 }
    L_0x0115:
        r3 = b;	 Catch:{ all -> 0x0136 }
        if (r3 == 0) goto L_0x011e;
    L_0x0119:
        r3 = b;	 Catch:{ all -> 0x0136 }
        r3.c();	 Catch:{ all -> 0x0136 }
    L_0x011e:
        monitor-exit(r21);
        return r2;
    L_0x0120:
        r2 = move-exception;
        r3 = r2;
        r4 = r10;
        r2 = r11;
    L_0x0124:
        r3.printStackTrace();	 Catch:{ all -> 0x014c }
        if (r4 == 0) goto L_0x012c;
    L_0x0129:
        r4.close();	 Catch:{ all -> 0x0136 }
    L_0x012c:
        r3 = b;	 Catch:{ all -> 0x0136 }
        if (r3 == 0) goto L_0x011e;
    L_0x0130:
        r3 = b;	 Catch:{ all -> 0x0136 }
        r3.c();	 Catch:{ all -> 0x0136 }
        goto L_0x011e;
    L_0x0136:
        r2 = move-exception;
        monitor-exit(r21);
        throw r2;
    L_0x0139:
        r2 = move-exception;
    L_0x013a:
        if (r10 == 0) goto L_0x013f;
    L_0x013c:
        r10.close();	 Catch:{ all -> 0x0136 }
    L_0x013f:
        r3 = b;	 Catch:{ all -> 0x0136 }
        if (r3 == 0) goto L_0x0148;
    L_0x0143:
        r3 = b;	 Catch:{ all -> 0x0136 }
        r3.c();	 Catch:{ all -> 0x0136 }
    L_0x0148:
        throw r2;	 Catch:{ all -> 0x0136 }
    L_0x0149:
        r2 = move-exception;
        r10 = r14;
        goto L_0x013a;
    L_0x014c:
        r2 = move-exception;
        r10 = r4;
        goto L_0x013a;
    L_0x014f:
        r2 = move-exception;
        r3 = r2;
        r4 = r14;
        r2 = r11;
        goto L_0x0124;
    L_0x0154:
        r2 = move-exception;
        r3 = r2;
        r4 = r14;
        r2 = r15;
        goto L_0x0124;
    L_0x0159:
        r2 = r11;
        goto L_0x0110;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.audiobook.i.c(long):java.util.ArrayList<com.qq.reader.framework.mark.Mark>");
    }

    private ArrayList<Mark> d() {
        Exception e;
        Cursor cursor;
        Throwable th;
        ArrayList<Mark> arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            Cursor query = b.a().query("lchapter", new String[]{"path", "all_id", "chapter_id", "name", "chapter_name", "read_all_time", "read_cur_time", MessageKey.MSG_DATE, SocialConstants.PARAM_COMMENT, "author", "time", "chapter_size", "type"}, null, null, "all_id,chapter_id", null, "chapter_id");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        long j = query.getLong(1);
                        long j2 = query.getLong(2);
                        String string2 = query.getString(3);
                        String string3 = query.getString(4);
                        String string4 = query.getString(5);
                        long j3 = query.getLong(6);
                        String string5 = query.getString(7);
                        String string6 = query.getString(8);
                        String string7 = query.getString(9);
                        long j4 = query.getLong(10);
                        String string8 = query.getString(11);
                        int i = query.getInt(12);
                        if (!(string == null || string2 == null || string5 == null || i != 3)) {
                            Mark musicDownloadMark = new MusicDownloadMark(string, string2, string3, j, j2, string4, string8, j3);
                            musicDownloadMark.setDescriptionStr(string6).setAuthor(string7).setReadTime(j4);
                            arrayList.add(musicDownloadMark);
                        }
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                if (b != null) {
                    b.c();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = query;
                try {
                    f.b("DB", "getAllAutoMarkDB with exception: " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (b != null) {
                    b.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            f.b("DB", "getAllAutoMarkDB with exception: " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            if (b != null) {
                b.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (b != null) {
                b.c();
            }
            throw th;
        }
        return arrayList;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists lchapter (_id integer primary key autoincrement,path text not null,all_id  long default 0,chapter_id  long default 0,name text,chapter_name text,read_all_time text not null,read_cur_time long default 0,date text,description text,author text,time text,chapter_size text,type integer default 3);");
        sQLiteDatabase.execSQL("create table if not exists lbook (_id integer primary key autoincrement,bookid  long default 0,bookname text,bookauthor text,lasttime long default 0,lastsong text,lastseek long default 0,chaptercount int default 0);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        Throwable th;
        if (i == 1) {
            Cursor query;
            try {
                long g;
                sQLiteDatabase.execSQL("alter table musicbook rename to lchapter");
                sQLiteDatabase.execSQL("create table if not exists lbook (_id integer primary key autoincrement,bookid  long default 0,bookname text,bookauthor text,lasttime long default 0,lastsong long default 0,lastseek long default 0,chaptercount int default 0);");
                String[] strArr = new String[]{"all_id", "name", "author"};
                SongInfo c = n.a().c();
                long b = n.a().b();
                if (c != null) {
                    g = c.g();
                } else {
                    g = -1;
                }
                query = sQLiteDatabase.query("lchapter", strArr, null, null, "all_id", null, "time");
                if (query != null) {
                    try {
                        if (query.getCount() > 0 && query.moveToFirst()) {
                            do {
                                ContentValues contentValues = new ContentValues();
                                long j = query.getLong(0);
                                contentValues.put("bookid", Long.valueOf(j));
                                contentValues.put("bookname", query.getString(1));
                                contentValues.put("bookauthor", query.getString(2));
                                if (g == j) {
                                    contentValues.put("lasttime", Long.valueOf(System.currentTimeMillis()));
                                    contentValues.put("lastsong", Long.valueOf(c.g()));
                                    contentValues.put("lastseek", Long.valueOf(b));
                                }
                                sQLiteDatabase.insert("lbook", null, contentValues);
                            } while (query.moveToNext());
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    private i() {
        b = new a(this, a.au, null, 2);
    }
}
