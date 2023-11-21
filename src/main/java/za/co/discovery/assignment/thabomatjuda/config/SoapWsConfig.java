package za.co.discovery.assignment.thabomatjuda.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import za.co.discovery.assignment.thabomatjuda.constants.SpecialCharacters;

/**
 * Spring Configuration for SOAP Web Service.
 * We extend the WsConfigurerAdapter base class, which configures the annotation-driven Spring-WS programming model.
 * @author : Thabo Matjuda
 */

// Enabling SOAP Web Service features in this Spring Boot application using this Configuration
@EnableWs
@Configuration
public class SoapWsConfig extends WsConfigurerAdapter {

    public static final String NAMESPACE_URI = "http://gen.soap.thabomatjuda.assignment.discovery.co.za";
    public static final String SOAP_WS_BASE_PATH = SpecialCharacters.FOWARD_SLASH + "soap";

    @Value("${spp.app.conf.file.soap-schema}")
    private Resource schemaFileLocation;


    /**
     * Let’s create a MessageDispatcherServlet, which is used for handling SOAP requests:
     * @param applicationContext : We inject this ApplicationContext so that Spring-WS can find other Spring beans.
     * @return
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, SOAP_WS_BASE_PATH + SpecialCharacters.FOWARD_SLASH + SpecialCharacters.ASTERIX);
    }


    /**
     * We’ll also enable the WSDL location servlet transformation.
     * This transforms the location attribute of soap:address in the WSDL so that it reflects the URL of the incoming request.
     * This also exposes a standard WSDL 1.1 using an XsdSchema. The WSDL name will be the same as the bean name:
     * @return
     */
    @Bean(name = "calculation")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ShortestPathPort");
        wsdl11Definition.setLocationUri(SOAP_WS_BASE_PATH);
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        wsdl11Definition.setSchema(shortestPathSchema());
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema shortestPathSchema() {
        return new SimpleXsdSchema(schemaFileLocation);
    }


}
