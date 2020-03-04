package boot.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    /*Метод configure(HttpSecurity) определяет, какие URL пути
    должны быть защищены, а какие нет.*/
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http
                .authorizeRequests()
                /*Разрешаем не авторизованный доступ к js-файлам,
                файлам стилей и картинкам иначе они не будут отображаться*/
                .antMatchers("/static/css/*", "/static/js/*", "/static/images/*")
                .permitAll()
                .anyRequest().permitAll();
    }

}



