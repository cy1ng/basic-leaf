package com.rexlin600.leaf.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ApiModel(value = "生成ID所需的参数" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenId {

    @Max(1023)
    @Min(0)
    @ApiModelProperty(value = "工作机器ID" )
    @JsonProperty("machineId" )
    private long machineId = -1;

    @Max(1023)
    @Min(0)
    @ApiModelProperty(value = "数据中心ID" )
    @JsonProperty("datacenterId" )
    private long datacenterId = -1;

    @ApiModelProperty(value = "时间戳", required = true)
    @JsonProperty("timestamp" )
    private long timestamp = -1;

    @Max(4095)
    @Min(0)
    @ApiModelProperty(value = "序列号", required = true)
    @JsonProperty("sequence" )
    private long sequence = -1;
}
