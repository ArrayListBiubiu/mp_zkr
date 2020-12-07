package com.flxin.mp_zkr.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data    //lombok 插件快速生成set()、get()方法
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 0表示未删除，1表示已删除
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private boolean isDelete;

}
