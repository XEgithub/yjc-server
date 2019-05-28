package com.dk.config;

import cn.sourcespro.commons.interceptor.LRTreeInterceptor;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * conf
 *
 * @author xyz
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"cn.sourcespro.commons.dao", "com.dk.data.dao"})
public class MybatisPlusConfig {

    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.info("初始化分页插件");
        return new PaginationInterceptor();
    }

    /**
     * 左右值插件
     */
    @Bean
    public LRTreeInterceptor lrTreeInterceptor() {
        logger.info("初始化左右值插件");
        return new LRTreeInterceptor();
    }

    /**
     * 逻辑删除
     */
    @Bean
    public ISqlInjector sqlInjector() {
        logger.info("初始化逻辑删除插件");
        return new LogicSqlInjector();
    }

}
