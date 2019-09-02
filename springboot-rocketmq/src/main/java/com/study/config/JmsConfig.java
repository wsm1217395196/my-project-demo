package com.study.config;

public class JmsConfig {
    /**
     * 地址，因为是集群部署 所以有多个用 分号 隔开
     */
    public final static String namesrvAddr = "192.168.199.129:9876";

    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public final static String topic = "topic";

    /**
     * 生产者
     */
    public final static String producerName = "producer";

    /**
     * 消费者
     */
    public final static String consumerName = "consumer";
}
