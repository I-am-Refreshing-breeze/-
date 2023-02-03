package com.situ.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.situ.hotel.interceptor.AdminInterceptor;
import com.situ.hotel.interceptor.UserInterceptor;

/**
 * 这是一个配置类
 * @author Zhi Jiu
 *
 */
@Configuration		//SpringBoot 扫描这个类，作为配置信息
public class WebConfig implements WebMvcConfigurer {

	@Value("${upload.path}")
	private String path;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Autowired
	private UserInterceptor userInterceptor;
	
	// 静态资源映射
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//添加静态资源的处理器,将url映射到硬盘上
		registry.addResourceHandler("/upload/**")	//upload/*/*
			.addResourceLocations("file:" + path);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//管理员的拦截器
		registry.addInterceptor(adminInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/admin/login.html", "/api/admin/**", "/layui/**",
				"/user/login.html", "/api/user/login",
				"/user/reg.html", "/api/user/reg",
				"/user/index.html", 
				"/user/order.html","/user/myorder.html","/user/center.html",
				"/api/user/logout","/api/user/loginUser",
				"/api/room/user/**","/api/order/user/**",
				"/error"
				);
		// 如果还有需要放行的路径，在此处放行
		
		// 用户拦截器,只拦截与用户相关的操作
		registry.addInterceptor(userInterceptor)
			.addPathPatterns(
					"/user/index.html", 
					"/user/order.html","/user/myorder.html","/user/center.html",
					"/api/user/logout","/api/user/loginUser",
					"/api/room/user/**","/api/order/user/**");
	}
	
}
