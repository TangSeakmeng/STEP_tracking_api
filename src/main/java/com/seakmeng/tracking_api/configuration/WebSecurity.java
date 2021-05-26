package com.seakmeng.tracking_api.configuration;

import com.seakmeng.tracking_api.security.JWTAuthenticationFilter;
import com.seakmeng.tracking_api.security.JWTAuthorizationFilter;
import com.seakmeng.tracking_api.service.UserDetailServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  public static final String SIGN_UP_URL = "/api/users";
  public static final String CUSTOMER_SIGN_UP_URL = "/api/customers";

  @SuppressWarnings("unused")
  @Autowired private UserDetailServiceImpl userDetailService;

  @SuppressWarnings("unused")
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurity(
      UserDetailServiceImpl userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailService = userDetailService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, SIGN_UP_URL, CUSTOMER_SIGN_UP_URL)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilter(this.getJWTAuthenticationFilter()) // Add JWT Authentication Filter
        .addFilter(
            new JWTAuthorizationFilter(authenticationManager())) // Add JWT Authorization Filter
        .sessionManagement()
        .sessionCreationPolicy(
            SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
//    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration(
//        "/**",
//        new CorsConfiguration()
//            .applyPermitDefaultValues()); 
//
//    return source;
	  
//	  ----------------------------------------------------------------------------
	  
	  CorsConfiguration configuration = new CorsConfiguration();
	  
	  configuration.setAllowCredentials(true);
	  configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); 
	  configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	  configuration.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization"));
	  configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));

	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", configuration);
	  
	  return source;
  }

  public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception {
    final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
    filter.setFilterProcessesUrl("/api/auth/login"); // override the default spring login url
    return filter;
  }
  
}
