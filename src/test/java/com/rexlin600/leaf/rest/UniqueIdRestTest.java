package com.rexlin600.leaf.rest;

import com.rexlin600.leaf.domain.vo.GenId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UniqueIdRestTest {

    @Autowired
    private UniqueIdRest uniqueIdRest;

    @Test
    public void genId() {
        log.info("生成的ID为, {}", uniqueIdRest.genId());
    }

    @Test
    public void explainId() {
        log.info("解析ID的元数据信息，{}", uniqueIdRest.explainId(uniqueIdRest.genId()));
    }

    @Test
    public void transTime() {
        log.info("解析时间戳，{}", uniqueIdRest.transTime(21832582419L));
    }

    @Test
    public void makeId() {
        GenId genId = new GenId(1L, 1L, 21832926905L, 2L);
        log.info("传入指定参数生成ID，{}", uniqueIdRest.makeId(genId));
    }
}