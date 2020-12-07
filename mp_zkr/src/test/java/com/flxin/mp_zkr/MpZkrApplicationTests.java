package com.flxin.mp_zkr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flxin.mp_zkr.entity.User;
import com.flxin.mp_zkr.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)//设置启动器
@SpringBootTest
public class MpZkrApplicationTests {

    @Autowired
    UserMapper userMapper;

    // 1.测试mp环境的搭建
    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 2.增
    @Test
    public void insert(){
        User user = new User();
        user.setName("Tom");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    // 3.1.删
    @Test
    public void deleteById(){
        int result = userMapper.deleteById(5L);
        System.out.println(result);
    }

    // 3.2.批量删除
    @Test
    public void deleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(3, 4));
        System.out.println(result);
    }

    // 3.3.引入逻辑删除的插件，实现逻辑删除
    @Test
    public void deleteById2(){
        int result = userMapper.deleteById(6L);
        System.out.println(result);
    }

    // 4.改
    @Test
    public void updateById(){
        User user = new User();
        user.setId(3L);
        user.setAge(28);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    // 5.1.查
    @Test
    public void selectById(){
        User user = userMapper.selectById(3L);
        System.out.println(user);
    }

    // 5.2.批量查询
    @Test
    public void selectBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(3, 4, 5));
        System.out.println(users);
    }

    // 5.3.利用map对象封装查找条件
    @Test
    public void selectByMap() {
        Map map = new HashMap();
        map.put("name", "Tom");
        map.put("age", 28);
        List list = userMapper.selectByMap(map);
        System.out.println(list);
    }

    // 5.4.分页查询
    @Test
    public void selectByPage() {
        // 1.创建page对象，传入当前页 和 每页记录数
        Page<User> page = new Page<>(1, 3);

        // 2.调用方法实现分页查询，
        // mp的底层逻辑是，将查询到的结果封装到page对象中
        userMapper.selectPage(page, null);

        // 3.从page对象中获取分页的数据
        long current = page.getCurrent();  // 当前页
        List<User> records = page.getRecords();  // 每页数据的List集合
        long size = page.getSize();  // 每页记录数
        long total = page.getTotal();  // 总记录数
        boolean hasNext = page.hasNext();  // 是否有下一页
        boolean hasPrevious = page.hasPrevious();  // 是否有上一页
    }


    // 5.5.条件查询
    @Test
    public void query() {
        // 创建QueryWrapper条件封装器对象（QueryWrapper继承Wrapper）
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 插入条件
        // （1）ge >=    gt >    le <=    lt <
        // （2）eq =    ne !=
        // （3）between
        // （4）like
        // （5）asc    desc
        wrapper.eq("age", 20);    // age=20
        wrapper.between("age", 10, 20);    // age>=10 and age<=20
        wrapper.like("name", "m");    // name like "%m%"
        wrapper.orderByDesc("id");
        wrapper.select("email");    // 查询指定字段

        // 参数是条件封装器对象，如果出入null，那就是 select * from user
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }




}
