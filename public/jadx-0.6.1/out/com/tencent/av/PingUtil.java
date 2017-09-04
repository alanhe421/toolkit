package com.tencent.av;

import android.util.Log;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.av.MultiVideoTinyId;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PingUtil implements Runnable {
    private static final PingUtil instance = new PingUtil();
    private static final String tag = "av.PingUtil";
    TIMPingCallBack callback;
    DatagramChannel channel;
    InetAddress client;
    byte[] data;
    private String identifer = "";
    int interval;
    List<PingResult> pingResults = new ArrayList();
    int pkgNum;
    private volatile boolean running;
    List<ServerInfo> server = new ArrayList();
    Map<Integer, Calendar> timeRecord = new HashMap();
    int timeout;

    private PingUtil() {
    }

    public static PingUtil getInstance() {
        return instance;
    }

    private byte[] getLongConnUdtDataHeadV2(short s, short s2) {
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.identifer);
        long tinyid = msfUserInfo != null ? msfUserInfo.getTinyid() : 0;
        ByteBuffer allocate = ByteBuffer.allocate(41);
        int nextInt = IMMsfCoreProxy.get().random.nextInt();
        allocate.putShort(s);
        allocate.putInt(nextInt);
        allocate.putShort(s2);
        allocate.putLong(tinyid);
        allocate.putInt(0);
        allocate.putInt(0);
        allocate.putShort((short) 0);
        allocate.putLong(0);
        allocate.put((byte) 0);
        allocate.putInt(nextInt);
        allocate.putShort((short) 0);
        Calendar instance = Calendar.getInstance();
        this.timeRecord.put(Integer.valueOf(nextInt), instance);
        QLog.e(tag, 1, "send seq " + nextInt + " time " + instance.getTimeInMillis());
        return allocate.array();
    }

    private void reportToServer() {
        ByteBuffer allocate = ByteBuffer.allocate((((this.pingResults.size() * 30) + 19) + 2) + 2);
        int sdkAppId = IMMsfCoreProxy.get().getSdkAppId();
        int i = this.interval;
        byte size = (byte) this.pingResults.size();
        allocate.clear();
        allocate.put((byte) 2).put((byte) 1).putShort((short) 7).putShort((short) 6).putInt(sdkAppId).putInt(23678484).putInt(i).put(size);
        for (PingResult pingResult : this.pingResults) {
            allocate.put(pingResult.server.ip.getAddress());
            allocate.putShort(pingResult.server.port);
            allocate.put(pingResult.server.getIdcNo());
            allocate.put(pingResult.server.getIspNo());
            allocate.put(this.client.getAddress());
            allocate.putInt(this.pkgNum);
            allocate.putInt(pingResult.receivePkg);
            allocate.putInt(pingResult.useTime);
            allocate.putInt(0);
            allocate.putShort((short) 0);
        }
        allocate.putShort((short) 0);
        allocate.putShort((short) 0);
        MultiVideoTinyId.get().requestMultiVideoInfo(NetworkUtil.formReq(this.identifer, 210, 0, "", allocate.array()), new ab(this));
        this.running = false;
    }

    private void send(ServerInfo serverInfo) throws IOException, InterruptedException {
        for (int i = 0; i < this.pkgNum && this.running; i++) {
            byte[] GetPingPackage = GetPingPackage(this.data);
            ByteBuffer allocate = ByteBuffer.allocate(GetPingPackage.length);
            allocate.put(GetPingPackage);
            allocate.flip();
            try {
                this.channel.write(allocate);
            } catch (Exception e) {
            }
            allocate.clear();
            if (this.callback != null) {
                this.callback.onProgress(serverInfo, this.pkgNum, i);
            }
            Thread.sleep((long) this.interval);
        }
    }

    public byte[] GetPingPackage(byte[] bArr) {
        int length = (bArr.length + 46) + 1;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.put((byte) 2);
        allocate.put(getLongConnUdtDataHeadV2((short) length, (short) 469));
        allocate.putInt(bArr.length);
        allocate.put(bArr);
        allocate.put((byte) 3);
        return allocate.array();
    }

    public void init(byte[] bArr, TIMPingCallBack tIMPingCallBack) {
        try {
            byte[] bArr2 = new byte[4];
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
            allocate.put(bArr);
            allocate.position(0);
            short s = allocate.getShort();
            int i = allocate.getInt();
            allocate.getShort();
            allocate.position(18);
            allocate.get();
            allocate.get(bArr2);
            InetAddress byAddress = InetAddress.getByAddress(bArr2);
            allocate.get();
            allocate.getShort();
            allocate.getShort();
            int i2 = allocate.getInt();
            int i3 = allocate.getInt();
            int i4 = allocate.getInt();
            int i5 = allocate.getInt();
            if (i5 <= allocate.remaining()) {
                byte[] bArr3 = new byte[i5];
                allocate.get(bArr3);
                allocate.get();
                byte b = allocate.get();
                List arrayList = new ArrayList();
                for (byte b2 = (byte) 0; b2 < b; b2++) {
                    ServerInfo serverInfo = new ServerInfo();
                    for (i5 = 3; i5 >= 0; i5--) {
                        bArr2[i5] = allocate.get();
                    }
                    serverInfo.ip = InetAddress.getByAddress(bArr2);
                    serverInfo.port = allocate.getShort();
                    serverInfo.setIDC(allocate.get());
                    serverInfo.setISP(allocate.get());
                    arrayList.add(serverInfo);
                }
                Log.d(tag, "subcmd:" + s + "|retcode:" + i + "|servers:" + arrayList.size());
                allocate.clear();
                this.server = arrayList;
                this.data = bArr3;
                this.interval = i2;
                this.pkgNum = i4;
                this.timeout = i3;
                this.callback = tIMPingCallBack;
                this.client = byAddress;
                if (this.callback != null) {
                    this.callback.onStart(arrayList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logResult() {
        for (PingResult pingResult : this.pingResults) {
            QLog.e(tag, 1, "serverIp:" + pingResult.server.ip.toString() + " useTime:" + pingResult.useTime + " totalPkg:" + pingResult.totalPkg + " receivePkg:" + pingResult.receivePkg);
        }
    }

    public void run() {
        try {
            if (this.server != null && this.server.size() != 0) {
                for (ServerInfo serverInfo : this.server) {
                    if (this.running) {
                        List<Long> arrayList = new ArrayList();
                        Selector open = Selector.open();
                        this.channel = DatagramChannel.open();
                        this.channel.connect(new InetSocketAddress(serverInfo.ip, serverInfo.port));
                        this.channel.configureBlocking(false);
                        this.channel.register(open, 1);
                        new Thread(new aa(this, open, arrayList)).start();
                        send(serverInfo);
                        if (this.running) {
                            for (int i = 0; i < this.timeout / 100 && arrayList.size() != this.pkgNum; i++) {
                                Thread.sleep(100);
                            }
                            open.close();
                            long j = 0;
                            long j2 = 0;
                            for (Long l : arrayList) {
                                if (l.longValue() < ((long) this.timeout)) {
                                    j++;
                                    j2 += l.longValue();
                                }
                            }
                            PingResult pingResult = new PingResult();
                            pingResult.server = serverInfo;
                            pingResult.receivePkg = (int) j;
                            pingResult.totalPkg = this.pkgNum;
                            pingResult.useTime = j == 0 ? Integer.MAX_VALUE : (int) (j2 / j);
                            this.pingResults.add(pingResult);
                            if (this.callback != null) {
                                this.callback.onSuccess(pingResult);
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
                logResult();
                reportToServer();
                if (this.callback != null) {
                    this.callback.onFinish();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public void setIdentifer(String str) {
        this.identifer = str;
    }

    public void start() {
        new Thread(instance).start();
        this.running = true;
    }

    public void stop() {
        this.server.clear();
        this.pingResults.clear();
        this.timeRecord.clear();
        this.running = false;
    }
}
