package com.flxin.mp_zkr.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@MapperScan("com.flxin.mp_zkr.mapper")  // 既然增加了配置类，就把这个注释由启动类中转移到这里，逻辑上更加合适
public class MyConfig {

    // 分页插件插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    // 逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    // 性能分析插件
    // 用于输出每条SQL语句及其执行时间，开发环境使用，线上不推荐
    @Bean
    @Profile({"dev","test"})    // 声明仅在开发环境、测试环境下开启SQL性能分析（需要在application.properties配置文件中，声明当前是什么环境）
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100);    //SQL最大执行时长ms，超过此处设置的值，抛出异常
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
