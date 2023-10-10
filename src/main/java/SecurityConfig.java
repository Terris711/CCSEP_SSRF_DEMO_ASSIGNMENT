//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Disable CSRF for simplicity. Consider enabling it in production.
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN") // Only allow users with ADMIN role to access /admin endpoints
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic(); // Use basic authentication for simplicity. Consider using JWT or OAuth2 in production.
//    }
//}