package com.itheima.reggie.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.itheima.reggie.utils.BaseContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充[insert]");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("createUser", BaseContextUtil.get());
        metaObject.setValue("updateUser", BaseContextUtil.get());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段填充[insert]");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("updateUser", BaseContextUtil.get());
    }
}
