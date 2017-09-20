package com.xiaolan.common.config.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Import({DataSourceConfig.class})
@MapperScan("com.**.mapper")//Mapper扫描
public class DaoConfig implements EnvironmentAware {
    private static Log logger = LogFactory.getLog(DaoConfig.class);
    private RelaxedPropertyResolver mybatis;

    @Override
    public void setEnvironment(Environment environment) {
        this.mybatis = new RelaxedPropertyResolver(environment, "mybatis.");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            if (!StringUtils.isEmpty(mybatis.getProperty("typeAliasesPackage"))) {
                sessionFactory.setTypeAliasesPackage(mybatis.getProperty("typeAliasesPackage"));
            }
            if (!StringUtils.isEmpty(mybatis.getProperty("typeHandlersPackage"))) {
                sessionFactory.setTypeHandlersPackage(mybatis.getProperty("typeHandlersPackage"));
            }
            if (!StringUtils.isEmpty(mybatis.getProperty("mapperLocations"))) {
                sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                        .getResources(mybatis.getProperty("mapperLocations")));
            }
            if (!StringUtils.isEmpty(mybatis.getProperty("configLocation"))) {
                sessionFactory.setConfigLocation(new DefaultResourceLoader()
                        .getResource(mybatis.getProperty("configLocation")));
            }
            return sessionFactory.getObject();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

}