package com.xiaolan.config.mybatis;

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

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Import({DataSourceConfig.class})
@MapperScan("com.xiaolan.**.mapper")//Mapper扫描
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
            if (mybatis
                    .getProperty("typeAliasesPackage") != null) {
                sessionFactory.setTypeAliasesPackage(mybatis
                        .getProperty("typeAliasesPackage"));
            }
            if (mybatis
                    .getProperty("mapperLocations") != null) {
                sessionFactory
                        .setMapperLocations(new PathMatchingResourcePatternResolver()
                                .getResources(mybatis
                                        .getProperty("mapperLocations")));
            }
            if (mybatis
                    .getProperty("configLocation") != null) {
                sessionFactory
                        .setConfigLocation(new DefaultResourceLoader()
                                .getResource(mybatis
                                        .getProperty("configLocation")));

            }

            return sessionFactory.getObject();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

}