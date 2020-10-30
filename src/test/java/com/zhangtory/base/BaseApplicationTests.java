package com.zhangtory.base;

import com.alibaba.fastjson.JSONObject;
import com.zhangtory.base.component.RedisHelper;
import com.zhangtory.base.model.entity.LmRefundLog;
import com.zhangtory.base.service.ILmRefundLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BaseApplicationTests {

    @Autowired
    private ILmRefundLogService lmRefundLogService;

    @Autowired
    private RedisHelper redisHelper;

    @Test
    void redisTest() {
        Object o = redisHelper.get("yysc:czb:token");
        log.info(o.toString());
    }

    @Test
    void contextLoads() {
        LmRefundLog byId = lmRefundLogService.getById(11);
        log.info(JSONObject.toJSONString(byId));
    }

}
