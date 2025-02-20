package ch.cyberlogic.camel.examples.configuration;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This configuration is needed as a workaround for <a href="https://issues.apache.org/jira/browse/CXF-9097">this bug</a>.
 * Even though the issue specifies that the problem seems to be related to Tomcat 9, and we have Tomcat 10, the problem is still present.
 * The bug is present in cxf 4.0.6 that we're getting from camel 4.8.3.
 * The bug is fixed in cxf version 4.0.7.
 */
@Configuration
public class TomcatConfiguration {
    @Bean
    TomcatConnectorCustomizer disabledFacadeDiscard() {
        return binding -> binding.setDiscardFacades(false);
    }
}