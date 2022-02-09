/*
 * MIT License
 *
 * Copyright (c) 2021 James Zow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software
 */
package ai.james.common;

/**
 * 雪花ID生成工具
 */
public class SnowFlake {
    /**
     * 起始的时间戳
     */
    private final static long twepoch = 0L;

    /**
     * 每一部分占用的位数
     */
    private final static long workerIdBits = 5L;
    private final static long datacenterIdBits = 5L;
    private final static long sequenceBits = 12L;

    /**
     * 每一部分的最大值
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final static long maxSequence = -1L ^ (-1L << sequenceBits);

    /**
     * 每一部分向左的位移
     */
    private final static long workerIdShift = sequenceBits;
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    private final static long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 数据中心ID
     */
    private long datacenterId;
    /**
     * 机器ID
     */
    private long workerId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    private final static SnowFlake snowFlake=new SnowFlake(1,16);

    public SnowFlake(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0L) {
                timestamp = tilNextMillis();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        // 返回 时间戳部分 数据中心部分 机器标识部分 序列号部分
        return (timestamp - twepoch) << timestampShift
                | datacenterId << datacenterIdShift
                | workerId << workerIdShift
                | sequence;
    }

    private long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public  static String autoSnowId(){
        return String.valueOf(snowFlake.nextId());
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(1, 12);
        System.out.println(String.valueOf(snowFlake.nextId()));
    }


}
