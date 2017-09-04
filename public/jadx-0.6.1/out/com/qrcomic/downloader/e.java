package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qrcomic.a.a;
import com.qrcomic.entity.f;
import com.qrcomic.entity.i;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* compiled from: QRComicDownloaderObserver */
public class e implements a {
    public void a(int i, boolean z, Object obj) {
        u uVar;
        if (obj instanceof u) {
            uVar = (u) obj;
            String a = b.a().b().a();
            f fVar = uVar.f;
            long j = uVar.r.get();
            int p = uVar.p();
            j = Math.max(j, uVar.u);
            p = Math.max(p, uVar.t);
            i iVar = new i();
            iVar.g = j;
            iVar.h = uVar.q.get();
            iVar.f = uVar.c;
            iVar.e = uVar.d;
            iVar.b = fVar.a;
            iVar.c = fVar.b;
            iVar.l = fVar.f;
            iVar.j = com.qrcomic.util.e.a();
            iVar.j = System.currentTimeMillis();
            iVar.a = a;
            iVar.d = uVar.n();
            iVar.i = p;
            iVar.k = fVar.d;
            iVar.n = uVar.s;
            uVar.f.r = iVar;
            uVar.f.s = true;
        } else {
            uVar = null;
        }
        HashMap hashMap;
        switch (i) {
            case 0:
                if (uVar != null) {
                    b(uVar.f, uVar.c, uVar.d);
                    return;
                }
                return;
            case 1:
                if (uVar != null) {
                    a(uVar.f, uVar.c, uVar.d);
                    return;
                }
                return;
            case 2:
                if (uVar != null) {
                    a(uVar.f);
                    break;
                }
                break;
            case 3:
                break;
            case 4:
                if (uVar != null) {
                    f fVar2 = uVar.f;
                    a(fVar2, fVar2.d);
                    return;
                }
                return;
            case 5:
                if (uVar != null) {
                    a(uVar.f, z);
                    return;
                }
                return;
            case 6:
                if (uVar != null) {
                    b(uVar.f, z);
                    return;
                }
                return;
            case 7:
                if (obj instanceof ArrayList) {
                    try {
                        a((ArrayList) obj, z);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                a(null, false);
                return;
            case 8:
                if (obj instanceof LinkedList) {
                    try {
                        a((LinkedList) obj, false);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                a(null, false);
                return;
            case 9:
                if (obj instanceof HashMap) {
                    try {
                        a((HashMap) obj, true);
                        return;
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        return;
                    }
                }
                a(null, false);
                return;
            case 10:
                if (obj instanceof ArrayList) {
                    try {
                        b((ArrayList) obj, true);
                        return;
                    } catch (Exception e222) {
                        e222.printStackTrace();
                        return;
                    }
                }
                b(null, false);
                return;
            case 11:
                if (obj instanceof HashMap) {
                    hashMap = (HashMap) obj;
                    a(new ArrayList((List) hashMap.get("comicIds")), ((Long) hashMap.get("removeSpaceSum")).longValue(), z);
                    return;
                }
                return;
            case 12:
                if (obj instanceof HashMap) {
                    b((HashMap) obj, z);
                    return;
                }
                return;
            case 13:
                if (obj instanceof HashMap) {
                    hashMap = (HashMap) obj;
                    if (hashMap.containsKey(ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID)) {
                        String str = (String) hashMap.get(ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID);
                        if (TextUtils.isEmpty(str)) {
                            a(hashMap, false, null);
                            return;
                        } else {
                            a(hashMap, z, d.b().d(str));
                            return;
                        }
                    }
                    a(hashMap, false, null);
                    return;
                }
                return;
            case 14:
                if (obj instanceof v) {
                    a((v) obj, z);
                    return;
                }
                return;
            case 15:
                if (z && (obj instanceof f)) {
                    f fVar3 = (f) obj;
                    a(fVar3.a, fVar3.b, fVar3.c);
                    return;
                }
                return;
            case 16:
                a();
                return;
            default:
                return;
        }
        if (uVar != null) {
            int i2 = uVar.o.get();
            long j2 = uVar.s;
            if (i2 > 0) {
                f fVar4 = uVar.f;
                i iVar2 = fVar4.r;
                a(fVar4, iVar2.i, iVar2.g, j2);
            }
        }
    }

    public void b(f fVar, int i, String str) {
    }

    public void a(f fVar, int i, String str) {
    }

    public void a(f fVar) {
    }

    public void a(f fVar, int i, long j, long j2) {
    }

    public void a(f fVar, long j) {
    }

    public void a(f fVar, boolean z) {
    }

    public void b(f fVar, boolean z) {
    }

    public void a(ArrayList<f> arrayList, boolean z) {
    }

    public void a(LinkedList<f> linkedList, boolean z) {
    }

    public void a(HashMap<String, f> hashMap, boolean z) {
    }

    public void b(ArrayList<c> arrayList, boolean z) {
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onGetDownloadComic success=" + z);
            if (arrayList != null) {
                stringBuilder.append(",comics size=" + arrayList.size());
            }
            g.b("QRComicDownloaderObserver", g.d, stringBuilder.toString());
        }
    }

    public void a(ArrayList<String> arrayList, long j, boolean z) {
    }

    public void b(HashMap<String, v> hashMap, boolean z) {
    }

    public void a(HashMap<String, String> hashMap, boolean z, v vVar) {
    }

    public void a(v vVar, boolean z) {
        if (g.a()) {
            g.b("QRComicDownloaderObserver", g.d, "onComicProgress comicStatus: " + vVar.toString());
        }
    }

    public void a(boolean z, int i, int i2) {
        if (g.a()) {
            g.b("QRComicDownloaderObserver", g.d, "onComicNewOrNumChange showRedPoint=" + z + ",downloadComicNum=" + i + ",unFinishedComicNum=" + i2);
        }
    }

    public void a() {
        if (g.a()) {
            g.b("QRComicDownloaderObserver", g.d, "onNetOutOfWifi ");
        }
    }
}
