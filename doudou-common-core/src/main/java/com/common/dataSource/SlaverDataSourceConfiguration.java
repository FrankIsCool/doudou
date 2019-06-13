package com.common.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 自定义数据库
 */
@Configuration // 注入到spring容器中..
@MapperScan(basePackages = "com.impl.dao.slaver.*", sqlSessionFactoryRef =
        "slaverSqlSessionFactory")
public class SlaverDataSourceConfiguration {

    /**
     * @methodDesc: 功能描述:(配置test2数据库)
     */
    @Bean(name = "slaverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource slaverDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @methodDesc: 功能描述:(test2 sql会话工厂)
     */
    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaverDataSource")
                                                           DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * @methodDesc: 功能描述:(test2 事物管理)
     */
    @Bean(name = "slaverTransactionManager")
    public DataSourceTransactionManager
    testTransactionManager(@Qualifier("slaverDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "slaverSqlSessionTemplate")
    public SqlSessionTemplate slaverSqlSessionTemplate(
            @Qualifier("slaverSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}