package com.rexlin600.leaf.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UniqueId implements Serializable {

    /**
     * 0     +   41       +   5             +       5           +       12
     * 固定  +  时间戳    +   工作机器ID     +     数据中心ID    +      序列号
     */

    private static final long serialVersionUID = 5060227261172398215L;

    /**
     * 工作机器ID、数据中心ID、序列号、上次生成ID的时间戳
     */
    private long machineId;

    private long datacenterId;

    private long sequence;

    private long timestamp;

}
