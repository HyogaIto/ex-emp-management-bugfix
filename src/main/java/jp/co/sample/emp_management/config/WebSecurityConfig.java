package jp.co.sample.emp_management.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ログインページを指定。
        //ログインページへのアクセスは全員許可する。
        http.formLogin().loginPage("/")
        .permitAll();
        http.authorizeRequests().antMatchers("/css/**","/js/**","/img/**","/","/login","/insert","/toInsert")
        .permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
    

    
}