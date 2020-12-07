package com.flxin.mp_zkr.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 添加的时候，执行该方法
    // 第一次添加的时候，"createTime"，"updateTime"都要添加
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("isDelete", 0, metaObject);
    }

    // 修改的时候，执行该方法
    // 修改的时候，只需要修改"updateTime"的值即可
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}
