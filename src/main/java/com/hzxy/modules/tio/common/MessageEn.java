package com.hzxy.modules.tio.common;

/**
 * @author YanBiuBiu
 * @date 2018-12-07 00:10
 */
public class MessageEn {

    public MessageEn(){

    }

    /**
     * 帧头码(2字节 short)
     */
     private String frameHead;
     /**
     * 数据帧字节数(2字节 short)
     */
    private String dataLen;
    /**
     * 功能码(1字节 byte)
     */
    private String functionCode;
    /**
     * 箱柜地址码(15字节 String)
     */
    private String deviceNo;
    /**
     * 数据段(42字节 byte[])
     */
    private String dataSegment;
    /**
     * 帧尾码(2字节 short)
     */
    private String frameTail;

    /**
     * 柜门的状态(26字节 String)
     */
    private String cabinetStatus;
    /**
     * 预留1(1字节 byte)
     */
    private String book1;
    /**
     * 预留2(15字节 byte[])
     */
    private String book2;
    /**
     * 柜门的开锁请求/回应(26字节 String)
     */
    private String openRequestReply;
    /**
     * 开箱类型(1字节 byte)
     */
    private String openType;
    /**
     * 订单号(15字节 byte[])
     */
    private String orderNo;
    /**
     * 错误信息
     */
    private String error;

    public String getFrameHead() {
        return frameHead;
    }

    public void setFrameHead(String frameHead) {
        this.frameHead = frameHead;
    }

    public String getDataLen() {
        return dataLen;
    }

    public void setDataLen(String dataLen) {
        this.dataLen = dataLen;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDataSegment() {
        return dataSegment;
    }

    public void setDataSegment(String dataSegment) {
        this.dataSegment = dataSegment;
    }

    public String getFrameTail() {
        return frameTail;
    }

    public void setFrameTail(String frameTail) {
        this.frameTail = frameTail;
    }

    public String getCabinetStatus() {
        return cabinetStatus;
    }

    public void setCabinetStatus(String cabinetStatus) {
        this.cabinetStatus = cabinetStatus;
    }

    public String getBook1() {
        return book1;
    }

    public void setBook1(String book1) {
        this.book1 = book1;
    }

    public String getBook2() {
        return book2;
    }

    public void setBook2(String book2) {
        this.book2 = book2;
    }

    public String getOpenRequestReply() {
        return openRequestReply;
    }

    public void setOpenRequestReply(String openRequestReply) {
        this.openRequestReply = openRequestReply;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getHeartbeat(){
        return frameHead+dataLen+functionCode+deviceNo+cabinetStatus+book1+book2+frameTail;
    }

}
