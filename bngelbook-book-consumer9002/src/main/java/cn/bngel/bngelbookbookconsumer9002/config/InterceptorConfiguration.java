package cn.bngel.bngelbookbookconsumer9002.config;

import cn.bngel.bngelbookcommonapi.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Value("${bngelbook-redis-config.password}")
    private String redisPassword;

    @Value("${bngelbook-redis-config.host}")
    private String redisHost;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(redisHost, redisPassword))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // knife4j 相关资源
                        "/swagger-ui.html/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/api-docs/**",
                        "/api/**",
                        "/service-worker.js/**",
                        "/v2/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").
                addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").
                addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
