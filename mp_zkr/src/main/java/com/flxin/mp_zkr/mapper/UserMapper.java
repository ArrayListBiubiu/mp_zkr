package com.flxin.mp_zkr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flxin.mp_zkr.entity.User;
import org.springframework.stereotype.Repository;

//BaseMapper中已经封装好基本的增删改查等操作，只需要调用即可
@Repository
public interface UserMapper extends BaseMapper<User> {

}