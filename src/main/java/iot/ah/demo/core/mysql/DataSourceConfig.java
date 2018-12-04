package iot.ah.demo.core.mysql;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Value("com.mysql.jdbc.Driver")
    private String DB_DRIVER;

    private static String DB_USER = "iot.hosting.demodb.mysqlUser";

    private static String DB_LOGINPWD = "iot.hosting.demodb.mysqlPassword";

    private static String DB_URL = "iot.hosting.demodb.mysqlUrl";

    @Bean("msql-datasource")
    DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(env.getProperty(DB_URL));
        dataSource.setUsername(env.getProperty(DB_USER));
        dataSource.setPassword(env.getProperty(DB_LOGINPWD));
        return dataSource;
    }

    @Bean(name = "mysql-jdbctemplate")
    JdbcTemplate jdbcTemplate(@Qualifier("msql-datasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
