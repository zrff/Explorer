package com.github.zrff.backend;

import com.github.zrff.backend.auth.interceptor.GlobalInterceptor;
import com.github.zrff.backend.auth.interceptor.GlobalSessionInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.github.zrff.backend")
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/assets","/dist","/png");
    }

    String[] excludePath = {
            "/login","/assets/**","/png/**","/dist/**",
            "/dingtalk/**","/leetcode/index",
            "/pub/**"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new GlobalSessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login");
    }
}
