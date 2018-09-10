package com.rexlin600.leaf.rest;

import com.rexlin600.leaf.domain.entity.UniqueId;
import com.rexlin600.leaf.domain.vo.GenId;
import com.rexlin600.leaf.service.UniqueIdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Api(value = "/leaf", tags = "UniqueIdRest" )
@RestController
@Slf4j
public class UniqueIdRest {

    @Autowired
    private UniqueIdService uniqueIdService;

    @GetMapping(value = "/id" )
    @ApiOperation(value = "生成唯一ID", httpMethod = "GET", notes = "成功则返回ID", response = long.class)
    public long genId() {
        long id = uniqueIdService.getId();
        log.info("id is {}", id);
        return id;
    }


    @GetMapping("/id/{id:[0-9]*}" )
    @ApiOperation(value = "解析ID的元数据信息", httpMethod = "GET", notes = "成功则返回ID的元数据信息（json格式）", response = UniqueId.class)
    public UniqueId explainId(@ApiParam(value = "待解析的ID", required = true) @PathVariable("id" ) long id) {
        UniqueId uniqueId = uniqueIdService.explainId(id);
        log.info("UniqueId is {}", uniqueId);
        return uniqueId;
    }


    @GetMapping("/time/{time:[0-9]*}" )
    @ApiOperation(value = "解析时间戳", httpMethod = "GET", notes = "成功返回标准时间格式的日期时间（毫秒级）", response = String.class)
    public String transTime(
            @ApiParam(value = "待解析的时间戳", required = true) @PathVariable("time" ) long time) {
        log.info("time is {}", time);
        return DateFormatUtils.format(uniqueIdService.transTime(time), "yyyy-MM-dd HH:mm:ssS" );
    }


    @PostMapping("/id" )
    @ApiOperation(value = "传入指定参数生成ID", httpMethod = "POST", notes = "成功返回ID", response = long.class)
    public long makeId(
            @ApiParam(value = "传入的指定参数（json格式）", required = true) @RequestBody GenId genId) {
        log.info("the param is {}", genId.toString());
        return uniqueIdService.generateId(genId);
    }

}
