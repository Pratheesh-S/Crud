//package com.diatoz.task1.config;
//
//import com.diatoz.task1.intercepterpack.InputDataValidateInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//
//public class InterceptorConfiguration  implements WebMvcConfigurer {
//
//    @Autowired
//    InputDataValidateInterceptor inputDataValidateInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(inputDataValidateInterceptor).addPathPatterns("/student/saveStudent");
//    }
//}
