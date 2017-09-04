package tencent.tls.request;

import java.util.ArrayList;

public class TLVPacker {
    private int bodyLen = 0;
    private int tlvCnt = 0;
    private ArrayList<byte[]> tlvList = new ArrayList();
    private int[] tlvs;

    public TLVPacker(int[] iArr) {
        this.tlvs = iArr;
    }

    protected byte[] loop(int i) {
        return new byte[0];
    }

    public byte[] doit() {
        /* JADX: method processing error */
/*
Error: java.lang.IndexOutOfBoundsException: bitIndex < 0: -1
	at java.util.BitSet.get(BitSet.java:623)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.usedArgAssign(CodeShrinker.java:138)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.access$300(CodeShrinker.java:43)
	at jadx.core.dex.visitors.CodeShrinker.canMoveBetweenBlocks(CodeShrinker.java:282)
	at jadx.core.dex.visitors.CodeShrinker.shrinkBlock(CodeShrinker.java:230)
	at jadx.core.dex.visitors.CodeShrinker.shrinkMethod(CodeShrinker.java:38)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkArrayForEach(LoopRegionVisitor.java:196)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkForIndexedLoop(LoopRegionVisitor.java:119)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:65)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:52)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:46)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        r2 = 0;
        r1 = r7.tlvs;
        r3 = r1.length;
        r0 = r2;
    L_0x0005:
        if (r0 >= r3) goto L_0x0025;
    L_0x0007:
        r4 = r1[r0];
        r4 = r7.loop(r4);
        r5 = r4.length;
        r6 = 4;
        if (r5 <= r6) goto L_0x0022;
    L_0x0011:
        r5 = r7.tlvCnt;
        r5 = r5 + 1;
        r7.tlvCnt = r5;
        r5 = r7.bodyLen;
        r6 = r4.length;
        r5 = r5 + r6;
        r7.bodyLen = r5;
        r5 = r7.tlvList;
        r5.add(r4);
    L_0x0022:
        r0 = r0 + 1;
        goto L_0x0005;
    L_0x0025:
        r0 = r7.bodyLen;
        r4 = new byte[r0];
        r1 = r2;
        r3 = r2;
    L_0x002b:
        r0 = r7.tlvCnt;
        if (r1 >= r0) goto L_0x0041;
    L_0x002f:
        r0 = r7.tlvList;
        r0 = r0.get(r1);
        r0 = (byte[]) r0;
        r5 = r0.length;
        java.lang.System.arraycopy(r0, r2, r4, r3, r5);
        r0 = r0.length;
        r3 = r3 + r0;
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x002b;
    L_0x0041:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: tencent.tls.request.TLVPacker.doit():byte[]");
    }

    public int getTlvCnt() {
        return this.tlvCnt;
    }
}
