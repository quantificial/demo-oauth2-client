package demo.oauth2.client;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
    public void customize(ConfigurableServletWebServerFactory factory) {
        //factory.setPort(8080);
        //factory.setContextPath("");
    	
    	//factory.set
     }
	
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return (container -> {
//            container.setSessionTimeout(1000);  // session timeout value
//        });
//    }

}
