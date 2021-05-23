package com.wei.base.constant;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public enum  ExceptionCodeEnum {
    networkException("500", "internal.server.error"),
    networkEmptyMqttTopic("598", "empty.mqtt.topic"),
    networkIllegalAddress("597", "illegal.network.address"),
    interfaceExceptionTasknameIsNull("9001", "taskName.can.not.be.null"),
    interfaceExceptionResendpointIsNull("9002", "resEndPoint.can.not.be.null"),
    interfaceExceptionNoToken("9003", "interface.exception.no.token"),
    numberFormatException("5001", "config.error.should.be.number"),
    dateFormatException("5002", "config.error.should.be.date"),
    mqttTopicErrorModuleFunctionIsNUll("30022", "mqtt.topic.error.module.function.is.null"),
    mqttTopicGroupIsNUll("30022", "mqtt.topic.error.group.is.null"),
    hbccException("10000", "hbcc.error"),
    hbccNetworkException("10001", "hbcc.network.error"),
    hbccClassNotFoundException("10002", "hbcc.class.not.found.exception"),
    hbccMsgTypeNotSetException("10003", "hbcc.msgtype.not.set.exception"),
    hbccIllegalContentException("20000", "hbcc.session.illegal.content.type"),
    caCertificateNumberException("600", "ca.rsa.years.should.greater.then.zero"),
    caNoCretFileFoundException("600", "ca.no.cretfile.found.exception"),
    websocketEmptyConfigException("700", "websocket.empty.client.configexception"),
    websocketNoPathException("701", "websocket.empty.client.not.path.exception"),
    websocketNoEndpointClassException("702", "websocket.empty.client.not.endpoint.class.exception"),
    securityCANotRecognizedExcetion("801", "security.ca.not.recognized.excetion"),
    securityEmptyCretExcetion("802", "security.empty.cret.excetion"),
    securityEmptyMainOrgExcetion("803", "security.empty.main.org.excetion"),
    securityEmptyReceiverExcetion("804", "security.empty.receiver.excetion");

    private String expCode;
    private String expMsg;

    public String getExpCode() {
        return this.expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpMsg() {
        return this.expMsg;
    }

    public void setExpMsg(String expMsg) {
        this.expMsg = expMsg;
    }

    private ExceptionCodeEnum(String expCode, String expMsg) {
        this.expCode = expCode;
        this.expMsg = expMsg;
    }

    public static ExceptionCodeEnum fromName(String name) {
        return valueOf(name);
    }

    public static ExceptionCodeEnum fromElem(String code) {
        ExceptionCodeEnum[] types = values();

        for(int x = 0; x < types.length; ++x) {
            if (types[x].expCode.equals(code) || types[x].expMsg.equals(code)) {
                return types[x];
            }
        }

        throw new IllegalArgumentException("No enum found for the passed code: " + code);
    }
}
