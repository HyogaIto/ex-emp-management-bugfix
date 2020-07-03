package jp.co.sample.emp_management.config;

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

//	private static String ROLE_ADMIN = "ADMIN";
	//@Autowired
//    private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
//    	http.authorizeRequests().antMatchers("/employee/**").hasRole(ROLE_ADMIN);
    	http.authorizeRequests().antMatchers("/css/**","/js/**","/img/**","/","/login","/insert","/toInsert","/error/**")
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("name").password("password").roles("ADMIN");
//    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
    

    
}