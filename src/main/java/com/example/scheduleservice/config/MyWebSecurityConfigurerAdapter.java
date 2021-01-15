package com.example.scheduleservice.config;

import com.example.scheduleservice.filter.CustomFilterInvocationSecurityMetadataSource;
import com.example.scheduleservice.filter.JwtAuthorizationTokenFilter;
import com.example.scheduleservice.handle.*;
import com.example.scheduleservice.service.AnthenticateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyWebSecurityConfigurerAdapter
 * @Description spring security配置类
 * @Author JohnTang
 * @LastChangeDate 2020/2/1
 * @Version v1.0
 */

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    // todo 后台监控和swagger与前台提供给用户的接口抽离出来设置ip白名单，127.0.0.1之内的都能访问

    @Resource
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Resource
    AnthenticateService anthenticateService;

    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    @Resource
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Resource
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Resource
    CustomAccessDecisionManager customAccessDecisionManager;

    @Resource
    GlobalSecurityConfig globalSecurityConfig;

//  启用密码加密
//	@Bean
//    PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
//	}



    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        return new DelegatingPasswordEncoder("noop", encoders);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anthenticateService).passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
    }


    //注意数据库中的权限表中的权限名称必须是ROLE_****的形式，不然无法自动匹配 !!!!
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(customAccessDecisionManager);
                return o;
            }
        })
//                .antMatchers("/v2/**",
//                          "/swagger-resources/**",
//                          "/swagger-resources",
//                          "/swagger-ui**","/webjars/**")
                .antMatchers(globalSecurityConfig.getAllowUrlList().toArray(new String[globalSecurityConfig.getAllowUrlList().size()]))
                .permitAll()
//                .antMatchers("/user/**")
//                .hasRole("User")
//                .antMatchers("/merchant/**")
//                .hasRole("Merchant")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(myAuthenticationAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler).permitAll();
    }
}
