package com.devbu03.base.config;

import com.devbu03.base.common.enums.RoleEnum;
import com.devbu03.base.exception.CustomAuthenticationEntryPoint;
import com.devbu03.base.exception.CustomeAccessDeniedHandler;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;
  private final JwtLogoutHandler jwtLogoutHandler;
  private final MessageSource messageSource;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    final String ROLE_STAFF = RoleEnum.ROLE_STAFF.getName();
    final String ROLE_OWNER = RoleEnum.ROLE_OWNER.getName();

    final String URL_ACCESS_CART_STAFF = "/api/v1/cart/staff/**";
    final String URL_ACCESS_CART_OWNER = "/api/v1/cart/owner/**";
    final String URL_ACCESS_CART_DETAIL_STAFF = "/api/v1/cart-detail/staff/**";
    final String URL_ACCESS_CART_DETAIL_OWNER = "/api/v1/cart-detail/owner/**";
    final String URL_ACCESS_CATEGORY_STAFF = "/api/v1/category/staff/**";
    final String URL_ACCESS_CATEGORY_OWNER = "/api/v1/category/owner/**";
    final String URL_ACCESS_ORDER_STAFF = "/api/v1/order/staff/**";
    final String URL_ACCESS_ORDER_OWNER = "/api/v1/order/owner/**";
    final String URL_ACCESS_ORDER_DETAIL_STAFF = "/api/v1/order-detail/staff/**";
    final String URL_ACCESS_ORDER_DETAIL_OWNER = "/api/v1/order-detail/owner/**";
    final String URL_ACCESS_PRODUCT_STAFF = "/api/v1/product/staff/**";
    final String URL_ACCESS_PRODUCT_OWNER = "/api/v1/product/owner/**";
    final String URL_ACCESS_ROLE_STAFF = "/api/v1/role/staff/**";
    final String URL_ACCESS_ROLE_OWNER = "/api/v1/role/owner/**";
    final String URL_ACCESS_USER_STAFF = "/api/v1/user/staff/**";
    final String URL_ACCESS_USER_OWNER = "/api/v1/user/owner/**";
    final String URL_ACCESS_USER_PRODUCT_STAFF = "/api/v1/user-product/staff/**";
    final String URL_ACCESS_USER_PRODUCT_OWNER = "/api/v1/user-product/owner/**";
    final String URL_ACCESS_USER_ROLE_STAFF = "/api/v1/user-role/staff/**";
    final String URL_ACCESS_USER_ROLE_OWNER = "/api/v1/user-role/owner/**";

    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity
        .cors()
        .and()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .requestMatchers("/api/v1/auth/**", "/api/v1/product/").permitAll()
        .requestMatchers(URL_ACCESS_ORDER_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_ORDER_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_PRODUCT_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_PRODUCT_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_CART_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_CART_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_CART_DETAIL_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_CART_DETAIL_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_CATEGORY_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_CATEGORY_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_ORDER_DETAIL_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_ORDER_DETAIL_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_ROLE_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_ROLE_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_USER_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_USER_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_USER_PRODUCT_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_USER_PRODUCT_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .requestMatchers(URL_ACCESS_USER_ROLE_STAFF)
        .hasAnyAuthority(ROLE_OWNER, ROLE_STAFF)
        .requestMatchers(URL_ACCESS_USER_ROLE_OWNER)
        .hasAnyAuthority(ROLE_OWNER)
        .anyRequest().authenticated()
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(jwtLogoutHandler)
        .logoutSuccessHandler(
            (new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
        .and().exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint())
        .accessDeniedHandler(myAccessDeniedHandler())
        .and()
        .csrf().disable();
    return httpSecurity.build();
  }

  private AccessDeniedHandler myAccessDeniedHandler() {
    return new CustomeAccessDeniedHandler(messageSource);
  }

  private AuthenticationEntryPoint myAuthenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint(messageSource);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
    config.addAllowedMethod(HttpMethod.PUT);
    config.addAllowedMethod(HttpMethod.DELETE);
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}