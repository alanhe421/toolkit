package com.qq.reader.common.utils.c;

import com.etrump.jni.ETConverter;
import com.google.zxing.common.StringUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.reflect.Array;
import tencent.tls.platform.TLSErrInfo;

/* compiled from: SinoDetect */
public class c {
    public static String[] b;
    public static String[] c;
    int[][] a = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{94, 94}));

    public c() {
        c = new String[10];
        c[0] = StringUtils.GB2312;
        c[1] = "GBK";
        c[2] = "HZ";
        c[3] = "BIG5";
        c[4] = "CNS11643";
        c[5] = "ISO2022CN";
        c[6] = "UTF8";
        c[7] = "Unicode";
        c[8] = "ASCII";
        c[9] = "OTHER";
        b = new String[10];
        b[0] = StringUtils.GB2312;
        b[1] = "GBK";
        b[2] = "HZ";
        b[3] = "Big5";
        b[4] = "CNS 11643";
        b[5] = "ISO 2022-CN";
        b[6] = "UTF-8";
        b[7] = "Unicode";
        b[8] = "ASCII";
        b[9] = "OTHER";
        a();
    }

    public int a(byte[] bArr) throws Exception {
        int i = 0;
        int[] iArr = new int[10];
        iArr[0] = b(bArr);
        iArr[6] = c(bArr);
        iArr[7] = d(bArr);
        iArr[8] = e(bArr);
        iArr[9] = 0;
        int i2 = 9;
        for (int i3 = 0; i3 < 10; i3++) {
            if (iArr[i3] > i) {
                i = iArr[i3];
                i2 = i3;
            }
        }
        if (i <= 40) {
            return 9;
        }
        return i2;
    }

    int b(byte[] bArr) {
        int i = 1;
        long j = 0;
        long j2 = 1;
        int length = bArr.length;
        int i2 = 0;
        int i3 = 1;
        while (i2 < length - 1) {
            if (bArr[i2] < (byte) 0) {
                i3++;
                if ((byte) -95 <= bArr[i2] && bArr[i2] <= (byte) -9 && (byte) -95 <= bArr[i2 + 1] && bArr[i2 + 1] <= (byte) -2) {
                    i++;
                    j2 += 500;
                    int i4 = (bArr[i2] + 256) - 161;
                    int i5 = (bArr[i2 + 1] + 256) - 161;
                    if (this.a[i4][i5] != 0) {
                        j += (long) this.a[i4][i5];
                    } else if (15 <= i4 && i4 < 55) {
                        j += 200;
                    }
                }
                i2++;
            }
            i2++;
        }
        return (int) (((((float) j) / ((float) j2)) * 50.0f) + ((((float) i) / ((float) i3)) * 50.0f));
    }

    int c(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            if ((bArr[i3] & Opcodes.NEG_FLOAT) == bArr[i3]) {
                i++;
            } else if ((byte) -64 <= bArr[i3] && bArr[i3] <= (byte) -33 && i3 + 1 < length && Byte.MIN_VALUE <= bArr[i3 + 1] && bArr[i3 + 1] <= (byte) -65) {
                i2 += 2;
                i3++;
            } else if ((byte) -32 <= bArr[i3] && bArr[i3] <= (byte) -17 && i3 + 2 < length && Byte.MIN_VALUE <= bArr[i3 + 1] && bArr[i3 + 1] <= (byte) -65 && Byte.MIN_VALUE <= bArr[i3 + 2] && bArr[i3 + 2] <= (byte) -65) {
                i2 += 3;
                i3 += 2;
            }
            i3++;
        }
        if (i == length) {
            return 0;
        }
        i = (int) ((((float) i2) / ((float) (length - i))) * 100.0f);
        if (i > 98) {
            return i;
        }
        if (i <= 95 || i2 <= 30) {
            return 0;
        }
        return i;
    }

    int d(byte[] bArr) {
        if (((byte) -2 == bArr[0] && (byte) -1 == bArr[1]) || ((byte) -1 == bArr[0] && (byte) -2 == bArr[1])) {
            return 100;
        }
        return 0;
    }

    int e(byte[] bArr) {
        int length = bArr.length;
        int i = 70;
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i2] < (byte) 0) {
                i -= 5;
            } else if (bArr[i2] == (byte) 27) {
                i -= 5;
            }
        }
        return i;
    }

    void a() {
        for (int i = 0; i < 93; i++) {
            for (int i2 = 0; i2 < 93; i2++) {
                this.a[i][i2] = 0;
            }
        }
        this.a[20][35] = 599;
        this.a[49][26] = 598;
        this.a[41][38] = 597;
        this.a[17][26] = 596;
        this.a[32][42] = 595;
        this.a[39][42] = 594;
        this.a[45][49] = 593;
        this.a[51][57] = 592;
        this.a[50][47] = 591;
        this.a[42][90] = 590;
        this.a[52][65] = 589;
        this.a[53][47] = 588;
        this.a[19][82] = 587;
        this.a[31][19] = 586;
        this.a[40][46] = 585;
        this.a[24][89] = 584;
        this.a[23][85] = 583;
        this.a[20][28] = 582;
        this.a[42][20] = 581;
        this.a[34][38] = 580;
        this.a[45][9] = 579;
        this.a[54][50] = 578;
        this.a[25][44] = 577;
        this.a[35][66] = 576;
        this.a[20][55] = 575;
        this.a[18][85] = 574;
        this.a[20][31] = 573;
        this.a[49][17] = 572;
        this.a[41][16] = 571;
        this.a[35][73] = 570;
        this.a[20][34] = 569;
        this.a[29][44] = 568;
        this.a[35][38] = 567;
        this.a[49][9] = 566;
        this.a[46][33] = 565;
        this.a[49][51] = 564;
        this.a[40][89] = 563;
        this.a[26][64] = 562;
        this.a[54][51] = 561;
        this.a[54][36] = 560;
        this.a[39][4] = 559;
        this.a[53][13] = 558;
        this.a[24][92] = 557;
        this.a[27][49] = 556;
        this.a[48][6] = 555;
        this.a[21][51] = 554;
        this.a[30][40] = 553;
        this.a[42][92] = 552;
        this.a[31][78] = 551;
        this.a[25][82] = 550;
        this.a[47][0] = 549;
        this.a[34][19] = 548;
        this.a[47][35] = 547;
        this.a[21][63] = 546;
        this.a[43][75] = 545;
        this.a[21][87] = 544;
        this.a[35][59] = 543;
        this.a[25][34] = 542;
        this.a[21][27] = 541;
        this.a[39][26] = 540;
        this.a[34][26] = 539;
        this.a[39][52] = 538;
        this.a[50][57] = 537;
        this.a[37][79] = 536;
        this.a[26][24] = 535;
        this.a[22][1] = 534;
        this.a[18][40] = 533;
        this.a[41][33] = 532;
        this.a[53][26] = 531;
        this.a[54][86] = 530;
        this.a[20][16] = 529;
        this.a[46][74] = 528;
        this.a[30][19] = 527;
        this.a[45][35] = 526;
        this.a[45][61] = 525;
        this.a[30][9] = 524;
        this.a[41][53] = 523;
        this.a[41][13] = 522;
        this.a[50][34] = 521;
        this.a[53][86] = im_common.BU_FRIEND;
        this.a[47][47] = 519;
        this.a[22][28] = 518;
        this.a[50][53] = 517;
        this.a[39][70] = im_common.GRP_PUBGROUP;
        this.a[38][15] = im_common.MSG_PUSH;
        this.a[42][88] = im_common.GRP_HRTX;
        this.a[16][29] = im_common.GRP_CONFERENCE;
        this.a[27][90] = 512;
        this.a[29][12] = 511;
        this.a[44][22] = 510;
        this.a[34][69] = 509;
        this.a[24][10] = 508;
        this.a[44][11] = 507;
        this.a[39][92] = 506;
        this.a[49][48] = 505;
        this.a[31][46] = 504;
        this.a[19][50] = 503;
        this.a[21][14] = 502;
        this.a[32][28] = 501;
        this.a[18][3] = http.Internal_Server_Error;
        this.a[53][9] = 499;
        this.a[34][80] = 498;
        this.a[48][88] = 497;
        this.a[46][53] = 496;
        this.a[22][53] = 495;
        this.a[28][10] = 494;
        this.a[44][65] = 493;
        this.a[20][10] = 492;
        this.a[40][76] = 491;
        this.a[47][8] = 490;
        this.a[50][74] = 489;
        this.a[23][62] = 488;
        this.a[49][65] = 487;
        this.a[28][87] = 486;
        this.a[15][48] = 485;
        this.a[22][7] = 484;
        this.a[19][42] = 483;
        this.a[41][20] = 482;
        this.a[26][55] = 481;
        this.a[21][93] = 480;
        this.a[31][76] = 479;
        this.a[34][31] = 478;
        this.a[20][66] = 477;
        this.a[51][33] = 476;
        this.a[34][86] = 475;
        this.a[37][67] = 474;
        this.a[53][53] = 473;
        this.a[40][88] = 472;
        this.a[39][10] = 471;
        this.a[24][3] = 470;
        this.a[27][25] = 469;
        this.a[26][15] = 468;
        this.a[21][88] = 467;
        this.a[52][62] = 466;
        this.a[46][81] = 465;
        this.a[38][72] = 464;
        this.a[17][30] = 463;
        this.a[52][92] = 462;
        this.a[34][90] = 461;
        this.a[21][7] = 460;
        this.a[36][13] = 459;
        this.a[45][41] = 458;
        this.a[32][5] = 457;
        this.a[26][89] = 456;
        this.a[23][87] = 455;
        this.a[20][39] = 454;
        this.a[27][23] = 453;
        this.a[25][59] = 452;
        this.a[49][20] = 451;
        this.a[54][77] = 450;
        this.a[27][67] = 449;
        this.a[47][33] = 448;
        this.a[41][17] = 447;
        this.a[19][81] = 446;
        this.a[16][66] = 445;
        this.a[45][26] = 444;
        this.a[49][81] = 443;
        this.a[53][55] = 442;
        this.a[16][26] = 441;
        this.a[54][62] = 440;
        this.a[20][70] = 439;
        this.a[42][35] = 438;
        this.a[20][57] = 437;
        this.a[34][36] = 436;
        this.a[46][63] = 435;
        this.a[19][45] = 434;
        this.a[21][10] = 433;
        this.a[52][93] = 432;
        this.a[25][2] = 431;
        this.a[30][57] = 430;
        this.a[41][24] = 429;
        this.a[28][43] = 428;
        this.a[45][86] = 427;
        this.a[51][56] = 426;
        this.a[37][28] = 425;
        this.a[52][69] = 424;
        this.a[43][92] = 423;
        this.a[41][31] = 422;
        this.a[37][87] = 421;
        this.a[47][36] = 420;
        this.a[16][16] = 419;
        this.a[40][56] = 418;
        this.a[24][55] = 417;
        this.a[17][1] = http.Requested_Range_Not_Satisfiable;
        this.a[35][57] = 415;
        this.a[27][50] = http.Request_URI_Too_Long;
        this.a[26][14] = http.Request_Entity_Too_Large;
        this.a[50][40] = 412;
        this.a[39][19] = 411;
        this.a[19][89] = 410;
        this.a[29][91] = 409;
        this.a[17][89] = http.Request_Timeout;
        this.a[39][74] = ErrorCode.INFO_CAN_NOT_LOAD_X5;
        this.a[46][39] = ErrorCode.INFO_CAN_LOAD_TBS;
        this.a[40][28] = ErrorCode.INFO_CAN_NOT_LOAD_TBS;
        this.a[45][68] = 404;
        this.a[43][10] = 403;
        this.a[42][13] = 402;
        this.a[44][81] = 401;
        this.a[41][47] = 400;
        this.a[48][58] = 399;
        this.a[43][68] = 398;
        this.a[16][79] = 397;
        this.a[19][5] = 396;
        this.a[54][59] = 395;
        this.a[17][36] = 394;
        this.a[18][0] = 393;
        this.a[41][5] = 392;
        this.a[41][72] = 391;
        this.a[16][39] = 390;
        this.a[54][0] = 389;
        this.a[51][16] = 388;
        this.a[29][36] = 387;
        this.a[47][5] = 386;
        this.a[47][51] = 385;
        this.a[44][7] = 384;
        this.a[35][30] = 383;
        this.a[26][9] = 382;
        this.a[16][7] = 381;
        this.a[32][1] = 380;
        this.a[33][76] = 379;
        this.a[34][91] = 378;
        this.a[52][36] = 377;
        this.a[26][77] = 376;
        this.a[35][48] = 375;
        this.a[40][80] = 374;
        this.a[41][92] = 373;
        this.a[27][93] = 372;
        this.a[15][17] = 371;
        this.a[16][76] = 370;
        this.a[51][12] = 369;
        this.a[18][20] = 368;
        this.a[15][54] = 367;
        this.a[50][5] = 366;
        this.a[33][22] = 365;
        this.a[37][57] = 364;
        this.a[28][47] = 363;
        this.a[42][31] = 362;
        this.a[18][2] = 361;
        this.a[43][64] = 360;
        this.a[23][47] = 359;
        this.a[28][79] = 358;
        this.a[25][45] = 357;
        this.a[23][91] = 356;
        this.a[22][19] = 355;
        this.a[25][46] = 354;
        this.a[22][36] = 353;
        this.a[54][85] = 352;
        this.a[46][20] = 351;
        this.a[27][37] = 350;
        this.a[26][81] = 349;
        this.a[42][29] = 348;
        this.a[31][90] = 347;
        this.a[41][59] = 346;
        this.a[24][65] = 345;
        this.a[44][84] = 344;
        this.a[24][90] = 343;
        this.a[38][54] = 342;
        this.a[28][70] = 341;
        this.a[27][15] = 340;
        this.a[28][80] = 339;
        this.a[29][8] = 338;
        this.a[45][80] = 337;
        this.a[53][37] = 336;
        this.a[28][65] = 335;
        this.a[23][86] = 334;
        this.a[39][45] = 333;
        this.a[53][32] = 332;
        this.a[38][68] = 331;
        this.a[45][78] = ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE;
        this.a[43][7] = ErrorCode.ERROR_GETSTRINGARRAY_JARFILE;
        this.a[46][82] = ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT;
        this.a[27][38] = ErrorCode.TEST_THROWABLE_ISNOT_NULL;
        this.a[16][62] = ErrorCode.TEST_THROWABLE_IS_NULL;
        this.a[24][17] = ErrorCode.THROWABLE_INITX5CORE;
        this.a[22][70] = ErrorCode.ERROR_SDKENGINE_CANLOADTBS;
        this.a[52][28] = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03;
        this.a[23][40] = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02;
        this.a[28][50] = ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01;
        this.a[42][91] = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
        this.a[47][76] = 319;
        this.a[15][42] = 318;
        this.a[43][55] = 317;
        this.a[29][84] = 316;
        this.a[44][90] = ErrorCode.ERROR_QBSDK_INIT;
        this.a[53][16] = ErrorCode.ERROR_CANLOADVIDEO_RETURN_NULL;
        this.a[22][93] = ErrorCode.ERROR_CANLOADVIDEO_RETURN_FALSE;
        this.a[34][10] = ErrorCode.ERROR_TBSCORE_SHARE_DIR;
        this.a[32][53] = ErrorCode.ERROR_TBSCORE_DEXOPT_DIR;
        this.a[43][65] = 310;
        this.a[28][7] = 309;
        this.a[35][46] = 308;
        this.a[21][39] = 307;
        this.a[44][18] = 306;
        this.a[40][10] = 305;
        this.a[54][53] = 304;
        this.a[38][74] = 303;
        this.a[28][26] = 302;
        this.a[15][13] = 301;
        this.a[39][34] = 300;
        this.a[39][46] = 299;
        this.a[42][66] = 298;
        this.a[33][58] = 297;
        this.a[15][56] = 296;
        this.a[18][51] = 295;
        this.a[49][68] = 294;
        this.a[30][37] = 293;
        this.a[51][84] = 292;
        this.a[51][9] = 291;
        this.a[40][70] = 290;
        this.a[41][84] = 289;
        this.a[28][64] = 288;
        this.a[32][88] = 287;
        this.a[24][5] = 286;
        this.a[53][23] = 285;
        this.a[42][27] = 284;
        this.a[22][38] = 283;
        this.a[32][86] = 282;
        this.a[34][30] = 281;
        this.a[38][63] = 280;
        this.a[24][59] = 279;
        this.a[22][81] = 278;
        this.a[32][11] = 277;
        this.a[51][21] = 276;
        this.a[54][41] = im_common.WPA_PAIPAI;
        this.a[21][50] = 274;
        this.a[23][89] = 273;
        this.a[19][87] = 272;
        this.a[26][7] = 271;
        this.a[30][75] = im_common.WPA_QZONE;
        this.a[43][84] = 269;
        this.a[51][25] = 268;
        this.a[16][67] = 267;
        this.a[32][9] = 266;
        this.a[48][51] = 265;
        this.a[39][7] = 264;
        this.a[44][88] = 263;
        this.a[52][24] = 262;
        this.a[23][34] = 261;
        this.a[32][75] = 260;
        this.a[19][10] = VoiceWakeuperAidl.RES_FROM_CLIENT;
        this.a[28][91] = VoiceWakeuperAidl.RES_SPECIFIED;
        this.a[32][83] = 257;
        this.a[25][75] = 256;
        this.a[53][45] = 255;
        this.a[29][85] = 254;
        this.a[53][59] = 253;
        this.a[16][2] = 252;
        this.a[19][78] = 251;
        this.a[15][75] = 250;
        this.a[51][42] = 249;
        this.a[45][67] = 248;
        this.a[15][74] = 247;
        this.a[25][81] = 246;
        this.a[37][62] = 245;
        this.a[16][55] = 244;
        this.a[18][38] = 243;
        this.a[23][23] = 242;
        this.a[38][30] = 241;
        this.a[17][28] = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
        this.a[44][73] = 239;
        this.a[23][78] = 238;
        this.a[40][77] = 237;
        this.a[38][87] = 236;
        this.a[27][19] = 235;
        this.a[38][82] = 234;
        this.a[37][22] = 233;
        this.a[41][30] = 232;
        this.a[54][9] = 231;
        this.a[32][30] = 230;
        this.a[30][52] = TLSErrInfo.LOGIN_NO_ACCOUNT;
        this.a[40][84] = 228;
        this.a[53][57] = 227;
        this.a[27][27] = Opcodes.USHR_INT_LIT8;
        this.a[38][64] = Opcodes.SHR_INT_LIT8;
        this.a[18][43] = Opcodes.SHL_INT_LIT8;
        this.a[23][69] = Opcodes.XOR_INT_LIT8;
        this.a[28][12] = Opcodes.OR_INT_LIT8;
        this.a[50][78] = 221;
        this.a[50][1] = 220;
        this.a[26][88] = 219;
        this.a[36][40] = 218;
        this.a[33][89] = 217;
        this.a[41][28] = 216;
        this.a[31][77] = 215;
        this.a[46][1] = 214;
        this.a[47][19] = 213;
        this.a[35][55] = 212;
        this.a[41][21] = 211;
        this.a[27][10] = 210;
        this.a[32][77] = 209;
        this.a[26][37] = 208;
        this.a[20][33] = 207;
        this.a[41][52] = 206;
        this.a[32][18] = 205;
        this.a[38][13] = 204;
        this.a[20][18] = 203;
        this.a[20][24] = 202;
        this.a[45][19] = 201;
        this.a[18][53] = 200;
    }
}
