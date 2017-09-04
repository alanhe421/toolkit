package oicq.wlogin_sdk.tools;

import android.content.Context;

public class RSACrypt {
    protected static final String DEFAULT_PRIV_KEY = "3082025e02010002818100daaa2a418b271f3dfcf8f0a9120326d47f07618593d8d71d61a4fe987cc47740e491105bf8e68bd479bf51dfe19d3b06e12017df6d87a0f43bb82b57f59bd4220f2a3d8d68904a6ddb51197989e6e82512d8d8fa6c41b755a8ca962595d3e1e1be7ea01677249be4794cd7c6682d611c1bd81f0a16231fb83517515b94d13e5d02030100010281806bbfca4ebde92b45fa7018f6d6ff6294f77b859cb2fbf9146b5748758f95a845fbdb57ba5a6e109d44d8f7d9606d7ff6a5dc90a6f26c10ee08b779f43ffce78c6fc0feb8a063885e1b9ee6f3615b8b850e6b89365fe7037de6928e3ca2b93c55f60fff2873ce9a88254c4c553aece69c311ddd37bb6dfc8c45399144a59f25e9024100f12a24798dfc2d56e719df7a8f9f870037007ac187c1a76a88e4749347cbc270ea54491b27309d02d0d0e1bb566a3f4972c286193e34b3863962a103ab2e9063024100e81db1b9e333baa72636599b792f7ae2fc06593a94851bd15c5d209c5d5d2836ecf2309c52426ca297475bfd8920e5fade8765afd9f6822ee4b7e333d234523f024100e356ead37bb981b42e5f0180b3eb9a83e5559a62ddeafc3b3d98bf1c27ce3919e08c5bee30df6ee3bc9d6c6e01645f0c8a163dfb85dc806fc3a0ea505f0aa229024100dee10c73f2bf0c1e4de9e8370ab155ad38d49bbf4d375713bc3dcbff7902e7877e13bc2b8e2d2c051f7faccc116d5e877a3fc69b898e5348d5e3e0ad34cd7a9f024100ede9b6081428b058d2db5c7ccbef7a178d9003c547319d177a5d1d219e9727f18dbe41008198af9a01fb684b6c96c536c8fbb98532b908028c2d4dce7281aff9";
    public static final String DEFAULT_PUB_KEY = "30818902818100daaa2a418b271f3dfcf8f0a9120326d47f07618593d8d71d61a4fe987cc47740e491105bf8e68bd479bf51dfe19d3b06e12017df6d87a0f43bb82b57f59bd4220f2a3d8d68904a6ddb51197989e6e82512d8d8fa6c41b755a8ca962595d3e1e1be7ea01677249be4794cd7c6682d611c1bd81f0a16231fb83517515b94d13e5d0203010001";
    private Context _context;
    private byte[] _priv_key = new byte[0];
    private byte[] _pub_key = new byte[0];

    private native byte[] decryptdata(byte[] bArr, byte[] bArr2);

    private native byte[] encryptdata(byte[] bArr, byte[] bArr2);

    private native int genrsakey();

    public RSACrypt(Context context) {
        this._context = context;
        try {
            util.loadLibrary("wtecdh", this._context);
        } catch (UnsatisfiedLinkError e) {
        }
    }

    public byte[] get_pub_key() {
        return this._pub_key;
    }

    public void set_pub_key(byte[] bArr) {
        this._pub_key = bArr;
    }

    public byte[] get_priv_key() {
        return this._priv_key;
    }

