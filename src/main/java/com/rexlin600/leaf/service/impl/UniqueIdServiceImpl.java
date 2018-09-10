package com.rexlin600.leaf.service.impl;

import com.rexlin600.leaf.domain.entity.UniqueId;
import com.rexlin600.leaf.domain.entity.UniqueIdMetaData;
import com.rexlin600.leaf.domain.vo.GenId;
import com.rexlin600.leaf.service.UniqueIdService;
import com.rexlin600.leaf.util.IdExplainUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Administrator
 */
@SuppressWarnings("ALL" )
@Slf4j
public class UniqueIdServiceImpl implements UniqueIdService {

    /**
     * 记录上一毫秒数
     */
    private static long lastTimestamp = -1L;

    /**
     * 记录毫秒内的序列，0-4095
     */
    private static long sequence = 0L;

    /**
     * 工作机器ID
     */
    private final long machineId;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    /**
     * ID生成元数据对象
     */
    protected UniqueIdMetaData uniqueIdMetaData;

    /**
     * 构造器
     *
     * @param machineId
     * @param datacenterId
     */
    public UniqueIdServiceImpl(long machineId, long datacenterId) {
        if (machineId > UniqueIdMetaData.MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", UniqueIdMetaData.MAX_MACHINE_ID));
        }
        if (datacenterId > UniqueIdMetaData.MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", UniqueIdMetaData.MAX_DATACENTER_ID));
        }
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }


    /**
     * 生成唯一ID
     *
     * @return
     */
    @Override
    public synchronized long getId() {
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        validateTimestamp(timestamp, lastTimestamp);

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & UniqueIdMetaData.SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算组成64位ID
        return ((timestamp - UniqueIdMetaData.START_TIME) << UniqueIdMetaData.TIMESTAMP_LEFT_SHIFT_BITS)
                | (datacenterId << UniqueIdMetaData.DATACENTER_SHIFT_BITS)
                | (machineId << UniqueIdMetaData.MACHINE_SHIFT_BITS)
                | sequence;
    }


    /**
     * 解析ID
     *
     * @param id
     * @return
     */
    @Override
    public UniqueId explainId(long id) {
        UniqueId uniqueId = IdExplainUtil.convert(id);
        if (uniqueId == null) {
            log.error("==>  解析ID失败, ID不合法" );
            return null;
        }
        return uniqueId;
    }


    /**
     * 解析生成ID的时间
     *
     * @param time
     * @return
     */
    @Override
    public Date transTime(long time) {
        return new Date(time + UniqueIdMetaData.START_TIME);
    }


    @Override
    public long generateId(GenId genId) {
        long machineId = genId.getMachineId();
        long datacenterId = genId.getDatacenterId();
        long timestamp = genId.getTimestamp();
        long sequence = genId.getSequence();
        if (timestamp == -1 || sequence == -1) {
            throw new IllegalArgumentException("Both time and sequence are required." );
        }
        if (machineId == -1 || datacenterId == -1) {
            throw new IllegalArgumentException("Both time and sequence are required." );
        }
        UniqueId uniqueId = new UniqueId(machineId, datacenterId, sequence, timestamp);
        long id = IdExplainUtil.makeId(uniqueId);
        return id;
    }


    /**
     * 验证时间戳
     *
     * @param timestamp
     * @param lastTimestamp
     */
    private void validateTimestamp(long timestamp, long lastTimestamp) {
        if (timestamp < lastTimestamp) {
            log.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new IllegalStateException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
    }


    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
