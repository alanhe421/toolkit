package com.tencent.av.channel;

public class AVChannelManager {
    private static AVAppChannel sAppChannel = null;

    private AVChannelManager() {
    }

    public static synchronized AVAppChannel getAppChannel() {
        AVAppChannel aVAppChannel;
        AVAppChannel aVAppChannel2 = null;
        synchronized (AVChannelManager.class) {
            if (sAppChannel != null) {
                aVAppChannel = sAppChannel;
            } else {
                Class cls;
                try {
                    cls = Class.forName("com.tencent.av.channel.IMAppChannel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    cls = null;
                }
                if (cls == null) {
                    aVAppChannel = null;
                } else {
                    try {
                        aVAppChannel2 = (AVAppChannel) cls.newInstance();
                    } catch (InstantiationException e2) {
                        e2.printStackTrace();
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                    }
                    sAppChannel = aVAppChannel2;
                    aVAppChannel = sAppChannel;
                }
            }
        }
        return aVAppChannel;
    }

    public static synchronized void setAppChannel(AVAppChannel aVAppChannel) {
        synchronized (AVChannelManager.class) {
            sAppChannel = aVAppChannel;
        }
    }
}
