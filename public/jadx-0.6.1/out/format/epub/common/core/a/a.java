package format.epub.common.core.a;

/* compiled from: ZLDTDParser */
final class a {
    a() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.io.InputStream r14, java.util.HashMap<java.lang.String, char[]> r15) throws java.io.IOException {
        /*
        r13 = this;
        r9 = new java.io.InputStreamReader;
        r0 = "us-ascii";
        r9.<init>(r14, r0);
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = new char[r0];
        r3 = 0;
        r2 = "";
        r1 = "";
        r0 = 0;
    L_0x0014:
        r10 = r9.read(r4);
        if (r10 > 0) goto L_0x001e;
    L_0x001a:
        r9.close();
    L_0x001d:
        return;
    L_0x001e:
        r5 = r4.length;
        if (r10 >= r5) goto L_0x0025;
    L_0x0021:
        r4 = format.epub.common.utils.j.a(r4, r10, r10);
    L_0x0025:
        r5 = -1;
        r12 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r12;
    L_0x002b:
        switch(r3) {
            case 0: goto L_0x002f;
            case 1: goto L_0x0039;
            case 2: goto L_0x0046;
            case 3: goto L_0x016f;
            case 4: goto L_0x017a;
            case 5: goto L_0x0187;
            case 6: goto L_0x0052;
            case 7: goto L_0x005c;
            case 8: goto L_0x0067;
            case 9: goto L_0x008a;
            case 10: goto L_0x0095;
            case 11: goto L_0x0164;
            default: goto L_0x002e;
        };
    L_0x002e:
        goto L_0x002b;
    L_0x002f:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 60: goto L_0x0037;
            default: goto L_0x0036;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0036:
        goto L_0x002f;
    L_0x0037:
        r3 = 1;
        goto L_0x002b;
    L_0x0039:
        r5 = r5 + 1;
        r3 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r3) {
            case 33: goto L_0x0044;
            default: goto L_0x0040;
        };
    L_0x0040:
        r9.close();
        goto L_0x001d;
    L_0x0044:
        r3 = 2;
        goto L_0x002b;
    L_0x0046:
        r5 = r5 + 1;
        r3 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r3) {
            case 45: goto L_0x004e;
            case 69: goto L_0x0050;
            default: goto L_0x004d;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x004d:
        goto L_0x0040;
    L_0x004e:
        r3 = 3;
        goto L_0x002b;
    L_0x0050:
        r3 = 6;
        goto L_0x002b;
    L_0x0052:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 8: goto L_0x005a;
            case 9: goto L_0x005a;
            case 10: goto L_0x005a;
            case 11: goto L_0x005a;
            case 12: goto L_0x005a;
            case 13: goto L_0x005a;
            case 32: goto L_0x005a;
            default: goto L_0x0059;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0059:
        goto L_0x0052;
    L_0x005a:
        r3 = 7;
        goto L_0x002b;
    L_0x005c:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 8: goto L_0x005c;
            case 9: goto L_0x005c;
            case 10: goto L_0x005c;
            case 11: goto L_0x005c;
            case 12: goto L_0x005c;
            case 13: goto L_0x005c;
            case 32: goto L_0x005c;
            default: goto L_0x0063;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0063:
        r3 = 8;
        r2 = r5;
        goto L_0x002b;
    L_0x0067:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 8: goto L_0x006f;
            case 9: goto L_0x006f;
            case 10: goto L_0x006f;
            case 11: goto L_0x006f;
            case 12: goto L_0x006f;
            case 13: goto L_0x006f;
            case 32: goto L_0x006f;
            default: goto L_0x006e;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x006e:
        goto L_0x0067;
    L_0x006f:
        r3 = 9;
        r6 = new java.lang.StringBuilder;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6.<init>();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = new java.lang.String;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r8 = r5 - r2;
        r7.<init>(r4, r2, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r1 = r6.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        goto L_0x002b;
    L_0x008a:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 8: goto L_0x008a;
            case 9: goto L_0x008a;
            case 10: goto L_0x008a;
            case 11: goto L_0x008a;
            case 12: goto L_0x008a;
            case 13: goto L_0x008a;
            case 32: goto L_0x008a;
            default: goto L_0x0091;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0091:
        r3 = 10;
        r2 = r5;
        goto L_0x002b;
    L_0x0095:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 8: goto L_0x009d;
            case 9: goto L_0x009d;
            case 10: goto L_0x009d;
            case 11: goto L_0x009d;
            case 12: goto L_0x009d;
            case 13: goto L_0x009d;
            case 32: goto L_0x009d;
            case 62: goto L_0x00bf;
            default: goto L_0x009c;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x009c:
        goto L_0x0095;
    L_0x009d:
        r3 = 11;
        r6 = new java.lang.StringBuilder;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6.<init>();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r0);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = new java.lang.String;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r8 = r5 - r2;
        r7.<init>(r4, r2, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r0 = r6.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r1 = "";
        r0 = "";
        goto L_0x002b;
    L_0x00bf:
        r3 = 0;
        r6 = new java.lang.StringBuilder;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6.<init>();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r0);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = new java.lang.String;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r8 = r5 - r2;
        r7.<init>(r4, r2, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = r6.append(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r0 = r6.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r11 = r0.length();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = 2;
        if (r11 <= r6) goto L_0x012c;
    L_0x00df:
        r6 = 0;
        r6 = r0.charAt(r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = 34;
        if (r6 != r7) goto L_0x012c;
    L_0x00e8:
        r6 = r11 + -1;
        r6 = r0.charAt(r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = 34;
        if (r6 != r7) goto L_0x012c;
    L_0x00f2:
        r6 = 1;
        r7 = r11 + -1;
        r0 = r0.substring(r6, r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r6 = "&#";
        r6 = r0.startsWith(r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        if (r6 == 0) goto L_0x014a;
    L_0x0102:
        r6 = ";";
        r6 = r0.endsWith(r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        if (r6 == 0) goto L_0x014a;
    L_0x010b:
        r7 = 0;
        r6 = 2;
        r6 = r0.charAt(r6);	 Catch:{ NumberFormatException -> 0x01a6 }
        r8 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r6 != r8) goto L_0x0134;
    L_0x0115:
        r6 = 3;
        r7 = r11 + -3;
        r6 = r0.substring(r6, r7);	 Catch:{ NumberFormatException -> 0x01a6 }
        r7 = 16;
        r6 = java.lang.Integer.parseInt(r6, r7);	 Catch:{ NumberFormatException -> 0x01a6 }
    L_0x0122:
        r7 = 1;
        r7 = new char[r7];	 Catch:{ NumberFormatException -> 0x01a6 }
        r8 = 0;
        r6 = (char) r6;	 Catch:{ NumberFormatException -> 0x01a6 }
        r7[r8] = r6;	 Catch:{ NumberFormatException -> 0x01a6 }
        r15.put(r1, r7);	 Catch:{ NumberFormatException -> 0x01a6 }
    L_0x012c:
        r1 = "";
        r0 = "";
        goto L_0x002b;
    L_0x0134:
        r6 = 2;
        r12 = r6;
        r6 = r7;
        r7 = r12;
    L_0x0138:
        r8 = r11 + -3;
        if (r7 >= r8) goto L_0x0122;
    L_0x013c:
        r6 = r6 * 10;
        r8 = r0.charAt(r7);	 Catch:{ NumberFormatException -> 0x01a6 }
        r8 = r8 + -48;
        r8 = r8 + r6;
        r6 = r7 + 1;
        r7 = r6;
        r6 = r8;
        goto L_0x0138;
    L_0x014a:
        r6 = r11 + -2;
        r6 = new char[r6];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r7 = 0;
        r8 = r11 + -2;
        r11 = 0;
        r0.getChars(r7, r8, r6, r11);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        r15.put(r1, r6);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        goto L_0x012c;
    L_0x0159:
        r5 = move-exception;
        switch(r3) {
            case 8: goto L_0x0194;
            case 9: goto L_0x015d;
            case 10: goto L_0x019d;
            default: goto L_0x015d;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x015d:
        r12 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0014;
    L_0x0164:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 62: goto L_0x016c;
            default: goto L_0x016b;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x016b:
        goto L_0x0164;
    L_0x016c:
        r3 = 0;
        goto L_0x002b;
    L_0x016f:
        r5 = r5 + 1;
        r6 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r6) {
            case 45: goto L_0x0177;
            default: goto L_0x0176;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0176:
        goto L_0x016f;
    L_0x0177:
        r3 = 4;
        goto L_0x002b;
    L_0x017a:
        r5 = r5 + 1;
        r3 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r3) {
            case 45: goto L_0x0184;
            default: goto L_0x0181;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
    L_0x0181:
        r3 = 3;
        goto L_0x002b;
    L_0x0184:
        r3 = 5;
        goto L_0x002b;
    L_0x0187:
        r5 = r5 + 1;
        r3 = r4[r5];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0159 }
        switch(r3) {
            case 62: goto L_0x0191;
            default: goto L_0x018e;
        };
    L_0x018e:
        r3 = 3;
        goto L_0x002b;
    L_0x0191:
        r3 = 0;
        goto L_0x002b;
    L_0x0194:
        r1 = new java.lang.String;
        r5 = r10 - r2;
        r1.<init>(r4, r2, r5);
        r2 = 0;
        goto L_0x015d;
    L_0x019d:
        r0 = new java.lang.String;
        r5 = r10 - r2;
        r0.<init>(r4, r2, r5);
        r2 = 0;
        goto L_0x015d;
    L_0x01a6:
        r6 = move-exception;
        goto L_0x012c;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.common.core.a.a.a(java.io.InputStream, java.util.HashMap):void");
    }
}
