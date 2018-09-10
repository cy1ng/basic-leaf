package com.rexlin600.leaf.domain.entity;

import lombok.Data;

/**
 * @author Administrator
 */
@SuppressWarnings("ALL" )
@Data
public class UniqueIdMetaData {

    /**
     * 2002年的第一场雪，比以往时候来的更晚一些
     * 2002-01-01开始时间戳
     */
    public static final long START_TIME = 1009814400000L;

    /**
     * 机器ID所占位数
     */
    public static final long MACHINE_ID_BITS = 5L;

    /**
     * 机器ID最大值31，0-31
     */
    public static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);

    /**
     * 数据中心ID所占位数
     */
    public static final long DATACENTER_ID_BITS = 5L;

    /**
     * 数据中心ID最大值31，0-31
     */
    public static final long MAX_DATACENTER_ID = ~(-1L << MACHINE_ID_BITS);

    /**
     * Sequence所占位数
     */
    public static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID偏移量12
     */
    public static final long MACHINE_SHIFT_BITS = SEQUENCE_BITS;

    /**
     * 数据中心ID偏移量12+5=17
     */
    public static final long DATACENTER_SHIFT_BITS = SEQUENCE_BITS + MACHINE_ID_BITS;

    /**
     * 时间戳的偏移量12+5+5=22
     */
    public static final long TIMESTAMP_LEFT_SHIFT_BITS = SEQUENCE_BITS + MACHINE_ID_BITS + DATACENTER_ID_BITS;

    /**
     * Sequence掩码4095
     */
    public static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 机器ID掩码1023
     */
    public static final long MACHINE_MASK = ~(-1L << MACHINE_ID_BITS);

    /**
     * 数据中心掩码1023
     */
    public static final long DATACENTER_MASK = ~(-1L << MACHINE_ID_BITS);

    /**
     * 时间戳掩码2的41次方减1
     */
    public static final long TIMESTAMP_MASK = ~(-1L << 41L);

}
