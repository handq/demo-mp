package com.example.demo.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.*;

/**
 * Created by handq on 2019/6/7.
 */
@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/test1")
    public String test01(){
        //id查
        User user = userMapper.selectById("1");
        user.setId(null);
        //insert
        int count = userMapper.insert(user);
        if (count>0){
            logger.info("insert seccuessfull ");
        }
        //delete
        int count2 =userMapper.deleteById(0);
        if (count2>0){
            logger.info("delete seccuessfull ");
        }

        //批量查
        List<Long> ids= new ArrayList<>();
        ids.add(1136891017674473473L);
        int count3= userMapper.deleteBatchIds(ids);
        if (count3>0){
            logger.info("batchDelete seccuessfull ");
        }

        Map<String,Object> parmas = new HashMap<>();
        parmas.put("id",1L);
        parmas.put("name", "Jack");
        List<User> results = userMapper.selectByMap(parmas);
        if (results.size()>0){
            results.forEach(User -> System.out.println(User));
        }

        //简单条件查询
        QueryWrapper contions = new QueryWrapper();
        contions.eq("id", "1");
        Integer count4 = userMapper.selectCount(contions);
        if (count4>0){
            logger.info("queryWapper case successfull");
        }

        //lambda条件查询
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper();
        wapper.eq(User::getId, "3").eq(User::getName, "Jack");
//        wapper.eq(User::getName, "Jack");
        List<User> results2 =userMapper.selectList(wapper);
        if (results2.size()>0){
            logger.info("lamdba query select successfull");
        }

        //分页查询 (需要配置分页插件PaginationInterceptor)
        LambdaQueryWrapper<User> wapper2 = new LambdaQueryWrapper();
        wapper2.isNotNull(User::getId);
        IPage<User> results4 = userMapper.selectPage(new Page(0, 2), wapper2);
        if (results4 !=null){
            logger.info("page of query is successfull");
            System.out.println(results4.getRecords());
        }

        //更新
        user.setName("test");
        int count5 = userMapper.updateById(user);
        if (count5>0){
            logger.info("update data successfull,user={}", user.toString());
        }

        //条件更新
        User user1 = new User();
        user1.setName("6");
        LambdaUpdateWrapper<User> wapperUpdate = new LambdaUpdateWrapper();
        wapper.eq(User::getId,1136904415615225858L);
        int count6 = userMapper.update(user1,wapperUpdate);
        if (count6>0){
            logger.info("update data successfull,user={}",user.toString());
        }

        return user.toString();
    }


    //代码生成
    public static void main(String[] args){
        //全局配置

        GlobalConfig config = new GlobalConfig();

        config.setActiveRecord(true) //是否支持AR模式

        .setAuthor("handaquan") //作者

        .setOutputDir("D:\\mi_workspace\\demo-mp\\src\\main\\java") //生成路径

        .setFileOverride(true)//文件覆盖

        .setServiceName("%sService") //设置生成的service接口名 首字母是否为I

        .setIdType(IdType.AUTO);//主键策略 ;


        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();

        dsConfig.setDbType(DbType.MYSQL)

        .setUrl("jdbc:mysql://localhost:3306/handq_all?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC")

        .setDriverName("com.mysql.jdbc.Driver")

        .setUsername("root")

        .setPassword("mysqladmin");

//策略配置

        StrategyConfig stConfig = new StrategyConfig();

        stConfig.setCapitalMode(true) // 全局大写命名
        .setNaming(NamingStrategy.underline_to_camel) // 数据 库表映射到实体的命名策略

        .setInclude("t_account") //生成的表

        .setTablePrefix("t_"); // 表前缀

//包名策略

        PackageConfig pkConfig = new PackageConfig();

        pkConfig.setParent("com.example.demo")

        .setController("controller")

        .setEntity("beans")

        .setService("service");

        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)

        .setDataSource(dsConfig)

        .setStrategy(stConfig)

        .setPackageInfo(pkConfig);

        ag.execute();

    }
}
