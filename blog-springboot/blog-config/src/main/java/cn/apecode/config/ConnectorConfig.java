package cn.apecode.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author apecode
 * @description 配置https
 * @date 4/4/2023 AM10:54
 */
@Configuration
@ConditionalOnProperty(prefix = "https", name = "enabled")
public class ConnectorConfig {
    @Value("${server.port}")
    private int serverPortHttps;
    @Value("${http.port}")
    private int serverPortHttp;

    /**
     * @param
     * @return {@link ServletWebServerFactory}
     * @description: http转发https
     * @auther apecode
     * @date 4/4/2023 AM10:04
     */
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(redirectConnector());
        return factory;
    }

    /**
     * @description: 重定向到HTTPS
     * 但不能同时在application.yml中同时配置两个connector
     * @return {@link Connector}
     * @auther apecode
     * @date 4/4/2023 AM11:16
    */
    private Connector redirectConnector() {
        Connector connector = new Connector(Http11NioProtocol.class.getName());
        connector.setScheme("http");
        connector.setPort(serverPortHttp);   // http端口
        connector.setSecure(false);
        connector.setRedirectPort(serverPortHttps);  // 重定向https端口
        return connector;
    }
}
