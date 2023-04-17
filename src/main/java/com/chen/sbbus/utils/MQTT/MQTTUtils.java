package com.chen.sbbus.utils.MQTT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MQTTUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQTTUtils.class);
    @Autowired
    private MQTTSubscribe mqttSubscribe;

    /**
     * 订阅主题
     * @param topic 主题
     * @param qos 消息级别
     * @return
     */
    public Boolean subscribeTopic(String topic, int qos) {
        mqttSubscribe.init(topic, qos);
        LOGGER.info("订阅Topic:"+ topic );
        return true;
    }

    /**
     * 退订主题
     * @param topic 主题
     * @return
     */
    public Boolean unSubscribeTopic(String topic) {
        mqttSubscribe.unionInit(topic);
        LOGGER.info("取消订阅Topic:"+ topic );
        return true;
    }

    public Boolean publishMsg(String topic, String msg, int qos) {
        mqttSubscribe.sendMQTTMessage(topic, msg, qos);
        LOGGER.info("发送了一条主题是‘"+topic+"’，内容是:"+msg+"，消息级别 "+qos);
        return true;
    }
}
