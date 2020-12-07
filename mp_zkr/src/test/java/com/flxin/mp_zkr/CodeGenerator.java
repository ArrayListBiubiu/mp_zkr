package com.flxin.mp_zkr;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;



/**
 * 主要修改：
 *      1.gc.setAuthor("lzx");  文档注释的作者
 *      2.gc.setIdType(IdType.ID_WORKER);  主键策略
 *      3.数据库名、用户名、密码
 *      4.生成的包名
 *      5.表名
 * 注：
 *      生成的代码已经删除了，因为还需要引入web、swagger等的依赖，这里仅作为mp的总结，如有需要下次自己手动引入
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1.创建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 2.全局配置
        GlobalConfig gc = new GlobalConfig();
        // 设置代码生成路径
        String projectPath = System.getProperty("user.dir");  // E:\workspace-idea\mp_zkr
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setAuthor("lzx");  // 新生成的类中文档注释：@author lzx
        gc.setOpen(false); // 生成代码之后，是否自动打开文件夹
        gc.setFileOverride(false); // 重新生成时文件是否覆盖
        gc.setServiceName("%sService");  // 去掉Service接口的首字母I，否则生成的接口为"IUserService"
        gc.setIdType(IdType.ID_WORKER);  // 主键策略，user表主键是bigint->ID_WORKER_STR
        gc.setDateType(DateType.ONLY_DATE);  //datetime->Date，  datetime->LocalDateTime（默认）
        gc.setSwagger2(true);  //开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 3.数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db1");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4.包配置
        PackageConfig pc = new PackageConfig();
        // 生成的包名为：com.flxin.user
        pc.setParent("com.flxin");
        pc.setModuleName("user");

        pc.setController("controller");  // "controller"的包名：com.flxin.user.controller
        pc.setEntity("entity");  // "entity"的包名：com.flxin.user.entity
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");  // 设置表名称，也可以设置多张表：strategy.setInclude("user", "student", "teacher")
        strategy.setNaming(NamingStrategy.underline_to_camel);  //数据库中表映射到java代码的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_");  //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);  //数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true);  // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setRestControllerStyle(true);  //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true);  //url中驼峰转连字符
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
