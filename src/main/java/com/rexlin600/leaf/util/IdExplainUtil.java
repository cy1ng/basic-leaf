package com.rexlin600.leaf.util;

import com.rexlin600.leaf.domain.entity.UniqueId;
import com.rexlin600.leaf.domain.entity.UniqueIdMetaData;

/**
 * @author Administrator
 */
public class IdExplainUtil {

    /**
     * 唯一ID对象解析返回ID
     *
     * @param uniqueId
     * @return
     */
    public static long convert(UniqueId uniqueId) {
        long result = 0;
        try {
            result = 0L;

            result |= uniqueId.getSequence();

            result |= uniqueId.getMachineId() << UniqueIdMetaData.MACHINE_SHIFT_BITS;

            result |= uniqueId.getDatacenterId() << UniqueIdMetaData.DATACENTER_SHIFT_BITS;

            result |= uniqueId.getTimestamp() << UniqueIdMetaData.TIMESTAMP_LEFT_SHIFT_BITS;

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }


    public static UniqueId convert(long id) {
        UniqueId uniqueId = null;
        try {
            uniqueId = new UniqueId();

            uniqueId.setSequence(id & UniqueIdMetaData.SEQUENCE_MASK);

            uniqueId.setMachineId((id >>> UniqueIdMetaData.MACHINE_SHIFT_BITS) & UniqueIdMetaData.MACHINE_MASK);

            uniqueId.setDatacenterId((id >>> UniqueIdMetaData.DATACENTER_SHIFT_BITS) & UniqueIdMetaData.DATACENTER_MASK);

            uniqueId.setTimestamp((id >>> UniqueIdMetaData.TIMESTAMP_LEFT_SHIFT_BITS) & UniqueIdMetaData.TIMESTAMP_MASK);

        } catch (Exception e) {
            e.printStackTrace();
            return uniqueId;
        }
        return uniqueId;
    }


    public static long makeId(UniqueId uniqueId) {
        return IdExplainUtil.convert(uniqueId);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        UniqueId uniqueId = IdExplainUtil.convert(91554493287763970L);
        System.out.println(uniqueId.toString());
        long id = IdExplainUtil.convert(uniqueId);
        System.out.println(id);
    }

}
