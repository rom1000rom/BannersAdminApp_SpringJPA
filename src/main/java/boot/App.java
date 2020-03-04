package boot;


import boot.dao.BannerRepository;
import boot.model.Banner;
import ch.qos.logback.core.db.dialect.PostgreSQLDialect;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**Класс представляет собой java-конфигурацию Spring Context а также точку входа
 * в Spring Boot приложение.
 @author Артемьев Р.А.
 @version 11.11.2019 */
@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Bean
    @Primary//Аннотация говорит подставлять именно эту реализацию бина в первую очередь
    @Profile("default")
    @ConfigurationProperties("app.datasource.work")
    public DataSourceProperties workDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @Profile("default")
    @ConfigurationProperties("app.datasource.work.configuration")
    //Используем пул соединений HikariCP
    public HikariDataSource workDataSource() {
        return workDataSourceProperties().initializeDataSourceBuilder().
                type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    @Profile("test")
    @ConfigurationProperties("app.datasource.test")
    public DataSourceProperties testDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @Profile("test")
    @ConfigurationProperties("app.datasource.test.configuration")
    //Используем пул соединений HikariCP
    public HikariDataSource testDataSource() {
        return testDataSourceProperties().initializeDataSourceBuilder().
                type(HikariDataSource.class).build();
    }

}
