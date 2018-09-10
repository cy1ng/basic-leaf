package com.rexlin600.leaf.service;


import com.rexlin600.leaf.domain.entity.UniqueId;
import com.rexlin600.leaf.domain.vo.GenId;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Administrator
 */
@Service
public interface UniqueIdService {

    long getId();

    UniqueId explainId(long id);

    Date transTime(long time);


    long generateId(GenId genId);

}
