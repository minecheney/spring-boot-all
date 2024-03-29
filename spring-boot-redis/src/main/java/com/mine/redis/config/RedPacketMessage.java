package com.mine.redis.config;

import java.io.Serializable;

/**
 * RedPacketMessage
 *
 * @Author mineChen
 * @Date 2020/8/12 9:49
 */
public class RedPacketMessage implements Serializable {

    /**
     * 红包 ID
     */
    private long redPacketId;

    /**
     * 创建时间戳
     */
    private long timestamp;

    public RedPacketMessage() {

    }

    public RedPacketMessage(long redPacketId) {
        this.redPacketId = redPacketId;
        this.timestamp = System.currentTimeMillis();
    }

    public long getRedPacketId() {
        return redPacketId;
    }

    public long getTimestamp() {
        return timestamp;
    }

}