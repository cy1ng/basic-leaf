package com.rexlin600.leaf.util;

import com.rexlin600.leaf.domain.entity.UniqueId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IdExplainUtilTest {

    @Test
    public void convert() {
        log.info("解析ID，{}", IdExplainUtil.convert(91573932649484290L).toString());
    }

    @Test
    public void convert1() {
        UniqueId uniqueId = new UniqueId(1L, 1L, 2L, 21832926905L);
        log.info("解析对象，{}", IdExplainUtil.convert(uniqueId));
    }

    @Test
    public void makeId() {
        UniqueId uniqueId = new UniqueId(1L, 1L, 2L, 21832926905L);
        log.info("生成ID，{}", IdExplainUtil.makeId(uniqueId));
    }

    @Test
    public void main() {
        // 略
    }

}