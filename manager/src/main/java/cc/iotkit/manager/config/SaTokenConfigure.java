package cc.iotkit.manager.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
            log.info("resource role check,path:{}", req.getRequestPath());
            SaRouter
                    //管理员、系统、客户端用户角色能使用的功能
                    .match("/space/addSpace/**",
                            "/space/saveSpace/**",
                            "/space/delSpace/**",
                            "/space/saveHome/**",
                            "/space/currentHome/**",
                            "/space/myRecentDevices/**",
                            "/space/spaces/**",
                            "/space/myDevices/**",
                            "/space/findDevice/**",
                            "/space/addDevice/**",
                            "/space/saveDevice",
                            "/space/removeDevice",
                            "/space/device/*",
                            "/device/*/consumer/*",
                            "/device/*/service/property/set",
                            "/device/*/service/*/invoke"
                    )
                    .check(c -> StpUtil.checkRoleOr("iot_admin", "iot_system", "iot_client"));

            SaRouter
                    //需要有可写权限的功能
                    .match(
                            "/**/save*/**",
                            "/**/remove*/**",
                            "/**/del*/**",
                            "/**/add*/**",
                            "/**/clear*/**",
                            "/**/set*/**",
                            "/**/set",
                            "/**/invoke"
                    ).check(c -> StpUtil.checkPermission("write"));

            SaRouter
                    //管理员、系统用户角色能使用的功能
                    .match("/**")
                    .check(c -> StpUtil.checkRoleOr("iot_admin", "iot_system", "iot_client"))

            ;
        })).addPathPatterns("/**")
                .excludePathPatterns(
                        "/*.png",
                        "/oauth2/**", "/*.html",
                        "/favicon.ico", "/v2/api-docs",
                        "/webjars/**", "/swagger-resources/**",
                        "/*.js");
    }

}