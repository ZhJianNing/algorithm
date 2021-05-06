package com.zjn.algorithm.xuehua;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SnowFlake  雪花算分
 *
 * 简单介绍下雪花算法：核心思想就是使用一个64位bit的long型数字作为全局id；那么这个64位的bit由如下几部分组成
 * 第一部分：1bit；0，表示为正数
 * 第二部分：41bit，时间戳,（我们系统获取的默认时间戳，都是减去1970-01-01 08:00:00这个时间点的毫秒数）
 * 第三部分：10bit（如果是多机房可以拆分成两部分，即机房部分+机器部分），机器的唯一标识
 * 第四部分：12bit，表示的序号，就是某个机房某台机器上这一毫秒内同时生成的 id 的序号
 *
 *
 *
 * 优点：
 *      高性能高可用：生成时不依赖数据库，完全在内存中生成
 *      容量大：每秒能生成数百万自增的ID
 *      ID自增：存入数据库中，索引效率高
 *
 * 缺点：
 *      依赖于系统时间的一致性，如果系统时间被回调，或者改变，可能会造成id冲突或者重复
 *
 * @author zjn
 * @date 2021/5/6
 **/
public class SnowFlake {


    /**
     * 机器id
     **/
    private final long id;
    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间，时间戳1587433423721L  ==> 2020-04-21 09:43:43
     * 1970-01-01 08:00:00，这个时间已经过去很久了，时间戳的值已经很大了，我们可以定义一个初始时间，以这个时间
     * 为起始时间来获取毫秒数
     */
    private final long epoch = 1587433423721L;
    /**
     * 机器标识位数
     */
    private final long workerIdBits = 10L;
    /**
     * 机器ID最大值: 1023
     */
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
    /**
     * 0，并发控制
     */
    private long sequence = 0L;
    /**
     * 毫秒内自增位
     */
    private final long sequenceBits = 12L;

    /**
     * 12
     */
    private final long workerIdShift = this.sequenceBits;
    /**
     * 22
     */
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
    /**
     * 4095,111111111111,12位
     */
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
    /**
     * 记录产生时间毫秒数，判断是否是同1毫秒
     */
    private long lastTimestamp = -1L;

    /**
     * 传入机器id
     * @param id
     */
    private SnowFlake(long id) {
        if (id > this.maxWorkerId || id < 0) {
            throw new IllegalArgumentException(String.format("机器id不能大于%d或小于0", this.maxWorkerId));
        }
        this.id = id;
    }

    public synchronized long nextId() {
        // 获取当前时间毫秒数
        long timestamp = timeGen();
        if (this.lastTimestamp == timestamp) {
            //如果上一个timestamp与新产生的相等，则sequence加一(最大4095)
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                // 超过最大值进行按位与，结果为0，也就是当这一毫秒序号超过最大值，就会循环等待下一毫秒
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        // 如果时间回退，则报错或者返回-1，调用端进行判断
        if (timestamp < this.lastTimestamp) {
            System.out.println(String.format("时钟回退，拒绝 %d 毫秒内生成雪花id", (this.lastTimestamp - timestamp)));
            return -1;
        }

        this.lastTimestamp = timestamp;

        // | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
        long result;
        // 当前时间-初始时间，然后左移timestampLeftShift，即把时间戳部分移动到bit1~bit41位置（需要移动12+10位）。
        result = timestamp - this.epoch << this.timestampLeftShift;
        // 将机器id左移workerIdShift，合并到result中
        result = result | this.id << this.workerIdShift;
        // 将每毫秒产生的sequence也合并到result中
        result = result | this.sequence;
        //
        return result;
//        return timestamp - this.epoch << this.timestampLeftShift | this.id << this.workerIdShift | this.sequence;
    }

    /**
     * 如果说几十年后id重复了，把机器id加1，再用几十年
     */
    private static SnowFlake flowIdWorker = new SnowFlake(1);


    public static long getSnowFlakeId() {
        return flowIdWorker.nextId();
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        //判断生成的记录是否有重复记录
        final Map<Long, Integer> map = new ConcurrentHashMap();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int s = 0; s < 2000; s++) {
                    long snowFlakeId = SnowFlake.getSnowFlakeId();
                    System.out.println("生成雪花ID="+snowFlakeId);
                    //如果put时，key已存在，则返回该key对应的旧值，否则返回null
                    Integer put = map.put(snowFlakeId, 1);
                    if (put != null) {
                        throw new RuntimeException("主键重复");
                    }
                }
            }).start();

        }
    }
}

