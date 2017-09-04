package com.tencent.av.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;

public interface IMediaCodecCallback {
    void onError(MediaCodec mediaCodec, Exception exception);

    void onInputBufferAvailable(MediaCodec mediaCodec, int i);

    void onOutputBufferAvailable(MediaCodec mediaCodec, int i, BufferInfo bufferInfo);

    void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat);
}
