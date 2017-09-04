package com.tencent.av.sdk;

import com.tencent.av.utils.QLog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class AVQualityStats {
    private static final String TAG = "AVQualityStats";
    public int audioCaptureChannelCount;
    public int audioCaptureSampleRate;
    public int audioCategory;
    ArrayList<AudioDecodeParam> audioDecodeInfo = new ArrayList();
    public int audioDecodeNum;
    public int audioEncodeBR;
    public int audioEncodeType;
    public int audioEncodeVAD;
    public int audioQosAec;
    public int audioQosAgc;
    public int audioQosChannelCount;
    public int audioQosEncodeBR;
    public int audioQosEncodeType;
    public int audioQosFec;
    public int audioQosJitterDropScale;
    public int audioQosJitterMaxMaxDelay;
    public int audioQosJitterMinDelay;
    public int audioQosJitterMinMaxDelay;
    public int audioQosMtu;
    public int audioQosPackDuration;
    public int audioQosRecm;
    public int audioQosRecn;
    public int audioQosSampleRare;
    public int audioQosVad;
    public int audioRecvLossRate;
    public int audioRecvPlayDelay;
    public int audioSendBR;
    public int audioSendBRUdt;
    public int audioSendFEC;
    public int audioSendJitter;
    public int audioSendLossRate;
    public int captureFps;
    public int captureHeight;
    public int captureWidth;
    public String clientIp;
    public int dwKbpsRecv;
    public int dwKbpsSend;
    public int dwPktpsRecv;
    public int dwPktpsSend;
    public int dwRTT;
    public String interfaceIp;
    public int isAnchor;
    public int isTcp;
    public int majorEncMode;
    public int majorEncodeType;
    public int majorFecType;
    public int majorGop;
    public int majorGopType;
    public int majorHw;
    public int majorIFecMinPkg;
    public int majorIFecMinSize;
    public int majorIFecPrecent;
    public int majorIMtu;
    public int majorMaxQP;
    public int majorMinQP;
    public int majorPFecMinPkg;
    public int majorPFecMinSize;
    public int majorPFecPrecent;
    public int majorPMtu;
    public int majorSPFecMinPkg;
    public int majorSPFecMinSize;
    public int majorSPFecPrecent;
    public int majorSPMtu;
    public int majorVidBitrate;
    public int majorVidFPS;
    public int majorVidHeight;
    public int majorVidWidth;
    public int miniEncMode;
    public int miniEncodeType;
    public int miniFecType;
    public int miniGop;
    public int miniGopType;
    public int miniHw;
    public int miniIFecMinPkg;
    public int miniIFecMinSize;
    public int miniIFecPrecent;
    public int miniIMtu;
    public int miniMaxQP;
    public int miniMinQP;
    public int miniPFecMinPkg;
    public int miniPFecMinSize;
    public int miniPFecPrecent;
    public int miniPMtu;
    public int miniSPFecMinPkg;
    public int miniSPFecMinSize;
    public int miniSPFecPrecent;
    public int miniSPMtu;
    public int miniVidBitrate;
    public int miniVidFPS;
    public int miniVidHeight;
    public int miniVidWidth;
    public int port;
    public String sdkVersion;
    public long tickCountBegin;
    public long tickCountEnd;
    public int unsendUdt;
    ArrayList<VideoDecodeParam> videoDecodeInfo = new ArrayList();
    public int videoDecodeNum;
    ArrayList<VideoEncodeParam> videoEncodeInfo = new ArrayList();
    public int videoSendIFec;
    public int videoSendLossRate;
    public int videoSendPkt;
    public int videoSendSPFec;
    public int videoSendStnSBBreak;
    public int videoSendStnSBDecLoss;
    public int videoSendStnSBGain;
    public int wExeCpuRate;
    public int wLossRateRecv;
    public int wLossRateRecvUdt;
    public int wLossRateSend;
    public int wLossRateSendUdt;
    public int wSysCpuRate;

    public static class AudioDecodeParam {
        public int channelCount;
        public int decodeType;
        public int sampleRate;
        public int tinyId;
    }

    public static class VideoDecodeParam {
        public int decBR;
        public int decFPS;
        public int decHeight;
        public int decType;
        public int decVideoStrType;
        public int decWidth;
        public int hw;
        public int hwDecDelay;
        public int recvBR;
        public int recvJitter;
        public int recvLossRate;
        public int senderUin;
    }

    public static class VideoEncodeParam {
        public int angle;
        public int encBR;
        public int encFPS;
        public int encHeight;
        public int encType;
        public int encVideoStrType;
        public int encWidth;
        public int hw;
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2;
            jSONObject.put("tickCountBegin", this.tickCountBegin);
            jSONObject.put("tickCountEnd", this.tickCountEnd);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("cpu_rate_app", this.wExeCpuRate);
            jSONObject3.put("cpu_rate_sys", this.wSysCpuRate);
            jSONObject3.put("sdk_version", this.sdkVersion);
            jSONObject.put("basic_info", jSONObject3);
            jSONObject3 = new JSONObject();
            jSONObject3.put("client_ip", this.clientIp);
            jSONObject3.put("interface_ip", this.interfaceIp);
            jSONObject3.put("interface_port", this.port);
            jSONObject3.put("is_tcp", this.isTcp);
            jSONObject3.put("kbps_send", this.dwKbpsSend);
            jSONObject3.put("packet_send", this.dwPktpsSend);
            jSONObject3.put("loss_rate_send", this.wLossRateSend);
            jSONObject3.put("loss_rate_send_udt", this.wLossRateSendUdt);
            jSONObject3.put("kbps_recv", this.dwKbpsRecv);
            jSONObject3.put("packet_recv", this.dwPktpsRecv);
            jSONObject3.put("loss_rate_recv", this.wLossRateRecv);
            jSONObject3.put("loss_rate_recv_udt", this.wLossRateRecvUdt);
            jSONObject3.put("rtt", this.dwRTT);
            jSONObject.put("network", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject3 = new JSONObject();
            jSONObject3.put("video_capture_width", this.captureWidth);
            jSONObject3.put("video_capture_height", this.captureHeight);
            jSONObject3.put("video_capture_fps", this.captureFps);
            jSONObject4.put("video_capture_param", jSONObject3);
            jSONObject4.put("is_anchor", this.isAnchor);
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.videoEncodeInfo.iterator();
            int i = 0;
            while (it.hasNext()) {
                VideoEncodeParam videoEncodeParam = (VideoEncodeParam) it.next();
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("video_encode_view_type", videoEncodeParam.encVideoStrType);
                jSONObject5.put("video_encode_view_witdh", videoEncodeParam.encWidth);
                jSONObject5.put("video_encode_view_height", videoEncodeParam.encHeight);
                jSONObject5.put("video_encode_fps", videoEncodeParam.encFPS);
                jSONObject5.put("video_encode_br", videoEncodeParam.encBR);
                jSONObject5.put("video_encode_angle", videoEncodeParam.angle);
                jSONObject5.put("video_encode_type", videoEncodeParam.encType);
                jSONObject5.put("video_encode_hw", videoEncodeParam.hw);
                int i2 = i + 1;
                jSONArray.put(jSONObject5);
                i = i2;
            }
            if (i > 0) {
                jSONObject4.put("video_encode_params", jSONArray);
            }
            jSONObject3 = new JSONObject();
            jSONObject3.put("video_send_loss_rate", this.videoSendLossRate);
            jSONObject3.put("video_send_pkt", this.videoSendPkt);
            jSONObject3.put("video_send_i_fec", this.videoSendIFec);
            jSONObject3.put("video_send_sp_fec", this.videoSendSPFec);
            jSONObject3.put("video_send_stnsb_gain_loss", this.videoSendStnSBGain);
            jSONObject3.put("video_send_stnsb_decloss", this.videoSendStnSBDecLoss);
            jSONObject3.put("video_send_stnsb_break", this.videoSendStnSBBreak);
            jSONObject4.put("video_send_param", jSONObject3);
            JSONArray jSONArray2 = new JSONArray();
            Iterator it2 = this.videoDecodeInfo.iterator();
            while (it2.hasNext()) {
                VideoDecodeParam videoDecodeParam = (VideoDecodeParam) it2.next();
                jSONObject2 = new JSONObject();
                jSONObject2.put("video_decode_tiny_id", videoDecodeParam.senderUin);
                jSONObject2.put("video_decode_view_type", videoDecodeParam.decVideoStrType);
                jSONObject2.put("video_decode_view_witdh", videoDecodeParam.decWidth);
                jSONObject2.put("video_decode_view_height", videoDecodeParam.decHeight);
                jSONObject2.put("video_decode_fps", videoDecodeParam.decFPS);
                jSONObject2.put("video_decode_br", videoDecodeParam.decBR);
                jSONObject2.put("video_decode_type", videoDecodeParam.decType);
                jSONObject2.put("video_decode_hw", videoDecodeParam.hw);
                jSONObject2.put("video_recv_loss_rate", videoDecodeParam.recvLossRate);
                jSONObject2.put("video_recv_loss_jitter", videoDecodeParam.recvJitter);
                jSONObject2.put("video_recv_br", videoDecodeParam.recvBR);
                jSONArray2.put(jSONObject2);
            }
            if (this.videoDecodeNum > 0) {
                jSONObject4.put("video_decode_count", this.videoDecodeNum);
                jSONObject4.put("video_decode_params", jSONArray2);
            }
            if (this.majorVidWidth > 0) {
                jSONObject3 = new JSONObject();
                jSONObject3.put("qos_width", this.majorVidWidth);
                jSONObject3.put("qos_height", this.majorVidHeight);
                jSONObject3.put("qos_fps", this.majorVidFPS);
                jSONObject3.put("qos_bitrate", this.majorVidBitrate);
                jSONObject3.put("qos_encode_type", this.majorEncodeType);
                jSONObject3.put("qos_min_qp", this.majorMinQP);
                jSONObject3.put("qos_max_qp", this.majorMaxQP);
                jSONObject3.put("qos_fectype", this.majorFecType);
                jSONObject3.put("qos_i_fec_precent", this.majorIFecMinPkg);
                jSONObject3.put("qos_sp_fec_precent", this.majorSPFecMinPkg);
                jSONObject3.put("qos_p_fec_precent", this.majorPFecMinPkg);
                jSONObject3.put("qos_i_mtu", this.majorIMtu);
                jSONObject3.put("qos_sp_mtu", this.majorSPMtu);
                jSONObject3.put("qos_p_mtu", this.majorPMtu);
                jSONObject3.put("qos_i_fec_min_pkg", this.majorIFecMinPkg);
                jSONObject3.put("qos_sp_fec_min_pkg", this.majorSPFecMinPkg);
                jSONObject3.put("qos_p_fec_min_pkg", this.majorPFecMinPkg);
                jSONObject3.put("qos_i_fec_min_size", this.majorIFecMinSize);
                jSONObject3.put("qos_sp_fec_min_size", this.majorSPFecMinSize);
                jSONObject3.put("qos_p_fec_min_size", this.majorPFecMinSize);
                jSONObject3.put("qos_gop_type", this.majorGopType);
                jSONObject3.put("qos_gop", this.majorGop);
                jSONObject3.put("qos_hw", this.majorHw);
                jSONObject.put("qos_main", jSONObject3);
            }
            if (this.miniVidWidth > 0) {
                jSONObject3 = new JSONObject();
                jSONObject3.put("qos_width", this.miniVidWidth);
                jSONObject3.put("qos_height", this.miniVidHeight);
                jSONObject3.put("qos_fps", this.miniVidFPS);
                jSONObject3.put("qos_bitrate", this.miniVidBitrate);
                jSONObject3.put("qos_encode_type", this.miniEncodeType);
                jSONObject3.put("qos_min_qp", this.miniMinQP);
                jSONObject3.put("qos_max_qp", this.miniMaxQP);
                jSONObject3.put("qos_fectype", this.miniFecType);
                jSONObject3.put("qos_i_fec_precent", this.miniIFecMinPkg);
                jSONObject3.put("qos_sp_fec_precent", this.miniSPFecMinPkg);
                jSONObject3.put("qos_p_fec_precent", this.miniPFecMinPkg);
                jSONObject3.put("qos_i_mtu", this.miniIMtu);
                jSONObject3.put("qos_sp_mtu", this.miniSPMtu);
                jSONObject3.put("qos_p_mtu", this.miniPMtu);
                jSONObject3.put("qos_i_fec_min_pkg", this.miniIFecMinPkg);
                jSONObject3.put("qos_i_sp_fec_min_pkg", this.miniSPFecMinPkg);
                jSONObject3.put("qos_p_fec_min_pkg", this.miniPFecMinPkg);
                jSONObject3.put("qos_i_fec_min_size", this.miniIFecMinSize);
                jSONObject3.put("qos_sp_fec_min_size", this.miniSPFecMinSize);
                jSONObject3.put("qos_p_fec_min_size", this.miniPFecMinSize);
                jSONObject3.put("qos_gop_type", this.miniGopType);
                jSONObject3.put("qos_gop", this.miniGop);
                jSONObject3.put("qos_hw", this.miniHw);
                jSONObject4.put("qos_mini", jSONObject3);
            }
            if (this.captureWidth > 0 || this.videoDecodeNum > 0) {
                jSONObject.put("video_param", jSONObject4);
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject3 = new JSONObject();
            jSONObject3.put("audio_capture_sample", this.audioCaptureSampleRate);
            jSONObject3.put("audio_capture_channel_count", this.audioCaptureChannelCount);
            jSONObject6.put("audio_capture_param", jSONObject3);
            jSONObject3 = new JSONObject();
            jSONObject3.put("audio_encode_type", this.audioEncodeType);
            jSONObject3.put("audio_encode_br", this.audioEncodeBR);
            jSONObject3.put("audio_encode_vad", this.audioEncodeVAD);
            jSONObject6.put("audio_encode_param", jSONObject3);
            jSONObject3 = new JSONObject();
            jSONObject3.put("audio_send_loss_rate", this.audioSendLossRate);
            jSONObject3.put("audio_send_fec", this.audioSendFEC);
            jSONObject3.put("audio_send_jitter", this.audioSendJitter);
            jSONObject3.put("audio_send_br", this.audioSendBR);
            jSONObject3.put("audio_send_br_udt", this.audioSendBRUdt);
            jSONObject3 = new JSONObject();
            jSONObject3.put("qos_sample_rate", this.audioQosSampleRare);
            jSONObject3.put("qos_codec_type", this.audioQosEncodeType);
            jSONObject3.put("qos_channel_count", this.audioQosChannelCount);
            jSONObject3.put("qos_bitrate", this.audioQosEncodeBR);
            jSONObject3.put("qos_aec", this.audioQosAec);
            jSONObject3.put("qos_agc", this.audioQosAgc);
            jSONObject3.put("qos_fec", this.audioQosFec);
            jSONObject3.put("qos_vad", this.audioQosVad);
            jSONObject3.put("qos_pack_duration", this.audioQosPackDuration);
            jSONObject3.put("qos_recn", this.audioQosRecn);
            jSONObject3.put("qos_recm", this.audioQosRecm);
            jSONObject3.put("qos_mtu", this.audioQosMtu);
            jSONObject3.put("qos_jitter_min_delay", this.audioQosJitterMinDelay);
            jSONObject3.put("qos_jitter_min_max_delay", this.audioQosJitterMinMaxDelay);
            jSONObject3.put("qos_jitter_max_max_delay", this.audioQosJitterMaxMaxDelay);
            jSONObject3.put("qos_jitter_drop_scale", this.audioQosJitterDropScale);
            jSONObject6.put("audio_qos_param", jSONObject3);
            if (this.audioDecodeNum > 0) {
                JSONArray jSONArray3 = new JSONArray();
                it2 = this.audioDecodeInfo.iterator();
                while (it2.hasNext()) {
                    AudioDecodeParam audioDecodeParam = (AudioDecodeParam) it2.next();
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("audio_decode_tiny_id", audioDecodeParam.tinyId);
                    jSONObject2.put("audio_decode_type", audioDecodeParam.decodeType);
                    jSONObject2.put("audio_decode_sample_rate", audioDecodeParam.sampleRate);
                    jSONObject2.put("audio_decode_channel_count", audioDecodeParam.channelCount);
                    jSONArray3.put(jSONObject2);
                }
                jSONObject6.put("audio_decode_param", jSONArray3);
                jSONObject3 = new JSONObject();
                jSONObject3.put("audio_recv_play_delay", this.audioRecvPlayDelay);
                jSONObject3.put("audio_recv_loss_rate", this.audioRecvLossRate);
                jSONObject6.put("audio_recv_param", jSONObject3);
            }
            jSONObject6.put("audio_category", this.audioCategory);
            if (this.audioCaptureSampleRate > 0 || this.audioDecodeNum > 0) {
                jSONObject.put("audio_param", jSONObject6);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            QLog.e(TAG, 0, "AVQualityJsonObj to json string error", e);
            return "";
        }
    }
}
