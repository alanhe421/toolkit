package com.qrcomic.manager;

import android.os.Bundle;
import com.qrcomic.a.h;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.activity.reader.a.b;
import com.qrcomic.activity.reader.a.e;
import com.qrcomic.downloader.j;
import com.qrcomic.e.b.d;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.a;
import com.qrcomic.entity.f;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: QRComicSectionPreloadManager */
public class c implements d {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    com.qrcomic.e.c e = new com.qrcomic.e.c(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            this.a.f.b(this.a.e);
            if (this.a.g == null) {
                return;
            }
            if (obj == null || !(obj instanceof d)) {
                if (g.a()) {
                    g.a("QRComicSectionPreloadManager", g.d, "onQueryUserBuyInfoSuccess, QueryUserBuyInfoPac is null");
                }
                this.a.g.a(null);
                return;
            }
            this.a.g.a((d) obj);
        }

        public void b(Object obj) {
            this.a.f.b(this.a.e);
            if (this.a.g == null) {
                return;
            }
            if (obj == null || !(obj instanceof com.qrcomic.e.b.c)) {
                if (g.a()) {
                    g.a("QRComicSectionPreloadManager", g.d, "onLoadPayedSectionsListenerFail, QueryUserBuyInfoError is null");
                }
                this.a.g.a(null);
                return;
            }
            if (g.a()) {
                StringBuilder stringBuilder = new StringBuilder();
                com.qrcomic.e.b.c cVar = (com.qrcomic.e.b.c) obj;
                stringBuilder.append("onQueryUserBuyInfoFailure, errorcode is " + cVar.b + ", comicId is ");
                Iterator it = cVar.a.iterator();
                while (it.hasNext()) {
                    stringBuilder.append(((QRComicBuyReqInfo) it.next()).a + ",");
                }
                g.a("QRComicSectionPreloadManager", g.d, stringBuilder.toString());
            }
            this.a.g.a((com.qrcomic.e.b.c) obj);
        }
    };
    private h f;
    private com.qrcomic.activity.reader.a.c g;
    private QRComicManager h;
    private com.qrcomic.downloader.d i;
    private a j;
    private j k;
    private j l;

    public c(h hVar) {
        this.f = hVar;
        this.h = (QRComicManager) hVar.a(1);
        this.i = com.qrcomic.downloader.d.b();
        this.j = (a) b.a().b().a(5);
    }

    public void a(Object obj, String str, int i, int i2, int i3, b bVar, int i4, boolean z, boolean z2) {
        final int[] iArr = new int[]{0};
        final List arrayList = new ArrayList();
        final b bVar2 = bVar;
        final boolean z3 = z;
        this.h.a(obj, str, i, i2, i3, this.f.a(), true, i4, z2, (com.qrcomic.manager.QRComicManager.c) new com.qrcomic.manager.QRComicManager.c(this) {
            final /* synthetic */ c e;

            public void a(a aVar, int i) {
                int[] iArr = iArr;
                iArr[0] = iArr[0] + 1;
                if (aVar == null && g.a()) {
                    g.a("QRComicSectionPreloadManager", g.d, "comic info is null");
                }
                if (!(aVar == null || aVar.p == null || aVar.p.size() <= 0)) {
                    arrayList.addAll(aVar.p);
                }
                if (bVar2 != null && iArr[0] == i) {
                    bVar2.a(aVar, arrayList, z3);
                }
            }

            public void a(List<ComicSectionPicInfo> list, String str, String str2) {
            }
        }, false);
    }

    public void a(String str, String str2, Boolean bool, boolean z, final com.qrcomic.activity.reader.a.d dVar) {
        this.h.a(str, str2, bool.booleanValue(), z, new com.qrcomic.manager.QRComicManager.c(this) {
            final /* synthetic */ c b;

            public void a(a aVar, int i) {
            }

            public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                if (list == null) {
                    if (g.a()) {
                        g.a("QRComicSectionPreloadManager", g.d, "picInfoList is null : comicId is " + str + ", sectionId is " + str2);
                    }
                    if (dVar != null) {
                        dVar.a(str, str2);
                    }
                } else if (dVar != null) {
                    dVar.a(list, str, str2);
                }
            }
        }, false);
    }

    public void a(com.qrcomic.activity.reader.a aVar, b bVar, boolean z) {
        int i;
        int i2 = 3;
        int i3 = aVar.E;
        int size = aVar.u.size();
        String str = aVar.j;
        if (z) {
            if (i3 <= 0 || !aVar.a(i3 - 1)) {
                i2 = 0;
            } else {
                i2 = 2;
            }
            if (i3 + 1 >= size || !aVar.a(i3 + 1)) {
                i = 1;
            } else {
                i2 |= 1;
                i = 1;
            }
        } else {
            i = Math.min(3, i3);
        }
        a(aVar.i, str, 0, i, i2, bVar, i3, true, z);
    }

    public void a(com.qrcomic.activity.reader.a aVar, int i, boolean z, b bVar) {
        if (aVar.E - i < 2 && i >= 1) {
            int i2 = i - 1;
            a(aVar.n, (String) aVar.u.get(i - 1), i2, Math.min(3, i2), 2, bVar, aVar.E, false, z);
        }
    }

    public void b(com.qrcomic.activity.reader.a aVar, int i, boolean z, b bVar) {
        int i2 = i - aVar.E;
        int size = aVar.u.size();
        if (i2 < 2 && i < size - 1) {
            int i3 = i + 1;
            a(aVar.n, (String) aVar.u.get(i + 1), i3, Math.min(3, size - i3), 1, bVar, aVar.E, false, z);
        }
    }

    public void a(com.qrcomic.activity.reader.a aVar, final e eVar) {
        this.a = 0;
        this.b = 1;
        int i = aVar.C;
        List list = aVar.r;
        List list2 = aVar.s;
        List list3 = aVar.t;
        LinkedList linkedList = new LinkedList();
        final ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) list.get(i);
        linkedList.add(comicSectionPicInfo);
        if (i < list.size() - 1) {
            linkedList.add(list.get(i + 1));
        } else if (list3 != null) {
            linkedList.add(list3.get(0));
        }
        if (i > 0) {
            linkedList.add(list.get(i - 1));
        } else if (list2 != null && list2.size() > 0) {
            linkedList.add(list2.get(list2.size() - 1));
        }
        this.l = new j(this) {
            final /* synthetic */ c c;

            public void a(ComicSectionPicInfo comicSectionPicInfo, long j, long j2) {
                c cVar = this.c;
                cVar.a++;
                if (this.c.a == this.c.b) {
                    eVar.a();
                }
                if (comicSectionPicInfo != null && comicSectionPicInfo.bitmap != null && !comicSectionPicInfo.bitmap.isRecycled()) {
                    comicSectionPicInfo.mState = 0;
                }
            }

            public void a(ComicSectionPicInfo comicSectionPicInfo, int i, String str) {
                eVar.a(comicSectionPicInfo);
                if (comicSectionPicInfo != null) {
                    comicSectionPicInfo.mState = 1;
                    if (g.a() && comicSectionPicInfo != null) {
                        g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtStart : request pic fail, sectionId is " + comicSectionPicInfo.sectionId + ",comicId is " + comicSectionPicInfo.comicId + ",  picId is " + comicSectionPicInfo.picId + ", curPicId is:" + comicSectionPicInfo.picId + ", errorCode is " + i + ", errMsg is " + str);
                    }
                }
                c cVar = this.c;
                cVar.a++;
                if (this.c.a == this.c.b && comicSectionPicInfo != null && comicSectionPicInfo.bitmap != null && !comicSectionPicInfo.bitmap.isRecycled()) {
                    eVar.a();
                }
            }

            public void a(ComicSectionPicInfo comicSectionPicInfo, String str) {
            }
        };
        if (!a(aVar, 3, linkedList, this.l) && eVar != null) {
            eVar.a();
        }
    }

    public void b(com.qrcomic.activity.reader.a aVar, final e eVar) {
        this.c = 0;
        this.d = 0;
        List list = aVar.r;
        if (list != null) {
            LinkedList linkedList = new LinkedList();
            int i = aVar.C;
            final ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) list.get(i);
            a(linkedList, comicSectionPicInfo);
            if (i + 1 < list.size()) {
                a(linkedList, (ComicSectionPicInfo) list.get(i + 1));
            }
            if (i > 0) {
                a(linkedList, (ComicSectionPicInfo) list.get(i - 1));
            } else {
                a(aVar, linkedList);
            }
            if (linkedList.size() != 0 || eVar == null) {
                this.k = new j(this) {
                    final /* synthetic */ c c;

                    public void a(ComicSectionPicInfo comicSectionPicInfo, long j, long j2) {
                        c cVar = this.c;
                        cVar.c++;
                        if (!(comicSectionPicInfo == null || comicSectionPicInfo == null || !g.a())) {
                            g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtSectionChanged : request comics success, sectionId is " + comicSectionPicInfo.sectionId + ", total number is " + this.c.d + " current number is " + this.c.c + ", picId is" + comicSectionPicInfo.picId);
                        }
                        if (this.c.c == this.c.d && eVar != null) {
                            eVar.a();
                        }
                        if (comicSectionPicInfo != null && comicSectionPicInfo.bitmap != null && !comicSectionPicInfo.bitmap.isRecycled()) {
                            comicSectionPicInfo.mState = 0;
                        }
                    }

                    public void a(ComicSectionPicInfo comicSectionPicInfo, int i, String str) {
                        if (comicSectionPicInfo != null) {
                            if (eVar != null) {
                                eVar.a(comicSectionPicInfo);
                            }
                            comicSectionPicInfo.mState = 1;
                            if (g.a()) {
                                g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtSectionChanged : request pic fail, sectionId is " + comicSectionPicInfo.sectionId + ",comicId is " + comicSectionPicInfo.comicId + ",  picId is " + comicSectionPicInfo.picId + ", curPicId is:" + comicSectionPicInfo.picId + ", errCode is " + i + ", errMsg is " + str);
                            }
                        }
                        c cVar = this.c;
                        cVar.c++;
                        if (this.c.a == this.c.b && comicSectionPicInfo != null && comicSectionPicInfo.bitmap != null && !comicSectionPicInfo.bitmap.isRecycled() && eVar != null) {
                            eVar.a();
                        }
                    }

                    public void a(ComicSectionPicInfo comicSectionPicInfo, String str) {
                    }
                };
                if (!a(aVar, 2, linkedList, this.k) && eVar != null) {
                    eVar.a();
                    return;
                }
                return;
            }
            eVar.a();
        } else if (g.a()) {
            g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtSectionChanged comicPicInfoList is null");
        }
    }

    public void a(com.qrcomic.activity.reader.a aVar, ComicSectionPicInfo comicSectionPicInfo, int i, List<ComicSectionPicInfo> list, j jVar, int i2) {
        if (comicSectionPicInfo != null && list != null && i < list.size()) {
            LinkedList linkedList = new LinkedList();
            a(linkedList, comicSectionPicInfo);
            if (aVar != null && aVar.h) {
                int i3;
                aVar.h = false;
                int i4 = this.f.c(this.f.b())[1];
                int i5 = comicSectionPicInfo.dstHeight;
                int i6 = i + 1;
                if (aVar.r != null) {
                    i3 = i5;
                    i5 = i6;
                    i6 = aVar.r.size();
                } else {
                    i3 = i5;
                    i5 = i6;
                    i6 = Integer.MIN_VALUE;
                }
                while (i3 < i4 && i5 < r1) {
                    ComicSectionPicInfo comicSectionPicInfo2 = (ComicSectionPicInfo) aVar.r.get(i5);
                    i3 += comicSectionPicInfo2.dstHeight;
                    a(linkedList, comicSectionPicInfo2);
                    if (g.a()) {
                        g.a("QRComicSectionPreloadManager", g.d, " 找到填充满屏的图片 位置index = " + i5);
                    }
                    i5++;
                }
                if (i5 < list.size()) {
                    a(linkedList, (ComicSectionPicInfo) list.get(i5));
                } else {
                    b(aVar, linkedList);
                }
            } else if (aVar != null) {
                if (i2 == 2) {
                    if (i + 1 < list.size()) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i + 1));
                    } else {
                        b(aVar, linkedList);
                    }
                    if (i + 2 < list.size()) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i + 2));
                    }
                    if (aVar.H == 1) {
                        if (i + 3 < list.size()) {
                            a(linkedList, (ComicSectionPicInfo) list.get(i + 3));
                        }
                        if (i + 4 < list.size()) {
                            a(linkedList, (ComicSectionPicInfo) list.get(i + 4));
                        }
                    }
                    if (i - 1 >= 0) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i - 1));
                    } else {
                        a(aVar, linkedList);
                    }
                } else {
                    if (i - 2 >= 0) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i - 2));
                    }
                    if (i - 1 >= 0) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i - 1));
                        a(aVar, linkedList);
                    } else {
                        a(aVar, linkedList);
                    }
                    if (i + 1 < list.size()) {
                        a(linkedList, (ComicSectionPicInfo) list.get(i + 1));
                    } else {
                        b(aVar, linkedList);
                    }
                }
            }
            if (linkedList.size() > 0) {
                a(aVar, 1, linkedList, jVar);
            } else if (jVar != null) {
                jVar.a(comicSectionPicInfo, 0, 0);
            }
            if (g.a()) {
                g.a("QRComicSectionPreloadManager", g.d, "LOADPIC 预加载图片 ，本次提交的图片size 是 -->" + linkedList.size());
            }
        }
    }

    private void a(LinkedList<ComicSectionPicInfo> linkedList, ComicSectionPicInfo comicSectionPicInfo) {
        if (comicSectionPicInfo == null) {
            return;
        }
        if ((comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) && !linkedList.contains(comicSectionPicInfo)) {
            linkedList.add(comicSectionPicInfo);
        }
    }

    private void a(com.qrcomic.activity.reader.a aVar, LinkedList<ComicSectionPicInfo> linkedList) {
        if (aVar.s != null && aVar.s.size() > 0) {
            a((LinkedList) linkedList, (ComicSectionPicInfo) aVar.s.get(aVar.s.size() - 1));
        }
    }

    private void b(com.qrcomic.activity.reader.a aVar, LinkedList<ComicSectionPicInfo> linkedList) {
        if (aVar.t != null && aVar.t.size() > 0) {
            a((LinkedList) linkedList, (ComicSectionPicInfo) aVar.t.get(0));
        }
    }

    public void a(QRComicReadingBaseActivity qRComicReadingBaseActivity, ComicSectionPicInfo comicSectionPicInfo, j jVar) {
        List list = qRComicReadingBaseActivity.Z.r;
        if (list != null) {
            if (g.a()) {
                g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtPagerChanged picinfo is " + comicSectionPicInfo);
            }
            if (comicSectionPicInfo.sectionId.equals(((ComicSectionPicInfo) list.get(0)).sectionId)) {
                ComicSectionPicInfo comicSectionPicInfo2;
                int i = comicSectionPicInfo.index;
                LinkedList linkedList = new LinkedList();
                a(linkedList, comicSectionPicInfo);
                if (i + 1 < list.size()) {
                    comicSectionPicInfo2 = (ComicSectionPicInfo) list.get(i + 1);
                    if (comicSectionPicInfo2 != null) {
                        a(linkedList, comicSectionPicInfo2);
                    }
                } else if (qRComicReadingBaseActivity.Z.t != null && qRComicReadingBaseActivity.Z.t.size() > 0) {
                    comicSectionPicInfo2 = (ComicSectionPicInfo) qRComicReadingBaseActivity.Z.t.get(0);
                    if (comicSectionPicInfo2 != null) {
                        a(linkedList, comicSectionPicInfo2);
                    }
                    if (qRComicReadingBaseActivity.Z.t.size() > 1) {
                        comicSectionPicInfo2 = (ComicSectionPicInfo) qRComicReadingBaseActivity.Z.t.get(1);
                        if (comicSectionPicInfo2 != null) {
                            a(linkedList, comicSectionPicInfo2);
                        }
                    }
                }
                if (!qRComicReadingBaseActivity.Z.h) {
                    if (i + 2 < list.size()) {
                        comicSectionPicInfo2 = (ComicSectionPicInfo) list.get(i + 2);
                        if (comicSectionPicInfo2 != null) {
                            a(linkedList, comicSectionPicInfo2);
                        }
                    }
                    if (i + 3 < list.size()) {
                        comicSectionPicInfo2 = (ComicSectionPicInfo) list.get(i + 3);
                        if (comicSectionPicInfo2 != null) {
                            a(linkedList, comicSectionPicInfo2);
                        }
                    }
                }
                comicSectionPicInfo2 = null;
                if (i > 0) {
                    comicSectionPicInfo2 = (ComicSectionPicInfo) list.get(i - 1);
                } else if (qRComicReadingBaseActivity.Z.s != null && qRComicReadingBaseActivity.Z.s.size() > 0) {
                    comicSectionPicInfo2 = (ComicSectionPicInfo) qRComicReadingBaseActivity.Z.s.get(qRComicReadingBaseActivity.Z.s.size() - 1);
                }
                if (comicSectionPicInfo2 != null) {
                    a(linkedList, comicSectionPicInfo2);
                }
                if (linkedList.size() > 0) {
                    a(qRComicReadingBaseActivity.Z, 0, linkedList, jVar);
                } else if (jVar != null) {
                    jVar.a(comicSectionPicInfo, 0, 0);
                }
                qRComicReadingBaseActivity.Z.h = false;
            }
        } else if (g.a()) {
            g.a("QRComicSectionPreloadManager", g.d, "preloadPicsAtPagerChanged comicPicInfoList is null");
        }
    }

    public boolean a(com.qrcomic.activity.reader.a aVar, int i, LinkedList<ComicSectionPicInfo> linkedList, j jVar) {
        LinkedList linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) it.next();
            f b = aVar.b(comicSectionPicInfo.sectionId);
            if ((b != null && b.t == 0 && aVar.d(b)) || b == null) {
                linkedList2.add(comicSectionPicInfo);
            }
        }
        if (linkedList2.size() <= 0) {
            return false;
        }
        if (i == 3 || i == 2) {
            this.d = linkedList2.size();
            this.b = linkedList2.size();
        }
        this.i.a(linkedList2, jVar, false);
        return true;
    }

    public void a(String str, com.qrcomic.activity.reader.a.c cVar, Bundle bundle) {
        this.g = cVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new QRComicBuyReqInfo(str, null));
        this.f.a(this.e);
        this.h.a(arrayList, bundle, false);
    }

    public void a() {
        if (this.f != null && this.e != null) {
            this.f.b(this.e);
        }
    }
}
