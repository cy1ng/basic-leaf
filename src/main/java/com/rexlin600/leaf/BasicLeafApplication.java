package com.rexlin600.leaf;

import com.rexlin600.leaf.config.UniqueParam;
import com.rexlin600.leaf.service.impl.UniqueIdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 */
@Slf4j
@SpringBootApplication
public class BasicLeafApplication {

    @Autowired
    private UniqueParam uniqueParam;

    public static void main(String[] args) {
        SpringApplication.run(BasicLeafApplication.class, args);
    }

    @Bean
    public UniqueIdServiceImpl uniqueIdService() {
        log.info("machine id is :{}", uniqueParam.getMachineId() + "datacenter id is :{}", uniqueParam.getDatacenterId());
        return new UniqueIdServiceImpl(Long.parseLong(uniqueParam.getMachineId()), Long.parseLong(uniqueParam.getDatacenterId()));
    }

}
