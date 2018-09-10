package com.rexlin600.leaf.service.impl;

import com.rexlin600.leaf.domain.entity.UniqueId;
import com.rexlin600.leaf.domain.vo.GenId;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UniqueIdServiceImplTest {

    @Autowired
    private UniqueIdServiceImpl uniqueIdService;

    @Test
    public void getId() {
        log.info("生成的ID为, {}", uniqueIdService.getId());
    }

    @Test
    public void explainId() {
        long id = uniqueIdService.getId();
        UniqueId uniqueId = uniqueIdService.explainId(id);
        log.info("ID翻译为, {}", uniqueId.toString());
    }

    @Test
    public void transTime() {
        long id = uniqueIdService.getId();
        UniqueId uniqueId = uniqueIdService.explainId(id);
        Date date = uniqueIdService.transTime(uniqueId.getTimestamp());
        String format = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ssS" );
        log.info("翻译时间为，{}", format);
    }


    @Test
    public void generateId1() {
        long id = uniqueIdService.getId();
        System.out.println(id);
        UniqueId uniqueId = uniqueIdService.explainId(id);
        GenId genId = new GenId(uniqueId.getMachineId(), uniqueId.getDatacenterId(), uniqueId.getTimestamp(), uniqueId.getSequence());
        long generateId = uniqueIdService.generateId(genId);
        log.info("多参数生成ID为，{}", generateId);
    }

}