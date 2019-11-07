package com.common.dataSource;

import com.common.paging.PagingInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration // 注入到spring容器中..
@MapperScan(basePackages = "com.impl.dao.master.*", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfiguration {

    /**
     * @methodDesc: 功能描述:(配置test1数据库)
     */
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @methodDesc: 功能描述:(test1 sql会话工厂)
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //MyBatis分页插件
        Interceptor[] plugins =  new Interceptor[]{new PagingInterceptor()};
        bean.setPlugins(plugins);
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    /**
     * 当存在多于1个数据源的时候，必须选择一个作为主数据源（Primary DataSource），
     * 即如果数据库操作没有指明使用哪个数据源的时候，默认使用主数据源。
     * 同时，把数据源绑定到不同的JdbcTemplate上。
     * 用@Primary把其中某一个Bean标识为“主要的”，使用@Autowired注入时会首先使用被标记为@Primary的Bean。
     */
    /**
     * @methodDesc: 功能描述:(test1 事物管理)
     */
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 使用@Autowired注释进行byType注入，如果需要byName（byName就是通过id去标识）注入，
     * 增加@Qualifier注释。一般在候选Bean数目不为1时应该加@Qualifier注释。
     * 在默认情况下使用 @Autowired 注释进行自动注入时，Spring 容器中匹配的候选 Bean 数目必须有且仅有一个。
     * 当找不到一个匹配的 Bean 时，Spring 容器将抛出
     * BeanCreationException 异常，并指出必须至少拥有一个匹配的 Bean。
     * 和找不到一个类型匹配 Bean 相反的一个错误是：如果 Spring 容器中拥有多个候选 Bean，
     * Spring 容器在启动时也会抛出 BeanCreationException 异常。
     * Spring 允许我们通过 @Qualifier 注释指定注入 Bean 的名称，这样歧义就消除了，可以通过下面的方法解决异常：
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(
            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}