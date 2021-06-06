package com.example.hotelmanagenetsystem.config;

import com.example.hotelmanagenetsystem.exception.RoomNotFoundException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Configuration
@ControllerAdvice
public class Webconfig implements WebMvcConfigurer {


    @ExceptionHandler({IllegalArgumentException.class, EntityNotFoundException.class, RoomNotFoundException.class,UsernameNotFoundException.class})
    public ModelAndView handleException(HttpServletRequest request,Exception ex){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",ex);
        modelAndView.addObject("message",ex.getMessage());
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.setViewName("admin/roomexistederror");
        return modelAndView;


    }
    @Bean
    public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasename("i18n/messages");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
      LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
      bean.setValidationMessageSource(messageSource());
      return bean;
    }
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/home");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
  
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        exposeDirectory("hotel-photos", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("./hotel-photos");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        //if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/hotel-photos/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
    
    
}
