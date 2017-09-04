package format.epub.view;

import java.util.ArrayList;
import java.util.List;

public final class ZLTextElementAreaArrayList extends ArrayList<h> {
    public static final int AREA_TYPE_COMMON = 1;
    public static final int AREA_TYPE_IMAGE = 2;
    public static final int AREA_TYPE_PRE = 3;
    private static final long serialVersionUID = -7880472347947563506L;
    final ArrayList<i> ElementRegions = new ArrayList();
    private int areaListType = 1;
    private List<h> mImageAreas = new ArrayList();
    private i myCurrentElementRegion;

    public void clear() {
        this.ElementRegions.clear();
        this.myCurrentElementRegion = null;
        this.areaListType = 1;
        this.mImageAreas.clear();
        super.clear();
    }

    public boolean add(h hVar) {
        if (hVar.s == 1) {
            m mVar;
            if (hVar.t == 1) {
                this.areaListType = 3;
            }
            if (hVar.n != null) {
                mVar = hVar.n.b;
            } else {
                mVar = null;
            }
            if (mVar == null || mVar.b == null) {
                if (hVar.o instanceof p) {
                    this.ElementRegions.add(new q((p) hVar.o, this, size()));
                    this.myCurrentElementRegion = null;
                    p pVar = (p) hVar.o;
                    if (pVar.l != null && pVar.l.trim().length() > 0) {
                        this.areaListType = 2;
                        this.mImageAreas.add(hVar);
                    } else if (pVar.k) {
                        this.areaListType = 2;
                        this.mImageAreas.add(hVar);
                    } else if (pVar.h()) {
                        this.areaListType = 2;
                        this.mImageAreas.add(hVar);
                    }
                } else if (!(hVar.o instanceof y) || !((y) hVar.o).a()) {
                    this.myCurrentElementRegion = null;
                } else if ((this.myCurrentElementRegion instanceof aa) && ((aa) this.myCurrentElementRegion).d == hVar.o) {
                    this.myCurrentElementRegion.a();
                } else {
                    this.myCurrentElementRegion = new aa((y) hVar.o, this, size());
                    this.ElementRegions.add(this.myCurrentElementRegion);
                }
            } else if ((this.myCurrentElementRegion instanceof o) && ((o) this.myCurrentElementRegion).d == mVar) {
                this.myCurrentElementRegion.a();
            } else {
                this.myCurrentElementRegion = new o(mVar, this, size());
                this.ElementRegions.add(this.myCurrentElementRegion);
            }
        }
        return super.add(hVar);
    }

    private boolean isActiveArea() {
        if (this.areaListType == 2 || this.areaListType == 3) {
            return true;
        }
        return false;
    }

    public h binarySearch(float f, float f2) {
        if (!isActiveArea()) {
            return null;
        }
        h hVar;
        for (h hVar2 : this.mImageAreas) {
            if (hVar2 != null && hVar2.a(f, f2)) {
                return hVar2;
            }
        }
        int i = 0;
        int size = size();
        while (i < size) {
            int i2;
            int i3 = (i + size) / 2;
            hVar2 = (h) get(i3);
            if (hVar2.i > f2) {
                i2 = i3;
                i3 = i;
            } else if (hVar2.j < f2) {
                i3++;
                i2 = size;
            } else if (hVar2.g > f) {
                i2 = i3;
                i3 = i;
            } else if (hVar2.h >= f) {
                return hVar2;
            } else {
                i3++;
                i2 = size;
            }
            size = i2;
            i = i3;
        }
        return null;
    }
}
