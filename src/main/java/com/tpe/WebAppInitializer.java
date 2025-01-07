package com.tpe;
//Java tabanlı Web uygulamaları web.xml dosyası ile config edilir
//bu classı web.xml yerine kullanacağız.


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//AbstractAnnotationConfig... :DispatcherServlet konfigurasyonu ve başlatılması için gerekli adımları gösterir

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {//dataya erişim : Hibernode,JDBC
        // icin gerekli configrasyon yapmamız gerekir
        ///root dedimiz zaman data gelecek aklımza gelen
        return new Class[]{
                RootConfig.class//databaseconfig veya hibernoteconfig de diyebilirim.
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//viewresolver, handlermapping
       ///görsel düzenlemeler yapılacak
        return new Class[]{
                WebMvcConfig.class//oto işlem yapacak işlemler.
        };
    }

    @Override
    protected String[] getServletMappings() {//hangi url ile gelen istekler servlet tarafından karşılanacak ayarlaması
        ///tüm istek leri karşılayacak hangi istekleri karşılıcamızı pachleri vericez
        return new String[]{
                "/"/*,"/student" gibi belirtebilirdik.*/
        };
    }
}
