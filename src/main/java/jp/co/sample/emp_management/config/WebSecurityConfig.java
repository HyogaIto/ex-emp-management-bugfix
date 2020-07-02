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

	private static String ROLE_ADMIN = "ADMIN";
	@Autowired
    private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
//    	http.authorizeRequests().antMatchers("/employee/**").hasRole(ROLE_ADMIN);
    	http.authorizeRequests().antMatchers("/css/**","/js/**","/img/**","/","/login","/insert","/toInsert")
    	.permitAll();
    	
    	
        //ログインページを指定。
        //ログインページへのアクセスは全員許可する。
//        http.formLogin()
//        .loginPage("/")
//        .loginProcessingUrl("/login")
//        .usernameParameter("mailAddress")
//        .passwordParameter("password")
//        .defaultSuccessUrl("/employee/showList")
//        .permitAll();
    	 
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("name").password("password").roles("ADMIN");
    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
    

    
}