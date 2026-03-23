package com.HiloNacional;

import com.HiloNacional.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());

        registrationBean.addUrlPatterns("/HiloNacional/usuarios/*");
        registrationBean.addUrlPatterns("/HiloNacional/pedidos/*");       
        registrationBean.addUrlPatterns("/HiloNacional/vendedor/*");    
        registrationBean.addUrlPatterns("/HiloNacional/comprador/*");     
        registrationBean.addUrlPatterns("/HiloNacional/detalle-pedido/*");
       
        return registrationBean;
    }

}


