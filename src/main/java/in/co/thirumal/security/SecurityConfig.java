/**
 * 
 */
package in.co.thirumal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Thirumal
 *
 */
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
	}*/

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    DefaultHttpFirewall firewall = new DefaultHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    return firewall;
	}


	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()  
        		//.anonymous().disable()
        
                .authorizeRequests()
								//.antMatchers("/voyager/**", "/graphiql", "/graphiql/**", "/vendor/**", "/graphql").permitAll()
						        .anyRequest().authenticated().and()
						.httpBasic().and()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);              
                //.oauth2ResourceServer().jwt();
        return http.build();
    }
	
	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(User.withUsername("user")
	      .password(bCryptPasswordEncoder.encode("user"))
	      .roles("USER")
	      .build());
	    manager.createUser(User.withUsername("admin")
	      .password("{noop}secret")
	      .roles("USER", "ADMIN")
	      .build());
	    return manager;
	}
	 
	 @Bean 
	 public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	 
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/login", "/swagger-ui/index.html", "/v3/**", "swagger-ui/**", "/images/**", "/js/**", "/webjars/**");
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
