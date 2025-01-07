package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan("com.tpe")//param vermezsek hangi packagenini icindeyse hepsini tarar.
@EnableWebMvc//MVC 'yi etkinleştirir.
public class WebMvcConfig implements WebMvcConfigurer {

    //view resolver:controllerdan sadece sayfanın ismi : students
    @Bean
    public InternalResourceViewResolver resolver(){

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");//view dosyası nerede
        resolver.setSuffix(".jsp");//dosyanın uzantısını nedir: /WEB-INF/views/students.jsp
        resolver.setViewClass(JstlView.class);
        //JSTL:JavaStandartTagLibrary:JSP içinde daha az Java kodu yazmamızı sağlar
        return resolver;
    }

    //handlermapping:statik sayfa içeren isteklerin servleta gönderilmesine gerek yok
    //static sayfa gelirse nolur yani kullanıcıya göre değişmeyen satibt şeyler gelirsestatic kaynaklar doğrudan karşılanabilir sörvıta gitmesine gerek yok
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")//bu url ile gelen istekleri statik olarak sun
                .addResourceLocations("/resources/")//statik olan kaynakların yeri
                .setCachePeriod(0);//cache periyodu için süre verilebilir.
    }





}
