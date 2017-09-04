package com.sijla.bean;

import com.sijla.an.Order;
import com.sijla.j.b;

@Order(order = {"appkey", "androidId", "appid", "appver", "blumac", "brand", "channel", "cpuCore", "cpuSerial", "imei", "imsi", "mode", "osver", "pn", "quid", "ram", "resolution", "rom", "root", "roserial", "scrSize", "simSerial", "status", "ts", "uuid", "wifimac", "mno", "addr", "cuid", "uid3"})
public class TruthInfo extends Info {
    private String addr = "";
    private String androidId = "";
    private String appid = "";
    private String appkey = "";
    private String appver = "";
    private String blumac = "";
    private String brand = "";
    private String channel = "";
    private String cpuCore = "";
    private String cpuSerial = "";
    private String cuid = "";
    private String imei = "";
    private String imsi = "";
    private String mno = "";
    private String mode = "";
    private String osver = "";
    private String pn = "";
    private String quid = "";
    private String ram = "";
    private String resolution = "";
    private String rom = "";
    private String root = "";
    private String roserial = "";
    private String scrSize = "";
    private String simSerial = "";
    private String status = "1";
    private String ts = (b.g() + "");
    private String uid3 = "";
    private String uuid = "";
    private String wifimac = "";

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public String getAppver() {
        return this.appver;
    }

    public void setAppver(String str) {
        this.appver = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getQuid() {
        return this.quid;
    }

    public void setQuid(String str) {
        this.quid = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getPn() {
        return this.pn;
    }

    public void setPn(String str) {
        this.pn = str;
    }

    public String getSimSerial() {
        return this.simSerial;
    }

    public void setSimSerial(String str) {
        this.simSerial = str;
    }

    public String getCpuSerial() {
        return this.cpuSerial;
    }

    public void setCpuSerial(String str) {
        this.cpuSerial = str;
    }

    public String getCpuCore() {
        return this.cpuCore;
    }

    public void setCpuCore(String str) {
        this.cpuCore = str;
    }

    public String getRoserial() {
        return this.roserial;
    }

    public void setRoserial(String str) {
        this.roserial = str;
    }

    public String getWifimac() {
        return this.wifimac;
    }

    public void setWifimac(String str) {
        this.wifimac = str;
    }

    public String getBlumac() {
        return this.blumac;
    }

    public void setBlumac(String str) {
        this.blumac = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getRam() {
        return this.ram;
    }

    public void setRam(String str) {
        this.ram = str;
    }

    public String getRom() {
        return this.rom;
    }

    public void setRom(String str) {
        this.rom = str;
    }

    public String getOsver() {
        return this.osver;
    }

    public void setOsver(String str) {
        this.osver = str;
    }

    public String getScrSize() {
        return this.scrSize;
    }

    public void setScrSize(String str) {
        this.scrSize = str;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public String getRoot() {
        return this.root;
    }

    public void setRoot(String str) {
        this.root = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    public void setAndroidId(String str) {
        this.androidId = str;
    }

    public String getMno() {
        return this.mno;
    }

    public void setMno(String str) {
        this.mno = str;
    }

    public String getTs() {
        return this.ts;
    }

    public void setTs(String str) {
        this.ts = str;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String str) {
        this.addr = str;
    }

    public String getCuid() {
        return this.cuid;
    }

    public void setCuid(String str) {
        this.cuid = str;
    }

    public String getUid3() {
        return this.uid3;
    }

    public void setUid3(String str) {
        this.uid3 = str;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public void setAppkey(String str) {
        this.appkey = str;
    }

    public String toString() {
        return "{\"status\":\"" + this.status + "\",\"appid\":\"" + this.appid + "\",\"appver\":\"" + this.appver + "\",\"channel\":\"" + this.channel + "\",\"uuid\":\"" + this.uuid + "\",\"quid\":\"" + this.quid + "\",\"imei\":\"" + this.imei + "\",\"imsi\":\"" + this.imsi + "\",\"pn\":\"" + this.pn + "\",\"simSerial\":\"" + this.simSerial + "\",\"cpuSerial\":\"" + this.cpuSerial + "\",\"cpuCore\":\"" + this.cpuCore + "\",\"roserial\":\"" + this.roserial + "\",\"wifimac\":\"" + this.wifimac + "\",\"blumac\":\"" + this.blumac + "\",\"brand\":\"" + this.brand + "\",\"mode\":\"" + this.mode + "\",\"ram\":\"" + this.ram + "\",\"rom\":\"" + this.rom + "\",\"osver\":\"" + this.osver + "\",\"scrSize\":\"" + this.scrSize + "\",\"resolution\":\"" + this.resolution + "\",\"root\":\"" + this.root + "\",\"androidId\":\"" + this.androidId + "\",\"mno\":\"" + this.mno + "\",\"addr\":\"" + this.addr + "\",\"cuid\":\"" + this.cuid + "\",\"ts\":\"" + this.ts + "\"}";
    }
}
