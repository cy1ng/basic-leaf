package com.rexlin600.leaf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties(prefix = "unique" )
@Data
public class UniqueParam {

    private String machineId;

    private String datacenterId;

}