    public void set_priv_key(byte[] bArr) {
        this._priv_key = bArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int GenRSAKey() {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6._context;
        r0 = oicq.wlogin_sdk.tools.util.get_rsa_pubkey(r0);
        r2 = r6._context;
        r2 = oicq.wlogin_sdk.tools.util.get_rsa_privkey(r2);
        if (r0 == 0) goto L_0x0028;
    L_0x000f:
        r3 = r0.length;
        if (r3 <= 0) goto L_0x0028;
    L_0x0012:
        if (r2 == 0) goto L_0x0028;
    L_0x0014:
        r3 = r2.length;
        if (r3 <= 0) goto L_0x0028;
    L_0x0017:
        r0 = r0.clone();
        r0 = (byte[]) r0;
        r6._pub_key = r0;
        r0 = r2.clone();
        r0 = (byte[]) r0;
        r6._priv_key = r0;
    L_0x0027:
        return r1;
    L_0x0028:
        r3 = oicq.wlogin_sdk.tools.RSACrypt.class;
        monitor-enter(r3);	 Catch:{ UnsatisfiedLinkError -> 0x01d1, all -> 0x0154 }
        r0 = r6._context;	 Catch:{ all -> 0x00bf }
        r0 = oicq.wlogin_sdk.tools.util.get_rsa_pubkey(r0);	 Catch:{ all -> 0x00bf }
        r2 = r6._context;	 Catch:{ all -> 0x00bf }
        r2 = oicq.wlogin_sdk.tools.util.get_rsa_privkey(r2);	 Catch:{ all -> 0x00bf }
        if (r0 == 0) goto L_0x0094;
    L_0x0039:
        r4 = r0.length;	 Catch:{ all -> 0x00bf }
        if (r4 <= 0) goto L_0x0094;
    L_0x003c:
        if (r2 == 0) goto L_0x0094;
    L_0x003e:
        r4 = r2.length;	 Catch:{ all -> 0x00bf }
        if (r4 <= 0) goto L_0x0094;
    L_0x0041:
        r0 = r0.clone();	 Catch:{ all -> 0x00bf }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x00bf }
        r6._pub_key = r0;	 Catch:{ all -> 0x00bf }
        r0 = r2.clone();	 Catch:{ all -> 0x00bf }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x00bf }
        r6._priv_key = r0;	 Catch:{ all -> 0x00bf }
        r0 = "rsa has generated";
        r2 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r2);	 Catch:{ all -> 0x00bf }
        r2 = 1;
    L_0x005b:
        monitor-exit(r3);	 Catch:{ all -> 0x01d8 }
        if (r2 != 0) goto L_0x0027;
    L_0x005e:
        r2 = oicq.wlogin_sdk.tools.RSACrypt.class;
        monitor-enter(r2);
        r0 = r6._context;	 Catch:{ all -> 0x01a4 }
        r0 = oicq.wlogin_sdk.tools.util.get_rsa_pubkey(r0);	 Catch:{ all -> 0x01a4 }
        r3 = r6._context;	 Catch:{ all -> 0x01a4 }
        r3 = oicq.wlogin_sdk.tools.util.get_rsa_privkey(r3);	 Catch:{ all -> 0x01a4 }
        if (r0 == 0) goto L_0x01a7;
    L_0x006f:
        r4 = r0.length;	 Catch:{ all -> 0x01a4 }
        if (r4 <= 0) goto L_0x01a7;
    L_0x0072:
        if (r3 == 0) goto L_0x01a7;
    L_0x0074:
        r4 = r3.length;	 Catch:{ all -> 0x01a4 }
        if (r4 <= 0) goto L_0x01a7;
    L_0x0077:
        r0 = r0.clone();	 Catch:{ all -> 0x01a4 }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x01a4 }
        r6._pub_key = r0;	 Catch:{ all -> 0x01a4 }
        r0 = r3.clone();	 Catch:{ all -> 0x01a4 }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x01a4 }
        r6._priv_key = r0;	 Catch:{ all -> 0x01a4 }
        r0 = "rsa has saved";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x01a4 }
    L_0x0090:
        monitor-exit(r2);	 Catch:{ all -> 0x01a4 }
        r0 = r1;
    L_0x0092:
        r1 = r0;
        goto L_0x0027;
    L_0x0094:
        r0 = "rsa begin";
        r2 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r2);	 Catch:{ all -> 0x00bf }
        r2 = r6.genrsakey();	 Catch:{ all -> 0x00bf }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d5 }
        r0.<init>();	 Catch:{ all -> 0x01d5 }
        r4 = "rsa end ";
        r0 = r0.append(r4);	 Catch:{ all -> 0x01d5 }
        r0 = r0.append(r2);	 Catch:{ all -> 0x01d5 }
        r0 = r0.toString();	 Catch:{ all -> 0x01d5 }
        r4 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r4);	 Catch:{ all -> 0x01d5 }
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x005b;
    L_0x00bf:
        r0 = move-exception;
        r2 = r1;
    L_0x00c1:
        monitor-exit(r3);	 Catch:{ all -> 0x01d5 }
        throw r0;	 Catch:{ UnsatisfiedLinkError -> 0x00c3, all -> 0x01c9 }
    L_0x00c3:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
    L_0x00c6:
        r2 = oicq.wlogin_sdk.tools.util.generateRSAKeyPair();	 Catch:{ all -> 0x01cf }
        if (r2 == 0) goto L_0x00e8;
    L_0x00cc:
        r3 = r2.getPublic();	 Catch:{ all -> 0x01cf }
        r3 = r3.getEncoded();	 Catch:{ all -> 0x01cf }
        r3 = oicq.wlogin_sdk.tools.util.RSAPubKeyFromJava(r3);	 Catch:{ all -> 0x01cf }
        r6._pub_key = r3;	 Catch:{ all -> 0x01cf }
        r2 = r2.getPrivate();	 Catch:{ all -> 0x01cf }
        r2 = r2.getEncoded();	 Catch:{ all -> 0x01cf }
        r2 = oicq.wlogin_sdk.tools.util.RSAPrivKeyFromJava(r2);	 Catch:{ all -> 0x01cf }
        r6._priv_key = r2;	 Catch:{ all -> 0x01cf }
    L_0x00e8:
        if (r0 != 0) goto L_0x0027;
    L_0x00ea:
        r2 = oicq.wlogin_sdk.tools.RSACrypt.class;
        monitor-enter(r2);
        r0 = r6._context;	 Catch:{ all -> 0x017f }
        r0 = oicq.wlogin_sdk.tools.util.get_rsa_pubkey(r0);	 Catch:{ all -> 0x017f }
        r3 = r6._context;	 Catch:{ all -> 0x017f }
        r3 = oicq.wlogin_sdk.tools.util.get_rsa_privkey(r3);	 Catch:{ all -> 0x017f }
        if (r0 == 0) goto L_0x0182;
    L_0x00fb:
        r4 = r0.length;	 Catch:{ all -> 0x017f }
        if (r4 <= 0) goto L_0x0182;
    L_0x00fe:
        if (r3 == 0) goto L_0x0182;
    L_0x0100:
        r4 = r3.length;	 Catch:{ all -> 0x017f }
        if (r4 <= 0) goto L_0x0182;
    L_0x0103:
        r0 = r0.clone();	 Catch:{ all -> 0x017f }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x017f }
        r6._pub_key = r0;	 Catch:{ all -> 0x017f }
        r0 = r3.clone();	 Catch:{ all -> 0x017f }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x017f }
        r6._priv_key = r0;	 Catch:{ all -> 0x017f }
        r0 = "rsa has saved";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x017f }
    L_0x011c:
        monitor-exit(r2);	 Catch:{ all -> 0x017f }
        r0 = r1;
        goto L_0x0092;
    L_0x0120:
        r1 = oicq.wlogin_sdk.tools.RSACrypt.class;
        monitor-enter(r1);
        r0 = r6._context;	 Catch:{ all -> 0x015b }
        r0 = oicq.wlogin_sdk.tools.util.get_rsa_pubkey(r0);	 Catch:{ all -> 0x015b }
        r3 = r6._context;	 Catch:{ all -> 0x015b }
        r3 = oicq.wlogin_sdk.tools.util.get_rsa_privkey(r3);	 Catch:{ all -> 0x015b }
        if (r0 == 0) goto L_0x015e;
    L_0x0131:
        r4 = r0.length;	 Catch:{ all -> 0x015b }
        if (r4 <= 0) goto L_0x015e;
    L_0x0134:
        if (r3 == 0) goto L_0x015e;
    L_0x0136:
        r4 = r3.length;	 Catch:{ all -> 0x015b }
        if (r4 <= 0) goto L_0x015e;
    L_0x0139:
        r0 = r0.clone();	 Catch:{ all -> 0x015b }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x015b }
        r6._pub_key = r0;	 Catch:{ all -> 0x015b }
        r0 = r3.clone();	 Catch:{ all -> 0x015b }
        r0 = (byte[]) r0;	 Catch:{ all -> 0x015b }
        r6._priv_key = r0;	 Catch:{ all -> 0x015b }
        r0 = "rsa has saved";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x015b }
    L_0x0152:
        monitor-exit(r1);	 Catch:{ all -> 0x015b }
        throw r2;
    L_0x0154:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0157:
        if (r0 == 0) goto L_0x0120;
    L_0x0159:
        goto L_0x0027;
    L_0x015b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x015b }
        throw r0;
    L_0x015e:
        r0 = "saversa begin";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x015b }
        r0 = r6._context;	 Catch:{ all -> 0x015b }
        r3 = r6._pub_key;	 Catch:{ all -> 0x015b }
        oicq.wlogin_sdk.tools.util.save_rsa_pubkey(r0, r3);	 Catch:{ all -> 0x015b }
        r0 = r6._context;	 Catch:{ all -> 0x015b }
        r3 = r6._priv_key;	 Catch:{ all -> 0x015b }
        oicq.wlogin_sdk.tools.util.save_rsa_privkey(r0, r3);	 Catch:{ all -> 0x015b }
        r0 = "saversa end";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x015b }
        goto L_0x0152;
    L_0x017f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x017f }
        throw r0;
    L_0x0182:
        r0 = "saversa begin";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x017f }
        r0 = r6._context;	 Catch:{ all -> 0x017f }
        r3 = r6._pub_key;	 Catch:{ all -> 0x017f }
        oicq.wlogin_sdk.tools.util.save_rsa_pubkey(r0, r3);	 Catch:{ all -> 0x017f }
        r0 = r6._context;	 Catch:{ all -> 0x017f }
        r3 = r6._priv_key;	 Catch:{ all -> 0x017f }
        oicq.wlogin_sdk.tools.util.save_rsa_privkey(r0, r3);	 Catch:{ all -> 0x017f }
        r0 = "saversa end";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x017f }
        goto L_0x011c;
    L_0x01a4:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x01a4 }
        throw r0;
    L_0x01a7:
        r0 = "saversa begin";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x01a4 }
        r0 = r6._context;	 Catch:{ all -> 0x01a4 }
        r3 = r6._pub_key;	 Catch:{ all -> 0x01a4 }
        oicq.wlogin_sdk.tools.util.save_rsa_pubkey(r0, r3);	 Catch:{ all -> 0x01a4 }
        r0 = r6._context;	 Catch:{ all -> 0x01a4 }
        r3 = r6._priv_key;	 Catch:{ all -> 0x01a4 }
        oicq.wlogin_sdk.tools.util.save_rsa_privkey(r0, r3);	 Catch:{ all -> 0x01a4 }
        r0 = "saversa end";
        r3 = "";
        oicq.wlogin_sdk.tools.util.LOGI(r0, r3);	 Catch:{ all -> 0x01a4 }
        goto L_0x0090;
    L_0x01c9:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x0157;
    L_0x01cf:
        r2 = move-exception;
        goto L_0x0157;
    L_0x01d1:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00c6;
    L_0x01d5:
        r0 = move-exception;
        goto L_0x00c1;
    L_0x01d8:
        r0 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x00c1;
        */
        throw new UnsupportedOperationException("Method not decompiled: oicq.wlogin_sdk.tools.RSACrypt.GenRSAKey():int");
    }

    public byte[] EncryptData(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        if (bArr == null || bArr2 == null) {
            return bArr3;
        }
        try {
            bArr3 = encryptdata(bArr, bArr2);
        } catch (UnsatisfiedLinkError e) {
        }
        if (bArr3 == null) {
            return util.RSAEncrypt(bArr2, util.RSAPubKeyFromJNI(bArr));
        }
        return bArr3;
    }

    public byte[] DecryptData(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        if (bArr2 != null) {
            if (bArr == null) {
                bArr = util.get_rsa_privkey(this._context);
                if (bArr == null || bArr.length == 0) {
                    bArr = util.string_to_buf(DEFAULT_PRIV_KEY);
                }
            }
            try {
                bArr3 = decryptdata(bArr, bArr2);
                if (bArr3 == null || bArr3.length == 0) {
                    bArr3 = decryptdata(util.string_to_buf(DEFAULT_PRIV_KEY), bArr2);
                }
            } catch (UnsatisfiedLinkError e) {
            }
            if (bArr3 == null) {
                try {
                    bArr3 = util.RSADecrypt(bArr2, util.RSAPrivKeyFromJNI(bArr));
                    if (bArr3 == null || bArr3.length == 0) {
                        bArr3 = util.RSADecrypt(bArr2, util.RSAPrivKeyFromJNI(util.string_to_buf(DEFAULT_PRIV_KEY)));
                    }
                } catch (Exception e2) {
                }
            }
        }
        return bArr3;
    }
}
